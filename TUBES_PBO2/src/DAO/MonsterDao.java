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
        return 0;
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
            String query = "SELECT m.*,e.Name AS namael FROM monsters m JOIN elements e ON e.Id=m.Elements_Id ";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res= ps.executeQuery();
            while (res.next()){
                int id = res.getInt("Id");
                String name= res.getString("Name");
                int hp =res.getInt("HP");
                int elements_id =res.getInt("Elements_id");
                String elements_nama =res.getString("namael");
                Monster m= new Monster();
                m.setId(id);
                m.setName(name);
                m.setHp(hp);
                m.setElement(elements_id);
                m.setNamaelement(elements_nama);
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
            String query = "SELECT m.*,e.Name AS namael FROM monsters m JOIN elements e ON e.Id=m.Elements_Id Where User_idpengguna=?";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ps.setInt(1,data.getUser_idpengguna());
            ResultSet res= ps.executeQuery();
            while (res.next()){
                int id = res.getInt("Id");
                String name= res.getString("Name");
                int hp =res.getInt("HP");
                int elements_id =res.getInt("Elements_id");
                String elements_nama =res.getString("namael");
                Monster m= new Monster();
                m.setId(id);
                m.setName(name);
                m.setHp(hp);
                m.setElement(elements_id);
                m.setNamaelement(elements_nama);
                mList.add(m);

            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return mList;
    }
}
