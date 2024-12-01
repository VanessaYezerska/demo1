package com.example.demo1;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.scene.control.TableView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import javafx.collections.ObservableList;
import javafx.collections.ListChangeListener;


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
    private ImageView image1;
    @FXML
    private Label label;

    @FXML
    private Button yes;

    @FXML
    private Button exit;

    @FXML
    private Label labelCount;

    @FXML
    private TextField searchField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    @FXML
    private TextField nameField; // Оголошення поля для ім'я
    @FXML
    private TextField phoneField; // Оголошення поля для телефону

    @FXML
    private TableView<Person> tableAddressBook;
    @FXML
    private TableColumn<Person, String> columnPIP;
    @FXML
    private TableColumn<Person, String> columnPhone;

    private final ObservableList<Person> addressBook = FXCollections.observableArrayList();

    private Person selectedPerson; // Для редагування запису

    private final ObservableList<Person> data = FXCollections.observableArrayList();


    // Відкриття діалогового вікна для додавання або редагування запису
    public void showAddressDialog() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("adress.fxml"));
            Parent root = fxmlLoader.load();

            AddressDialogController controller = fxmlLoader.getController();
            controller.setPerson(selectedPerson); // Передача даних для редагування

            Stage stage = new Stage();
            stage.setTitle(selectedPerson == null ? "Додати запис" : "Редагувати запис");
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.showAndWait();

            if (controller.isConfirmed()) {
                if (selectedPerson == null) {
                    addressBook.add(controller.getPerson());
                } else {
                    selectedPerson.setName(controller.getPerson().getName());
                    selectedPerson.setPhone(controller.getPerson().getPhone());
                    tableAddressBook.refresh();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Обробник для кнопки "Добавити"
    public void showDialog() {
        selectedPerson = null; // Очищення обраного запису
        showAddressDialog();
    }

    // Обробник для кнопки "Редагувати"
    public void editRecord() {
        selectedPerson = tableAddressBook.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            showAddressDialog();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Помилка");
            alert.setHeaderText("Немає обраного запису");
            alert.setContentText("Будь ласка, виберіть запис для редагування.");
            alert.showAndWait();
        }
    }
    // Метод для оновлення кількості записів у таблиці
    public void updateRecordCount() {
        ObservableList<Person> records = tableAddressBook.getItems();
        int count = records.size(); // Отримання кількості записів
        labelCount.setText("Кількість записів: " + count); // Встановлення тексту в мітці
    }

    @FXML
    private void deleteRecord() {
        // Отримуємо обраний запис
        Person selectedPerson = tableAddressBook.getSelectionModel().getSelectedItem();

        if (selectedPerson != null) {
            // Показуємо попереджувальне вікно
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Підтвердження видалення");
            alert.setHeaderText("Ви дійсно хочете видалити запис?");
            alert.setContentText("ПІП: " + selectedPerson.getName() + "\nТелефон: " + selectedPerson.getPhone());

            // Очікуємо на відповідь користувача
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Видаляємо запис з таблиці
                tableAddressBook.getItems().remove(selectedPerson);

                // Оновлюємо кількість записів
                updateRecordCount();
            }
        } else {
            // Якщо не обрано жодного запису
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Увага");
            alert.setHeaderText("Запис не обрано");
            alert.setContentText("Будь ласка, оберіть запис для видалення.");
            alert.showAndWait();
        }
    }

    @FXML
    private void searchRecords() {
        String searchText = searchField.getText().toLowerCase(); // Отримуємо текст для пошуку

        if (searchText.isEmpty()) {
            tableAddressBook.setItems(addressBook); // Повертаємо весь список
            return;
        }

        boolean found = false; // Прапорець для перевірки знайдених записів

        for (Person person : addressBook) {
            if (person.getName().toLowerCase().contains(searchText) ||
                    person.getPhone().toLowerCase().contains(searchText)) {
                tableAddressBook.getSelectionModel().select(person); // Виділяємо перший знайдений запис
                tableAddressBook.scrollTo(person); // Прокручуємо таблицю до знайденого запису
                found = true;
                break; // Зупиняємо пошук після першого збігу
            }
        }

        if (!found) {
            // Якщо записів не знайдено, показуємо попередження
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Результати пошуку");
            alert.setHeaderText(null);
            alert.setContentText("Записів не знайдено.");
            alert.showAndWait();
        }

        // Переконуємось, що кнопка залишиться активною
        search.setDisable(false);
    }





    public void exit() {
        System.exit(0);
    }

    @FXML
    void initialize() {
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert delete != null : "fx:id=\"delete\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert edit != null : "fx:id=\"edit\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert search != null : "fx:id=\"search\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert other != null : "fx:id=\"other\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert out != null : "fx:id=\"out\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert yes != null : "fx:id=\"yes\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert image1 != null : "fx:id=\"image1\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert exit != null : "fx:id=\"exit\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert labelCount != null : "fx:id=\"labelCount\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert searchField != null : "fx:id=\"searchField\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert saveButton != null : "fx:id=\"saveButton\" was not injected: check your FXML file 'hello-view.fxml'.";
        assert cancelButton != null : "fx:id=\"cancelButton\" was not injected: check your FXML file 'hello-view.fxml'.";
        search.setDisable(false); // Переконайтеся, що кнопка активна

        tableAddressBook.setItems(addressBook);


        columnPIP.setCellValueFactory(new PropertyValueFactory<>("name"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        tableAddressBook.setItems(addressBook);


        // Додаємо слухач змін до списку
        tableAddressBook.getItems().addListener((ListChangeListener<Person>) change -> {
            updateRecordCount(); // Оновлюємо кількість записів, якщо список змінюється
        });

        // Початкове оновлення кількості записів
        updateRecordCount();

    }

}


