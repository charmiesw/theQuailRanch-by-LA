package lk.ijse.theQuailRanch.bo.custom.impl;

import lk.ijse.theQuailRanch.bo.custom.SalaryBO;
import lk.ijse.theQuailRanch.dao.DAOFactory;
import lk.ijse.theQuailRanch.dao.custom.SalaryDAO;
import lk.ijse.theQuailRanch.dto.SalaryDto;
import lk.ijse.theQuailRanch.entity.Salary;

import java.sql.SQLException;
import java.util.ArrayList;

public class SalaryBOImpl implements SalaryBO {
    SalaryDAO salaryDAO = (SalaryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SALARY);

    @Override
    public ArrayList<SalaryDto> getAllSalaries() throws SQLException {
        ArrayList<SalaryDto> salaryDtos = new ArrayList<>();
        ArrayList<Salary> salaries = salaryDAO.getAll();

        for (Salary salary : salaries) {
            salaryDtos.add(new SalaryDto(salary.getSalId(), salary.getEmpId(), salary.getAmount(), salary.getPaidDate()));
        }
        return salaryDtos;
    }

    @Override
    public boolean saveSalary(SalaryDto dto) throws SQLException {
        return salaryDAO.save(new Salary(dto.getSalId(), dto.getEmpId(), dto.getAmount(), dto.getPaidDate()));
    }

    @Override
    public SalaryDto searchSalary(String id) throws SQLException {
        Salary salary = salaryDAO.search(id);
        return new SalaryDto(salary.getSalId(), salary.getEmpId(), salary.getAmount(), salary.getPaidDate());
    }

    @Override
    public boolean updateSalary(SalaryDto dto) throws SQLException {
        return salaryDAO.update(new Salary(dto.getSalId(), dto.getEmpId(), dto.getAmount(), dto.getPaidDate()));
    }

    @Override
    public boolean deleteSalary(String id) throws SQLException {
        return salaryDAO.delete(id);
    }

    @Override
    public String generateNextSalaryId() throws SQLException {
        return salaryDAO.generateNextId();
    }

    @Override
    public int getSalaryCount() throws SQLException {
        return salaryDAO.getCount();
    }
}
