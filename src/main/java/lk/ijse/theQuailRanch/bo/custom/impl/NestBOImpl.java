package lk.ijse.theQuailRanch.bo.custom.impl;

import lk.ijse.theQuailRanch.bo.custom.NestBO;
import lk.ijse.theQuailRanch.dao.DAOFactory;
import lk.ijse.theQuailRanch.dao.custom.NestDAO;
import lk.ijse.theQuailRanch.dto.NestDto;
import lk.ijse.theQuailRanch.entity.Nest;

import java.sql.SQLException;
import java.util.ArrayList;

public class NestBOImpl implements NestBO {
    NestDAO nestDAO = (NestDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.NEST);

    @Override
    public ArrayList<NestDto> getAllNests() throws SQLException {
        ArrayList<NestDto> nestDtos = new ArrayList<>();
        ArrayList<Nest> nests = nestDAO.getAll();

        for (Nest nest : nests) {
            nestDtos.add(new NestDto(nest.getId(), nest.getCategory(), nest.getAmountOfBirds()));
        }
        return nestDtos;
    }

    @Override
    public boolean saveNest(NestDto dto) throws SQLException {
        return nestDAO.save(new Nest(dto.getId(), dto.getCategory(), dto.getAmountOfBirds()));
    }

    @Override
    public NestDto searchNest(String id) throws SQLException {
        Nest nest = nestDAO.search(id);
        return new NestDto(nest.getId(), nest.getCategory(), nest.getAmountOfBirds());
    }

    @Override
    public boolean updateNest(NestDto dto) throws SQLException {
        return nestDAO.update(new Nest(dto.getId(), dto.getCategory(), dto.getAmountOfBirds()));
    }

    @Override
    public boolean deleteNest(String id) throws SQLException {
        return nestDAO.delete(id);
    }

    @Override
    public String generateNextNestId() throws SQLException {
        return nestDAO.generateNextId();
    }

    @Override
    public int getNestCount() throws SQLException {
        return nestDAO.getCount();
    }
}
