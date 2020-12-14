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
        return 0;
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
    public boolean searchData(UserEntity data) {
        ObservableList<UserEntity> mList = FXCollections.observableArrayList();
        Boolean temp = false;
        try {
            String query = "SELECT * FROM user Where username=? or password=?";
            
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1,data.getUsername());
            ps.setString(2,data.getPassword());
            ResultSet res= ps.executeQuery();
           
            if(res.next()) {
                temp=true;
            }
            else{
                temp=false;
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return temp;
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
}