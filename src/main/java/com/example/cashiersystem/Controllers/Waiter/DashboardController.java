package com.example.cashiersystem.Controllers.Waiter;

import com.example.cashiersystem.Model.Model;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {
    public Text greetings_lbl;
    public Label clock_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        greetings_lbl.setText("Hello " + Model.getInstance().getWaiter().nameProperty().get());
        startClock();
    }

    private void startClock() {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

        Timeline clock = new Timeline(new KeyFrame(Duration.seconds(1), event -> {
            String currentTime = LocalTime.now().format(timeFormatter);
            clock_lbl.setText(currentTime);
        }));

        clock.setCycleCount(Timeline.INDEFINITE);
        clock.play();
    }
}
