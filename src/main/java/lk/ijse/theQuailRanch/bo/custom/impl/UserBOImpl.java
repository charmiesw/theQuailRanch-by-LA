package lk.ijse.theQuailRanch.bo.custom.impl;

import lk.ijse.theQuailRanch.bo.custom.UserBO;
import lk.ijse.theQuailRanch.dao.DAOFactory;
import lk.ijse.theQuailRanch.dao.custom.UserDAO;
import lk.ijse.theQuailRanch.dto.UserDto;
import lk.ijse.theQuailRanch.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO {
    UserDAO userDAO = (UserDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public ArrayList<UserDto> getAllUsers() throws SQLException {
        ArrayList<UserDto> userDtos = new ArrayList<>();
        ArrayList<User> users = userDAO.getAll();

        for (User user : users) {
            userDtos.add(new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getTel()));
        }
        return userDtos;
    }

    @Override
    public boolean saveUser(UserDto dto) throws SQLException {
        return userDAO.save(new User(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getTel()));
    }

    @Override
    public UserDto searchUser(String id) throws SQLException {
        User user = userDAO.search(id);
        return new UserDto(user.getId(), user.getUsername(), user.getPassword(), user.getTel());
    }

    @Override
    public boolean updateUser(UserDto dto) throws SQLException {
        return userDAO.update(new User(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getTel()));
    }

    @Override
    public boolean deleteUser(String id) throws SQLException {
        return userDAO.delete(id);
    }

    @Override
    public String generateNextUserId() throws SQLException {
        return userDAO.generateNextId();
    }

    @Override
    public int getUserCount() throws SQLException {
        return userDAO.getCount();
    }

    @Override
    public boolean searchUser(String username, String password) throws SQLException {
        return userDAO.searchUser(username, password);
    }
}
