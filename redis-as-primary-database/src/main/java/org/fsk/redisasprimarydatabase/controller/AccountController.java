package org.fsk.redisasprimarydatabase.controller;

import lombok.RequiredArgsConstructor;
import org.fsk.redisasprimarydatabase.dto.request.accountrequests.CreateAccountRequest;
import org.fsk.redisasprimarydatabase.dto.request.accountrequests.DeleteAccountRequest;
import org.fsk.redisasprimarydatabase.dto.request.accountrequests.GetAccountRequest;
import org.fsk.redisasprimarydatabase.dto.request.accountrequests.UpdateAccountRequest;
import org.fsk.redisasprimarydatabase.dto.response.accountresponse.AccountResponse;
import org.fsk.redisasprimarydatabase.dto.response.accountresponse.DeleteAccountResponse;
import org.fsk.redisasprimarydatabase.dto.response.accountresponse.UpdateAccountResponse;
import org.fsk.redisasprimarydatabase.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/createAccount")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<AccountResponse> createAccount(@RequestBody CreateAccountRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(accountService.createAccount(request));
    }

    @PutMapping("/updateAccount")
    public ResponseEntity<UpdateAccountResponse> updateAccount(@RequestBody UpdateAccountRequest request) {
        return ResponseEntity.ok(accountService.updateAccount(request));
    }

    @DeleteMapping("/deleteAccount")
    public ResponseEntity<DeleteAccountResponse> deleteAccount(@RequestBody DeleteAccountRequest request) {
        return ResponseEntity.ok(accountService.deleteAccount(request));
    }

    @GetMapping("/getAccount")
    public ResponseEntity<AccountResponse> getAccount(@RequestBody GetAccountRequest request) {
        return ResponseEntity.ok(accountService.getAccount(request));
    }
}