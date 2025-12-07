package com.example.updatedatajavafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {

    @FXML private TableView<User> table;
    @FXML private TableColumn<User, String> colName;
    @FXML private TableColumn<User, String> colEmail;

    @FXML private TextField txtName;
    @FXML private TextField txtEmail;

    private final ObservableList<User> users = FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getName()));
        colEmail.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getEmail()));

        users.addAll(
                new User("levi", "levi@gmail.com"),
                new User("mikasa ackerman", "mikasa@gmail.com"),
                new User("sample", "sample@gmail.com")
        );

        table.setItems(users);

        table.setOnMouseClicked(event -> {
            User u = table.getSelectionModel().getSelectedItem();
            if (u != null) {
                txtName.setText(u.getName());
                txtEmail.setText(u.getEmail());
            }
        });
    }

    @FXML
    private void onAdd() {
        if (txtName.getText().isEmpty() || txtEmail.getText().isEmpty())
            return;

        users.add(new User(txtName.getText(), txtEmail.getText()));
        txtName.clear();
        txtEmail.clear();
    }

    @FXML
    private void onEdit() {
        User u = table.getSelectionModel().getSelectedItem();
        if (u == null) return;

        u.setName(txtName.getText());
        u.setEmail(txtEmail.getText());

        table.refresh();
    }

    @FXML
    private void onDelete() {
        User u = table.getSelectionModel().getSelectedItem();
        if (u != null) {
            users.remove(u);
            txtName.clear();
            txtEmail.clear();
        }
    }
}
