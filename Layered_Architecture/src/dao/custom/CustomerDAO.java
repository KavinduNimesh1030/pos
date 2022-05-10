package dao.custom;

import dao.CrudDAO;
import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO extends CrudDAO<CustomerDTO , String> {
    ArrayList<CustomerDTO>searchCustomer(String letter) throws SQLException, ClassNotFoundException;
}
