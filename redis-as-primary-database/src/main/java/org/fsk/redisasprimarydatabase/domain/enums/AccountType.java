package org.fsk.redisasprimarydatabase.domain.enums;

public enum AccountType {
    
    CHECKING("CHECKING"),
    SAVINGS("SAVINGS"),
    INVESTMENT("INVESTMENT"),
    CREDDIT("CREDIT");

    private String description;

    AccountType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
