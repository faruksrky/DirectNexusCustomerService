package com.example.DirectNexus.enums;

public enum WarrantyStatus {
    Garantili(0),
    Garanti_Dışı( 1),
    Bilinmiyor( 2);

    private final int priority;

    WarrantyStatus(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
