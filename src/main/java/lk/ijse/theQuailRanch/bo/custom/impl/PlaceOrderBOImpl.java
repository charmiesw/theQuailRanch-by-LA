package lk.ijse.theQuailRanch.bo.custom.impl;

import lk.ijse.theQuailRanch.bo.custom.PlaceOrderBO;
import lk.ijse.theQuailRanch.dao.DAOFactory;
import lk.ijse.theQuailRanch.dao.custom.*;
import lk.ijse.theQuailRanch.db.DbConnection;
import lk.ijse.theQuailRanch.dto.CustomerDto;
import lk.ijse.theQuailRanch.dto.OrderDetailsDto;
import lk.ijse.theQuailRanch.dto.OrderDto;
import lk.ijse.theQuailRanch.dto.SellStockDto;
import lk.ijse.theQuailRanch.entity.Customer;
import lk.ijse.theQuailRanch.entity.SellStock;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PlaceOrderBOImpl implements PlaceOrderBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    SellStockDAO sellStockDAO = (SellStockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SELL_STOCK);
    OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
    OrderDetailsDAO orderDetailsDAO = (OrderDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER_DETAILS);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.QUERY);

    @Override
    public boolean placeOrder(String id, LocalDate date, String customerId, List<OrderDetailsDto> orderDetails) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        try {
            connection = DbConnection.getInstance().getConnection();
            connection.setAutoCommit(false);
            boolean isSaved = orderDAO.save(new OrderDto());

            if (!isSaved) {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

            for (OrderDetailsDto detail : orderDetails) {
                boolean isOdSaved = orderDetailsDAO.save(detail);

                if (!isOdSaved) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }

                SellStockDto sellStockDto = searchSellStock(detail.getSellStockId());
                sellStockDto.setQuantity(sellStockDto.getQuantity() - detail.getQuantity());

                boolean isUpdated = sellStockDAO.update(new SellStock(sellStockDto.getId(), sellStockDto.getCategory(), sellStockDto.getQuantity(), sellStockDto.getUnitPrice()));

                if (!isUpdated) {
                    connection.rollback();
                    connection.setAutoCommit(true);
                    return false;
                }
            }

            connection.commit();
            connection.setAutoCommit(true);
            return true;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public CustomerDto searchCustomer(String id) throws SQLException, ClassNotFoundException {
        Customer customer = customerDAO.search(id);
        return new CustomerDto(customer.getId(), customer.getName(), customer.getTel());
    }

    @Override
    public SellStockDto searchSellStock(String code) throws SQLException, ClassNotFoundException {
        SellStock sellStock = sellStockDAO.search(code);
        return new SellStockDto(sellStock.getId(), sellStock.getCategory(), sellStock.getQuantity(), sellStock.getUnitPrice());
    }

    @Override
    public String generateOrderID() throws SQLException, ClassNotFoundException {
        return orderDAO.generateNextId();
    }

    @Override
    public ArrayList<CustomerDto> getAllCustomer() throws SQLException, ClassNotFoundException {
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();
        for (Customer customer : customers) {
            customerDtos.add(new CustomerDto(customer.getId(), customer.getName(), customer.getTel()));
        }
        return customerDtos;
    }

    @Override
    public ArrayList<SellStockDto> getAllSellStock() throws SQLException, ClassNotFoundException {
        ArrayList<SellStockDto> sellStockDtos = new ArrayList<>();
        ArrayList<SellStock> sellStocks = sellStockDAO.getAll();
        for (SellStock sellStock : sellStocks) {
            sellStockDtos.add(new SellStockDto(sellStock.getId(), sellStock.getCategory(), sellStock.getQuantity(), sellStock.getUnitPrice()));
        }
        return sellStockDtos;
    }

    @Override
    public boolean deleteOrderDetails(String id) throws SQLException {
        return orderDetailsDAO.delete(id);
    }
}
