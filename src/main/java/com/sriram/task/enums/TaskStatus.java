package com.sriram.task.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TaskStatus {
    TODO  ("Todo"),
    IN_PROGRESS ("In Progress"),
    DONE ("Done");

    @JsonValue
    private final String status;

    TaskStatus(String status) {
        this.status = status;
    }
}
