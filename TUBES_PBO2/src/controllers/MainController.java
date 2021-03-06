package controllers;

import DAO.EnemyDao;
import DAO.HistoryDao;
import Model.*;
import DAO.ElemenDao;
import DAO.MonsterDao;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

public class MainController implements Initializable {
    public AnchorPane mainMonster;
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
    public Button btnCombo1;
    public Button btnCombo2;
    public Button btnCombo3;
    public Button btnHistory;
    public Label lblHealth1;
    public Label lblHealth2;
    public Label lblHealth3;
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
        enemy1.setHPSementara(enemy1.getHP());

        enemy2 = new Enemy();
        enemy2.setId(rand2);
        EnemyDao dao2 = new EnemyDao();
        enemy2 = dao2.search(enemy2);
        enemy2.setHPSementara(enemy2.getHP());

        enemy3 = new Enemy();
        enemy3.setId(rand3);
        EnemyDao dao3 = new EnemyDao();
        enemy3 = dao3.search(enemy3);
        enemy3.setHPSementara(enemy3.getHP());

        basic1.setDisable(true);
        btnCombo1.setDisable(true);
        basic2.setDisable(true);
        btnCombo2.setDisable(true);
        basic3.setDisable(true);
        btnCombo3.setDisable(true);

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
        btnCombo1.setDisable(false);
        textHistory.setText("");
        if (enemy1 == null) {
            int rand1 = getRandomenemy();
            enemy1 = new Enemy();
            enemy1.setId(rand1);
            EnemyDao dao1 = new EnemyDao();
            enemy1 = dao1.search(enemy1);
            enemy1.setHPSementara(enemy1.getHP());
        }
        System.out.println("Ini nomor enenmy monster = " + enemy1.getId());
        System.out.println("Ini attacknya = " + enemy1.getAttack());
        monster1 = new Monster();
        monster1 = comboMonster1.getValue();
        monster1.setHpsementara(monster1.getHp());
        lblHp1.setText(String.valueOf(monster1.getAttfix()));
        lblHealth1.setText(String.valueOf(monster1.getHp()));

        double elM = cekElemen(monster1.getElement(), enemy1.getElement());
        double elE = cekElemen(enemy1.getElement(), monster1.getElement());
        int m1 = (int) (monster1.getAttfix() * elM);
        int e1 = (int) (enemy1.getAttack() * elE);
        monster1.setAtt(m1);
        enemy1.setAttack(e1);
        System.out.println("Ini attacknya setelah di ubah = " + enemy1.getAttack());
        System.out.println(monster1.getHp());
        System.out.println(enemy1.getHP());
        comboMonster1.setDisable(true);
        p="";

    }

    public int cekhp(int hp) {
        if (hp <= 0) {
            return hp = 0;
        } else {
            return hp;
        }
    }

    public void fight(Monster m, Enemy e) {
        p += "Hp " + m.getName() + " : " + String.valueOf(m.getHpsementara()) + "\n";
        p += "Hp " + e.getName() + " : " + String.valueOf(e.getHPSementara()) + "\n";
        m.kenaSerang(e.getAttack());
        e.kenaSerang(m.getAtt());
        p += m.getName() + " terkena serangan sebesar " + String.valueOf(e.getAttack()) + " " + e.getName() + "\n";
        p += e.getName() + " terkena serangan sebesar " + String.valueOf(m.getAtt()) + " " + m.getName() + "\n";
        int hpm1 = cekhp(m.getHpsementara());
        m.setHpsementara(hpm1);
        int hpe1 = cekhp(e.getHPSementara());
        e.setHPSementara(hpe1);

        p += "Hp " + m.getName() + " setelah diserang: " + String.valueOf(m.getHpsementara()) + "\n";
        p += "Hp " + e.getName() + " setelah diserang: " + String.valueOf(e.getHPSementara()) + "\n \n";
        textHistory.setText(p);
    }

    public void fightAction1(ActionEvent actionEvent) {
        monster1 = new Monster();
        monster1 = comboMonster1.getValue();
        while (monster1.getHpsementara() > 0 && enemy1.getHPSementara() > 0) {
            fight(monster1, enemy1);
            break;
        }
        if (monster1.getHpsementara() <= 0 || enemy1.getHPSementara() <= 0) {
            if (monster1.getHpsementara() <= 0) {
                System.out.println("Kalah");
                basic1.setDisable(true);
                btnCombo1.setDisable(true);
                comboMonster1.setDisable(false);
                p += "Kalah \n";
                addHistory("Kalah");
            } else {
                System.out.println("Menang");
                basic1.setDisable(true);
                btnCombo1.setDisable(true);
                comboMonster1.setDisable(false);
                p += "Menang \n";

                addHistory("Menang");
            }
            enemy1 = null;
            monster1 = null;
            textHistory.setText(p);
            p = "";
        }
    }

    public void cmbMonster2(ActionEvent actionEvent) {
        basic2.setDisable(false);
        btnCombo2.setDisable(false);
        textHistory.setText("");
        if (enemy2 == null) {
            int rand2 = getRandomenemy();
            enemy2 = new Enemy();
            enemy2.setId(rand2);
            EnemyDao dao2 = new EnemyDao();
            enemy2 = dao2.search(enemy2);
            enemy2.setHPSementara(enemy2.getHP());
        }
        System.out.println("Ini nomor enenmy monster = " + enemy2.getId());
        System.out.println("Ini attacknya = " + enemy2.getAttack());
        monster2 = new Monster();
        monster2 = comboMonster2.getValue();
        monster2.setHpsementara(monster2.getHp());
        lblHp2.setText(String.valueOf(monster2.getAttfix()));
        lblHealth2.setText(String.valueOf(monster2.getHp()));

        double elM = cekElemen(monster2.getElement(), enemy2.getElement());
        double elE = cekElemen(enemy2.getElement(), monster2.getElement());
        int m2 = (int) (monster2.getAttfix() * elM);
        int e2 = (int) (enemy2.getAttack() * elE);
        monster2.setAtt(m2);
        enemy2.setAttack(e2);
        System.out.println("Ini attacknya setelah di ubah = " + enemy1.getAttack());
        System.out.println(monster2.getHp());
        System.out.println(enemy2.getHP());
        comboMonster2.setDisable(true);
        p="";
    }

    public void fightAction2(ActionEvent actionEvent) {
        monster2 = comboMonster2.getValue();
        while (monster2.getHpsementara() > 0 && enemy2.getHPSementara() > 0) {
            fight(monster2, enemy2);
            break;
        }
        if (monster2.getHpsementara() <= 0 || enemy2.getHPSementara() <= 0) {
            if (monster2.getHpsementara() <= 0) {
                System.out.println("Kalah");
                basic2.setDisable(true);
                btnCombo2.setDisable(true);
                comboMonster2.setDisable(false);
                p += "Kalah \n";
                addHistory("Kalah");
            } else {
                System.out.println("Menang");
                basic2.setDisable(true);
                btnCombo2.setDisable(true);
                comboMonster2.setDisable(false);
                p += "Menang \n";

                addHistory("Menang");
            }
            enemy2 = null;
            monster2 = null;
            textHistory.setText(p);
            p = "";
        }
    }


    public void cmbMonster3(ActionEvent actionEvent) {
        basic3.setDisable(false);
        btnCombo3.setDisable(false);
        textHistory.setText("");
        if (enemy3 == null) {
            int rand3 = getRandomenemy();
            enemy3 = new Enemy();
            enemy3.setId(rand3);
            EnemyDao dao3 = new EnemyDao();
            enemy3 = dao3.search(enemy3);
            enemy3.setHPSementara(enemy3.getHP());
        }
        System.out.println("Ini nomor enenmy monster = " + enemy3.getId());
        System.out.println("Ini attacknya = " + enemy3.getAttack());
        monster3 = new Monster();
        monster3 = comboMonster3.getValue();
        monster3.setHpsementara(monster3.getHp());
        lblHp3.setText(String.valueOf(monster3.getAttfix()));
        lblHealth3.setText(String.valueOf(monster3.getHp()));

        double elM = cekElemen(monster3.getElement(), enemy3.getElement());
        double elE = cekElemen(enemy3.getElement(), monster3.getElement());
        int m3 = (int) (monster3.getAttfix() * elM);
        int e3 = (int) (enemy3.getAttack() * elE);
        monster3.setAtt(m3);
        enemy3.setAttack(e3);
        System.out.println("Ini attacknya setelah di ubah = " + enemy3.getAttack());
        System.out.println(monster3.getHp());
        System.out.println(enemy3.getHP());
        comboMonster3.setDisable(true);
        p="";
    }


    public void fightAction3(ActionEvent actionEvent) {
        monster3 = comboMonster3.getValue();
        System.out.println(monster3);
        while (monster3.getHpsementara() > 0 && enemy3.getHPSementara() > 0) {
            fight(monster3, enemy3);
            break;
        }
        if (monster3.getHpsementara() <= 0 || enemy3.getHPSementara() <= 0) {
            if (monster3.getHpsementara() <= 0) {
                System.out.println("Kalah");
                basic3.setDisable(true);
                btnCombo3.setDisable(true);
                comboMonster3.setDisable(false);
                p += "Kalah \n";
                addHistory("Kalah");
                enemy3 = null;
                monster3 = null;
            } else {
                System.out.println("Menang");
                basic3.setDisable(true);
                btnCombo3.setDisable(true);
                comboMonster3.setDisable(false);
                p += "Menang \n";

                addHistory("Menang");
                enemy3 = null;
                monster3 = null;
            }

            textHistory.setText(p);
            p = "";
        }
    }

    public void combo(Monster m , Enemy e){
        p += "Hp " + m.getName() + " : " + String.valueOf(m.getHpsementara()) + "\n";
        p += "Hp " + e.getName() + " : " + String.valueOf(e.getHPSementara()) + "\n";
        m.kenaSerang(e.getAttack());
        e.kenaSerang((int) (m.getAtt() * 1.5));
        p += m.getName() + " terkena serangan sebesar " + String.valueOf(e.getAttack()) + " " + e.getName() + "\n";
        p += e.getName() + " terkena serangan sebesar " + String.valueOf(m.getAtt()) + " " + m.getName() + "\n";
        int hpm1 = cekhp(m.getHpsementara());
        m.setHpsementara(hpm1);
        int hpe1 = cekhp(e.getHPSementara());
        e.setHPSementara(hpe1);

        p += "Hp " + m.getName() + " setelah diserang: " + String.valueOf(m.getHpsementara()) + "\n";
        p += "Hp " + e.getName() + " setelah diserang: " + String.valueOf(e.getHPSementara()) + "\n \n";
        textHistory.setText(p);
    }

    public void comboAttack1(ActionEvent actionEvent) {
        monster1 = new Monster();
        monster1 = comboMonster1.getValue();
        while (monster1.getHpsementara() > 0 && enemy1.getHPSementara() > 0) {
            combo(monster1, enemy1);
            break;
        }
        if (monster1.getHpsementara() <= 0 || enemy1.getHPSementara() <= 0) {
            if (monster1.getHpsementara() <= 0) {
                System.out.println("Kalah");
                basic1.setDisable(true);
                btnCombo1.setDisable(true);
                comboMonster1.setDisable(false);
                p += "Kalah \n";
                addHistory("Kalah");
            } else {
                System.out.println("Menang");
                basic1.setDisable(true);
                btnCombo1.setDisable(true);
                comboMonster1.setDisable(false);
                p += "Menang \n";

                addHistory("Menang");
            }
            enemy1 = null;
            monster1 = null;
            textHistory.setText(p);
            p = "";
        }
    }

    public void comboAttack2(ActionEvent actionEvent) {
        monster2 = comboMonster2.getValue();
        while (monster2.getHpsementara() > 0 && enemy2.getHPSementara() > 0) {
            combo(monster2, enemy2);
            break;
        }
        if (monster2.getHpsementara() <= 0 || enemy2.getHPSementara() <= 0) {
            if (monster2.getHpsementara() <= 0) {
                System.out.println("Kalah");
                basic2.setDisable(true);
                btnCombo2.setDisable(true);
                comboMonster2.setDisable(false);
                p += "Kalah \n";
                addHistory("Kalah");
            } else {
                System.out.println("Menang");
                basic2.setDisable(true);
                btnCombo2.setDisable(true);
                comboMonster2.setDisable(false);
                p += "Menang \n";

                addHistory("Menang");
            }
            enemy2 = null;
            monster2 = null;
            textHistory.setText(p);
            p = "";
        }
    }

    public void comboAttack3(ActionEvent actionEvent) {
        monster3 = comboMonster3.getValue();
        System.out.println(monster3);
        while (monster3.getHpsementara() > 0 && enemy3.getHPSementara() > 0) {
            combo(monster3, enemy3);
            break;
        }
        if (monster3.getHpsementara() <= 0 || enemy3.getHPSementara() <= 0) {
            if (monster3.getHpsementara() <= 0) {
                System.out.println("Kalah");
                basic3.setDisable(true);
                btnCombo3.setDisable(true);
                comboMonster3.setDisable(false);
                p += "Kalah \n";
                addHistory("Kalah");
                enemy3 = null;
                monster3 = null;
            } else {
                System.out.println("Menang");
                basic3.setDisable(true);
                btnCombo3.setDisable(true);
                comboMonster3.setDisable(false);
                p += "Menang \n";

                addHistory("Menang");
                enemy3 = null;
                monster3 = null;
            }

            textHistory.setText(p);
            p = "";
        }
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
        if (input.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Error");
            alert.setHeaderText("Element cannot be empty");
            alert.showAndWait();
        } else {
            e.setName(input.getText());
            ElemenDao elemenDao = new ElemenDao();
            elemenDao.addData(e);
        }
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
        monster1 = null;
        monster2 = null;
        monster3 = null;
        enemy1 = null;
        enemy2 = null;
        enemy3 = null;
        stage.close();

        Stage new_stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/MainView.fxml"));
        Parent root = loader.load();
        LoginController controler = loader.getController();
        controler.setmain(this);
        controler.iduser = 0;
        Scene scene1 = new Scene(root);

        new_stage.setScene(scene1);
        new_stage.setTitle("");
        new_stage.show();
    }

    public Double cekElemen(int m, int e) {
        double d = 0.8;
        switch (m) {
            case 1: //es
                switch (e) {
                    case 2: //api
                    case 4: //batu
                    case 5: //listrik
                        d = 0.5;
                        break;

                    case 6: //air
                        d = 2.0;
                        break;

                    case 3: //angin
                        d = 1.0;
                        break;
                }
                break;
            case 2: //api
                switch (e) {
                    case 1: //es
                    case 3: //angin
                        d = 2.0;
                        break;

                    case 6: //air
                    case 4: //batu
                        d = 0.5;
                        break;

                    case 5: //listrik
                        d = 1.0;
                        break;
                }
                break;
            case 3: //angin
                switch (e) {
                    case 1: //es
                    case 4: //batu
                    case 5: //listrik
                        d = 2.0;
                        break;

                    case 2: //api
                        d = 0.5;
                        break;

                    case 6: //air
                        d = 1.0;
                        break;
                }
                break;

            case 4: //batu
                switch (e) {
                    case 6: //air
                        d = 2.0;
                        break;

                    case 5: //listrik
                        d = 0.5;
                        break;

                    case 1: //es
                    case 2: //api
                    case 3: //angin
                        d = 1.0;
                        break;
                }
                break;

            case 5: //listrik
                switch (e) {
                    case 1: //es
                    case 6: //air
                        d = 2.0;
                        break;

                    case 2: //api
                    case 3: //angin
                        d = 1.0;
                        break;

                    case 4: //batu
                        d = 0.5;
                        break;
                }
                break;

            case 6: //air
                switch (e) {
                    case 2: //api
                    case 4: //batu
                        d = 2.0;
                        break;

                    case 1: //es
                    case 5: //listrik
                        d = 0.5;
                        break;

                    case 3: //angin
                        d = 1.0;
                        break;
                }
                break;
        }
        return d;
    }

    public void addHistory(String result) {

//        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
//        Date date = new Date(timestamp.getTime());
//        System.out.println(date);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        HistoryDao hDao = new HistoryDao();
        History h = new History();
        h.setId_user(id);
        h.setTanggal(dateFormat.format(date));
        h.setWinlose(result);

        hDao.addData(h);
    }

    public void openHistory(ActionEvent actionEvent) throws IOException {
        Stage new_stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/showHistory.fxml"));
        Parent root = loader.load();
        HistoryController controler = loader.getController();
        controler.setmain(this);
        Scene scene1 = new Scene(root);

        new_stage.setScene(scene1);
        new_stage.initModality(Modality.WINDOW_MODAL);
        new_stage.initOwner(mainMonster.getScene().getWindow());
        new_stage.setTitle("History");
        new_stage.showAndWait();
    }
}
