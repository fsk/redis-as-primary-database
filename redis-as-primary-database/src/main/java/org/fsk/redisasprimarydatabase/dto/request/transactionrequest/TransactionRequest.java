package org.fsk.redisasprimarydatabase.dto.request.transactionrequest;

import java.math.BigDecimal;

import org.fsk.redisasprimarydatabase.domain.enums.TransactionType;
import io.swagger.v3.oas.annotations.media.Schema;

public record TransactionRequest(
    @Schema(description = "Gönderen hesap ID'si", required = true)
    AccountId fromAccountId,

    @Schema(description = "Alıcı hesap ID'si", required = true)
    AccountId toAccountId,

    @Schema(description = "İşlem tipi", required = true, example = "TRANSFER")
    TransactionType transactionType,

    @Schema(description = "İşlem tutarı", required = true, example = "100.50")
    BigDecimal amount,

    @Schema(description = "İşlem açıklaması", required = true, example = "Kira ödemesi")
    String description
) {

}
