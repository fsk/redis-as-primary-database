package org.fsk.redisasprimarydatabase.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fsk.redisasprimarydatabase.domain.Transaction;
import org.fsk.redisasprimarydatabase.dto.request.transactionrequest.TransactionRequest;
import org.fsk.redisasprimarydatabase.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
@Slf4j
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/transfer")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Transaction> createTransaction(@RequestBody TransactionRequest request) {
        log.info("Transaction request received: {}", request);
        Transaction transaction = transactionService.createTransaction(request);
        log.info("Transaction completed successfully: {}", transaction);
        return ResponseEntity.status(HttpStatus.CREATED).body(transaction);
    }
}