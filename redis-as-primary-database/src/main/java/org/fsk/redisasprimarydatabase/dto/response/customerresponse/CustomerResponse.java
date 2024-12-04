package org.fsk.redisasprimarydatabase.dto.response.customerresponse;

import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record CustomerResponse(
    String customerId,
    String identityNumber,
    String customerName,
    String customerSurname,
    String customerEmail,
    String customerPhone,
    LocalDateTime createdAt,
    Boolean isActive
) {

}
