package lk.ijse.theQuailRanch.dao.custom.impl;

import lk.ijse.theQuailRanch.dao.SQLUtil;
import lk.ijse.theQuailRanch.dao.custom.CustomerDAO;
import lk.ijse.theQuailRanch.entity.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public ArrayList<Customer> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> allCustomers = new ArrayList<>();

        while (resultSet.next()) {
            Customer customer = new Customer(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
            allCustomers.add(customer);
        }
        return allCustomers;
    }

    @Override
    public boolean save(Customer customer) throws SQLException {
        return SQLUtil.execute("INSERT INTO Customer VALUES(?, ?, ?)", customer.getId(), customer.getName(), customer.getTel());
    }

    @Override
    public Customer search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Customer WHERE cus_id = ?", id);
        resultSet.next();

        return  new Customer(id + "", resultSet.getString("name"), resultSet.getString("tel"));
    }

    @Override
    public boolean update(Customer customer) throws SQLException {
        return SQLUtil.execute("UPDATE Customer SET name = ?, tel = ? WHERE cus_id = ?", customer.getName(), customer.getTel(), customer.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Customer WHERE cus_id = ?", id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT cus_id FROM Customer ORDER BY cus_id DESC LIMIT 1");

        String currentId = null;

        if(resultSet.next()) {
            currentId = resultSet.getString(1);
            return splitId(currentId);
        }
        return splitId(null);
    }

    private String splitId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("C");
            int id = Integer.parseInt(split[1]);
            id ++;
            return "C00" + id;
        }
        return "C001";
    }

    @Override
    public int getCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(cus_id) FROM Customer");

        int currentCount = 0;
        int newCount = 0;

        if(resultSet.next()) {
            currentCount = resultSet.getInt(1);
            newCount = currentCount;
        }
        return newCount;
    }
}
