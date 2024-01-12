package lk.ijse.theQuailRanch.bo.custom;

import lk.ijse.theQuailRanch.bo.SuperBO;
import lk.ijse.theQuailRanch.dto.NestDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface NestBO extends SuperBO {
    ArrayList<NestDto> getAllNests() throws SQLException;

    boolean saveNest(NestDto dto) throws SQLException;

    NestDto searchNest(String id) throws SQLException;

    boolean updateNest(NestDto dto) throws SQLException;

    boolean deleteNest(String id) throws SQLException;

    String generateNextNestId() throws SQLException;

    int getNestCount() throws SQLException;
}
