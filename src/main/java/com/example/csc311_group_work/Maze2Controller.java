package com.example.csc311_group_work;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.Optional;

public class Maze2Controller {
    @FXML
    private ImageView maze2Image, carImage;
    @FXML
    private StackPane carImgContainer;
    private static final double MOVE_SIZE = 3.0; //Change this to change speed of car
    private static int START_POSITION_Y = 84;
    private static int START_POSITION_X = 8;
    @FXML private Stage stage;
    @FXML
    private Scene scene;

    @FXML
    private Parent root;

    @FXML
    private MenuItem switch1;

    @FXML
    public void switchToMaze1(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("anchorMaze.fxml"));
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
        stage.close();
    }


    /**
     * event handler for the switch MenuItem under file.
     * this will switch to the second maze.
     * @param event
     * @throws IOException
     */


    //for the stack pane to move, link the imageView of the maze with the handleKey Movement
    /**
     *  Handles the movement of the car based on what key is being pressed
     * @param event when arrow key is pressed
     */
    @FXML
    public void handleCarMovement(KeyEvent event){
        //PixelReader will get the pixel colors
        WritableImage writableImage = maze2Image.snapshot(new SnapshotParameters(), null);
        PixelReader pixelReader = writableImage.getPixelReader();
        double x = carImgContainer.getLayoutX();
        double y = carImgContainer.getLayoutY();

        switch (event.getCode()){
            case UP:
                if (carCanMoveUp(carImgContainer.getLayoutX(), carImgContainer.getLayoutY(), pixelReader)){
                    moveCar(0, -MOVE_SIZE);
                }
                break;
            case DOWN:
                if (carCanMoveDown(carImgContainer.getLayoutX(), carImgContainer.getLayoutY(), pixelReader)){
                    moveCar(0, MOVE_SIZE);
                }
                break;
            case LEFT:
                if (carCanMoveLeft(carImgContainer.getLayoutX(), carImgContainer.getLayoutY(), pixelReader)){
                    moveCar(-MOVE_SIZE, 0);
                }
                break;
            case RIGHT:
                if (carCanMoveRight(carImgContainer.getLayoutX(), carImgContainer.getLayoutY(), pixelReader)){
                    moveCar(MOVE_SIZE, 0);
                }
                break;
            default:
                break;
        }
    }

    private boolean isMaze2w(Color color){
        // RGB values for blue wall
        return color.equals(Color.web("#003fffff"));
    }

    /**
     * Checks to see if the car can move left without hitting a wall
     * @param x x location
     * @param y y location
     * @param pixelReader used to read the pixel color from mazeImg
     * @return returns true or false based on if the car can move left
     */
    private boolean carCanMoveLeft(double x, double y, PixelReader pixelReader){
        //This for loop checks the line of pixels to the left of the car
        for(int i = 0; i < carImgContainer.getPrefHeight(); i++) {
            Color color = pixelReader.getColor((int) x - 1, (int) y + i);
            if(isMaze2w(color)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if the car can move right without hitting a wall
     * @param x x location
     * @param y y location
     * @param pixelReader used to read the pixel color from mazeImg
     * @return returns true or false based on if the car can move right
     */
    private boolean carCanMoveRight(double x, double y, PixelReader pixelReader){
        for (int i = 0; i < carImgContainer.getPrefHeight(); i++){
            Color color = pixelReader.getColor((int) (x + carImgContainer.getPrefWidth() + 1), (int) y + i);
            if (isMaze2w(color)){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if the car can move up without hitting a wall
     * @param x x location
     * @param y y location
     * @param pixelReader used to read the pixel color from mazeImg
     * @return returns true or false based on if the car can move up
     */
    private boolean carCanMoveUp(double x, double y, PixelReader pixelReader){
        for (int i = 0; i < carImgContainer.getPrefWidth(); i++) {
            Color color = pixelReader.getColor((int) x + i, (int) y - 1);
            if (isMaze2w(color)){
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if the car can move down without hitting a wall
     * @param x x location
     * @param y y location
     * @param pixelReader used to read the pixel color from mazeImg
     * @return returns true or false based on if the car can move down
     */
    private boolean carCanMoveDown(double x, double y, PixelReader pixelReader){
        for (int i = 0; i < carImgContainer.getPrefWidth(); i++){
            Color color = pixelReader.getColor((int) x + i, (int) (y + carImgContainer.getPrefHeight() + 1));
            if (isMaze2w(color)){
                return false;
            }
        }
        return true;
    }

    /**
     * Moves the robots position
     * @param dx change in x direction
     * @param dy change in y direction
     */
    private void moveCar(double dx, double dy){
        carImgContainer.setLayoutX(carImgContainer.getLayoutX() + dx);
        carImgContainer.setLayoutY(carImgContainer.getLayoutY() + dy);
    }
    private void gameReset() {
        carImgContainer.setLayoutX(START_POSITION_X);
        carImgContainer.setLayoutY(START_POSITION_Y);
    }
    @FXML
    public void restartOnAction(ActionEvent actionEvent) {
        ButtonType yesBtn = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
        ButtonType noBtn = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Are you sure you want to restart the game?",
                yesBtn,
                noBtn);
        alert.setTitle("Restart Warning");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(noBtn) == yesBtn) {
            carImgContainer.setLayoutX(START_POSITION_X);
            carImgContainer.setLayoutY(START_POSITION_Y);
        }
    }

    public void closeOnAction(ActionEvent actionEvent) {
        ButtonType yesBtn = new ButtonType("YES", ButtonBar.ButtonData.OK_DONE);
        ButtonType noBtn = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
        Alert alert = new Alert(Alert.AlertType.WARNING,
                "Are you sure you want to exit the game?",
                yesBtn,
                noBtn);
        alert.setTitle("Exit Warning");
        Optional<ButtonType> result = alert.showAndWait();

        if (result.orElse(noBtn) == yesBtn) {
            Stage stage = (Stage) maze2Image.getScene().getWindow();
            stage.close();
        }
    }

    public void helpOnAction(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Help");
        alert.setHeaderText("Welcome to Maze Runner!");
        alert.setContentText("Try to get the car to the end of the maze using the arrow keys!(UP,DOWN,LEFT,RIGHT) Good Luck and have fun.");
        alert.show();
    }
}
