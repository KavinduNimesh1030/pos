package dao;

import dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDaoFactory() {
        return daoFactory == null ? daoFactory = new DAOFactory():daoFactory;
    }

    //method for hide the object creation logic and return what client wants
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case CUSTOMER:
                return new CustomerDAOImpl(); //SuperDAO superDAO=new CustomerDAOImpl();
            case ITEM:
                return new ItemDAOImpl(); //SuperDAO superDAO=new ItemDAOImpl();
            case ORDER:
                return new OrderDAOImpl(); //SuperDAO superDAO = new OrderDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailDAOImpl(); //SuperDAO superDAO = new OrderDetailsDAOImpl();
            case QUERYDAO:
                return new QueryDAOImpl(); //SuperDAO superDAO = new QueryDAOImpl();
            default:
                return null;
        }
    }

    //public final static integer values
    public enum DAOTypes {
        CUSTOMER, ITEM, ORDER, ORDERDETAILS, QUERYDAO
    }
}
