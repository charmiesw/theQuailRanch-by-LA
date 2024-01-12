package lk.ijse.theQuailRanch.bo.custom;

import lk.ijse.theQuailRanch.bo.SuperBO;
import lk.ijse.theQuailRanch.dto.CustomerDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO extends SuperBO {
    ArrayList<CustomerDto> getAllCustomers() throws SQLException;
    boolean saveCustomer(CustomerDto dto) throws SQLException;
    CustomerDto searchCustomer(String id) throws SQLException;
    boolean updateCustomer(CustomerDto dto) throws SQLException;
    boolean deleteCustomer(String id) throws SQLException;

    String generateNextCustomerId() throws SQLException;

    int getCustomerCount() throws SQLException;
}
