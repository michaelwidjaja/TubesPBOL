package DAO;

import Model.Enemy;
import Util.JDBCConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EnemyDao implements daoInterface<Enemy>{

    @Override
    public int addData(Enemy data) {
        return 0;
    }

    @Override
    public int delData(Enemy data) {
        return 0;
    }

    @Override
    public int updateData(Enemy data) {
        return 0;
    }

    @Override
    public List<Enemy> showData() {
        ObservableList<Enemy> eList = FXCollections.observableArrayList();
        try {
            String query = "SELECT *  FROM enemymonster ";
            PreparedStatement ps;
            ps = JDBCConnection.getConnection().prepareStatement(query);
            ResultSet res = ps.executeQuery();
            while (res.next()) {
                int id = res.getInt("Id");
                String name = res.getString("Name");
                int hp = res.getInt("HP");
                int att = res.getInt("attack");
                int elements_id = res.getInt("Elements_id");
                Enemy eM = new Enemy();
                eM.setId(id);
                eM.setAttack(att);
                eM.setName(name);
                eM.setHP(hp);
                eM.setElement(elements_id);


            }
        }
        catch (SQLException ex){
            System.out.println(ex.getMessage());
        }
        return eList;
    }

}
