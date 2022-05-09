package dao;

import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerDAO {
    ArrayList<CustomerDTO>searchCustomer(String letter) throws SQLException, ClassNotFoundException;
}
