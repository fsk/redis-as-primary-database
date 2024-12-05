package org.fsk.redisasprimarydatabase.dto.request.accountrequests;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

import org.fsk.redisasprimarydatabase.domain.enums.AccountType;
import org.fsk.redisasprimarydatabase.dto.request.customerrequest.CustomerInformation;

@Schema(description = "Hesap oluşturma isteği")
public record CreateAccountRequest(

    @Schema(description = "Müşteri ID bilgisi")
    CustomerInformation customerInformation,
    
    @Schema(description = "Hesap tipi", example = "CHECKING")
    AccountType accountType,
    
    @Schema(description = "Hesap bakiyesi", example = "1000.00")
    BigDecimal accountBalance

) {}
