package org.fsk.redisasprimarydatabase.dto.response.customerresponse;

import java.time.LocalDateTime;

public record DeleteCustomerResponse(
    String customerId,
    String message
) {}
