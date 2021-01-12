package DAO;

import Model.History;
import Util.JDBCConnection;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class HistoryDao implements daoInterface<History> {

    @Override
    public int addData(History data) {
        int result = 0;
        try {
            String query = "INSERT INTO history(tanggal, id_user, win/lose) values(?, ?, ?);";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setDate(1, (Date) data.getTanggal());
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
        return null;
    }
}
