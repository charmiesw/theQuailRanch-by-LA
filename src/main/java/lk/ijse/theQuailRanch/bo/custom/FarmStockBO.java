package lk.ijse.theQuailRanch.bo.custom;

import lk.ijse.theQuailRanch.bo.SuperBO;
import lk.ijse.theQuailRanch.dto.FarmStockDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface FarmStockBO extends SuperBO {
    ArrayList<FarmStockDto> getAllFarmStocks() throws SQLException;

    boolean saveFarmStock(FarmStockDto dto) throws SQLException;

    FarmStockDto searchFarmStock(String id) throws SQLException;

    boolean updateFarmStock(FarmStockDto dto) throws SQLException;

    boolean deleteFarmStock(String id) throws SQLException;

    String generateNextFarmStockId() throws SQLException;

    int getFarmStockCount() throws SQLException;
}
