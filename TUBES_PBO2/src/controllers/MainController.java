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
import javafx.fxml.FXML;
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
import java.util.concurrent.ThreadLocalRandom;

public class MainController implements Initializable {
    public GridPane mainMonster;
    public Label lblHp1;
    public ComboBox<Monster> comboMonster1;
    public Label lblHp2;
    public ComboBox<Monster> comboMonster2;
    public Label lblHp3;
    public ComboBox<Monster> comboMonster3;
    public TextArea textHistory;
    public Button btnAddEnemy;
    public Button btnLogout;
    private LoginController main;
    public int id;
    public TextArea historytext;
    private Monster monster1;
    private Monster monster2;
    private Monster monster3;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int rand1=getRandomenemy();
        int rand2=getRandomenemy();
        int rand3=getRandomenemy();
        enemy1=new Enemy();
        enemy1.setId(rand1);
        EnemyDao dao1=new EnemyDao();
        enemy1=dao1.search(enemy1);

        enemy2=new Enemy();
        enemy2.setId(rand2);
        EnemyDao dao2=new EnemyDao();
        enemy2=dao2.search(enemy2);

        enemy3=new Enemy();
        enemy3.setId(rand3);
        EnemyDao dao3=new EnemyDao();
        enemy3=dao3.search(enemy3);
    }

    public void setmain(LoginController main) {
        this.main = main;
        MonsterDao mDao = new MonsterDao();
        Monster m =new Monster();
        m.setUser_idpengguna(main.iduser);
        ObservableList<Monster> mList = (ObservableList<Monster>) mDao.showDataselect(m);
        comboMonster1.setItems(mList);
        comboMonster2.setItems(mList);
        comboMonster3.setItems(mList);
        id =main.iduser;
        System.out.println(id);
    }
    public int getRandomenemy(){
        EnemyDao eDao = new EnemyDao();
        ObservableList<Enemy> eList = (ObservableList<Enemy>) eDao.showData();
        int x=1;
        int y=eList.size();
        int random = ThreadLocalRandom.current().nextInt(x,y);
        return random;
    }

    public void cmbMonster1(ActionEvent actionEvent) {
        monster1 = comboMonster1.getValue();
        System.out.println("Att");
        System.out.println(monster1.getAtt());
        lblHp1.setText(String.valueOf(monster1.getAtt()));
        System.out.println("random");
        System.out.println(getRandomenemy());
    }

    public void fightAction1(ActionEvent actionEvent)    {
        monster1 = comboMonster1.getValue();
        historytext.setText("Hp monster: "+String.valueOf(monster1.getHp()));
        monster1.kenaSerang(enemy1.getAttack());
        historytext.setText("Hp monster: "+String.valueOf(monster1.getHp()));
        System.out.println("Attack kita"+monster1.getAtt());
        System.out.println("Attack enemy"+enemy1.getAttack());

    }

//    public int getRandomDamage(Monster monster){
//        return (int)Math.floor(Math.random()*monster.getAtt()+5);
//    }
//    public int getRandomComboDamage(Monster monster){
//        return (int)Math.floor(Math.random()*monster.getAtt()*2)+5;
//    }
//
//    public void attackMonster(Monster monster){
//        Random random = new Random();
//        int x = random.nextInt(3);
//        int dmg = getRandomDamage(monster);
//        if (x==0){
//            enemy1.kenaSerang(dmg);
//            historytext.appendText("/n"+monster.getName()+" hit "+enemy1.getName()+" with "+dmg+" damage");
//        }
//        else if (x==1){
//            enemy2.kenaSerang(dmg);
//            historytext.appendText("/n"+monster.getName()+" hit "+enemy2.getName()+" with "+dmg+" damage");
//        }
//        else {
//            enemy3.kenaSerang(dmg);
//            historytext.appendText("/n"+monster.getName()+" hit "+enemy3.getName()+" with "+dmg+" damage");
//        }
//    }
//
//    public void comboAttack(Monster monster){
//        Random random = new Random();
//        int x = random.nextInt(3);
//        if (x==0){
//            enemy1.kenaSerang(getRandomComboDamage(monster));
//        }
//        else if (x==1){
//            enemy2.kenaSerang(getRandomComboDamage(monster));
//        }
//        else {
//            enemy3.kenaSerang(getRandomComboDamage(monster));
//        }
//    }



    public void cmbMonster2(ActionEvent actionEvent) {
        monster2 = comboMonster2.getValue();
    }

    public void cmbMonster3(ActionEvent actionEvent) {
        monster3 = comboMonster3.getValue();
    }



    public void fightAction2(ActionEvent actionEvent) {

    }

    public void fightAction3(ActionEvent actionEvent) {

    }

    public void comboAttack1(ActionEvent actionEvent) {
//        comboAttack(monster1);
    }

    public void comboAttack2(ActionEvent actionEvent) {
//        comboAttack(monster2);
    }

    public void comboAttack3(ActionEvent actionEvent) {
//        comboAttack(monster3);
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




    public void addEnemy(ActionEvent actionEvent) throws IOException {
        Stage new_stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/addEnemy.fxml"));
        Parent root = loader.load();
        AddEnemyController controler = loader.getController();
        controler.setmain(this);
        Scene scene1 = new Scene(root);

        new_stage.setScene(scene1);
        new_stage.initModality(Modality.WINDOW_MODAL);
        new_stage.initOwner(mainMonster.getScene().getWindow());
        new_stage.setTitle("Add Enemy Monster");
        new_stage.showAndWait();
    }

    public void Logout(ActionEvent actionEvent) throws IOException {
        Stage stage = (Stage) btnLogout.getScene().getWindow();
        stage.close();

        Stage new_stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainView.fxml"));
        Parent root = loader.load();
        LoginController controler = loader.getController();
        controler.setmain(this);
        Scene scene1 = new Scene(root);

        new_stage.setScene(scene1);
        new_stage.setTitle("");
        new_stage.show();
    }
}
