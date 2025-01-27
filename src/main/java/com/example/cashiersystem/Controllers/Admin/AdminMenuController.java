package com.example.cashiersystem.Controllers.Admin;

import com.example.cashiersystem.Model.Model;
import com.example.cashiersystem.Views.AdminEnums.AdminMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminMenuController implements Initializable {
    public Button admin_dashboard_btn;
    public Button change_item_price_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        admin_dashboard_btn.setOnAction(event -> onAdminDashboard());
        change_item_price_btn.setOnAction(event -> onChangeItemPrice());
        logout_btn.setOnAction(event -> onLogout());
    }

    private void onAdminDashboard() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.ADMINDASHBOARD);
    }

    private void onChangeItemPrice() {
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CHANGEITEMPRICE);
    }

    private void onLogout() {
        Stage stage = (Stage) logout_btn.getScene().getWindow();
        stage.close();
        Model.getInstance().getViewFactory().showLoginWindow();
    }
}
