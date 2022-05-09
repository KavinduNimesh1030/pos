package dao;

import com.sun.xml.internal.bind.v2.model.core.ID;
import db.DBConnection;
import model.CustomerDTO;
import model.ItemDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T, Id> {
    ArrayList<T> loadAll() throws SQLException, ClassNotFoundException;

    boolean save(T dto) throws SQLException, ClassNotFoundException;


    boolean update(T dto) throws SQLException, ClassNotFoundException;

    boolean exist(Id id) throws SQLException, ClassNotFoundException;

    T search(String id) throws SQLException, ClassNotFoundException;

    boolean delete(Id id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}
