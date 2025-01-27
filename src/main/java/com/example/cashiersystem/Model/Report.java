package com.example.cashiersystem.Model;

import javafx.beans.property.*;

public class Report {
    private final StringProperty date;
    private final IntegerProperty totalOrders;
    private final IntegerProperty totalOrdersByWaiter;
    private final IntegerProperty totalItemsOrdered;
    private final DoubleProperty totalRevenue;
    private final DoubleProperty totalPurchaseCost;
    private final DoubleProperty totalProfit;

    Report(String date, IntegerProperty totalOrders, IntegerProperty totalOrdersByWaiter, IntegerProperty totalItemsOrdered, DoubleProperty totalPrice, DoubleProperty totalPurchaseCost, DoubleProperty totalProfit){
        this.date = new SimpleStringProperty(this, "Date", date);
        this.totalOrders = new SimpleIntegerProperty(this, "TotalOrders", 0);
        this.totalOrdersByWaiter = new SimpleIntegerProperty(this, "TotalOrdersByWaiter", 0);
        this.totalItemsOrdered = new SimpleIntegerProperty(this, "TotalItemsOrdered", 0);
        this.totalRevenue = new SimpleDoubleProperty(this, "TotalPrice", 0);
        this.totalPurchaseCost = new SimpleDoubleProperty(this, "TotalPurchaseCost", 0);
        this.totalProfit = new SimpleDoubleProperty(this, "TotalProfit");
        this.totalProfit.bind(this.totalRevenue.subtract(this.totalPurchaseCost));
    }

    // getters
    public StringProperty dateProperty() {
        return date;
    }
    public IntegerProperty totalOrdersProperty() {
        return totalOrders;
    }
    public IntegerProperty totalOrdersByWaiterProperty() {
        return totalOrdersByWaiter;
    }
    public IntegerProperty totalItemsOrderedProperty() {
        return totalItemsOrdered;
    }
    public DoubleProperty totalRevenueProperty() {
        return totalRevenue;
    }
    public DoubleProperty totalPurchaseCostProperty() {
        return totalPurchaseCost;
    }
    public DoubleProperty totalProfitProperty() {
        return totalProfit;
    }

}
