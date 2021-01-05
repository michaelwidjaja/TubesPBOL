package controllers;

import DAO.UserDao;
import Model.UserEntity;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sun.rmi.runtime.Log;

public class SignUpController {
    public TextField regNama;
    public TextField regUsername;
    public PasswordField regPassword;
    public Button regButton;
    private LoginController main;

    public void registerNowAction(ActionEvent actionEvent) {

        String nama = regNama.getText();
        String username = regUsername.getText();
        String password = regPassword.getText();

        if(nama.isEmpty() || username.isEmpty() || password.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Tidak boleh ada form yang kosong");
            alert.showAndWait();
        }
        else {
            UserEntity u = new UserEntity();
            u.setNama(nama);
            u.setUsername(username);
            u.setPassword(password);

            UserDao uDao = new UserDao();
            uDao.addData(u);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Registration Successful");
            alert.setHeaderText("Registrasi berhasil di lakukan ");
            alert.showAndWait();

            Stage stage = (Stage) regButton.getScene().getWindow();
            stage.close();
        }
    }

    public void setmain(LoginController main) {
        this.main = main;
    }
}
