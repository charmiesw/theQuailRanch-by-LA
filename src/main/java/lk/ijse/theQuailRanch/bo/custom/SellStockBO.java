package lk.ijse.theQuailRanch.bo.custom;

import lk.ijse.theQuailRanch.bo.SuperBO;
import lk.ijse.theQuailRanch.dto.SellStockDto;
import lk.ijse.theQuailRanch.dto.tm.PlaceOrderCartTm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface SellStockBO extends SuperBO {
    ArrayList<SellStockDto> getAllSellStocks() throws SQLException;

    boolean saveSellStock(SellStockDto dto) throws SQLException;

    SellStockDto searchSellStock(String id) throws SQLException;

    boolean updateSellStock(SellStockDto dto) throws SQLException;

    boolean deleteSellStock(String id) throws SQLException;

    String generateNextSellStockId() throws SQLException;

    int getSellStockCount() throws SQLException;

    boolean updateSellStock(List<PlaceOrderCartTm> tmList) throws SQLException;
}
