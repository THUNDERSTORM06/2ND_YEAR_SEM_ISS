package controller;

import domain.Atributie;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Service;

public class ViewAtributiiController {
    private Service service;
    public void setService(Service service){
        this.service = service;

        getAtributii();

    }

    public void getAtributii(){
        clmId.setCellValueFactory(new PropertyValueFactory<Atributie,Integer>("id"));
        clmTitle.setCellValueFactory(new PropertyValueFactory<Atributie,String>("title"));
        clmCatre.setCellValueFactory(new PropertyValueFactory<Atributie,String>("catre"));
        clmDescriere.setCellValueFactory(new PropertyValueFactory<Atributie,String>("descriere"));
        clmAn.setCellValueFactory(new PropertyValueFactory<Atributie,Integer>("year_of_publication"));
        for(Atributie atributie : service.getAllAtributii()){
            atributii.add(atributie);
        }
        tabelAtributii.setItems(atributii);
    }
    @FXML
    private TableView<Atributie> tabelAtributii;

    @FXML
    private TableColumn<Atributie, Integer> clmId;

    @FXML
    private TableColumn<Atributie, String> clmTitle;

    @FXML
    private TableColumn<Atributie, String> clmCatre;

    @FXML
    private TableColumn<Atributie, String> clmDescriere;

    @FXML
    private TableColumn<Atributie, Integer> clmAn;

    ObservableList<Atributie> atributii = FXCollections.observableArrayList();
}
