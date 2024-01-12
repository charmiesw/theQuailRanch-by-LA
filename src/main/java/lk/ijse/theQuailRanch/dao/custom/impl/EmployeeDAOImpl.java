package lk.ijse.theQuailRanch.dao.custom.impl;

import lk.ijse.theQuailRanch.dao.SQLUtil;
import lk.ijse.theQuailRanch.dao.custom.EmployeeDAO;
import lk.ijse.theQuailRanch.entity.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public ArrayList<Employee> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee");
        ArrayList<Employee> allEmployees = new ArrayList<>();

        while (resultSet.next()) {
            Employee employee = new Employee(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
            allEmployees.add(employee);
        }
        return allEmployees;
    }

    @Override
    public boolean save(Employee employee) throws SQLException {
        return SQLUtil.execute("INSERT INTO Employee VALUES(?, ?, ?)", employee.getId(), employee.getName(), employee.getTel());
    }

    @Override
    public Employee search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Employee WHERE emp_id = ?", id);
        resultSet.next();

        return  new Employee(id + "", resultSet.getString(2), resultSet.getString(3));
    }

    @Override
    public boolean update(Employee employee) throws SQLException {
        return SQLUtil.execute("UPDATE Employee SET name = ?, tel = ? WHERE emp_id = ?", employee.getName(), employee.getTel(), employee.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Employee WHERE emp_id = ?", id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT emp_id FROM Employee ORDER BY emp_id DESC LIMIT 1");

        String currentId = null;

        if(resultSet.next()) {
            currentId = resultSet.getString(1);
            return splitId(currentId);
        }
        return splitId(null);
    }

    private String splitId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("E");
            int id = Integer.parseInt(split[1]);
            id ++;
            return "E00" + id;
        }
        return "E001";
    }

    @Override
    public int getCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(emp_id) FROM Employee");

        int currentCount = 0;
        int newCount = 0;

        if(resultSet.next()) {
            currentCount = resultSet.getInt(1);
            newCount = currentCount;
        }
        return newCount;
    }
}
