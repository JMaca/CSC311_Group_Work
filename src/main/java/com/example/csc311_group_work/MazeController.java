package com.example.csc311_group_work;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.image.PixelReader;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.SnapshotParameters;
import javafx.stage.Stage;

import java.io.IOException;

public class MazeController{

    public AnchorPane anchorPane;
    @FXML
    private ImageView mazeImg, maze2Img;
    @FXML
    private StackPane robotImgContainer, carImgContainer;
    private static final double MOVE_SIZE = 3.0; //Change this to change speed of robot

    @FXML private Stage stage;
    @FXML
    private Scene scene;

    @FXML
    private Parent root;

    @FXML
    private MenuItem switch1;

    @FXML
    public void switchToMaze1(ActionEvent event) throws IOException {
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("anchorMaze.fxml"));
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**
     * event handler for the switch MenuItem under file.
     * this will switch to the second maze.
     * @param event
     * @throws IOException
     */
    @FXML
    public void switchToMaze2(ActionEvent event) throws IOException {
        stage = new Stage();
        root = FXMLLoader.load(getClass().getResource("maze2.fxml"));
        scene= new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    //for the stackpane to move, link the imageView of the maze with the handleKeyMvmt
    /**
     *  Handles the movement of the robot based on what key is being pressed
     * @param event when arrow key is pressed
     */
    @FXML
    public void handleCarMovement(KeyEvent event){
        //PixelReader will get the pixel colors
        WritableImage writableImage = maze2Img.snapshot(new SnapshotParameters(), null);
        PixelReader pixelReader = writableImage.getPixelReader();

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
        return color.equals(Color.web("#076eff"));
    }

    /**
     * Checks to see if the robot can move left without hitting a wall
     * @param x x location
     * @param y y location
     * @param pixelReader used to read the pixel color from mazeImg
     * @return returns true or false based on if the robot can move left
     */
    private boolean carCanMoveLeft(double x, double y, PixelReader pixelReader){
        //This for loop checks the line of pixels to the left of the robot
        for(int i = 0; i < carImgContainer.getPrefHeight(); i++) {
            Color color = pixelReader.getColor((int) x - 1, (int) y + i);
            if(isMaze2w(color)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Checks to see if the robot can move right without hitting a wall
     * @param x x location
     * @param y y location
     * @param pixelReader used to read the pixel color from mazeImg
     * @return returns true or false based on if the robot can move right
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
     * Checks to see if the robot can move up without hitting a wall
     * @param x x location
     * @param y y location
     * @param pixelReader used to read the pixel color from mazeImg
     * @return returns true or false based on if the robot can move up
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
     * Checks to see if the robot can move down without hitting a wall
     * @param x x location
     * @param y y location
     * @param pixelReader used to read the pixel color from mazeImg
     * @return returns true or false based on if the robot can move down
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

    /**
     *  Handles the movement of the robot based on what key is being pressed
     * @param event when arrow key is pressed
     */
    @FXML
    public void handleKeyMovement(KeyEvent event){
        //PixelReader will get the pixel colors
        WritableImage writableImage = mazeImg.snapshot(new SnapshotParameters(), null);
        PixelReader pixelReader = writableImage.getPixelReader();

        switch (event.getCode()){
            case UP:
                if (canMoveUp(robotImgContainer.getLayoutX(), robotImgContainer.getLayoutY(), pixelReader)){
                    moveRobot(0, -MOVE_SIZE);
                }
                break;
            case DOWN:
                if (canMoveDown(robotImgContainer.getLayoutX(), robotImgContainer.getLayoutY(), pixelReader)){
                    moveRobot(0, MOVE_SIZE);
                }
                break;
            case LEFT:
                if (canMoveLeft(robotImgContainer.getLayoutX(), robotImgContainer.getLayoutY(), pixelReader)){
                    moveRobot(-MOVE_SIZE, 0);
                }
                break;
            case RIGHT:
                if (canMoveRight(robotImgContainer.getLayoutX(), robotImgContainer.getLayoutY(), pixelReader)){
                    moveRobot(MOVE_SIZE, 0);
                }
                break;
            default:
                break;
        }
    }

    /**
     * Checks if the color is the same color as the walls in the maze
     * @param color The color to check against the color of the maze wall
     * @return if the wall color being checked is the same color as the maze wall
     */
    private boolean isWall(Color color){
        // RGB values for blue wall
        return color.equals(Color.web("#005399"));
    }

    /**
     * Checks to see if the robot can move left without hitting a wall
     * @param x x location
     * @param y y location
     * @param pixelReader used to read the pixel color from mazeImg
     * @return returns true or false based on if the robot can move left
     */
    private boolean canMoveLeft(double x, double y, PixelReader pixelReader){
        if (x >= 0) {
            //This for loop checks the line of pixels to the left of the robot
            for (int i = 0; i < robotImgContainer.getPrefHeight(); i++) {
                Color color = pixelReader.getColor((int) x - 1, (int) y + i);
                if (isWall(color)) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }
    /**
     * Checks to see if the robot can move right without hitting a wall
     * @param x x location
     * @param y y location
     * @param pixelReader used to read the pixel color from mazeImg
     * @return returns true or false based on if the robot can move right
     */
    private boolean canMoveRight(double x, double y, PixelReader pixelReader){
        try {
            for (int i = 0; i < robotImgContainer.getPrefHeight(); i++) {
                Color color = pixelReader.getColor((int) (x + robotImgContainer.getPrefWidth() + 1), (int) y + i);
                if (isWall(color)) {
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    /**
     * Checks to see if the robot can move up without hitting a wall
     * @param x x location
     * @param y y location
     * @param pixelReader used to read the pixel color from mazeImg
     * @return returns true or false based on if the robot can move up
     */
    private boolean canMoveUp(double x, double y, PixelReader pixelReader){
        for (int i = 0; i < robotImgContainer.getPrefWidth(); i++) {
            Color color = pixelReader.getColor((int) x + i, (int) y - 1);
            if (isWall(color)){
                return false;
            }
        }
        return true;
    }
    /**
     * Checks to see if the robot can move down without hitting a wall
     * @param x x location
     * @param y y location
     * @param pixelReader used to read the pixel color from mazeImg
     * @return returns true or false based on if the robot can move down
     */
    private boolean canMoveDown(double x, double y, PixelReader pixelReader){
        for (int i = 0; i < robotImgContainer.getPrefWidth(); i++){
            Color color = pixelReader.getColor((int) x + i, (int) (y + robotImgContainer.getPrefHeight() + 1));
            if (isWall(color)){
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
    private void moveRobot(double dx, double dy){
        robotImgContainer.setLayoutX(robotImgContainer.getLayoutX() + dx);
        robotImgContainer.setLayoutY(robotImgContainer.getLayoutY() + dy);
    }
}