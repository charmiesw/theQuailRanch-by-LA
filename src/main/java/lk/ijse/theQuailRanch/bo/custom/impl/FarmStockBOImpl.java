package lk.ijse.theQuailRanch.bo.custom.impl;

import lk.ijse.theQuailRanch.bo.custom.FarmStockBO;
import lk.ijse.theQuailRanch.dao.DAOFactory;
import lk.ijse.theQuailRanch.dao.custom.FarmStockDAO;
import lk.ijse.theQuailRanch.dto.FarmStockDto;
import lk.ijse.theQuailRanch.entity.FarmStock;

import java.sql.SQLException;
import java.util.ArrayList;

public class FarmStockBOImpl implements FarmStockBO {
    FarmStockDAO farmStockDAO = (FarmStockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FARM_STOCK);


    @Override
    public ArrayList<FarmStockDto> getAllFarmStocks() throws SQLException {
        ArrayList<FarmStockDto> farmStockDtos = new ArrayList<>();
        ArrayList<FarmStock> farmStocks = farmStockDAO.getAll();

        for (FarmStock farmStock : farmStocks) {
            farmStockDtos.add(new FarmStockDto(farmStock.getId(), farmStock.getSupId(), farmStock.getCategory(), farmStock.getQuantity()));
        }
        return farmStockDtos;
    }

    @Override
    public boolean saveFarmStock(FarmStockDto dto) throws SQLException {
        return farmStockDAO.save(new FarmStock(dto.getId(), dto.getSupId(), dto.getCategory(), dto.getQuantity()));
    }

    @Override
    public FarmStockDto searchFarmStock(String id) throws SQLException {
        FarmStock farmStock = farmStockDAO.search(id);
        return new FarmStockDto(farmStock.getId(), farmStock.getSupId(), farmStock.getCategory(), farmStock.getQuantity());
    }

    @Override
    public boolean updateFarmStock(FarmStockDto dto) throws SQLException {
        return farmStockDAO.update(new FarmStock(dto.getId(), dto.getSupId(), dto.getCategory(), dto.getQuantity()));
    }

    @Override
    public boolean deleteFarmStock(String id) throws SQLException {
        return farmStockDAO.delete(id);
    }

    @Override
    public String generateNextFarmStockId() throws SQLException {
        return farmStockDAO.generateNextId();
    }

    @Override
    public int getFarmStockCount() throws SQLException {
        return farmStockDAO.getCount();
    }
}
