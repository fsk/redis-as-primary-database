package org.fsk.redisasprimarydatabase.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.fsk.redisasprimarydatabase.domain.enums.TransactionStatus;
import org.fsk.redisasprimarydatabase.domain.enums.TransactionType;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Reference;
import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Data;

@Data
@RedisHash("transaction")
@Builder
public class Transaction implements Serializable {

    @Id
    private String transactionId;
    @Reference
    private Account fromAccount;
    @Reference
    private Account toAccount;
    private TransactionType transactionType;
    private TransactionStatus transactionStatus;
    private BigDecimal transactionAmount;
    private LocalDateTime transactionDate;
    private String description;

}
