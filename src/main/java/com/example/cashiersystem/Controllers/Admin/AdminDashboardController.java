package com.example.cashiersystem.Controllers.Admin;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AdminDashboardController implements Initializable {

    public Label clock_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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
