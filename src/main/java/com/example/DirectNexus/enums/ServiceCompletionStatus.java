package com.example.DirectNexus.enums;

public enum ServiceCompletionStatus {
    Beklemede(0),
    Tamamlandı(1),
    Teslim_Edildi( 2);

    private final int priority;

    ServiceCompletionStatus(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
