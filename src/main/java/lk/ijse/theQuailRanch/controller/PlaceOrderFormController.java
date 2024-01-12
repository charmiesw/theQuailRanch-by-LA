package lk.ijse.theQuailRanch.controller;

import com.jfoenix.controls.JFXComboBox;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.theQuailRanch.bo.BOFactory;
import lk.ijse.theQuailRanch.bo.custom.CustomerBO;
import lk.ijse.theQuailRanch.bo.custom.PlaceOrderBO;
import lk.ijse.theQuailRanch.bo.custom.SellStockBO;
import lk.ijse.theQuailRanch.dto.CustomerDto;
import lk.ijse.theQuailRanch.dto.OrderDetailsDto;
import lk.ijse.theQuailRanch.dto.OrderDto;
import lk.ijse.theQuailRanch.dto.SellStockDto;
import lk.ijse.theQuailRanch.dto.tm.PlaceOrderCartTm;

import net.sf.jasperreports.engine.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PlaceOrderFormController {
    @FXML
    private JFXComboBox<String> cmbCustomerId;
    @FXML
    private JFXComboBox<String> cmbSellStockId;
    @FXML
    private TableColumn<?, ?> colAction;
    @FXML
    private TableColumn<?, ?> colCategory;
    @FXML
    private TableColumn<?, ?> colSellStockId;
    @FXML
    private TableColumn<?, ?> colQuantity;
    @FXML
    private TableColumn<?, ?> colTotal;
    @FXML
    private TableColumn<?, ?> colUnitPrice;
    @FXML
    private Label lblCustomerName;
    @FXML
    private Label lblCategory;
    @FXML
    private Label lblDate;
    @FXML
    private Label lblOrderId;
    @FXML
    private Label lblQtyOnHand;
    @FXML
    private Label lblUnitPrice;
    @FXML
    private AnchorPane root;
    @FXML
    private TableView<PlaceOrderCartTm> tblPlaceOrderCart;
    @FXML
    private TextField txtQuantity;
    @FXML
    private Label lblTotal;
    private String orderId;

    SellStockBO sellStockBO = (SellStockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SELL_STOCK);

    private final ObservableList<PlaceOrderCartTm> obList = FXCollections.observableArrayList();

    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    PlaceOrderBO placeOrderBO = (PlaceOrderBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PLACE_ORDER);

    public void initialize() {
        setCellValueFactory();
        generateNextOrderId();
        setDate();
        loadCustomerIds();
        loadSellStockIds();
    }

    private void setCellValueFactory() {
        colSellStockId.setCellValueFactory(new PropertyValueFactory<>("sellStockId"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }

    private void generateNextOrderId() {
        try {
            String orderId = placeOrderBO.generateOrderID();
            lblOrderId.setText(orderId);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadSellStockIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<SellStockDto> sellStockList = sellStockBO.getAllSellStocks();

            for (SellStockDto sellStockDto : sellStockList) {
                obList.add(sellStockDto.getId());
            }

            cmbSellStockId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadCustomerIds() {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            List<CustomerDto> cusList = customerBO.getAllCustomers();

            for (CustomerDto dto : cusList) {
                obList.add(dto.getId());
            }
            cmbCustomerId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setDate() {
        String date = String.valueOf(LocalDate.now());
        lblDate.setText(date);
    }

    @FXML
    void btnAddToCartOnAction(ActionEvent event) {
        String id = cmbSellStockId.getValue();
        String category = lblCategory.getText();
        int qty = Integer.parseInt(txtQuantity.getText());
        double unitPrice = Double.parseDouble(lblUnitPrice.getText());
        double total = qty * unitPrice;
        Button btn = new Button("Remove");
        btn.setCursor(Cursor.HAND);

        btn.setOnAction((e) -> {
            ButtonType yes = new ButtonType("Yes", ButtonBar.ButtonData.OK_DONE);
            ButtonType no = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);

            Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

            if(type.orElse(no) == yes) {
                int selectedIndex = tblPlaceOrderCart.getSelectionModel().getSelectedIndex();
                String sellStockId = (String) colSellStockId.getCellData(selectedIndex);

                deleteOrderDetails(sellStockId);

                obList.remove(selectedIndex);
                tblPlaceOrderCart.refresh();
            }
        });

        for (int i = 0; i < tblPlaceOrderCart.getItems().size(); i++) {
            if (id.equals(colSellStockId.getCellData(i))) {
                qty += (int) colQuantity.getCellData(i);
                total = qty * unitPrice;

                obList.get(i).setQuantity(qty);
                obList.get(i).setTotal(total);

                tblPlaceOrderCart.refresh();
                calculateNetTotal();
                return;
            }
        }

        obList.add(new PlaceOrderCartTm(
                id,
                category,
                qty,
                unitPrice,
                total,
                btn
        ));

        tblPlaceOrderCart.setItems(obList);
        calculateNetTotal();
        txtQuantity.clear();
    }

    private void deleteOrderDetails(String sellStockId) {
        try {
            boolean isDeleted = placeOrderBO.deleteOrderDetails(sellStockId);
            if(isDeleted)
                new Alert(Alert.AlertType.CONFIRMATION, "Order Details Deleted!").show();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    private void calculateNetTotal() {
        double total = 0;
        for (int i = 0; i < tblPlaceOrderCart.getItems().size(); i++) {
            total += (double) colTotal.getCellData(i);
        }

        lblTotal.setText(String.valueOf(total));
    }

    @FXML
    void btnPlaceOrderOnAction(ActionEvent event) throws JRException, ClassNotFoundException {
        boolean b = saveOrder(orderId, LocalDate.now(), cmbCustomerId.getValue(),
                tblPlaceOrderCart.getItems().stream().map(tm -> new OrderDetailsDto(orderId, tm.getSellStockId(), tm.getQuantity(), tm.getUnitPrice())).collect(Collectors.toList()));

        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Order has been placed successfully").show();
        } else {
            new Alert(Alert.AlertType.ERROR, "Order has not been placed successfully").show();
        }

        clearFields();
        initialize();
    }

    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailsDto> orderDetails) {
        try {
            return placeOrderBO.placeOrder(orderId, orderDate, customerId, orderDetails);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    void clearFields() {
        lblOrderId.setText("");
        lblDate.setText("");
        cmbCustomerId.setValue(null);
        lblCustomerName.setText("");
        cmbSellStockId.setValue(null);
        lblCategory.setText("");
        lblQtyOnHand.setText("");
        lblUnitPrice.setText("");
        txtQuantity.setText("");
        lblTotal.setText("");
        tblPlaceOrderCart.setItems(null);
    }


    @FXML
    void cmbSellStockIdOnAction(ActionEvent event) {
        String id = cmbSellStockId.getValue();

        txtQuantity.requestFocus();

        try {
            SellStockDto dto = sellStockBO.searchSellStock(id);

            lblCategory.setText(dto.getCategory());
            lblUnitPrice.setText(String.valueOf(dto.getUnitPrice()));
            lblQtyOnHand.setText(String.valueOf(dto.getQuantity()));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void cmbCustomerIdOnAction(ActionEvent event) throws SQLException {
        String id = cmbCustomerId.getValue();
        CustomerDto dto = customerBO.searchCustomer(id);

        lblCustomerName.setText(dto.getName());
    }

    @FXML
    void txtQuantityOnAction(ActionEvent event) {
        btnAddToCartOnAction(event);
    }

    @FXML
    void btnAddNewCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customerForm.fxml"));
        Scene scene = new Scene(anchorPane);

        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Customer Management");
        stage.centerOnScreen();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        Parent anchorPane = FXMLLoader.load(getClass().getResource("/view/orderWindow.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Order Management");
        stage.centerOnScreen();
    }
}
