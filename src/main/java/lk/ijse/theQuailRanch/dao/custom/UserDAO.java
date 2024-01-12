package lk.ijse.theQuailRanch.dao.custom;

import lk.ijse.theQuailRanch.dao.CrudDAO;
import lk.ijse.theQuailRanch.entity.User;

import java.sql.SQLException;

public interface UserDAO extends CrudDAO<User> {
    boolean searchUser(String username, String password) throws SQLException;
}
