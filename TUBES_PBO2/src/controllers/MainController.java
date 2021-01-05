package controllers;

import DAO.ElemenDao;
import DAO.MonsterDao;
import Model.Elemen;
import Model.Monster;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
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
        Monster m =new Monster();
        m.setUser_idpengguna(main.iduser);
        ObservableList<Monster> mList = (ObservableList<Monster>) mDao.showDataselect(m);
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
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add new element");
        dialog.setHeaderText("Confirmation");
        dialog.setContentText("Element baru:");
        String result = String.valueOf(dialog.showAndWait());
        Elemen e = new Elemen();
        e.setName(String.valueOf(result));
        ElemenDao elemenDao = new ElemenDao();
        elemenDao.addData(e);
    }

    public void comboAction3(ActionEvent actionEvent) {
    }

    public void fightAction3(ActionEvent actionEvent) {
    }

    public void cmbMonster3(ActionEvent actionEvent) {
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
