package lk.ijse.theQuailRanch.dao.custom.impl;

import lk.ijse.theQuailRanch.dao.SQLUtil;
import lk.ijse.theQuailRanch.dao.custom.FarmStockDAO;
import lk.ijse.theQuailRanch.entity.FarmStock;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FarmStockDAOImpl implements FarmStockDAO {
    @Override
    public ArrayList<FarmStock> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Farm_stock");
        ArrayList<FarmStock> allFarmStocks = new ArrayList<>();

        while (resultSet.next()) {
            FarmStock farmStock = new FarmStock(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            allFarmStocks.add(farmStock);
        }
        return allFarmStocks;
    }

    @Override
    public boolean save(FarmStock farmStock) throws SQLException {
        return SQLUtil.execute("INSERT INTO Farm_stock VALUES(?, ?, ?, ?)", farmStock.getId(), farmStock.getSupId(), farmStock.getCategory(), farmStock.getQuantity());
    }

    @Override
    public FarmStock search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Farm_stock WHERE farm_stock_id = ?", id);
        resultSet.next();

        return  new FarmStock(id + "", resultSet.getString("sup_id"), resultSet.getString("category"), resultSet.getString("quantity"));
    }

    @Override
    public boolean update(FarmStock farmStock) throws SQLException {
        return SQLUtil.execute("UPDATE Farm_stock SET sup_id = ?, category = ?, quantity = ? WHERE farm_stock_id = ?", farmStock.getSupId(), farmStock.getCategory(), farmStock.getQuantity(), farmStock.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Farm_stock WHERE farm_stock_id = ?", id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT farm_stock_id FROM Farm_stock ORDER BY farm_stock_id DESC LIMIT 1");

        String currentId = null;

        if(resultSet.next()) {
            currentId = resultSet.getString(1);
            return splitId(currentId);
        }
        return splitId(null);
    }

    private String splitId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("F");
            int id = Integer.parseInt(split[1]);
            id ++;
            return "F00" + id;
        }
        return "F001";
    }

    @Override
    public int getCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(farm_stock_id) FROM Farm_stock");

        int currentCount = 0;
        int newCount = 0;

        if(resultSet.next()) {
            currentCount = resultSet.getInt(1);
            newCount = currentCount;
        }
        return newCount;
    }
}
