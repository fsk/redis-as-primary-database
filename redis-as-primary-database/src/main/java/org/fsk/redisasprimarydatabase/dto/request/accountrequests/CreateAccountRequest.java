package org.fsk.redisasprimarydatabase.dto.request.accountrequests;

import java.math.BigDecimal;

import org.fsk.redisasprimarydatabase.domain.enums.AccountType;
import org.fsk.redisasprimarydatabase.dto.request.customerrequest.CustomerId;

public record CreateAccountRequest(
    String accountId,
    String accountNumber,
    CustomerId customerId,
    AccountType accountType,
    BigDecimal accountBalance
) {}
