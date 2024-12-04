package org.fsk.redisasprimarydatabase.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.fsk.redisasprimarydatabase.domain.Account;
import org.fsk.redisasprimarydatabase.domain.Customer;
import org.fsk.redisasprimarydatabase.domain.enums.AccountStatus;
import org.fsk.redisasprimarydatabase.dto.request.accountrequests.CreateAccountRequest;
import org.fsk.redisasprimarydatabase.dto.request.accountrequests.DeleteAccountRequest;
import org.fsk.redisasprimarydatabase.dto.request.accountrequests.GetAccountRequest;
import org.fsk.redisasprimarydatabase.dto.request.accountrequests.UpdateAccountRequest;
import org.fsk.redisasprimarydatabase.dto.response.accountresponse.AccountResponse;
import org.fsk.redisasprimarydatabase.dto.response.accountresponse.DeleteAccountResponse;
import org.fsk.redisasprimarydatabase.dto.response.accountresponse.UpdateAccountResponse;
import org.fsk.redisasprimarydatabase.dto.response.customerresponse.CustomerResponse;
import org.fsk.redisasprimarydatabase.repository.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountService {
    
    private final AccountRepository accountRepository;
    private final CustomerService customerService;

    public AccountService(AccountRepository accountRepository, CustomerService customerService) {
        this.accountRepository = accountRepository;
        this.customerService = customerService;
    }

    @Transactional
    public AccountResponse createAccount(CreateAccountRequest createAccountRequest) {
        Customer customer = customerService.getCustomerById(createAccountRequest.customerId().toString());
        
        Account newAccount = Account.builder()
            .accountId(UUID.randomUUID().toString())
            .accountNumber(createAccountRequest.accountNumber())
            .customer(customer)
            .accountType(createAccountRequest.accountType())
            .accountBalance(createAccountRequest.accountBalance())
            .accountStatus(AccountStatus.ACTIVE)
            .createdAt(LocalDateTime.now())
            .build();

        Account savedAccount = accountRepository.save(newAccount);
        log.info("Account created successfully: {}", savedAccount);
        
        return AccountResponse.builder()
            .accountId(savedAccount.getAccountId())
            .accountNumber(savedAccount.getAccountNumber())
            .customer(buildCustomerResponse(savedAccount.getCustomer()))
            .accountType(savedAccount.getAccountType())
            .accountBalance(savedAccount.getAccountBalance())
            .accountStatus(savedAccount.getAccountStatus())
            .createdAt(savedAccount.getCreatedAt())
            .build();
    }

    @Transactional
    public UpdateAccountResponse updateAccount(UpdateAccountRequest request) {
        Account existingAccount = accountRepository.findById(request.accountId())
            .orElseThrow(() -> {
                log.error("Account not found. AccountId: {}", request.accountId());
                return new RuntimeException("Account not found");
            });
        
        existingAccount.setAccountType(request.accountType());
        existingAccount.setAccountBalance(request.accountBalance());
        existingAccount.setAccountStatus(request.accountStatus());
        
        Account savedAccount = accountRepository.save(existingAccount);
        log.info("Account updated successfully: {}", savedAccount);
        
        return new UpdateAccountResponse(
            savedAccount.getAccountId(),
            savedAccount.getAccountNumber(),
            buildCustomerResponse(savedAccount.getCustomer()),
            savedAccount.getAccountType(),
            savedAccount.getAccountBalance(),
            savedAccount.getAccountStatus(),
            savedAccount.getCreatedAt()
        );
    }

    @Transactional
    public DeleteAccountResponse deleteAccount(DeleteAccountRequest request) {
        Account existingAccount = accountRepository.findById(request.accountId())
            .orElseThrow(() -> {
                log.error("Account not found. AccountId: {}", request.accountId());
                return new RuntimeException("Account not found");
            });
            
        existingAccount.setAccountStatus(AccountStatus.CLOSED);
        Account savedAccount = accountRepository.save(existingAccount);
        
        log.info("Account deleted successfully. AccountId: {}", request.accountId());
        return new DeleteAccountResponse(
            savedAccount.getAccountId(),
            savedAccount.getAccountStatus(),
            "Account successfully deleted"
        );
    }

    public AccountResponse getAccount(GetAccountRequest request) {
        Account account = accountRepository.findById(request.accountId())
            .orElseThrow(() -> {
                log.error("Account not found. AccountId: {}", request.accountId());
                return new RuntimeException("Account not found");
            });
            
        log.info("Account retrieved successfully: {}", account);
        return AccountResponse.builder()
            .accountId(account.getAccountId())
            .accountNumber(account.getAccountNumber())
            .customer(buildCustomerResponse(account.getCustomer()))
            .accountType(account.getAccountType())
            .accountBalance(account.getAccountBalance())
            .accountStatus(account.getAccountStatus())
            .createdAt(account.getCreatedAt())
            .build();
    }

    private CustomerResponse buildCustomerResponse(Customer customer) {
        return CustomerResponse.builder()
            .customerId(customer.getCustomerId())
            .customerName(customer.getCustomerName())
            .customerSurname(customer.getCustomerSurname())
            .customerEmail(customer.getCustomerEmail())
            .customerPhone(customer.getCustomerPhone())
            .identityNumber(customer.getIdentityNumber())
            .isActive(customer.getIsActive())
            .createdAt(customer.getCreatedAt())
            .build();
    }
}
