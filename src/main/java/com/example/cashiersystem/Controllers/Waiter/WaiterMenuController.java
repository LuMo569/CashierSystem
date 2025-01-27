package com.example.cashiersystem.Controllers.Waiter;

import com.example.cashiersystem.Model.Model;
import com.example.cashiersystem.Views.WaiterEnums.WaiterMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class WaiterMenuController implements Initializable {
    public Button dashboard_btn;
    public Button orders_btn;
    public Button reports_btn;
    public Button reservation_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        dashboard_btn.setOnAction(event -> onDashboard());
        orders_btn.setOnAction(event -> onOrders());
        reports_btn.setOnAction(event -> onReports());
        reservation_btn.setOnAction(event -> onReservation());

        logout_btn.setOnAction(event -> onLogout());
    }


    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(WaiterMenuOptions.DASHBOARD);
    }

    private void onOrders() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(WaiterMenuOptions.ORDERS);
    }

    private void onReports() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(WaiterMenuOptions.REPORTS);
    }

    private void onReservation() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(WaiterMenuOptions.RESERVATION);
    }

    private void onLogout() {
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        stage.close();
        Model.getInstance().setWaiterLoginSuccessFlag(false);
        Model.getInstance().setAdminLoginSuccessFlag(false);
        Model.getInstance().getViewFactory().showLoginWindow();
    }

}
