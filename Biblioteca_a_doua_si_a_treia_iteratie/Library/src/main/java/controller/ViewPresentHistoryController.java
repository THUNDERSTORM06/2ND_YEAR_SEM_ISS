package controller;

import domain.Atributie;
import domain.AtributiePresent;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import service.Service;

public class ViewPresentHistoryController {
    public Service service;
    public domain.Voluntar Voluntar;

    public void setService(Service service) {
        this.service = service;
    }

    public void setVoluntar(domain.Voluntar Voluntar) {
        this.Voluntar = Voluntar;
        getPresents();
    }

    public void getPresents(){
        idClm.setCellValueFactory(new PropertyValueFactory<AtributiePresent,Integer>("id"));
        daysClm.setCellValueFactory(new PropertyValueFactory<AtributiePresent, Integer>("days"));

        titleClm.setCellValueFactory(cellData -> {
            AtributiePresent present = cellData.getValue();
            int idAtributie = present.getId();
            Atributie atributie = service.getAtributie(idAtributie);
            return new SimpleStringProperty(atributie.getTitle());
        });

        catreClm.setCellValueFactory(cellData -> {
            AtributiePresent present = cellData.getValue();
            int idAtributie = present.getId();
            Atributie atributie = service.getAtributie(idAtributie);
            return new SimpleStringProperty(atributie.getCatre());
        });

        returnedClm.setCellValueFactory(cellData -> {
            AtributiePresent present = cellData.getValue();
            if(present.getReturned() == 0)
                return new SimpleStringProperty("NOT RETURNED") ;
            else
                return new SimpleStringProperty("RETURNED") ;
        });

        for(AtributiePresent atributiePresent: service.getPresentHistory(Voluntar)){
            atributiePresents.add(atributiePresent);
        }

        tabelPresentHistory.setItems(atributiePresents);
    }

    @FXML
    private TableView<AtributiePresent> tabelPresentHistory;

    @FXML
    private TableColumn<AtributiePresent, Integer> idClm;

    @FXML
    private TableColumn<AtributiePresent, String> titleClm;

    @FXML
    private TableColumn<AtributiePresent, String> catreClm;

    @FXML
    private TableColumn<AtributiePresent, Integer> daysClm;


    @FXML
    private TableColumn<AtributiePresent, String> returnedClm;

    ObservableList<AtributiePresent> atributiePresents = FXCollections.observableArrayList();

}
