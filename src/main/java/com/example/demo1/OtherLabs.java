package com.example.demo1;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;
public class OtherLabs {
    @FXML
    private Button yes;

    @FXML
    private ImageView image1;


    @FXML
    void displayImage() {
        // Завантаження нового зображення
        Image newImage = new Image(getClass().getResource("/secondImage.jpg").toExternalForm());
        image1.setImage(newImage);
    }




    void initialize() {
        assert yes != null :"fx:id=\"yes\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert image1 != null :"fx:id=\"image1\" was not injected: check your FXML file 'hello-view.fxml'.";
    }
}
