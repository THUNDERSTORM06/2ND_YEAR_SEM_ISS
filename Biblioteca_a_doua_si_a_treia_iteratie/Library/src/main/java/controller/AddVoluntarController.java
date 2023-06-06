package controller;

import domain.Voluntar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;
import java.util.Objects;

public class AddVoluntarController {
    private Service service;

    public void setService(Service sr){this.service = sr;}

    @FXML
    private TextField txtUsername;

    @FXML
    private TextField txtPassword;

    @FXML
    private TextField txtFName;

    @FXML
    private TextField txtLName;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtPhNr;

    @FXML
    void clickAddVoluntar(MouseEvent event) throws IOException {
        if( Objects.equals(txtUsername.getText(), "") || Objects.equals(txtPassword.getText(), "") || Objects.equals(txtFName.getText(), "") || Objects.equals(txtLName.getText(), "") || Objects.equals(txtEmail.getText(), "") || Objects.equals(txtPhNr.getText(), "")){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController ctrl = fxmlLoader.getController();
            ctrl.setMessage("Toate campurile trebuie completate corect!");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }else{
            String us = txtUsername.getText();
            String ps = txtPassword.getText();
            String fn = txtFName.getText();
            String ln = txtLName.getText();
            String em = txtEmail.getText();
            String pn = txtPassword.getText();

            Voluntar Voluntar = new Voluntar(us,ps,fn,ln,em,pn);
            service.addVoluntar(Voluntar);
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController ctrl = fxmlLoader.getController();
            ctrl.setMessage("Voluntarul a fost adaugat cu succes!");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();

            Stage st = (Stage) txtPhNr.getScene().getWindow();
            st.close();

            txtUsername.setText("");
            txtPassword.setText("");
            txtFName.setText("");
            txtLName.setText("");
            txtEmail.setText("");
            txtPhNr.setText("");
        }
    }

}
