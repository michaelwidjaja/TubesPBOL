package controllers;

import DAO.UserDao;
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

    public void btnSignin(ActionEvent actionEvent) throws IOException {
        String user= txtUsername.getText();
        String pass = txtPassword.getText();
        txtUsername.setText("");
        txtPassword.setText("");
        UserEntity u = new UserEntity();
        u.setUsername(user);
        u.setPassword(pass);
        boolean temp=false;
        UserDao udao=new UserDao();
        temp=udao.searchData(u);
        int id=u.getIdpengguna();
        System.out.println(id);
        System.out.println(temp);
        if (user.equals("")||pass.equals("")){
            JOptionPane.showMessageDialog(null,"Please fill Username and password");
        }
        else{
            if(temp==true ){
                Stage stage = (Stage) signin.getScene().getWindow();
                stage.close();
                System.out.println("Login Success");
                id=u.getIdpengguna();
                System.out.println(id);
                Stage new_stage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../View/sample.fxml"));
                Parent root = loader.load();
                MainController controler = loader.getController();
                controler.setmain(this);
                Scene scene1 = new Scene(root);

                new_stage.setScene(scene1);
                new_stage.setTitle("Add Matchers Dialog");
                new_stage.showAndWait();
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
