package DAO;

import Model.Elemen;
import Util.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ElemenDao implements daoInterface<Elemen> {
    public int addData(Elemen data) {
        int result = 0;
        try {
            String query = "INSERT INTO elements(name) values(?);";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1,data.getName());
            result = ps.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int delData(Elemen data) {
        return 0;
    }

    @Override
    public int updateData(Elemen data) {
        return 0;
    }



    @Override
    public List<Elemen> showData() {
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
