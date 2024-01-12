package lk.ijse.theQuailRanch.dao;

import lk.ijse.theQuailRanch.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {}

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER, EMPLOYEE, FARM_STOCK, MAINTENANCE, NEST, ORDER, ORDER_DETAILS, QUERY, RANCH, SALARY, SELL_STOCK, SUPPLIER, USER
    }

    public SuperDAO getDAO(DAOTypes daoTypes) {
        switch (daoTypes) {
            case CUSTOMER:
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case FARM_STOCK:
                return new FarmStockDAOImpl();
            case MAINTENANCE:
                return new MaintenanceDAOImpl();
            case NEST:
                return new NestDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            case RANCH:
                return new RanchDAOImpl();
            case SALARY:
                return new  SalaryDAOImpl();
            case SELL_STOCK:
                return new SellStockDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            case USER:
                return new UserDAOImpl();
            default:
                return null;
        }
    }
}
