package org.fsk.redisasprimarydatabase.dto.request.transactionrequest;

import java.math.BigDecimal;

import org.fsk.redisasprimarydatabase.domain.enums.TransactionType;

public record TransactionRequest(
    AccountId fromAccountId,
    AccountId toAccountId,
    TransactionType transactionType,
    BigDecimal amount,
    String description
) {

}
