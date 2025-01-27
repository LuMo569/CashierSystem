package com.example.cashiersystem.Model;

import javafx.beans.property.*;

public class Item {
    private final IntegerProperty id;
    private final StringProperty name;
    private final DoubleProperty currentPrice;
    private final ObjectProperty<Double> newPrice = new SimpleObjectProperty<>(this, "newPrice", null);

    public Item(Integer id, String name, Double currentPrice) {
        this.id = new SimpleIntegerProperty(this, "id", id);
        this.name = new SimpleStringProperty(this, "name", name);
        this.currentPrice = new SimpleDoubleProperty(this, "currentPrice", currentPrice);
    }

    public IntegerProperty idProperty() {
        return this.id;
    }
    public StringProperty nameProperty() {
        return this.name;
    }
    public DoubleProperty currentPriceProperty() {
        return this.currentPrice;
    }

    public ObjectProperty<Double> newPriceProperty() {
        return this.newPrice;
    }
}
