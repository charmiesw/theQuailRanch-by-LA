package lk.ijse.theQuailRanch.bo.custom;

import lk.ijse.theQuailRanch.bo.SuperBO;
import lk.ijse.theQuailRanch.dto.CustomerDto;
import lk.ijse.theQuailRanch.dto.OrderDetailsDto;
import lk.ijse.theQuailRanch.dto.OrderDto;
import lk.ijse.theQuailRanch.dto.SellStockDto;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    boolean placeOrder(String id, LocalDate date, String customerId, List<OrderDetailsDto> orderDetails) throws SQLException, ClassNotFoundException;

    CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException;

    SellStockDto searchSellStock(String code) throws SQLException, ClassNotFoundException;

    String generateOrderID() throws SQLException, ClassNotFoundException;

    ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException;

    ArrayList<SellStockDto> getAllSellStock() throws SQLException, ClassNotFoundException;

    boolean deleteOrderDetails(String id) throws SQLException;
}
