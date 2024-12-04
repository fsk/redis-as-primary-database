package org.fsk.redisasprimarydatabase.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.fsk.redisasprimarydatabase.domain.enums.AccountStatus;
import org.fsk.redisasprimarydatabase.domain.enums.AccountType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Data;

@Data
@RedisHash("account")
@Builder
public class Account implements Serializable {

    @Id
    private String accountId;
    private String accountNumber;
    @Reference
    private Customer customer;
    private AccountType accountType;
    private BigDecimal accountBalance;
    private AccountStatus accountStatus;
    private LocalDateTime createdAt;

}
