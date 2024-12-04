package org.fsk.redisasprimarydatabase.dto.response.transactionresponse;

import java.math.BigDecimal;

public record AccountTransactionResponse(
    String accountId,
    String accountNumber,
    BigDecimal accountBalance
) {
    public static AccountTransactionResponseBuilder builder() {
        return new AccountTransactionResponseBuilder();
    }

    public static class AccountTransactionResponseBuilder {
        private String accountId;
        private String accountNumber;
        private BigDecimal accountBalance;

        public AccountTransactionResponseBuilder accountId(String accountId) {
            this.accountId = accountId;
            return this;
        }

        public AccountTransactionResponseBuilder accountNumber(String accountNumber) {
            this.accountNumber = accountNumber;
            return this;
        }

        public AccountTransactionResponseBuilder accountBalance(BigDecimal accountBalance) {
            this.accountBalance = accountBalance;
            return this;
        }

        public AccountTransactionResponse build() {
            return new AccountTransactionResponse(accountId, accountNumber, accountBalance);
        }
    }
}