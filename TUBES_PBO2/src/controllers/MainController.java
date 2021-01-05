package controllers;

import DAO.MonsterDao;
import Model.Monster;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

public class MainController {
    public GridPane mainMonster;
    public Label lblHp1;
    public ComboBox cmbMonster1;
    public Label lblHp2;
    public ComboBox cmbMonster2;
    public Label lblHp3;
    public ComboBox cmbMonster3;
    private LoginController main;

    public void setmain(LoginController main) {
        this.main = main;
        MonsterDao mDao = new MonsterDao();
        ObservableList<Monster> mList = (ObservableList<Monster>) mDao.showData();
        cmbMonster1.setItems(mList);
        cmbMonster2.setItems(mList);
        cmbMonster3.setItems(mList);
    }

    public void comboAction1(ActionEvent actionEvent) {
    }

    public void cmbMonster1(ActionEvent actionEvent) {
    }

    public void fightAction1(ActionEvent actionEvent) {
    }

    public void comboAction2(ActionEvent actionEvent) {
    }

    public void cmbMonster2(ActionEvent actionEvent) {
    }

    public void fightAction2(ActionEvent actionEvent) {
    }

    public void addNewMonster(ActionEvent actionEvent) {
    }

    public void addElement(ActionEvent actionEvent) {
    }

    public void comboAction3(ActionEvent actionEvent) {
    }

    public void fightAction3(ActionEvent actionEvent) {
    }

    public void cmbMonster3(ActionEvent actionEvent) {
    }
}
