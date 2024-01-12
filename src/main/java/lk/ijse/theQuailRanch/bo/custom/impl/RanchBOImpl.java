package lk.ijse.theQuailRanch.bo.custom.impl;

import lk.ijse.theQuailRanch.bo.custom.RanchBO;
import lk.ijse.theQuailRanch.dao.DAOFactory;
import lk.ijse.theQuailRanch.dao.custom.RanchDAO;
import lk.ijse.theQuailRanch.dto.RanchDto;
import lk.ijse.theQuailRanch.entity.Ranch;

import java.sql.SQLException;
import java.util.ArrayList;

public class RanchBOImpl implements RanchBO {
    RanchDAO ranchDAO = (RanchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.RANCH);

    @Override
    public ArrayList<RanchDto> getAllRanches() throws SQLException {
        ArrayList<RanchDto> ranchDtos = new ArrayList<>();
        ArrayList<Ranch> ranches = ranchDAO.getAll();

        for (Ranch ranch : ranches) {
            ranchDtos.add(new RanchDto(ranch.getId(), ranch.getDate(), ranch.getCategory(), ranch.getAmountOfBirds()));
        }
        return ranchDtos;
    }

    @Override
    public boolean saveRanch(RanchDto dto) throws SQLException {
        return ranchDAO.save(new Ranch(dto.getId(), dto.getDate(), dto.getCategory(), dto.getAmountOfBirds()));
    }

    @Override
    public RanchDto searchRanch(String id) throws SQLException {
        Ranch ranch = ranchDAO.search(id);
        return new RanchDto(ranch.getId(), ranch.getDate(), ranch.getCategory(), ranch.getAmountOfBirds());
    }

    @Override
    public boolean updateRanch(RanchDto dto) throws SQLException {
        return ranchDAO.update(new Ranch(dto.getId(), dto.getDate(), dto.getCategory(), dto.getAmountOfBirds()));
    }

    @Override
    public boolean deleteRanch(String id) throws SQLException {
        return ranchDAO.delete(id);
    }

    @Override
    public String generateNextRanchId() throws SQLException {
        return ranchDAO.generateNextId();
    }

    @Override
    public int getRanchCount() throws SQLException {
        return ranchDAO.getCount();
    }
}
