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
        UserEntity u = new UserEntity();
        u.setUsername(user);
        u.setPassword(pass);
        UserDao udao=new UserDao();
        boolean temp=udao.searchData(u);
        if (user.equals("")||pass.equals("")){
            JOptionPane.showMessageDialog(null,"Please fill Username and password");
        }
        else{
            if(temp=true){
                System.out.println("Login Success");
                txtUsername.setText("");
                txtPassword.setText("");
            }
            else{
                lblErrors.setText("Invalid username or password");
            }
        }

    }
    public void btnSignup(ActionEvent actionEvent) {
    }
}
