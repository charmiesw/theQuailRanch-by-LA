package lk.ijse.theQuailRanch.dao.custom;

import lk.ijse.theQuailRanch.dao.CrudDAO;
import lk.ijse.theQuailRanch.dto.tm.PlaceOrderCartTm;
import lk.ijse.theQuailRanch.entity.SellStock;

import java.sql.SQLException;
import java.util.List;

public interface SellStockDAO extends CrudDAO<SellStock> {
    boolean updateSellStock(List<PlaceOrderCartTm> tmList) throws SQLException;
}
