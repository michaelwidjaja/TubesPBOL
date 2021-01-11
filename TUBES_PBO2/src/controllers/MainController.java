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
    public Button basic1;
    public Button basic2;
    public Button basic3;
    private LoginController main;
    public int id;
    private Monster monster1;
    private Monster monster2;
    private Monster monster3;
    private Enemy enemy1;
    private Enemy enemy2;
    private Enemy enemy3;
    String p = new String();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        int rand1 = getRandomenemy();
        int rand2 = getRandomenemy();
        int rand3 = getRandomenemy();
        enemy1 = new Enemy();
        enemy1.setId(rand1);
        EnemyDao dao1 = new EnemyDao();
        enemy1 = dao1.search(enemy1);

        enemy2 = new Enemy();
        enemy2.setId(rand2);
        EnemyDao dao2 = new EnemyDao();
        enemy2 = dao2.search(enemy2);

        enemy3 = new Enemy();
        enemy3.setId(rand3);
        EnemyDao dao3 = new EnemyDao();
        enemy3 = dao3.search(enemy3);

    }

    public void setmain(LoginController main) {
        this.main = main;
        MonsterDao mDao = new MonsterDao();
        Monster m = new Monster();
        m.setUser_idpengguna(main.iduser);
        ObservableList<Monster> mList = (ObservableList<Monster>) mDao.showDataselect(m);
        comboMonster1.setItems(mList);
        comboMonster2.setItems(mList);
        comboMonster3.setItems(mList);
        id = main.iduser;
        System.out.println(id);
    }

    public int getRandomenemy() {
        EnemyDao eDao = new EnemyDao();
        ObservableList<Enemy> eList = (ObservableList<Enemy>) eDao.showData();
        int x = 1;
        int y = eList.size();
        int random = ThreadLocalRandom.current().nextInt(x, y);
        return random;
    }

    public void cmbMonster1(ActionEvent actionEvent) {
        basic1.setDisable(false);
        textHistory.setText("");
        if(enemy1==null){
            int rand1 = getRandomenemy();
            enemy1 = new Enemy();
            enemy1.setId(rand1);
            EnemyDao dao1 = new EnemyDao();
            enemy1 = dao1.search(enemy1);
        }
        System.out.println("Ini nomor enenmy monster = "+enemy1.getId());
        System.out.println("Ini attacknya = "+ enemy1.getAttack());
        monster1 = new Monster();
        monster1 = comboMonster1.getValue();
        lblHp1.setText(String.valueOf(monster1.getAtt()));

        double elM=cekElemen(monster1.getElement(),enemy1.getElement());
        double elE=cekElemen(enemy1.getElement(),monster1.getElement());
        int m1= (int) (monster1.getAtt()*elM);
        int e1= (int) (enemy1.getAttack()*elE);
        monster1.setAtt(m1);
        enemy1.setAttack(e1);
        System.out.println("Ini attacknya setelah di ubah = "+ enemy1.getAttack());
        System.out.println(monster1.getHp());
        System.out.println(enemy1.getHP());
        comboMonster1.setDisable(true);

    }

    public void fightAction1(ActionEvent actionEvent) {
        monster1 = comboMonster1.getValue();
        if (monster1 != null) {
            if (monster1.getHp() > 0 && enemy1.getHP()>0) {
                p += "Hp monster1: " + String.valueOf(monster1.getHp()) + "\n";
                p += "Hp enemy1: " + String.valueOf(enemy1.getHP()) + "\n";
                textHistory.setText(p);
                monster1.kenaSerang(enemy1.getAttack());
                enemy1.kenaSerang(monster1.getAtt());
                textHistory.setText(p);
            }
            else if(monster1.getHp() <= 0 && enemy1.getHP()>0){
                p += "Kalah \n";
                System.out.println("Kalah");
                comboMonster1.setDisable(false);
                enemy1=null;
                monster1=null;
                basic1.setDisable(true);
                textHistory.setText(p);
                p="";
            }
            else if(monster1.getHp() > 0 && enemy1.getHP()<=0){
                System.out.println("Menang");
                p += "Menang \n";
                comboMonster1.setDisable(false);
                enemy1=null;
                monster1=null;
                basic1.setDisable(true);
                textHistory.setText(p);
                p="";
            }

        }
    }


    public void cmbMonster2(ActionEvent actionEvent) {
        monster2 = new Monster();
        monster2 = comboMonster2.getValue();
        System.out.println("Att");
        System.out.println(monster2.getAtt());
        lblHp2.setText(String.valueOf(monster2.getAtt()));
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
        Optional<String> result = dialog.showAndWait();
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
        monster1=null;
        monster2=null;
        monster3=null;
        enemy1=null;
        enemy2=null;
        enemy3=null;
        stage.close();

        Stage new_stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainView.fxml"));
        Parent root = loader.load();
        LoginController controler = loader.getController();
        controler.setmain(this);
        controler.iduser=0;
        Scene scene1 = new Scene(root);

        new_stage.setScene(scene1);
        new_stage.setTitle("");
        new_stage.show();
    }

    public Double cekElemen(int m, int e) {
        double d=0.8;
        switch (m) {
            case 1: //es
                switch (e) {
                    case 2: //api
                    case 4: //batu
                    case 5: //listrik
                        d=0.5;
                        break;

                    case 6: //air
                        d= 2.0;
                    break;

                    case 3: //angin
                        d=1.0;
                    break;
                }
                break;
            case 2: //api
                switch (e) {
                    case 1: //es
                    case 3: //angin
                        d= 2.0;
                    break;

                    case 6: //air
                    case 4: //batu
                        d= 0.5;
                    break;

                    case 5: //listrik
                        d= 1.0;
                    break;
                }
                break;
            case 3: //angin
                switch (e) {
                    case 1: //es
                    case 4: //batu
                    case 5: //listrik
                        d= 2.0;
                    break;

                    case 2: //api
                        d= 0.5;
                    break;

                    case 6: //air
                        d= 1.0;
                    break;
                }
                break;

            case 4: //batu
                switch (e) {
                    case 6: //air
                        d= 2.0;
                    break;

                    case 5: //listrik
                        d= 0.5;
                    break;

                    case 1: //es
                    case 2: //api
                    case 3: //angin
                        d= 1.0;
                    break;
                }
                break;

            case 5: //listrik
                switch (e) {
                    case 1: //es
                    case 6: //air
                        d= 2.0;
                    break;

                    case 2: //api
                    case 3: //angin
                        d= 1.0;
                    break;

                    case 4: //batu
                        d= 0.5;
                    break;
                }
                break;

            case 6: //air
                switch (e) {
                    case 2: //api
                    case 4: //batu
                        d= 2.0;
                    break;

                    case 1: //es
                    case 5: //listrik
                        d= 0.5;
                    break;

                    case 3: //angin
                        d= 1.0;
                    break;
                }
                break;
        }
        return d;
    }
}
