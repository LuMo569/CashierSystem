package com.example.cashiersystem.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Waiter {
    private final StringProperty username;
    private final StringProperty password;
    private final IntegerProperty loggedInWaiterId;

    Waiter(String username, String password, int loggedInWaiterId) {
        this.username = new SimpleStringProperty(this, "Username", username);
        this.password = new SimpleStringProperty(this, "Password", password);
        this.loggedInWaiterId = new SimpleIntegerProperty(this, "WaiterId", -1);
    }

    // getters
    public StringProperty nameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public IntegerProperty waiterIdProperty() {
        return loggedInWaiterId;
    }

}