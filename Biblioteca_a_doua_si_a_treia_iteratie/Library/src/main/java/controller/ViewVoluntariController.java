package controller;

import domain.Voluntar;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Service;

public class ViewVoluntariController {
    private Service service;
    public void setService(Service service){
        this.service = service;

        getVoluntari();

    }

    ObservableList<Voluntar> Voluntars = FXCollections.observableArrayList();

    public void getVoluntari(){
        idClm.setCellValueFactory(new PropertyValueFactory<Voluntar,Integer>("id"));
        usernameClm.setCellValueFactory(new PropertyValueFactory<Voluntar,String>("username"));
        passwlm.setCellValueFactory(new PropertyValueFactory<Voluntar,String>("password"));
        fnameClm.setCellValueFactory(new PropertyValueFactory<Voluntar,String>("first_name"));
        lnameClm.setCellValueFactory(new PropertyValueFactory<Voluntar,String>("last_name"));
        emailClm.setCellValueFactory(new PropertyValueFactory<Voluntar,String>("email"));
        phoneClm.setCellValueFactory(new PropertyValueFactory<Voluntar,String>("phone_number"));
        for(domain.Voluntar Voluntar: service.getAllVoluntari())
            Voluntars.add(Voluntar);
        tabelVoluntari.setItems(Voluntars);
    }

    @FXML
    private TableView<Voluntar> tabelVoluntari;

    @FXML
    private TableColumn<Voluntar, Integer> idClm;

    @FXML
    private TableColumn<Voluntar, String> usernameClm;

    @FXML
    private TableColumn<Voluntar, String> passwlm;

    @FXML
    private TableColumn<Voluntar, String> fnameClm;

    @FXML
    private TableColumn<Voluntar, String> lnameClm;

    @FXML
    private TableColumn<Voluntar, String> emailClm;

    @FXML
    private TableColumn<Voluntar, String> phoneClm;

}
