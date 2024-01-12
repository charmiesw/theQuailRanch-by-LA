package lk.ijse.theQuailRanch.bo.custom;

import lk.ijse.theQuailRanch.bo.SuperBO;
import lk.ijse.theQuailRanch.dto.RanchDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RanchBO extends SuperBO {
    ArrayList<RanchDto> getAllRanches() throws SQLException;

    boolean saveRanch(RanchDto dto) throws SQLException;

    RanchDto searchRanch(String id) throws SQLException;

    boolean updateRanch(RanchDto dto) throws SQLException;

    boolean deleteRanch(String id) throws SQLException;

    String generateNextRanchId() throws SQLException;

    int getRanchCount() throws SQLException;
}
