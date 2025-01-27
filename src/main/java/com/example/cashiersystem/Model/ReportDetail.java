package com.example.cashiersystem.Model;

import javafx.beans.property.*;

import java.util.List;

public class ReportDetail {
    private final StringProperty date;
    private final StringProperty waiterName;
    private final List<OrderItem> items;
    private final DoubleProperty revenue;
    private final DoubleProperty purchasePrice;
    private final DoubleProperty profit;

    public ReportDetail(String date, String waiterName, List<OrderItem> items, double revenue, double purchasePrice, double profit) {
        this.date = new SimpleStringProperty(date);
        this.waiterName = new SimpleStringProperty(waiterName);
        this.items = items;
        this.revenue = new SimpleDoubleProperty(revenue);
        this.purchasePrice = new SimpleDoubleProperty(purchasePrice);
        this.profit = new SimpleDoubleProperty(profit);
    }

    public StringProperty dateProperty() {
        return date;
    }

    public StringProperty waiterNameProperty() {
        return waiterName;
    }

    public DoubleProperty revenueProperty() {
        return revenue;
    }

    public DoubleProperty purchasePriceProperty() {
        return purchasePrice;
    }

    public DoubleProperty profitProperty() {
        return profit;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public String getFormattedItems() {
        StringBuilder formattedItems = new StringBuilder();
        for (OrderItem item : items) {
            formattedItems.append(item.nameProperty().get()).append(" x").append(item.quantityProperty().get()).append(", ");
        }
        return formattedItems.toString().replaceAll(", $", "");  // remove last comma
    }
}
