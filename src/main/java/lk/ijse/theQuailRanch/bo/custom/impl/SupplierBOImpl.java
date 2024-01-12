package lk.ijse.theQuailRanch.bo.custom.impl;

import lk.ijse.theQuailRanch.bo.custom.SupplierBO;
import lk.ijse.theQuailRanch.dao.DAOFactory;
import lk.ijse.theQuailRanch.dao.custom.SupplierDAO;
import lk.ijse.theQuailRanch.dto.SupplierDto;
import lk.ijse.theQuailRanch.entity.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierBOImpl implements SupplierBO {
    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);

    @Override
    public ArrayList<SupplierDto> getAllSuppliers() throws SQLException {
        ArrayList<SupplierDto> supplierDtos = new ArrayList<>();
        ArrayList<Supplier> suppliers = supplierDAO.getAll();

        for (Supplier supplier : suppliers) {
            supplierDtos.add(new SupplierDto(supplier.getId(), supplier.getName()));
        }
        return supplierDtos;
    }

    @Override
    public boolean saveSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.save(new Supplier(dto.getId(), dto.getName()));
    }

    @Override
    public SupplierDto searchSupplier(String id) throws SQLException {
        Supplier supplier = supplierDAO.search(id);
        return new SupplierDto(supplier.getId(), supplier.getName());
    }

    @Override
    public boolean updateSupplier(SupplierDto dto) throws SQLException {
        return supplierDAO.update(new Supplier(dto.getId(), dto.getName()));
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException {
        return supplierDAO.delete(id);
    }

    @Override
    public String generateNextSupplierId() throws SQLException {
        return supplierDAO.generateNextId();
    }

    @Override
    public int getSupplierCount() throws SQLException {
        return supplierDAO.getCount();
    }
}
