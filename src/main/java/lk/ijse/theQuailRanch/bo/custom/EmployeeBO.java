package lk.ijse.theQuailRanch.bo.custom;

import lk.ijse.theQuailRanch.bo.SuperBO;
import lk.ijse.theQuailRanch.dto.EmployeeDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface EmployeeBO extends SuperBO {
    ArrayList<EmployeeDto> getAllEmployees() throws SQLException;

    boolean saveEmployee(EmployeeDto dto) throws SQLException;

    EmployeeDto searchEmployee(String id) throws SQLException;

    boolean updateEmployee(EmployeeDto dto) throws SQLException;

    boolean deleteEmployee(String id) throws SQLException;

    String generateNextEmployeeId() throws SQLException;

    int getEmployeeCount() throws SQLException;
}
