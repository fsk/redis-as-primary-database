package org.fsk.redisasprimarydatabase.service;

import java.time.LocalDateTime;

import org.fsk.redisasprimarydatabase.domain.Account;
import org.fsk.redisasprimarydatabase.domain.Transaction;
import org.fsk.redisasprimarydatabase.domain.enums.TransactionStatus;
import org.fsk.redisasprimarydatabase.dto.request.transactionrequest.TransactionRequest;
import org.fsk.redisasprimarydatabase.dto.response.transactionresponse.AccountTransactionResponse;
import org.fsk.redisasprimarydatabase.dto.response.transactionresponse.TransactionResponse;
import org.fsk.redisasprimarydatabase.repository.AccountRepository;
import org.fsk.redisasprimarydatabase.repository.TransactionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionService {
    
    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;

    public TransactionService(TransactionRepository transactionRepository, 
                            AccountRepository accountRepository) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Transaction createTransaction(TransactionRequest request) {
        Account fromAccount = accountRepository.findById(request.fromAccountId().accountId())
            .orElseThrow(() -> new RuntimeException("From account not found"));
        Account toAccount = accountRepository.findById(request.toAccountId().accountId())
            .orElseThrow(() -> new RuntimeException("To account not found"));

        
        if (fromAccount.getAccountBalance().compareTo(request.amount()) < 0) {
            throw new RuntimeException("Insufficient balance");
        }

        Transaction transaction = Transaction.builder()
            .fromAccount(fromAccount)
            .toAccount(toAccount)
            .transactionType(request.transactionType())
            .transactionStatus(TransactionStatus.PENDING)
            .transactionAmount(request.amount())
            .transactionDate(LocalDateTime.now())
            .description(request.description())
            .build();

        try {
            
            fromAccount.setAccountBalance(fromAccount.getAccountBalance().subtract(request.amount()));
            toAccount.setAccountBalance(toAccount.getAccountBalance().add(request.amount()));
            
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);

            transaction.setTransactionStatus(TransactionStatus.COMPLETED);
            
        } catch (Exception e) {
            transaction.setTransactionStatus(TransactionStatus.FAILED);
            log.error("Transaction failed", e);
            throw new RuntimeException("Transaction failed", e);
        }

        return transactionRepository.save(transaction);
    }

    private TransactionResponse convertToTransactionResponse(Transaction transaction) {
        AccountTransactionResponse fromAccountResponse = AccountTransactionResponse.builder()
            .accountId(transaction.getFromAccount().getAccountId())
            .accountNumber(transaction.getFromAccount().getAccountNumber())
            .accountBalance(transaction.getFromAccount().getAccountBalance())
            .build();

        AccountTransactionResponse toAccountResponse = AccountTransactionResponse.builder()
            .accountId(transaction.getToAccount().getAccountId())
            .accountNumber(transaction.getToAccount().getAccountNumber())
            .accountBalance(transaction.getToAccount().getAccountBalance())
            .build();

        return TransactionResponse.builder()
            .transactionId(transaction.getTransactionId())
            .fromAccount(fromAccountResponse)
            .toAccount(toAccountResponse)
            .transactionType(transaction.getTransactionType())
            .transactionStatus(transaction.getTransactionStatus())
            .transactionAmount(transaction.getTransactionAmount())
            .transactionDate(transaction.getTransactionDate())
            .description(transaction.getDescription())
            .build();
    }

}
