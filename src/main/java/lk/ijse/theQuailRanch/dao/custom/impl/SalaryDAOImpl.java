package lk.ijse.theQuailRanch.dao.custom.impl;

import lk.ijse.theQuailRanch.dao.SQLUtil;
import lk.ijse.theQuailRanch.dao.custom.SalaryDAO;
import lk.ijse.theQuailRanch.entity.Salary;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryDAOImpl implements SalaryDAO {
    @Override
    public ArrayList<Salary> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(Salary salary) throws SQLException {
        return SQLUtil.execute("INSERT INTO Salary VALUES(?, ?, ?, ?)", salary.getSalId(), salary.getEmpId(), salary.getAmount(), salary.getPaidDate());
    }

    @Override
    public Salary search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Salary WHERE sal_id = ?", id);
        resultSet.next();

        return  new Salary(id + "", resultSet.getString(2), resultSet.getString(3), resultSet.getDate(4));
    }

    @Override
    public boolean update(Salary salary) throws SQLException {
        return SQLUtil.execute("UPDATE Salary SET emp_id = ?, amount = ?, paid_date = ? WHERE sal_id = ?", salary.getEmpId(), salary.getAmount(), salary.getPaidDate(), salary.getSalId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Salary WHERE sal_id = ?", id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT sal_id FROM Salary ORDER BY sal_id DESC LIMIT 1");

        String currentId = null;

        if(resultSet.next()) {
            currentId = resultSet.getString(1);
            return splitId(currentId);
        }
        return splitId(null);
    }

    private String splitId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("Z");
            int id = Integer.parseInt(split[1]);
            id ++;
            return "Z00" + id;
        }
        return "Z001";
    }

    @Override
    public int getCount() throws SQLException {
        return 0;
    }
}
