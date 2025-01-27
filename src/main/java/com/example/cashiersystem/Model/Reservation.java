package com.example.cashiersystem.Model;

import com.example.cashiersystem.Views.WaiterEnums.ReservationStatus;
import javafx.beans.property.*;

import java.time.LocalDateTime;

public class Reservation {
    private final IntegerProperty reservationId;
    private final StringProperty tableName;
    private final StringProperty reservedBy;
    private final ObjectProperty<LocalDateTime> reservationTime;
    private final IntegerProperty guestCont;
    private final StringProperty notes;
    private final StringProperty status;

    Reservation(Integer reservationId, String tableId, String reservedBy, LocalDateTime reservation_time, Integer guest_count, String notes, String status){
        this.reservationId = new SimpleIntegerProperty(this, "reservationId", reservationId);
        this.tableName = new SimpleStringProperty(this, "table_id", tableId);
        this.reservedBy = new SimpleStringProperty(this, "reserved_by", reservedBy);
        this.reservationTime = new SimpleObjectProperty<>(this, "reservation_time", reservation_time);
        this.guestCont = new SimpleIntegerProperty(this, "guest_count", guest_count);
        this.notes = new SimpleStringProperty(this, "notes", notes);
        this.status = new SimpleStringProperty(this, "status", status);
    }

    public IntegerProperty reservationIdProperty() {
        return reservationId;
    }
    public StringProperty tableNameProperty() {
        return tableName;
    }
    public StringProperty reservedByProperty() {
        return reservedBy;
    }
    public ObjectProperty<LocalDateTime> reservationTimeProperty() {
        return reservationTime;
    }
    public IntegerProperty guestCountProperty() {
        return guestCont;
    }
    public StringProperty notesProperty() {
        return notes;
    }
    public StringProperty statusProperty() {
        return status;
    }

    public ReservationStatus getStatus() {
        return ReservationStatus.valueOf(status.get());
    }

    // Setter für den Status
    public void setStatus(ReservationStatus status) {
        this.status.set(status.name());
    }
}
