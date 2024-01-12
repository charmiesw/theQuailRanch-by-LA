package lk.ijse.theQuailRanch.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.theQuailRanch.bo.BOFactory;
import lk.ijse.theQuailRanch.bo.custom.NestBO;
import lk.ijse.theQuailRanch.dao.DAOFactory;
import lk.ijse.theQuailRanch.dto.NestDto;
import lk.ijse.theQuailRanch.dto.tm.NestTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

public class NestFormController {
    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colCategory;

    @FXML
    private TableColumn<?, ?> colAmountOfBirds;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<NestTm> tblNest;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtAmountOfBirds;

    NestBO nestBO = (NestBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.NEST);

    public void initialize() {
        txtCategory.requestFocus();
        setCellValueFactory();
        loadAllNests();
        generateNextNestId();
    }

    private void setCellValueFactory() {
        colId.setCellValueFactory(new PropertyValueFactory<>("Id"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        colAmountOfBirds.setCellValueFactory(new PropertyValueFactory<>("AmountOfBirds"));
    }

    private void loadAllNests() {
        tblNest.getItems().clear();

        try {
            List<NestDto> dtoList = nestBO.getAllNests();

            for(NestDto dto : dtoList) {
                tblNest.getItems().add(
                        new NestTm (
                                dto.getId(),
                                dto.getCategory(),
                                dto.getAmountOfBirds()
                        )
                );
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void generateNextNestId() {
        try {
            String id = nestBO.generateNextNestId();
            txtId.setText(id);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean validateNest(String id) {
        boolean isNestIdValidated = Pattern.matches("[N][0-9]{3,}", id);
        if (!isNestIdValidated) {
            new Alert(Alert.AlertType.ERROR, "Invalid Nest Id!!").show();
            return false;
        }
        return true;
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String id = txtId.getText();
        String category = txtCategory.getText();
        String amountOfBirds = txtAmountOfBirds.getText();

        boolean isNestValidated = validateNest(id);

        if (isNestValidated) {
            try {
                boolean isSaved = nestBO.saveNest(new NestDto(id, category, amountOfBirds));
                if (isSaved) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Nest Registered Successfully!").show();
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
        String amountOfBirds = txtAmountOfBirds.getText();

        boolean isNestValidated = validateNest(id);

        if (isNestValidated) {
            try {
                boolean isUpdated = nestBO.updateNest(new NestDto(id, category, amountOfBirds));
                if(isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Nest Details Updated!").show();
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
            boolean isDeleted = nestBO.deleteNest(id);

            if(isDeleted) {
                tblNest.refresh();
                new Alert(Alert.AlertType.CONFIRMATION, "Nest Deleted!").show();
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
        txtAmountOfBirds.setText("");
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        txtCategory.requestFocus();
        String id = txtId.getText();

        try {
            NestDto dto = nestBO.searchNest(id);

            if(dto != null) {
                fillFields(dto);
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Nest Not Found!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void fillFields(NestDto dto) {
        txtId.setText(dto.getId());
        txtCategory.setText(dto.getCategory());
        txtAmountOfBirds.setText(dto.getAmountOfBirds());
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/registrationWindow.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Registrations");
        stage.centerOnScreen();
    }

    @FXML
    public void txtAmountOfBirdsOnAction(ActionEvent actionEvent) {
        btnSaveOnAction(actionEvent);
    }

    @FXML
    void txtCategoryOnAction(ActionEvent actionEvent) {
        txtAmountOfBirds.requestFocus();
    }
}
