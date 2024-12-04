package org.fsk.redisasprimarydatabase.dto.response.accountresponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;
import org.fsk.redisasprimarydatabase.domain.enums.AccountStatus;
import org.fsk.redisasprimarydatabase.domain.enums.AccountType;
import org.fsk.redisasprimarydatabase.dto.response.customerresponse.CustomerResponse;

@Builder
public record AccountResponse(
    String accountId,
    String accountNumber,
    CustomerResponse customer,
    AccountType accountType,
    BigDecimal accountBalance,
    AccountStatus accountStatus,
    LocalDateTime createdAt
) {

}
