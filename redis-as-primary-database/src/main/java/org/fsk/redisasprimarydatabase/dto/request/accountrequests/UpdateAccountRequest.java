package org.fsk.redisasprimarydatabase.dto.request.accountrequests;

import java.math.BigDecimal;

import org.fsk.redisasprimarydatabase.domain.enums.AccountStatus;
import org.fsk.redisasprimarydatabase.domain.enums.AccountType;

public record UpdateAccountRequest(
    String accountId,
    AccountType accountType,
    BigDecimal accountBalance,
    AccountStatus accountStatus
) {}
