package lk.ijse.theQuailRanch.dao;

import lk.ijse.theQuailRanch.dto.CustomerDto;
import lk.ijse.theQuailRanch.entity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    ArrayList<T> getAll() throws SQLException;
    boolean save(T dto) throws SQLException;
    T search(String id) throws SQLException;
    boolean update(T dto) throws SQLException;
    boolean delete(String id) throws SQLException;
    String generateNextId() throws SQLException;
    int getCount() throws SQLException;
    }
