package lk.ijse.theQuailRanch.dao.custom.impl;

import lk.ijse.theQuailRanch.dao.SQLUtil;
import lk.ijse.theQuailRanch.dao.custom.NestDAO;
import lk.ijse.theQuailRanch.entity.Nest;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class NestDAOImpl implements NestDAO {
    @Override
    public ArrayList<Nest> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Nests");
        ArrayList<Nest> allNests = new ArrayList<>();

        while (resultSet.next()) {
            Nest nest = new Nest(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
            allNests.add(nest);
        }
        return allNests;
    }

    @Override
    public boolean save(Nest nest) throws SQLException {
        return SQLUtil.execute("INSERT INTO Nests VALUES(?, ?, ?)", nest.getId(), nest.getCategory(), nest.getAmountOfBirds());
    }

    @Override
    public Nest search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Nests WHERE nest_id = ?", id);
        resultSet.next();

        return  new Nest(id + 1, resultSet.getString(2), resultSet.getString(3));
    }

    @Override
    public boolean update(Nest nest) throws SQLException {
        return SQLUtil.execute("UPDATE Nests SET category = ?, amount_of_birds = ? WHERE nest_id = ?", nest.getCategory(), nest.getAmountOfBirds(), nest.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Nests WHERE nest_id = ?", id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT nest_id FROM Nests ORDER BY nest_id DESC LIMIT 1");

        String currentId = null;

        if(resultSet.next()) {
            currentId = resultSet.getString(1);
            return splitId(currentId);
        }
        return splitId(null);
    }

    private String splitId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("N");
            int id = Integer.parseInt(split[1]);
            id ++;
            return "N00" + id;
        }
        return "N001";
    }

    @Override
    public int getCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(nest_id) FROM Nests");

        int currentCount = 0;
        int newCount = 0;

        if(resultSet.next()) {
            currentCount = resultSet.getInt(1);
            newCount = currentCount;
        }
        return newCount;
    }
}
