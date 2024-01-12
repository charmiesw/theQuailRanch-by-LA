package lk.ijse.theQuailRanch.dao.custom.impl;

import lk.ijse.theQuailRanch.dao.SQLUtil;
import lk.ijse.theQuailRanch.dao.custom.RanchDAO;
import lk.ijse.theQuailRanch.entity.Ranch;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RanchDAOImpl implements RanchDAO {
    @Override
    public ArrayList<Ranch> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Quails");
        ArrayList<Ranch> allRanches = new ArrayList<>();

        while (resultSet.next()) {
            Ranch ranch = new Ranch(
                    resultSet.getString(1),
                    resultSet.getDate(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
            allRanches.add(ranch);
        }
        return allRanches;
    }

    @Override
    public boolean save(Ranch ranch) throws SQLException {
        return SQLUtil.execute("INSERT INTO Quails VALUES(?, ?, ?, ?)", ranch.getId(), ranch.getDate(), ranch.getCategory(), ranch.getAmountOfBirds());
    }

    @Override
    public Ranch search(String id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Quails WHERE ranch_id = ?", id);
        resultSet.next();

        return  new Ranch(id + "", resultSet.getDate(2), resultSet.getString(3), resultSet.getString(4));
    }

    @Override
    public boolean update(Ranch ranch) throws SQLException {
        return SQLUtil.execute("UPDATE Quails SET date = ?, category = ?, amount_of_birds = ? WHERE ranch_id = ?", ranch.getDate(), ranch.getCategory(), ranch.getAmountOfBirds(), ranch.getId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Quails WHERE ranch_id = ?", id);
    }

    @Override
    public String generateNextId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT ranch_id FROM Quails ORDER BY ranch_id DESC LIMIT 1");

        String currentId = null;

        if(resultSet.next()) {
            currentId = resultSet.getString(1);
            return splitId(currentId);
        }
        return splitId(null);
    }

    private String splitId(String currentId) {
        if(currentId != null) {
            String[] split = currentId.split("R");
            int id = Integer.parseInt(split[1]);
            id ++;
            return "R00" + id;
        }
        return "R001";
    }

    @Override
    public int getCount() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT COUNT(ranch_id) FROM Quails");

        int currentCount = 0;
        int newCount = 0;

        if(resultSet.next()) {
            currentCount = resultSet.getInt(1);
            newCount = currentCount;
        }
        return newCount;
    }
}
