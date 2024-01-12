package lk.ijse.theQuailRanch.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.theQuailRanch.bo.BOFactory;
import lk.ijse.theQuailRanch.bo.custom.CustomerBO;
import lk.ijse.theQuailRanch.bo.custom.NestBO;
import lk.ijse.theQuailRanch.bo.custom.RanchBO;
import lk.ijse.theQuailRanch.bo.custom.SupplierBO;

import java.io.IOException;
import java.sql.SQLException;

public class RegisterWindowController {
    @FXML
    private Label lblCusCount;

    @FXML
    private Label lblSupCount;

    @FXML
    private Label lblRanchCount;

    @FXML
    private Label lblNestCount;

    @FXML
    private AnchorPane root;

    public void initialize() {
        setCounts();
    }

    private void setCounts() {
        CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);
        NestBO nestBO = (NestBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.NEST);
        SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
        RanchBO ranchBO = (RanchBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.RANCH);

        try {
            int currentCustomerCount = customerBO.getCustomerCount();
            int currentSupplierCount = supplierBO.getSupplierCount();
            int currentRanchCount = ranchBO.getRanchCount();
            int currentNestCount = nestBO.getNestCount();

            lblCusCount.setText(String.valueOf(currentCustomerCount));
            lblSupCount.setText(String.valueOf(currentSupplierCount));
            lblRanchCount.setText(String.valueOf(currentRanchCount));
            lblNestCount.setText(String.valueOf(currentNestCount));

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
    void btnCMOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customerForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Customer Management");
        stage.centerOnScreen();
    }

    @FXML
    void btnNMOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/nestForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Nest Management");
        stage.centerOnScreen();
    }

    @FXML
    void btnRMOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/ranchForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Ranch Management");
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
    void btnSMOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/supplierForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Supplier Management");
        stage.centerOnScreen();
    }
}
