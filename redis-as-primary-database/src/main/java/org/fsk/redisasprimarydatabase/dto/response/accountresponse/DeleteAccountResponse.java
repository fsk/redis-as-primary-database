package org.fsk.redisasprimarydatabase.dto.response.accountresponse;

import org.fsk.redisasprimarydatabase.domain.enums.AccountStatus;

public record DeleteAccountResponse(
    String accountId, 
    AccountStatus accountStatus,
    String message
) {

}
