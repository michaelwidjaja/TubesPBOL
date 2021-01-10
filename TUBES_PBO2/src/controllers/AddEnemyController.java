package controllers;

import DAO.ElemenDao;
import DAO.MonsterDao;
import Model.Elemen;
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
        Monster m = new Monster();
        m.setName(txtNamaEnemyBaru.getText());
        m.setHp((int)Math.floor(Math.random() * 100));
        m.setAtt((int)Math.floor(Math.random() * 50) + 5);
        m.setElement(txtCmbNewElement.getValue().getId());
        System.out.println(m.getElement());
        MonsterDao monsterDao = new MonsterDao();
        int res =monsterDao.addData(m);
        System.out.println("Ini hasil " + res);
        if (res!=0){
            Stage stage = (Stage) btnTmbhEnemy.getScene().getWindow();
            stage.close();
        }
    }
}
