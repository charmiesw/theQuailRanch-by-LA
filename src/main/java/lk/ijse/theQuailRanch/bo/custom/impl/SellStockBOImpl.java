package lk.ijse.theQuailRanch.bo.custom.impl;

import lk.ijse.theQuailRanch.bo.custom.SellStockBO;
import lk.ijse.theQuailRanch.dao.DAOFactory;
import lk.ijse.theQuailRanch.dao.custom.SellStockDAO;
import lk.ijse.theQuailRanch.dto.SellStockDto;
import lk.ijse.theQuailRanch.dto.tm.PlaceOrderCartTm;
import lk.ijse.theQuailRanch.entity.SellStock;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SellStockBOImpl implements SellStockBO {
    SellStockDAO sellStockDAO = (SellStockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SELL_STOCK);

    @Override
    public ArrayList<SellStockDto> getAllSellStocks() throws SQLException {
        ArrayList<SellStockDto> sellStockDtos = new ArrayList<>();
        ArrayList<SellStock> sellStocks = sellStockDAO.getAll();

        for (SellStock sellStock : sellStocks) {
            sellStockDtos.add(new SellStockDto(sellStock.getId(), sellStock.getCategory(), sellStock.getQuantity(), sellStock.getUnitPrice()));
        }
        return sellStockDtos;
    }

    @Override
    public boolean saveSellStock(SellStockDto dto) throws SQLException {
        return sellStockDAO.save(new SellStock(dto.getId(), dto.getCategory(), dto.getQuantity(), dto.getUnitPrice()));
    }

    @Override
    public SellStockDto searchSellStock(String id) throws SQLException {
        SellStock sellStock = sellStockDAO.search(id);
        return new SellStockDto(sellStock.getId(), sellStock.getCategory(), sellStock.getQuantity(), sellStock.getUnitPrice());
    }

    @Override
    public boolean updateSellStock(SellStockDto dto) throws SQLException {
        return sellStockDAO.update(new SellStock(dto.getId(), dto.getCategory(), dto.getQuantity(), dto.getUnitPrice()));
    }

    @Override
    public boolean deleteSellStock(String id) throws SQLException {
        return sellStockDAO.delete(id);
    }

    @Override
    public String generateNextSellStockId() throws SQLException {
        return sellStockDAO.generateNextId();
    }

    @Override
    public int getSellStockCount() throws SQLException {
        return sellStockDAO.getCount();
    }

    @Override
    public boolean updateSellStock(List<PlaceOrderCartTm> tmList) throws SQLException {
        return sellStockDAO.updateSellStock(tmList);
    }
}
