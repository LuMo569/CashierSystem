package com.example.cashiersystem.Controllers.Waiter;

import com.example.cashiersystem.Model.Model;
import com.example.cashiersystem.Views.WaiterEnums.WaiterMenuOptions;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.net.URL;
import java.util.ResourceBundle;

public class OrdersController implements Initializable {
    public Button table1_btn;
    public Button table2_btn;
    public Button table3_btn;
    public Button table4_btn;
    public Button table5_btn;
    public Button table6_btn;
    public Button bar1_btn;
    public Button bar2_btn;
    public Button bar3_btn;
    public Button bar4_btn;
    public Button bar5_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
    }

    private void addListeners() {
        table1_btn.setOnAction(event -> onTableClicked(1));
        table2_btn.setOnAction(event -> onTableClicked(2));
        table3_btn.setOnAction(event -> onTableClicked(3));
        table4_btn.setOnAction(event -> onTableClicked(4));
        table5_btn.setOnAction(event -> onTableClicked(5));
        table6_btn.setOnAction(event -> onTableClicked(6));
        bar1_btn.setOnAction(event -> onTableClicked(7));
        bar2_btn.setOnAction(event -> onTableClicked(8));
        bar3_btn.setOnAction(event -> onTableClicked(9));
        bar4_btn.setOnAction(event -> onTableClicked(10));
        bar5_btn.setOnAction(event -> onTableClicked(11));
    }

    private void onTableClicked(int tableId) {
        Model.getInstance().getOrder().waiterIdProperty().set(Model.getInstance().getWaiter().waiterIdProperty().get()); // redundant? just leave out waiterId in ORDER?
        Model.getInstance().getOrder().tableIdProperty().set(tableId);
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(WaiterMenuOptions.ITEMPICKER);
    }
}
