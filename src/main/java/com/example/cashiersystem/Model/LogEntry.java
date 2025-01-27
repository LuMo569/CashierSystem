package com.example.cashiersystem.Model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDateTime;

public class LogEntry {
    private final ObjectProperty<LocalDateTime> logDate;
    private final StringProperty logText;

    public LogEntry(LocalDateTime logDate, String logText) {
        this.logDate = new SimpleObjectProperty<>(this, "logDate", logDate);
        this.logText = new SimpleStringProperty(this, "logText", logText);
    }

    public ObjectProperty<LocalDateTime> logDateProperty() {
        return this.logDate;
    }
    public StringProperty logTextProperty() {
        return this.logText;
    }
}
