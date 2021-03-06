package controllers;

import DAO.ElemenDao;
import DAO.EnemyDao;
import DAO.MonsterDao;
import Model.Elemen;
import Model.Enemy;
import Model.Monster;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddEnemyController {
    public TextField txtNamaEnemyBaru;
    public ComboBox<Elemen> txtCmbNewElement;
    public Button btnTmbhEnemy;
    private MainController main;
    private int id;

    public void setmain(MainController main) {
        this.main=main;
        id= main.id;
        ElemenDao elemenDao = new ElemenDao();
        ObservableList<Elemen> eList = (ObservableList<Elemen>) elemenDao.showData();
        txtCmbNewElement.setItems(eList);
    }

    public void addEnemyBaru(ActionEvent actionEvent) {
        Enemy m = new Enemy();
        m.setName(txtNamaEnemyBaru.getText());
        m.setHP((int)Math.floor(Math.random() * 100));
        m.setAttack((int)Math.floor(Math.random() * 50) + 5);
        m.setElement(txtCmbNewElement.getValue().getId());
        System.out.println(m.getElement());
        EnemyDao monsterDao = new EnemyDao();
        int res =monsterDao.addData(m);
        System.out.println("Ini hasil " + res);
        if (res!=0){
            Stage stage = (Stage) btnTmbhEnemy.getScene().getWindow();
            stage.close();
        }
    }
}
