package com.example.demo1;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddressDialogController {
    @FXML
    private TextField nameField;
    @FXML
    private TextField phoneField;
    @FXML
    private Button saveButton;
    @FXML
    private Button cancelButton;

    private Person person;
    private boolean confirmed = false;

    public void setPerson(Person person) {
        this.person = person;

        if (person != null) {
            nameField.setText(person.getName());
            phoneField.setText(person.getPhone());
        }
    }

    public Person getPerson() {
        return new Person(nameField.getText(), phoneField.getText());
    }

    public boolean isConfirmed() {
        return confirmed;
    }

    @FXML
    public void save() {
        confirmed = true;
        close();
    }

    @FXML
    public void cancel() {
        confirmed = false;
        close();
    }

    private void close() {
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
    }
}

