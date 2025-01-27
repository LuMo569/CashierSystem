package com.example.cashiersystem.Model;

import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Order {
    private final IntegerProperty orderId;
    private final IntegerProperty waiterId;
    private final IntegerProperty tableId;
    private final ObjectProperty<LocalDate> date;
    private final Map<Integer, Integer> itemMap;

    Order(int orderId, int waiterId, int tableId, LocalDate date, ListProperty<Integer> ItemIds,Map<Integer, Integer> itemMap) {
        this.orderId = new SimpleIntegerProperty(this, "OrderId", orderId);
        this.waiterId = new SimpleIntegerProperty(this, "WaiterId", waiterId);
        this.tableId = new SimpleIntegerProperty(this, "TableId", tableId);
        this.date = new SimpleObjectProperty<>(this, "Date", date);
        this.itemMap = new HashMap<>();
    }

    // getters
    public IntegerProperty orderIdProperty() {
        return orderId;
    }

    public IntegerProperty waiterIdProperty() {
        return waiterId;
    }

    public IntegerProperty tableIdProperty() {
        return tableId;
    }

    public ObjectProperty<LocalDate> dateProperty() {
        return date;
    }


    // order items methods
    public void addItemId(int itemId) {
        if (itemMap.containsKey(itemId)) {
            int currentCount = itemMap.get(itemId);
            itemMap.put(itemId, currentCount + 1);
        } else {
            itemMap.put(itemId, 1);
        }
    }

    public void clearItems() {
        this.itemMap.clear();
    }

    public Map<Integer, Integer> getItemMap() {
        return itemMap;
    }

    public int getQuantityForItemId(int itemId) {
        // Abrufen der Artikelmenge aus der Map
        return getItemMap().getOrDefault(itemId, 0);
    }
}
