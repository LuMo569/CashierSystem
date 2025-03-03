package com.example.cashiersystem.Controllers.Admin;

import com.example.cashiersystem.Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminController implements Initializable {
    public BorderPane admin_parent;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
         Model.getInstance().getViewFactory().getAdminSelectedMenuItem().addListener((observableValue, soldVal, newVal) -> {
             switch (newVal) {
                 case CHANGEITEMPRICE -> admin_parent.setCenter(Model.getInstance().getViewFactory().getChangeItemPriceView());
                 default -> admin_parent.setCenter(Model.getInstance().getViewFactory().getAdminDashboardView());
            }
        });
    }
}
