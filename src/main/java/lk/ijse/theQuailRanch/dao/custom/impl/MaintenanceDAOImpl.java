package lk.ijse.theQuailRanch.dao.custom.impl;

import lk.ijse.theQuailRanch.dao.SQLUtil;
import lk.ijse.theQuailRanch.dao.custom.MaintenanceDAO;
import lk.ijse.theQuailRanch.entity.Employee;
import lk.ijse.theQuailRanch.entity.Maintenance;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MaintenanceDAOImpl implements MaintenanceDAO {
    @Override
    public ArrayList<Maintenance> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Maintenance maintenance) throws SQLException {
        return SQLUtil.execute("INSERT INTO Cleaning_tt VALUES(?, ?, ?, ?)", maintenance.getTtId(), maintenance.getEmpId(), maintenance.getNestId(), maintenance.getDate());
    }

    @Override
    public Maintenance search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Cleaning_tt WHERE tt_id = ?", id);
        resultSet.next();

        return  new Maintenance(id + "", resultSet.getString("emp_id"), resultSet.getString("nest_id"), resultSet.getDate("date"));
    }

    @Override
    public boolean update(Maintenance maintenance) throws SQLException {
        return SQLUtil.execute("UPDATE Cleaning_tt SET emp_id = ?, nest_id = ?, date = ? WHERE tt_id = ?", maintenance.getEmpId(), maintenance.getNestId(), maintenance.getDate(), maintenance.getTtId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Cleaning_tt WHERE tt_id = ?", id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT tt_id FROM Cleaning_tt ORDER BY tt_id DESC LIMIT 1");

        String currentId = null;

        if(resultSet.next()) {
            currentId = resultSet.getString(1);
            return splitId(currentId);
        }
        return splitId(null);
    }

    private String splitId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("T");
            int id = Integer.parseInt(split[1]);
            id ++;
            return "T00" + id;
        }
        return "T001";
    }

    @Override
    public int getCount() throws SQLException {
        return 0;
    }
}
