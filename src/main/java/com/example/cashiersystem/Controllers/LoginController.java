package com.example.cashiersystem.Controllers;

import com.example.cashiersystem.Model.Model;
import com.example.cashiersystem.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {

    public LoginController() {}

    @FXML
    public ChoiceBox<AccountType> account_selector;
    public TextField name_field;
    public TextField password_field;
    public Button login_btn;
    public Label name_lbl;
    public Label password_lbl;
    public Label error_lbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        account_selector.setItems(FXCollections.observableArrayList(AccountType.WAITER, AccountType.ADMIN));
        account_selector.setValue(Model.getInstance().getLoginAccountType());
        account_selector.valueProperty().addListener(observable -> Model.getInstance().setLoginAccountType(account_selector.getValue()));
        login_btn.setOnAction(event -> onLogin());
    }

    private void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();         //workaround
        // WAITER LOGIN
        if (Model.getInstance().getLoginAccountType() == AccountType.WAITER) {
            Model.getInstance().evaluateLoginCred(name_field.getText(), password_field.getText(), AccountType.WAITER); // maybe check if it is empty
            if (Model.getInstance().getWaiterLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showWaiterWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
                error_lbl.setText("");
            } else {
                resetLoginStage();
            }
        // ADMIN LOGIN
        } else if (Model.getInstance().getLoginAccountType() == AccountType.ADMIN){
            Model.getInstance().evaluateLoginCred(name_field.getText(), password_field.getText(), AccountType.ADMIN);
            if(Model.getInstance().getAdminLoginSuccessFlag()) {
                Model.getInstance().getViewFactory().showAdminWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
                error_lbl.setText("");
            } else {
                resetLoginStage();
            }
        }

    }

    private void resetLoginStage() {
        name_field.setText("");
        password_field.setText("");
        error_lbl.setText("Error, try again!");
    }
}
