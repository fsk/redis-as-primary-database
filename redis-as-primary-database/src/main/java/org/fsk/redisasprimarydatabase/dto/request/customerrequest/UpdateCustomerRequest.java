package org.fsk.redisasprimarydatabase.dto.request.customerrequest;

public record UpdateCustomerRequest(
    String customerId,
    String customerName,
    String customerSurname,
    String customerEmail,
    String customerPhone
) {}