package lk.ijse.theQuailRanch.dao.custom.impl;

import lk.ijse.theQuailRanch.dao.SQLUtil;
import lk.ijse.theQuailRanch.dao.custom.OrderDAO;
import lk.ijse.theQuailRanch.dto.OrderDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDAOImpl implements OrderDAO {
    @Override
    public ArrayList<OrderDto> getAll() throws SQLException {
        return null;
    }

    @Override
    public boolean save(OrderDto orderDto) throws SQLException {
        return SQLUtil.execute("INSERT INTO Orders VALUES(?, ?, ?)", orderDto.getOrderId(), orderDto.getCusId(), orderDto.getDate());
    }

    @Override
    public OrderDto search(String id) throws SQLException {
        return null;
    }

    @Override
    public boolean update(OrderDto orderDto) throws SQLException {
        return false;
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT order_id FROM Orders ORDER BY order_id DESC LIMIT 1");

        String currentId = null;

        if(resultSet.next()) {
            currentId = resultSet.getString(1);
            return splitId(currentId);
        }
        return splitId(null);
    }

    private String splitId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("O");
            int id = Integer.parseInt(split[1]);
            id ++;
            return "O00" + id;
        }
        return "O001";
    }

    @Override
    public int getCount() throws SQLException {
        return 0;
    }
}
