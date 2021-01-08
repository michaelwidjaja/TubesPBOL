package controllers;

import DAO.UserDao;
import Model.Monster;
import Model.UserEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sun.security.provider.MD5;

import javax.swing.*;
import java.io.IOException;

public class LoginController {


    public PasswordField txtPassword;
    public TextField txtUsername;
    public Label lblErrors;
    public Button signin;
    public int iduser=0;

    public void btnSignin(ActionEvent actionEvent) throws IOException {
        String user= txtUsername.getText();
        String pass = txtPassword.getText();
        txtUsername.setText("");
        txtPassword.setText("");
        UserEntity u = new UserEntity();
        u.setUsername(user);
        u.setPassword(pass);
        UserDao udao=new UserDao();
        UserEntity user1=udao.Login(u);

        System.out.println(user1.getIdpengguna());
        System.out.println(user1.getNama());
        iduser=user1.getIdpengguna();
        System.out.println(iduser);
        Monster m =new Monster();
        m.setUser_idpengguna(iduser);
        if (user.equals("")||pass.equals("")){
            JOptionPane.showMessageDialog(null,"Please fill Username and password");
        }
        else{
            if(iduser!=0 ){
                Stage stage = (Stage) signin.getScene().getWindow();
                stage.close();
                System.out.println("Login Success");
                Stage new_stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/sample.fxml"));
                Parent root = loader.load();
                MainController controler = loader.getController();
                controler.setmain(this);
                Scene scene1 = new Scene(root);

                new_stage.setScene(scene1);
                new_stage.setTitle("");
                new_stage.show();
            }
            else{
                lblErrors.setText("Invalid username or password");
            }
        }

    }
    public void btnSignup(ActionEvent actionEvent) throws IOException {
        Stage new_stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/signup.fxml"));
        Parent root = loader.load();
        SignUpController controler = loader.getController();
        controler.setmain(this);
        Scene scene1 = new Scene(root);

        new_stage.setScene(scene1);
        new_stage.setTitle("Sign Up Dialog");
        new_stage.showAndWait();

    }
}
