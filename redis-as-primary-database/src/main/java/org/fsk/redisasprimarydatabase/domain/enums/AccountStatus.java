package org.fsk.redisasprimarydatabase.domain.enums;

public enum AccountStatus {

    ACTIVE("ACTIVE"),
    BLOCKED("BLOCKED"),
    CLOSED("CLOSED");

    private String description;

    AccountStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
