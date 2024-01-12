package lk.ijse.theQuailRanch.bo.custom.impl;

import lk.ijse.theQuailRanch.bo.custom.CustomerBO;
import lk.ijse.theQuailRanch.dao.DAOFactory;
import lk.ijse.theQuailRanch.dao.custom.CustomerDAO;
import lk.ijse.theQuailRanch.dto.CustomerDto;
import lk.ijse.theQuailRanch.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);


    @Override
    public ArrayList<CustomerDto> getAllCustomers() throws SQLException {
        ArrayList<CustomerDto> customerDtos = new ArrayList<>();
        ArrayList<Customer> customers = customerDAO.getAll();

        for (Customer customer : customers) {
            customerDtos.add(new CustomerDto(customer.getId(), customer.getName(), customer.getTel()));
        }
        return customerDtos;
    }

    @Override
    public boolean saveCustomer(CustomerDto dto) throws SQLException {
        return customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getTel()));
    }

    @Override
    public CustomerDto searchCustomer(String id) throws SQLException {
        Customer customer = customerDAO.search(id);
        return new CustomerDto(customer.getId(), customer.getName(), customer.getTel());
    }

   @Override
   public boolean updateCustomer(CustomerDto dto) throws SQLException {
        return customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getTel()));
   }

   @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
   }

   @Override
    public String generateNextCustomerId() throws SQLException {
        return customerDAO.generateNextId();
   }

   @Override
    public int getCustomerCount() throws SQLException {
       return customerDAO.getCount();
   }
}
