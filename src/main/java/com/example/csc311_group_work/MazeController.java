package com.example.csc311_group_work;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class MazeController {
    @FXML
    public ImageView robotImg;

    public void movementCheck(KeyEvent e){
        if (e.getCode() == KeyCode.UP) {
            robotImg.setLayoutY(robotImg.getLayoutY() + 2);
        }
}
}
