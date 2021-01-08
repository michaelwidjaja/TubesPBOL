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

public class AddMonsterController {
    public ComboBox<Elemen> cmbElemenBaru;
    public TextField txtNamaMonsterBaru;
    public Button btnTmbhMonster;
    private MainController main;

    public void setmain(MainController mainController) {
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
//        m.setElement(cmbElemenBaru);

        MonsterDao monsterDao = new MonsterDao();
        monsterDao.addData(m);
    }


}
