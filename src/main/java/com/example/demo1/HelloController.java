package com.example.demo1;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;


public class HelloController {

    @FXML
    private Button add;

    @FXML
    private Button delete;

    @FXML
    private Button edit;

    @FXML
    private Button other;

    @FXML
    private Button out;

    @FXML
    private Button search;


    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void hhb(ActionEvent event) {

    }



    @FXML
    void searching(ActionEvent event) {

    }


    @FXML
    void handleYesButton(ActionEvent event) {

    }

    @FXML
    void hhh(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Видалення");
        alert.setHeaderText("Results");
        alert.setContentText("Ви упішно видалили запис!");
        alert.showAndWait();

    }


    @FXML
     void showDialog(ActionEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("adress.fxml"));
        try {
            Stage stage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(), 600,300);
            stage.setScene(scene);

            stage.setTitle("Добавляємо");
            stage.setMinHeight(300);
            stage.setMinWidth(300);
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(add.getScene().getWindow());
            stage.show();

        } catch (IOException e){
            e.getStackTrace();
        }
    }

    @FXML
    void page(ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloController.class.getResource("otherPage.fxml"));

        try {
            Stage stage = new Stage();
            Scene scene=new Scene(fxmlLoader.load(),600,600);
            stage.setScene(scene);

            stage.setTitle("Other Labs");
            stage.setMinHeight(200);
            stage.setMinWidth(400);
            stage.setResizable(false);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.initOwner(other.getScene().getWindow());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @FXML
    void initialize() {
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert delete != null : "fx:id=\"delete\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert edit != null : "fx:id=\"edit\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert other != null : "fx:id=\"other\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert out != null : "fx:id=\"out\" was not injected: check your FXML file 'hello-view.fxml'.";
    }


}