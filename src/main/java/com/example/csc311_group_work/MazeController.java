package com.example.csc311_group_work;

import javafx.animation.AnimationTimer;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class MazeController implements Initializable {
    @FXML
    public ImageView robotImg, mazeImg;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mazeImg.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent key) {
                if (key.getCode().equals(KeyCode.RIGHT)) {
                    robotImg.setLayoutX(robotImg.getLayoutX() + 5);
                } else if (key.getCode().equals(KeyCode.LEFT)) {
                    robotImg.setLayoutX(robotImg.getLayoutX() - 5);
                }else if (key.getCode().equals(KeyCode.UP)) {
                    robotImg.setLayoutY(robotImg.getLayoutY() - 5);
                }else if (key.getCode().equals(KeyCode.DOWN)) {
                    robotImg.setLayoutY(robotImg.getLayoutY() + 5);
                }
            }
        });
    }
}
