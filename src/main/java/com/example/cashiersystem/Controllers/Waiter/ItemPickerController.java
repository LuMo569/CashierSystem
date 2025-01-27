package com.example.cashiersystem.Controllers.Waiter;

import com.example.cashiersystem.Model.Model;
import com.example.cashiersystem.Model.OrderItem;
import com.example.cashiersystem.Views.WaiterEnums.WaiterMenuOptions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ItemPickerController implements Initializable {
    public GridPane food_grid_pane;
    public GridPane drinks_grid_pane;

    // Control Buttons
    public Button place_order_btn;
    public Button abort_order_btn;
    public Button clear_all_items_btn;
    public TableView<OrderItem> selected_items_view;
    public TableColumn<OrderItem, String> itemNameColumn;
    public TableColumn<OrderItem, Integer> quantityColumn;

    private final ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Connect columns with their properties
        itemNameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asObject());

        // Load menu items dynamically
        loadMenuItems("food");
        loadMenuItems("drinks");

        place_order_btn.setOnAction(event -> {
            placeOrder();
            clearSelected();
        });
        clear_all_items_btn.setOnAction(event -> {
            Model.getInstance().getOrder().clearItems();
            clearSelected();
        });
        abort_order_btn.setOnAction(event -> {
            Model.getInstance().getOrder().clearItems();
            clearSelected();
            Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(WaiterMenuOptions.ORDERS);
        });
    }

    private void loadMenuItems(String category) {
        List<Map<String, Object>> menuItems;

        // Load items from the DatabaseDriver
        if (category.equals("food")) {
            menuItems = Model.getInstance().getDatabaseDriver().getMenuItemsByCategory("food");
        } else if (category.equals("drinks")) {
            menuItems = Model.getInstance().getDatabaseDriver().getMenuItemsByCategory("drinks");
        } else {
            throw new IllegalArgumentException("Invalid category: " + category);
        }

        GridPane targetGridPane = category.equals("food") ? food_grid_pane : drinks_grid_pane;
        AtomicInteger column = new AtomicInteger(0);
        AtomicInteger row = new AtomicInteger(0);

        targetGridPane.setVgap(10);
        targetGridPane.setHgap(6.5);

        for (Map<String, Object> item : menuItems) {
            int id = (int) item.get("id");
            String name = (String) item.get("name");
            String subcategory = (String) item.get("subcategory");

            Button button = new Button(name);
            button.getStyleClass().add("menu-button");
            button.getStyleClass().add(subcategory.toLowerCase().replace(" ", "-"));

            button.setOnAction(event -> {
                addItem(id);
                displaySelected(id);
            });

            // Add button to the GridPane
            if (column.get() == 5) {
                column.set(0);
                row.getAndIncrement();
            }

            targetGridPane.add(button, column.getAndIncrement(), row.get());
        }
    }

    private void addItem(int id) {
        Model.getInstance().getOrder().addItemId(id);
    }

    private void displaySelected(int id) {
        String itemName = Model.getInstance().getDatabaseDriver().getItemName(id);
        int quantity = Model.getInstance().getOrder().getQuantityForItemId(id);

        // Check if the item already exists in the list
        for (OrderItem item : orderItems) {
            if (item.nameProperty().get().equals(itemName)) {
                item.quantityProperty().set(quantity);
                selected_items_view.refresh();
                return;
            }
        }

        // Add new item if it doesn't exist
        orderItems.add(new OrderItem(itemName, quantity));
        selected_items_view.setItems(orderItems);
    }

    private void clearSelected() {
        selected_items_view.setItems(null);
        orderItems.clear();
    }

    private void placeOrder() {
        Model.getInstance().getDatabaseDriver().createOrder();
    }
}
