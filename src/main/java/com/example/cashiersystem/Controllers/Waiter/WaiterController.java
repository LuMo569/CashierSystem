package com.example.cashiersystem.Controllers.Waiter;

import com.example.cashiersystem.Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class WaiterController implements Initializable {
    public BorderPane waiter_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            switch (newVal) {
                case ORDERS -> waiter_parent.setCenter(Model.getInstance().getViewFactory().getOrdersView());
                case REPORTS -> waiter_parent.setCenter(Model.getInstance().getViewFactory().getReportsView());
                case ITEMPICKER -> waiter_parent.setCenter(Model.getInstance().getViewFactory().getItemPickerView());
                case RESERVATION -> waiter_parent.setCenter(Model.getInstance().getViewFactory().getReservationView());
                default -> waiter_parent.setCenter(Model.getInstance().getViewFactory().getDashboardView());
            }
        });
    }
}
