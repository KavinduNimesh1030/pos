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

public class PlaceOrderDAOImpl implements PlaceOrderDAO {
    @Override
    public String selectCustomer(String newValue) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer WHERE id=?", newValue);
        CustomerDTO customerDTO = null;
        while (rst.next()) {
            customerDTO = new CustomerDTO(newValue + "", rst.getString("name"), rst.getString("address"));

        }
        return customerDTO.getName();

    }

    @Override
    public ItemDTO selectItems(String newItemCode) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?", newItemCode);
        ArrayList<ItemDTO> itemDTOS = new ArrayList<>();
        ItemDTO item = null;
        while (rst.next()) {
            item = new ItemDTO(newItemCode + "", rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        }
        return item;

    }

    @Override
    public String generateId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` ORDER BY oid DESC LIMIT 1;");
        return rst.next() ? String.format("OID-%03d", (Integer.parseInt(rst.getString("oid").replace("OID-", "")) + 1)) : "OID-001";
    }

    @Override
    public boolean existItem(String code) throws SQLException, ClassNotFoundException {
        ResultSet resultSet1 = SQLUtil.execute("SELECT code FROM Item WHERE code=?", code);
        return resultSet1.next();
    }

    @Override
    public boolean existCustomer(String id) throws SQLException, ClassNotFoundException {
        ResultSet resultSet1 = SQLUtil.execute("SELECT id FROM Customer WHERE id=?", id);
        ;
        return resultSet1.next();
    }

    @Override
    public String getCustomerId() throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Customer");
        String id = "";
        while (rst.next()) {
            id = rst.getString("id");
        }
        return id;
    }

    @Override
    public ItemDTO findItems(String code) throws SQLException, ClassNotFoundException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Item WHERE code=?", code);
        ItemDTO itemDTO = null;
        while (rst.next()) {
            itemDTO = new ItemDTO(code, rst.getString("description"), rst.getBigDecimal("unitPrice"), rst.getInt("qtyOnHand"));
        }
        return itemDTO;
    }

    @Override
    public boolean saveOrder(String orderId, LocalDate orderDate, String customerId, List<OrderDetailDTO> orderDetails) {
        /*Transaction*/
        Connection connection = null;
        try {
            ResultSet rst = SQLUtil.execute("SELECT oid FROM `Orders` WHERE oid=?", orderId);
            if (rst.next()) {

            }
            connection.setAutoCommit(false);
            if (SQLUtil.execute("INSERT INTO `Orders` (oid, date, customerID) VALUES (?,?,?)", orderId, orderDate, customerId)) {
                String itemCode = "";
                BigDecimal unitPrice = BigDecimal.valueOf(0);
                int qty = 0;
                for (OrderDetailDTO detail : orderDetails) {
                    itemCode = detail.getItemCode();
                    unitPrice = detail.getUnitPrice();
                    qty = detail.getQty();
                }

                if (SQLUtil.execute("INSERT INTO OrderDetails (oid, itemCode, unitPrice, qty) VALUES (?,?,?,?)", orderId, itemCode, unitPrice, qty)) {
                    ItemDTO item = PlaceOrderFormController.findItem(itemCode);
                    item.setQtyOnHand(item.getQtyOnHand() - qty);


                    if (SQLUtil.execute("UPDATE Item SET description=?, unitPrice=?, qtyOnHand=? WHERE code=?", item.getDescription(), item.getUnitPrice(), item.getQtyOnHand(), item.getCode())) {
                        connection.commit();
                    } else {
                        connection.rollback();
                        connection.setAutoCommit(true);
                    }

                } else {
                    connection.rollback();
                    connection.setAutoCommit(true);
                }

            } else {
                connection.rollback();
                connection.setAutoCommit(true);
                return false;
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            return true;

        }

    }
}
