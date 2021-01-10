package DAO;

import Model.Monster;
import Util.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MonsterDao implements daoInterface<Monster>{
    @Override
    public int addData(Monster data) {
        int result = 0;
        try {
            String query = "INSERT INTO monsters(name,hp,attack,elements_id,User_idpengguna) values(?,?,?,?,?);";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setString(1,data.getName());
            ps.setInt(2,data.getHp());
            ps.setInt(3,data.getAtt());
            ps.setInt(4,data.getElement());
            ps.setInt(5,data.getUser_idpengguna());
            result = ps.executeUpdate();
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return result;
    }

    @Override
    public int delData(Monster data) {
        return 0;
    }

    @Override
    public int updateData(Monster data) {
        return 0;
    }

    @Override
    public List<Monster> showData() {
        ObservableList<Monster> mList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM monsters";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res= ps.executeQuery();
            while (res.next()){
                int id = res.getInt("Id");
                String name= res.getString("Name");
                int hp =res.getInt("HP");
                int elements_id =res.getInt("Elements_id");
                int User_idpengguna =res.getInt("User_idpengguna");
                Monster m= new Monster();
                m.setId(id);
                m.setName(name);
                m.setHp(hp);
                m.setElement(elements_id);
                m.setUser_idpengguna(User_idpengguna);
                mList.add(m);

            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return mList;
    }
    public List<Monster> showDataselect(Monster data) {
        ObservableList<Monster> mList = FXCollections.observableArrayList();

        try {
            String query = "SELECT * FROM monsters  Where User_idpengguna=?";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getUser_idpengguna());
            ResultSet res= ps.executeQuery();
            while (res.next()){
                int id = res.getInt("Id");
                String name= res.getString("Name");
                int hp =res.getInt("HP");
                int att =res.getInt("attack");
                int elements_id =res.getInt("Elements_id");
                int User_idpengguna =res.getInt("User_idpengguna");
                Monster m= new Monster();
                m.setId(id);
                m.setAtt(att);
                m.setName(name);
                m.setHp(hp);
                m.setElement(elements_id);
                m.setUser_idpengguna(User_idpengguna);
                mList.add(m);
            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return mList;
    }
}
