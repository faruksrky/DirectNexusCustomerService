package com.example.DirectNexus.enums;

public enum ServiceCompletionStatus {
    Talebiniz_Alındı(0),
    Ürün_Teslim_Alındı(1),
    işlem_Tamamlandı( 2),
    Kargoya_Verildi(3);


    private final int priority;

    ServiceCompletionStatus(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
