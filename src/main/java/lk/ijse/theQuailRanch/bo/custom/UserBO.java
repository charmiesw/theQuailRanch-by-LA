package lk.ijse.theQuailRanch.bo.custom;

import lk.ijse.theQuailRanch.bo.SuperBO;
import lk.ijse.theQuailRanch.dto.UserDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserBO extends SuperBO {
    ArrayList<UserDto> getAllUsers() throws SQLException;

    boolean saveUser(UserDto dto) throws SQLException;

    UserDto searchUser(String id) throws SQLException;

    boolean updateUser(UserDto dto) throws SQLException;

    boolean deleteUser(String id) throws SQLException;

    String generateNextUserId() throws SQLException;

    int getUserCount() throws SQLException;

    boolean searchUser(String username, String password) throws SQLException;
}
