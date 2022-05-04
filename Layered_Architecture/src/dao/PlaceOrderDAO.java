package dao;

import controller.PlaceOrderFormController;
import db.DBConnection;
import model.CustomerDTO;
import model.ItemDTO;
import model.OrderDetailDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface PlaceOrderDAO {
    public String selectCustomer( String newValue) throws SQLException, ClassNotFoundException;
    public ItemDTO selectItems(String newItemCode) throws SQLException, ClassNotFoundException ;
    public String generateId () throws SQLException, ClassNotFoundException;
    public boolean existItem(String code) throws SQLException, ClassNotFoundException;
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException;
    public String getCustomerId () throws SQLException, ClassNotFoundException;
    public ItemDTO findItems(String code) throws SQLException, ClassNotFoundException ;
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) ;

}
