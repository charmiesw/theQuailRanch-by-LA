package lk.ijse.theQuailRanch.bo.custom;

import lk.ijse.theQuailRanch.bo.SuperBO;
import lk.ijse.theQuailRanch.dto.SupplierDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierBO extends SuperBO {
    ArrayList<SupplierDto> getAllSuppliers() throws SQLException;

    boolean saveSupplier(SupplierDto dto) throws SQLException;

    SupplierDto searchSupplier(String id) throws SQLException;

    boolean updateSupplier(SupplierDto dto) throws SQLException;

    boolean deleteSupplier(String id) throws SQLException;

    String generateNextSupplierId() throws SQLException;

    int getSupplierCount() throws SQLException;
}
