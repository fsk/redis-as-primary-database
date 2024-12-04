package org.fsk.redisasprimarydatabase.dto.request.customerrequest;

import io.swagger.v3.oas.annotations.media.Schema;

public record CreateCustomerRequest(
    @Schema(description = "Customer Identity Number", example = "12345678901")
    String identityNumber,

    @Schema(description = "Customer Name", example = "Furkan")
    String customerName,

    @Schema(description = "Customer Surname", example = "Kulaksiz")
    String customerSurname,

    @Schema(description = "Customer Email", example = "furkan.kulaksiz@email.com")
    String customerEmail,

    @Schema(description = "Customer Phone", example = "05551234567")
    String customerPhone
) {}
