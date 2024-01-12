package lk.ijse.theQuailRanch.dao.custom.impl;

import lk.ijse.theQuailRanch.dao.SQLUtil;
import lk.ijse.theQuailRanch.dao.custom.UserDAO;
import lk.ijse.theQuailRanch.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM User");
        ArrayList<User> allUsers = new ArrayList<>();

        while (resultSet.next()) {
            User user = new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            allUsers.add(user);
        }
        return allUsers;
    }

    @Override
    public boolean save(User user) throws SQLException {
        return SQLUtil.execute("INSERT INTO User VALUES(?, ?, ?, ?)", user.getId(), user.getUsername(), user.getPassword(), user.getTel());
    }

    @Override
    public User search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM User WHERE user_id = ?", id);
        resultSet.next();

        return  new User(id + "", resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
    }

    @Override
    public boolean update(User user) throws SQLException {
        return SQLUtil.execute("UPDATE User SET name = ?, tel = ?, pw = ? WHERE user_id = ?", user.getUsername(), user.getTel(), user.getPassword(), user.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM User WHERE user_id = ?", id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT user_id FROM User ORDER BY user_id DESC LIMIT 1");

        String currentId = null;

        if(resultSet.next()) {
            currentId = resultSet.getString(1);
            return splitId(currentId);
        }
        return splitId(null);
    }

    private String splitId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("U");
            int id = Integer.parseInt(split[1]);
            id ++;
            return "U00" + id;
        }
        return "U001";
    }

    @Override
    public int getCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(user_id) FROM User");

        int currentCount = 0;
        int newCount = 0;

        if(resultSet.next()) {
            currentCount = resultSet.getInt(1);
            newCount = currentCount;
        }
        return newCount;
    }

    @Override
    public boolean searchUser(String username, String password) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM User WHERE name = ? AND pw = ?", username, password);
        if (resultSet.next()) {
            return true;
        }
        return false;
    }
}
