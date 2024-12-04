package org.fsk.redisasprimarydatabase.dto.response.transactionresponse;

import org.fsk.redisasprimarydatabase.domain.enums.TransactionStatus;
import org.fsk.redisasprimarydatabase.domain.enums.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record TransactionResponse(
    String transactionId,
    AccountTransactionResponse fromAccount,
    AccountTransactionResponse toAccount,
    TransactionType transactionType,
    TransactionStatus transactionStatus,
    BigDecimal transactionAmount,
    LocalDateTime transactionDate,
    String description
) {
    public static TransactionResponseBuilder builder() {
        return new TransactionResponseBuilder();
    }

    public static class TransactionResponseBuilder {
        private String transactionId;
        private AccountTransactionResponse fromAccount;
        private AccountTransactionResponse toAccount;
        private TransactionType transactionType;
        private TransactionStatus transactionStatus;
        private BigDecimal transactionAmount;
        private LocalDateTime transactionDate;
        private String description;

        public TransactionResponseBuilder transactionId(String transactionId) {
            this.transactionId = transactionId;
            return this;
        }

        public TransactionResponseBuilder fromAccount(AccountTransactionResponse fromAccount) {
            this.fromAccount = fromAccount;
            return this;
        }

        public TransactionResponseBuilder toAccount(AccountTransactionResponse toAccount) {
            this.toAccount = toAccount;
            return this;
        }

        public TransactionResponseBuilder transactionType(TransactionType transactionType) {
            this.transactionType = transactionType;
            return this;
        }

        public TransactionResponseBuilder transactionStatus(TransactionStatus transactionStatus) {
            this.transactionStatus = transactionStatus;
            return this;
        }

        public TransactionResponseBuilder transactionAmount(BigDecimal transactionAmount) {
            this.transactionAmount = transactionAmount;
            return this;
        }

        public TransactionResponseBuilder transactionDate(LocalDateTime transactionDate) {
            this.transactionDate = transactionDate;
            return this;
        }

        public TransactionResponseBuilder description(String description) {
            this.description = description;
            return this;
        }

        public TransactionResponse build() {
            return new TransactionResponse(
                transactionId, fromAccount, toAccount, transactionType,
                transactionStatus, transactionAmount, transactionDate, description
            );
        }
    }
}