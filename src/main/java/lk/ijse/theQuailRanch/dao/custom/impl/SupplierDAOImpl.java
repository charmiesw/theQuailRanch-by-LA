package lk.ijse.theQuailRanch.dao.custom.impl;

import lk.ijse.theQuailRanch.dao.SQLUtil;
import lk.ijse.theQuailRanch.dao.custom.SupplierDAO;
import lk.ijse.theQuailRanch.entity.Supplier;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public ArrayList<Supplier> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Supplier");
        ArrayList<Supplier> allSuppliers = new ArrayList<>();

        while (resultSet.next()) {
            Supplier supplier = new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2)
            );
            allSuppliers.add(supplier);
        }
        return allSuppliers;
    }

    @Override
    public boolean save(Supplier supplier) throws SQLException {
        return SQLUtil.execute("INSERT INTO Supplier VALUES(?, ?)", supplier.getId(), supplier.getName());
    }

    @Override
    public Supplier search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Supplier WHERE sup_id = ?", id);
        resultSet.next();

        return  new Supplier(id + "", resultSet.getString(2));
    }

    @Override
    public boolean update(Supplier supplier) throws SQLException {
        return SQLUtil.execute("UPDATE Supplier SET name = ? WHERE sup_id = ?", supplier.getName(), supplier.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Supplier WHERE sup_id = ?", id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT sup_id FROM Supplier ORDER BY sup_id DESC LIMIT 1");

        String currentId = null;

        if(resultSet.next()) {
            currentId = resultSet.getString(1);
            return splitId(currentId);
        }
        return splitId(null);
    }

    private String splitId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("S");
            int id = Integer.parseInt(split[1]);
            id ++;
            return "S00" + id;
        }
        return "S001";
    }

    @Override
    public int getCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(sup_id) FROM Supplier");

        int currentCount = 0;
        int newCount = 0;

        if(resultSet.next()) {
            currentCount = resultSet.getInt(1);
            newCount = currentCount;
        }
        return newCount;
    }
}
