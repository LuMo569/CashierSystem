package com.example.cashiersystem.Model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Admin {
    private final StringProperty username;
    private final StringProperty password;
    private final IntegerProperty AdminId;

    Admin(String username, String password, int loggedInWaiterId) {
        this.username = new SimpleStringProperty(this, "Username", username);
        this.password = new SimpleStringProperty(this, "Password", password);
        this.AdminId = new SimpleIntegerProperty(this, "AdminId", -1);
    }

    // getters
    public StringProperty nameProperty() {
        return username;
    }

    public StringProperty passwordProperty() {
        return password;
    }

    public IntegerProperty AdminIdProperty() {
        return AdminId;
    }

}