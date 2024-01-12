package lk.ijse.theQuailRanch.bo;

import lk.ijse.theQuailRanch.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOTypes {
        CUSTOMER, EMPLOYEE, FARM_STOCK, MAINTENANCE, NEST, PLACE_ORDER, RANCH, SALARY, SELL_STOCK, SUPPLIER, USER
    }

    public SuperBO getBO(BOTypes bOTypes) {
        switch (bOTypes) {
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case FARM_STOCK:
                return new FarmStockBOImpl();
            case MAINTENANCE:
                return new MaintenanceBOImpl();
            case NEST:
                return new NestBOImpl();
            case PLACE_ORDER:
                return new PlaceOrderBOImpl();
            case RANCH:
                return new RanchBOImpl();
            case SALARY:
                return new  SalaryBOImpl();
            case SELL_STOCK:
                return new SellStockBOImpl();
            case SUPPLIER:
                return new SupplierBOImpl();
            case USER:
                return new UserBOImpl();
            default:
                return null;
        }
    }
}
