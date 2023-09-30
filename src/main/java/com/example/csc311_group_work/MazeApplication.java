package com.example.csc311_group_work;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MazeApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MazeApplication.class.getResource("anchorMaze.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        MazeController controller = fxmlLoader.getController();
        scene.setOnKeyPressed(controller::handleKeyMovement);

        stage.setTitle("Maze Runner");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

