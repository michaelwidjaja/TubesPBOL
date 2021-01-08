package controllers;

import DAO.ElemenDao;
import DAO.MonsterDao;
import Model.Elemen;
import Model.Monster;
import Model.UserEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.io.IOException;

public class AddMonsterController {
    public ComboBox<Elemen> cmbElemenBaru;
    public TextField txtNamaMonsterBaru;
    public Button btnTmbhMonster;
    private MainController main;
    private LoginController log;

    public void setmain(MainController main) {
        this.main=main;
        ElemenDao elemenDao = new ElemenDao();
        ObservableList<Elemen> eList = (ObservableList<Elemen>) elemenDao.showData();
        cmbElemenBaru.setItems(eList);
    }

    public void addMonsterBaru(ActionEvent actionEvent) {
        Monster m = new Monster();
        m.setName(txtNamaMonsterBaru.getText());
        m.setHp((int)Math.floor(Math.random() * 3000) + 1000);
        m.setAtt((int)Math.floor(Math.random() * 3000) + 1000);

        m.setElement(cmbElemenBaru.getValue().getId());
        System.out.println(m.getElement());
        MonsterDao monsterDao = new MonsterDao();
//        monsterDao.addData(m);
    }


}
