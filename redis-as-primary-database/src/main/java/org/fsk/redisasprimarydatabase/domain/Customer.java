package org.fsk.redisasprimarydatabase.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.Data;

@Data
@RedisHash("customer")
@Builder
public class Customer implements Serializable {

    @Id
    private String customerId;
    private String identityNumber;
    private String customerName;
    private String customerSurname;
    private String customerEmail;
    private String customerPhone;
    private Boolean isActive;
    private LocalDateTime createdAt;
    private List<Account> accounts;
}
