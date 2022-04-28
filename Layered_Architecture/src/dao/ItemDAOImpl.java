package dao;

import db.DBConnection;
import model.CustomerDTO;
import model.ItemDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ItemDAOImpl {
    public ArrayList<ItemDTO>loadAllItems() throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        Statement stm = connection.createStatement();
        ResultSet rst = stm.executeQuery("SELECT * FROM Item");

        ArrayList<ItemDTO>allItems = new ArrayList<>();
        while (rst.next()){
            String id = rst.getString(1);
            String dec = rst.getString(2);
            BigDecimal unitPrice = BigDecimal.valueOf(rst.getDouble(3));
            int qty = Integer.parseInt(rst.getString(4));
            allItems.add(new ItemDTO(id,dec,unitPrice,qty));
        }
        return allItems;
    }
}
