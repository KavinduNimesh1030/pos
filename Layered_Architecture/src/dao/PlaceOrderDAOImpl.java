package dao;

import db.DBConnection;
import model.CustomerDTO;
import model.ItemDTO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PlaceOrderDAOImpl {
    public String selectCustomer( String newValue) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Customer WHERE id=?");
        pstm.setString(1, newValue + "");
        ResultSet rst = pstm.executeQuery();
        rst.next();
        CustomerDTO customerDTO = new CustomerDTO(newValue + "", rst.getString("name"), rst.getString("address"));

        return customerDTO.getName();

    }
    public ItemDTO selectItems(String newItemCode) throws SQLException, ClassNotFoundException {
        Connection connection = DBConnection.getDbConnection().getConnection();
        PreparedStatement pstm = connection.prepareStatement("SELECT * FROM Item WHERE code=?");
        pstm.setString(1, newItemCode + "");
        ResultSet rst = pstm.executeQuery();
        rst.next();
        ItemDTO item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        ArrayList<ItemDTO>itemDTOS = new ArrayList<>();
        while (rst.next()) {
            String dec = rst.getString(1);
            BigDecimal unitPrice = rst.getBigDecimal(2);
            int qty =rst.getInt(3);
        }
        return item;

    }

}
