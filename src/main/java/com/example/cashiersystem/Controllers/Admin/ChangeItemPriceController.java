package com.example.cashiersystem.Controllers.Admin;

import com.example.cashiersystem.Model.Item;
import com.example.cashiersystem.Model.LogEntry;
import com.example.cashiersystem.Model.Model;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class ChangeItemPriceController implements Initializable {
    public Label item_name_lbl;
    public TextField item_name_text_field;
    public Label filter_lbl;

    public ChoiceBox<String> filter_choice_box;
    ObservableList<String> filterOptions = FXCollections.observableArrayList("drink", "food");

    public Button search_btn;
    public Button confirm_btn;
    public Button reset_btn;

    public Label info_lbl;
    public Label error_lbl;

    // table view for search results
    public TableView<Item> items_table_view;
    public TableColumn<Item, Integer> items_id_column;
    public TableColumn<Item, String> items_name_column;
    public TableColumn<Item, Double> items_curr_price_column;
    public TableColumn<Item, Double> items_new_price_column;

    // table view for log entries
    public TableView<LogEntry> log_table_view;
    public TableColumn<LogEntry, LocalDateTime> log_time_column;
    public TableColumn<LogEntry, String> log_text_column;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        filter_choice_box.setItems(filterOptions);

        configureTableView();
        configureLogTable();
        addListeners();
    }

    private void configureTableView() {
        items_id_column.setCellValueFactory(new PropertyValueFactory<Item, Integer>("id"));
        items_name_column.setCellValueFactory(new PropertyValueFactory<Item, String>("name"));
        items_curr_price_column.setCellValueFactory(new PropertyValueFactory<Item, Double>("currentPrice"));

        // custom cell factory for items_new_price_column
        items_new_price_column.setCellValueFactory(new PropertyValueFactory<Item, Double>("newPrice"));
        items_new_price_column.setCellFactory(column -> new TableCell<Item, Double>() {
            private final TextField textField = new TextField();

            @Override
            protected void updateItem(Double item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    textField.setText(item == null ? "" : item.toString());
                    setGraphic(textField);

                    // save the value if focus is lost
                    textField.focusedProperty().addListener((observable, oldValue, newValue) -> {
                        if (!newValue) {
                            String text = textField.getText();
                            if (text.isEmpty()) {
                                getTableView().getItems().get(getIndex()).newPriceProperty().set(null);
                            } else {
                                try {
                                    double newPrice = Double.parseDouble(text);
                                    getTableView().getItems().get(getIndex()).newPriceProperty().set(newPrice);
                                } catch (NumberFormatException e) {
                                    getTableView().getItems().get(getIndex()).newPriceProperty().set(null);
                                }
                            }
                        }
                    });
                }
            }
        });

        // Add a listener to load data dynamically (if necessary)
        items_table_view.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    private void configureLogTable() {
        log_text_column.setCellValueFactory(new PropertyValueFactory<>("logText"));
        log_time_column.setCellValueFactory(new PropertyValueFactory<>("logDate"));
        log_time_column.setCellFactory(new Callback<TableColumn<LogEntry, LocalDateTime>, TableCell<LogEntry, LocalDateTime>>() {
            @Override
            public TableCell<LogEntry, LocalDateTime> call(TableColumn<LogEntry, LocalDateTime> param) {
                return new TableCell<LogEntry, LocalDateTime>() {
                    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

                    @Override
                    protected void updateItem(LocalDateTime item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty || item == null) {
                            setText(null);
                        } else {
                            setText(item.format(formatter));  // Formatierung des Timestamps
                        }
                    }
                };
            }
        });
        updateLogTable();
    }

    private void addListeners() {
        search_btn.setOnAction(event -> onSearchItems());
        confirm_btn.setOnAction(event -> onChangePrice());
        reset_btn.setOnAction(event -> onReset());
    }

    private void onSearchItems() {
        String searchText = null;
        if (!Objects.equals(item_name_text_field.getText(), "")) {
            searchText = item_name_text_field.getText();
        }
        String filter = filter_choice_box.getValue();

        List<Item> searchResults = Model.getInstance().getDatabaseDriver().searchItems(searchText, filter);

        items_table_view.getItems().clear();
        items_table_view.getItems().addAll(searchResults);
    }

    private void onChangePrice() {
        boolean success = false;
        for (Item item : items_table_view.getItems()) {
            if (item.newPriceProperty().get() != null) {
                success = Model.getInstance().getDatabaseDriver().updateItemPrice(item.idProperty().get(), item.newPriceProperty().get());
            }else {
                error_lbl.setText("Error, try again!");
                info_lbl.setText("");
            }
        }
        if (success) {
            info_lbl.setText("Changes successful!");
            error_lbl.setText("");
        }
        updateLogTable();
    }

    private void updateLogTable() {
        List<LogEntry> logEntries = Model.getInstance().getDatabaseDriver().getLog();
        ObservableList<LogEntry> logList = FXCollections.observableArrayList(logEntries);
        log_table_view.setItems(logList);
    }

    private void onReset() {
        item_name_text_field.clear();
        filter_choice_box.setValue(null);
        items_table_view.getItems().clear();
        info_lbl.setText("");
        error_lbl.setText("");
    }
}
