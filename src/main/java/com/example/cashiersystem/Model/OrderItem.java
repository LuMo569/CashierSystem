package com.example.cashiersystem.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class OrderItem {
    private final StringProperty name;
    private final IntegerProperty quantity;

    public OrderItem(String name, Integer quantity) {
        this.name = new SimpleStringProperty(this, "name", name);
        this.quantity = new SimpleIntegerProperty(this, "quantity", quantity);
    }

    public StringProperty nameProperty() {
        return this.name;
    }
    public IntegerProperty quantityProperty() {
        return this.quantity;
    }
}
