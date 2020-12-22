package controllers;

import DAO.UserDao;
import Model.UserEntity;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import sun.security.provider.MD5;

import javax.swing.*;

public class LoginController {


    public PasswordField txtPassword;
    public TextField txtUsername;
    public Label lblErrors;

    public void btnSignin(ActionEvent actionEvent) {
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
                System.out.println("Login Success");
                id=u.getIdpengguna();
                System.out.println(id);
            }
            else{
                lblErrors.setText("Invalid username or password");
            }
        }

    }
    public void btnSignup(ActionEvent actionEvent) {
    }
}
