package lk.ijse.theQuailRanch.bo.custom;

import lk.ijse.theQuailRanch.bo.SuperBO;
import lk.ijse.theQuailRanch.dto.SalaryDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SalaryBO extends SuperBO {
    ArrayList<SalaryDto> getAllSalaries() throws SQLException;

    boolean saveSalary(SalaryDto dto) throws SQLException;

    SalaryDto searchSalary(String id) throws SQLException;

    boolean updateSalary(SalaryDto dto) throws SQLException;

    boolean deleteSalary(String id) throws SQLException;

    String generateNextSalaryId() throws SQLException;

    int getSalaryCount() throws SQLException;
}
