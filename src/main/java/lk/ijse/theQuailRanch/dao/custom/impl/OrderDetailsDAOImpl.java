package lk.ijse.theQuailRanch.dao.custom.impl;

import lk.ijse.theQuailRanch.dao.SQLUtil;
import lk.ijse.theQuailRanch.dao.custom.OrderDetailsDAO;
import lk.ijse.theQuailRanch.dto.OrderDetailsDto;

import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public ArrayList<OrderDetailsDto> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(OrderDetailsDto dto) throws SQLException {
        return SQLUtil.execute("INSERT INTO Order_details VALUES(?, ?, ?, ?", dto.getOrderId(), dto.getSellStockId(), dto.getQuantity(), dto.getUnitPrice());
    }

    @Override
    public OrderDetailsDto search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(OrderDetailsDto dto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Order_details WHERE order_id = ?", id);
    }

    @Override
    public String generateNextId() throws SQLException {
        return "";
    }

    @Override
    public int getCount() throws SQLException {
        return 0;
    }
}
