package DAO;

import Model.Elemen;
import Util.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ElemenDao implements daoInterface {
    @Override
    public int addData(Object data) {
        return 0;
    }

    @Override
    public int delData(Object data) {
        return 0;
    }

    @Override
    public int updateData(Object data) {
        return 0;
    }

    @Override
    public boolean searchData(Object data) {
        return false;
    }

    @Override
    public List showData() {
        ObservableList<Elemen> uList = FXCollections.observableArrayList();

        try {
            String query = "select* from elements";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int id = res.getInt("Id");
                String nama = res.getString("Name");
                Elemen e = new Elemen();
                e.setId(id);
                e.setName(nama);
                uList.add(e);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return uList;
    }
}
