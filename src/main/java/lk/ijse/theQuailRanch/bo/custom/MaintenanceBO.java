package lk.ijse.theQuailRanch.bo.custom;

import lk.ijse.theQuailRanch.bo.SuperBO;
import lk.ijse.theQuailRanch.dto.MaintenanceDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface MaintenanceBO extends SuperBO {
    ArrayList<MaintenanceDto> getAllTts() throws SQLException;

    boolean saveTt(MaintenanceDto dto) throws SQLException;

    MaintenanceDto searchTt(String id) throws SQLException;

    boolean updateTt(MaintenanceDto dto) throws SQLException;

    boolean deleteTt(String id) throws SQLException;

    String generateNextTtId() throws SQLException;

    int getTtCount() throws SQLException;
}
