package lk.ijse.theQuailRanch.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.theQuailRanch.bo.BOFactory;
import lk.ijse.theQuailRanch.bo.custom.SellStockBO;
import lk.ijse.theQuailRanch.dto.SellStockDto;
import lk.ijse.theQuailRanch.dto.tm.SellStockTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class SellStockFormController {
    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colQuantity;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<SellStockTm> tblSellStock;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtUnitPrice;

    SellStockBO sellStockBO = (SellStockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SELL_STOCK);

    public void initialize() {
        txtCategory.requestFocus();
        setCellValueFactory();
        loadAllSellStocks();
        generateNextSellStockId();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
    }

    private void loadAllSellStocks() {
        tblSellStock.getItems().clear();

        try {
            List<SellStockDto> dtoList = sellStockBO.getAllSellStocks();

            for(SellStockDto dto : dtoList) {
                tblSellStock.getItems().add(
                        new SellStockTm (
                                dto.getId(),
                                dto.getCategory(),
                                dto.getQuantity(),
                                dto.getUnitPrice()
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextSellStockId() {
        try {
            String id = sellStockBO.generateNextSellStockId();
            txtId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateSellStock(String id, String category) {
        boolean isSellStockIdValidated = Pattern.matches("[Q][0-9]{3,}", id);
        if (!isSellStockIdValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Sell-stock Id!!").show();
            return false;
        }

        boolean isSellStockCategoryValidated = Pattern.matches("[A-Za-z]{3,}", category);
        if (!isSellStockCategoryValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Category!!").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String category = txtCategory.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());

        boolean isSellStockValidated = validateSellStock(id, category);

        if (isSellStockValidated) {
            try {
                boolean isSaved = sellStockBO.saveSellStock(new SellStockDto(id, category, quantity, unitPrice));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Sell-stock Added Successfully!").show();
                    clearFields();
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String id = txtId.getText();
        String category = txtCategory.getText();
        int quantity = Integer.parseInt(txtQuantity.getText());
        double unitPrice = Double.parseDouble(txtUnitPrice.getText());

        boolean isSellStockValidated = validateSellStock(id, category);

        if (isSellStockValidated) {
            try {
                boolean isUpdated = sellStockBO.updateSellStock(new SellStockDto(id, category, quantity, unitPrice));
                if(isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Sell-stock Details Updated!").show();
                    clearFields();
                    initialize();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        String id = txtId.getText();

        try {
            boolean isDeleted = sellStockBO.deleteSellStock(id);

            if(isDeleted) {
                tblSellStock.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "Sell-stock Deleted!").show();
                initialize();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
        initialize();
    }

    void clearFields() {
        txtId.setText("");
        txtCategory.setText("");
        txtQuantity.setText("");
        txtUnitPrice.setText("");
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        txtCategory.requestFocus();
        String id = txtId.getText();

        try {
            SellStockDto dto = sellStockBO.searchSellStock(id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Sell-stock Details Not Found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void fillFields(SellStockDto dto) {
        txtId.setText(dto.getId());
        txtCategory.setText(dto.getCategory());
        txtQuantity.setText(String.valueOf(dto.getQuantity()));
        txtUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/stockWindow.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Stock Management");
        stage.centerOnScreen();
    }

    @FXML
    void txtCategoryOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        txtQuantity.requestFocus();
    }

    @FXML
    void txtQuantityOnAction(ActionEvent actionEvent) throws SQLException, IOException {
        txtUnitPrice.requestFocus();
    }

    @FXML
    void txtUnitPriceOnAction(ActionEvent actionEvent) {
        btnSaveOnAction(actionEvent);
    }
}
