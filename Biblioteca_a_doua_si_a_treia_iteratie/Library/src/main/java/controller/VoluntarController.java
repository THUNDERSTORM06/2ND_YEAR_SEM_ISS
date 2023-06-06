package controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;

public class VoluntarController {
    private domain.Voluntar Voluntar;
    public void setVoluntar(domain.Voluntar Voluntar){
        this.Voluntar = Voluntar;
        welcomeLabel.setText("Welcome "+ Voluntar.getFirst_name() + " " + Voluntar.getLast_name());
    }

    private Service service;
    public void setService(Service service){
        this.service = service;
    }

    @FXML
    private Label welcomeLabel;

    @FXML
    void clickPresentAtributie(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/PresentAtributieView.fxml"));
        Parent root = fxmlLoader.load();
        PresentAtributieController ctrl = fxmlLoader.getController();
        ctrl.setService(service);
        ctrl.setVoluntar(Voluntar);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickReturnAtributie(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ReturnAtributiiView.fxml"));
        Parent root = fxmlLoader.load();
        ReturnAtributiiController ctrl = fxmlLoader.getController();
        ctrl.setService(service);
        ctrl.setVoluntar(Voluntar);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void clickViewPresentHistory(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ViewPresentHistoryView.fxml"));
        Parent root = fxmlLoader.load();
        ViewPresentHistoryController ctrl = fxmlLoader.getController();
        ctrl.setService(service);
        ctrl.setVoluntar(Voluntar);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
