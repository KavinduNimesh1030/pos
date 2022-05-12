package bo;

import model.ItemDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO {
     ArrayList<ItemDTO> loadAllItems() throws SQLException, ClassNotFoundException;
     ArrayList searchItemCount(int Count) throws SQLException, ClassNotFoundException;
     boolean deleteItem(String code) throws SQLException, ClassNotFoundException;
     boolean saveItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
     boolean updateItem(ItemDTO dto) throws SQLException, ClassNotFoundException;
     boolean existItem(String code) throws SQLException, ClassNotFoundException;
     String generateNewItemID () throws SQLException, ClassNotFoundException;
}
