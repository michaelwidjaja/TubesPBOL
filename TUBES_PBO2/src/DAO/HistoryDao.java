package DAO;

import Model.Elemen;
import Model.History;
import Util.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class HistoryDao implements daoInterface<History> {

    @Override
    public int addData(History data) {
        int result = 0;
        try {
            String query = "INSERT INTO history(tanggal, id_user, result) values(?, ?, ?)";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1, data.getTanggal());
            ps.setInt(2,data.getId_user());
            ps.setString(3,data.getWinlose());
            result = ps.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int delData(History data) {
        return 0;
    }

    @Override
    public int updateData(History data) {
        return 0;
    }

    @Override
    public List<History> showData() {
        ObservableList<History> hList = FXCollections.observableArrayList();

        try {
            String query = "SELECT u.Nama,h.* FROM history h JOIN user u ON u.idpengguna = h.id_user";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                int id = res.getInt("id");
                String tanggal = res.getString("Tanggal");
                String result = res.getString("result");
                String name = res.getString("nama");
                History h = new History();
                h.setId(id);
                h.setTanggal(tanggal);
                h.setWinlose(result);
                h.setNameUser(name);
                hList.add(h);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }

        return hList;
    }
}
