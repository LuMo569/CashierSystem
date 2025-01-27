package com.example.cashiersystem.Views.WaiterEnums;

public enum Years {
    YEAR24(24),
    YEAR25(25);

    private final int value;

    Years(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
