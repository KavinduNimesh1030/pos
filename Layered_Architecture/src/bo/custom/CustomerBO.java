package bo.custom;

import model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomerBO {
     ArrayList<CustomerDTO> getAllCustomers() throws SQLException, ClassNotFoundException ;
     boolean saveCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
     boolean updateCustomer(CustomerDTO dto) throws SQLException, ClassNotFoundException;
     boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
     boolean deleteCustomer(String id) throws SQLException, ClassNotFoundException;
     String generateNewCustomerID() throws SQLException, ClassNotFoundException;
     public ArrayList<CustomerDTO> searchCustomer(String letter) throws SQLException, ClassNotFoundException ;

}
