package dao;

import db.DBConnection;
import model.ItemDTO;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;

public interface ItemDAO {
    ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException;
    boolean saveItem(ItemDTO item) throws SQLException, ClassNotFoundException;
    boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
    boolean updateItem(ItemDTO item) throws SQLException, ClassNotFoundException;
    String generateItemId () throws SQLException, ClassNotFoundException;
    boolean existItem(String code) throws SQLException, ClassNotFoundException;

}
