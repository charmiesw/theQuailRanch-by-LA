package lk.ijse.theQuailRanch.bo.custom.impl;

import lk.ijse.theQuailRanch.bo.custom.EmployeeBO;
import lk.ijse.theQuailRanch.dao.DAOFactory;
import lk.ijse.theQuailRanch.dao.custom.EmployeeDAO;
import lk.ijse.theQuailRanch.dto.EmployeeDto;
import lk.ijse.theQuailRanch.entity.Employee;

import java.sql.SQLException;
import java.util.ArrayList;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public ArrayList<EmployeeDto> getAllEmployees() throws SQLException {
        ArrayList<EmployeeDto> employeeDtos = new ArrayList<>();
        ArrayList<Employee> employees = employeeDAO.getAll();

        for (Employee employee : employees) {
            employeeDtos.add(new EmployeeDto(employee.getId(), employee.getName(), employee.getTel()));
        }
        return employeeDtos;
    }

    @Override
    public boolean saveEmployee(EmployeeDto dto) throws SQLException {
        return employeeDAO.save(new Employee(dto.getId(), dto.getName(), dto.getTel()));
    }

    @Override
    public EmployeeDto searchEmployee(String id) throws SQLException {
        Employee employee = employeeDAO.search(id);
        return new EmployeeDto(employee.getId(), employee.getName(), employee.getTel());
    }

    @Override
    public boolean updateEmployee(EmployeeDto dto) throws SQLException {
        return employeeDAO.update(new Employee(dto.getId(), dto.getName(), dto.getTel()));
    }

    @Override
    public boolean deleteEmployee(String id) throws SQLException {
        return employeeDAO.delete(id);
    }

    @Override
    public String generateNextEmployeeId() throws SQLException {
        return employeeDAO.generateNextId();
    }

    @Override
    public int getEmployeeCount() throws SQLException {
        return employeeDAO.getCount();
    }
}
