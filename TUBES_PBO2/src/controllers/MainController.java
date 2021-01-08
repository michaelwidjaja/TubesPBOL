package controllers;

import DAO.EnemyDao;
import Model.Enemy;
import DAO.ElemenDao;
import DAO.MonsterDao;
import Model.Elemen;
import Model.Monster;
import Model.UserEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.Random;
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
    public int id;
    private Monster monster1;
    private Monster monster2;
    private Monster monster3;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;


    public void setmain(LoginController main) {
        this.main = main;
        MonsterDao mDao = new MonsterDao();
        Monster m =new Monster();
        m.setUser_idpengguna(main.iduser);
        ObservableList<Monster> mList = (ObservableList<Monster>) mDao.showDataselect(m);
        cmbMonster1.setItems(mList);
        cmbMonster2.setItems(mList);
        cmbMonster3.setItems(mList);
        id =main.iduser;
        System.out.println(id);
    }

    public int getRandomDamage(Monster monster){
        return (int)Math.floor(Math.random()*monster.getAtt())+5;
    }
    public int getRandomComboDamage(Monster monster){
        return (int)Math.floor(Math.random()*monster.getAtt()*2)+5;
    }

    public void attackMonster(Monster monster){
        Random random = new Random();
        int x = random.nextInt(3);
        if (x==0){
            enemy1.kenaSerang(getRandomDamage(monster));
        }
        else if (x==1){
            enemy2.kenaSerang(getRandomDamage(monster));
        }
        else {
            enemy3.kenaSerang(getRandomDamage(monster));
        }
    }

    public void comboAttack(Monster monster){
        Random random = new Random();
        int x = random.nextInt(3);
        if (x==0){
            enemy1.kenaSerang(getRandomComboDamage(monster));
        }
        else if (x==1){
            enemy2.kenaSerang(getRandomComboDamage(monster));
        }
        else {
            enemy3.kenaSerang(getRandomComboDamage(monster));
        }
    }

    public void cmbMonster1(ActionEvent actionEvent) {
    }

    public void cmbMonster2(ActionEvent actionEvent) {
    }

    public void cmbMonster3(ActionEvent actionEvent) {
    }

    public void fightAction1(ActionEvent actionEvent) {
        attackMonster(monster1);
    }

    public void fightAction2(ActionEvent actionEvent) {
        attackMonster(monster2);
    }

    public void fightAction3(ActionEvent actionEvent) {
        attackMonster(monster3);
    }

    public void comboAction1(ActionEvent actionEvent) {
        comboAttack(monster1);
    }

    public void comboAction2(ActionEvent actionEvent) {
        comboAttack(monster2);
    }

    public void comboAction3(ActionEvent actionEvent) {
        comboAttack(monster3);
    }

    public void addNewMonster(ActionEvent actionEvent) throws IOException {
        Stage new_stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/addMonster.fxml"));
        Parent root = loader.load();
        AddMonsterController controler = loader.getController();
        controler.setmain(this);
        Scene scene1 = new Scene(root);

        new_stage.setScene(scene1);
        new_stage.initModality(Modality.WINDOW_MODAL);
        new_stage.initOwner(mainMonster.getScene().getWindow());
        new_stage.setTitle("Add Monster Baru");
        new_stage.showAndWait();
    }

    public void addElement(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Add new element");
        dialog.setHeaderText("Confirmation");
        dialog.setContentText("Element baru:");
        Optional <String> result = dialog.showAndWait();
        TextField input = dialog.getEditor();
        Elemen e = new Elemen();
        e.setName(input.getText());
        ElemenDao elemenDao = new ElemenDao();
        elemenDao.addData(e);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
