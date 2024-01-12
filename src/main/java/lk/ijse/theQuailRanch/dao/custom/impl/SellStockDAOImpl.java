package lk.ijse.theQuailRanch.dao.custom.impl;

import lk.ijse.theQuailRanch.dao.SQLUtil;
import lk.ijse.theQuailRanch.dao.custom.SellStockDAO;
import lk.ijse.theQuailRanch.db.DbConnection;
import lk.ijse.theQuailRanch.dto.tm.PlaceOrderCartTm;
import lk.ijse.theQuailRanch.entity.SellStock;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellStockDAOImpl implements SellStockDAO {
    @Override
    public ArrayList<SellStock> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Sell_stock");
        ArrayList<SellStock> allSellStocks = new ArrayList<>();

        while (resultSet.next()) {
            SellStock sellStock = new SellStock(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getDouble(4)
            );
            allSellStocks.add(sellStock);
        }
        return allSellStocks;
    }

    @Override
    public boolean save(SellStock sellStock) throws SQLException {
        return SQLUtil.execute("INSERT INTO Sell_stock VALUES(?, ?, ?, ?)", sellStock.getId(), sellStock.getCategory(), sellStock.getQuantity(), sellStock.getUnitPrice());
    }

    @Override
    public SellStock search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Sell_stock WHERE sell_stock_id = ?", id);
        resultSet.next();

        return  new SellStock(id + "", resultSet.getString(2), resultSet.getInt(3), resultSet.getDouble(4));
    }

    @Override
    public boolean update(SellStock sellStock) throws SQLException {
        return SQLUtil.execute("UPDATE Sell_stock SET category = ?, quantity = ?, unit_price = ? WHERE sell_stock_id = ?", sellStock.getCategory(), sellStock.getQuantity(), sellStock.getUnitPrice(), sellStock.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Sell_stock WHERE sell_stock_id = ?", id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT sell_stock_id FROM Sell_stock ORDER BY sell_stock_id DESC LIMIT 1");

        String currentId = null;

        if(resultSet.next()) {
            currentId = resultSet.getString(1);
            return splitId(currentId);
        }
        return splitId(null);
    }

    private String splitId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("Q");
            int id = Integer.parseInt(split[1]);
            id ++;
            return "Q00" + id;
        }
        return "Q001";
    }

    @Override
    public int getCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(sell_stock_id) FROM Sell_stock");

        int currentCount = 0;
        int newCount = 0;

        if(resultSet.next()) {
            currentCount = resultSet.getInt(1);
            newCount = currentCount;
        }
        return newCount;
    }

    @Override
    public boolean updateSellStock(List<PlaceOrderCartTm> tmList) throws SQLException {
        for (PlaceOrderCartTm cartTm : tmList) {
            if(!updateQty(cartTm)) {
                return false;
            }
        }
        return true;
    }

    private boolean updateQty(PlaceOrderCartTm cartTm) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE Sell_stock SET quantity = quantity - ? WHERE sell_stock_id = ?";

        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setInt(1, cartTm.getQuantity());
        pstm.setString(2, cartTm.getSellStockId());

        return pstm.executeUpdate() > 0;
    }
}
