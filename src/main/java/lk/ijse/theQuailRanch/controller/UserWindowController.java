package lk.ijse.theQuailRanch.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.theQuailRanch.bo.BOFactory;
import lk.ijse.theQuailRanch.bo.custom.UserBO;

import java.io.IOException;
import java.sql.SQLException;

public class UserWindowController {
    @FXML
    private Label lblUserCount;

    @FXML
    private AnchorPane root;

    public void initialize() {
        setCounts();
    }

    private void setCounts() {
        UserBO userBO = (UserBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.USER);

        try {
            int currentUserCount = userBO.getUserCount();

            lblUserCount.setText(String.valueOf(currentUserCount));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void btnRegisterOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/registrationWindow.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Registrations");
        stage.centerOnScreen();
    }

    @FXML
    void btnStockOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/stockWindow.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Stock Management");
        stage.centerOnScreen();
    }

    @FXML
    void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/orderWindow.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Order Management");
        stage.centerOnScreen();
    }

    @FXML
    void btnWSOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/workspaceWindow.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Workspace");
        stage.centerOnScreen();
    }

    @FXML
    void btnUserOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/userWindow.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("User Management");
        stage.centerOnScreen();
    }

    @FXML
    void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("The Quail Ranch");
        stage.centerOnScreen();
    }

    @FXML
    void btnUMOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/userForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("User Form");
        stage.centerOnScreen();
    }
}
