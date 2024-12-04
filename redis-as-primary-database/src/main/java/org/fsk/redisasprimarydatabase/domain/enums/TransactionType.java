package org.fsk.redisasprimarydatabase.domain.enums;

public enum TransactionType {

    DEPOSIT("DEPOSIT"),
    WITHDRAWAL("WITHDRAWAL"),
    PAYMENT("PAYMENT"),
    TRANSFER("TRANSFER");

    private String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
