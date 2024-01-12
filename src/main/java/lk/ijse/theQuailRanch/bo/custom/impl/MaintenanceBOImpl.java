package lk.ijse.theQuailRanch.bo.custom.impl;

import lk.ijse.theQuailRanch.bo.custom.MaintenanceBO;
import lk.ijse.theQuailRanch.dao.DAOFactory;
import lk.ijse.theQuailRanch.dao.custom.MaintenanceDAO;
import lk.ijse.theQuailRanch.dto.MaintenanceDto;
import lk.ijse.theQuailRanch.entity.Maintenance;

import java.sql.SQLException;
import java.util.ArrayList;

public class MaintenanceBOImpl implements MaintenanceBO {
    MaintenanceDAO maintenanceDAO = (MaintenanceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.MAINTENANCE);

    @Override
    public ArrayList<MaintenanceDto> getAllTts() throws SQLException {
        ArrayList<MaintenanceDto> maintenanceDtos = new ArrayList<>();
        ArrayList<Maintenance> maintenances = maintenanceDAO.getAll();

        for (Maintenance maintenance : maintenances) {
            maintenanceDtos.add(new MaintenanceDto(maintenance.getTtId(), maintenance.getEmpId(), maintenance.getNestId(), maintenance.getDate()));
        }
        return maintenanceDtos;
    }

    @Override
    public boolean saveTt(MaintenanceDto dto) throws SQLException {
        return maintenanceDAO.save(new Maintenance(dto.getTtId(), dto.getEmpId(), dto.getNestId(), dto.getDate()));
    }

    @Override
    public MaintenanceDto searchTt(String id) throws SQLException {
        Maintenance maintenance = maintenanceDAO.search(id);
        return new MaintenanceDto(maintenance.getTtId(), maintenance.getEmpId(), maintenance.getNestId(), maintenance.getDate());
    }

    @Override
    public boolean updateTt(MaintenanceDto dto) throws SQLException {
        return maintenanceDAO.update(new Maintenance(dto.getTtId(), dto.getEmpId(), dto.getNestId(), dto.getDate()));
    }

    @Override
    public boolean deleteTt(String id) throws SQLException {
        return maintenanceDAO.delete(id);
    }

    @Override
    public String generateNextTtId() throws SQLException {
        return maintenanceDAO.generateNextId();
    }

    @Override
    public int getTtCount() throws SQLException {
        return maintenanceDAO.getCount();
    }
}
