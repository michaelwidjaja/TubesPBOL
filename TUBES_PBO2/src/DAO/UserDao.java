package DAO;

import Model.UserEntity;
import Util.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDao implements daoInterface <UserEntity> {
    @Override
    public int addData(UserEntity data) {
        int result = 0;
        try {
            String query = "INSERT INTO user(nama, username, password) values(?, ?, ?);";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1,data.getNama());
            ps.setString(2,data.getUsername());
            ps.setString(3,data.getPassword());
            result = ps.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }


    @Override
    public int delData(UserEntity data) {

        return 0;
    }

    @Override
    public int updateData(UserEntity data) {

        return 0;
    }

    @Override
    public List searchData(UserEntity data) {
        return null;
    }


    @Override
    public List<UserEntity> showData() {
        ObservableList<UserEntity> uList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM user;";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()){
                String user = res.getString("username");
                String pass = res.getString("password");
                UserEntity c = new UserEntity();
                c.setUsername(user);
                c.setPassword(pass);
                uList.add(c);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return uList;
    }

    public UserEntity Login(UserEntity data){
        UserEntity u = new UserEntity();

        try {
            String query = "SELECT * FROM user Where username=? and password=?";

            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1,data.getUsername());
            ps.setString(2,data.getPassword());
            ResultSet res= ps.executeQuery();
            while (res.next()){
                int id = res.getInt("idpengguna");
                String nama = res.getString("nama");
                String username = res.getString("username");
                String password = res.getString("password");

                u.setIdpengguna(id);
                u.setNama(nama);
                u.setUsername(username);
                u.setPassword(password);

            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return u;
    }
}
