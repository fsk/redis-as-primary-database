package org.fsk.redisasprimarydatabase.domain.enums;

public enum TransactionStatus {

    SUCCESS("SUCCESS"),
    COMPLETED("COMPLETED"),
    FAILED("FAILED"),
    PENDING("PENDING"),           
    CANCELLED("CANCELLED"); 

    private String description;

    TransactionStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
