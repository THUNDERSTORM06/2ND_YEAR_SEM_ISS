package controller;

import domain.Voluntar;
import domain.Lider;
import domain.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class LoginController {
    private Service service;

    public void setService(Service service){
        this.service = service;
    }

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnLogin;

    @FXML
    void loginClick(MouseEvent event) throws IOException {
        String username = txtUsername.getText();
        String password = txtPassword.getText();
        User user = service.findUser(username,password);
        if(user instanceof Voluntar){
            Stage st = (Stage) btnLogin.getScene().getWindow();
            st.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/VoluntarView.fxml"));
            Parent root = fxmlLoader.load();
            VoluntarController ctrl = fxmlLoader.getController();
            ctrl.setService(this.service);
            ctrl.setVoluntar((Voluntar) user);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Welcome " + ((Voluntar) user).getLast_name());
            stage.show();
        }
        else if(user instanceof Lider){
            Stage st = (Stage) btnLogin.getScene().getWindow();
            st.close();

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/LiderView.fxml"));
            Parent root = fxmlLoader.load();
            LiderController ctrl = fxmlLoader.getController();
            ctrl.setService(this.service);
            ctrl.setLibrarian((Lider) user);
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.setTitle("Welcome " + ((Lider) user).getLast_name());
            stage.show();

        }
        else{
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController ctrl = fxmlLoader.getController();
            ctrl.setMessage("Datele de conectare sunt gresite!");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
            txtPassword.setText("");
            txtUsername.setText("");
        }

    }

}
