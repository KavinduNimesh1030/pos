package bo.custom.impl;

import dao.SuperDAO;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory() {
    }
    public static BOFactory getBoFactory() {
        return boFactory == null ? boFactory = new BOFactory():boFactory;
    }
    public enum BOTypes {
        CUSTOMER, ITEM, ORDER,
    }
    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerBOImpl(); //SuperDAO superDAO=new CustomerDAOImpl();
            case ITEM:
                return new ItemBOImpl(); //SuperDAO superDAO=new ItemDAOImpl();
            case ORDER:
                return new PurchaseOrderBOImpl(); //SuperDAO superDAO = new OrderDAOImpl();
            default:
                return null;
        }
    }
}
