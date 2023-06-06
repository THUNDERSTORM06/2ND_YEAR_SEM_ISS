package controller;

import domain.Atributie;
import domain.AtributiePresent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import service.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PresentAtributieController {
    private Service service;
    private domain.Voluntar voluntar;

    public void setService(Service service) {
        this.service = service;
    }

    public void setVoluntar(domain.Voluntar voluntar) {
        this.voluntar = voluntar;

        clmId.setCellValueFactory(new PropertyValueFactory<Atributie,Integer>("id"));
        clmTitle.setCellValueFactory(new PropertyValueFactory<Atributie,String>("title"));
        clmCatre.setCellValueFactory(new PropertyValueFactory<Atributie,String>("catre"));
        clmDescriere.setCellValueFactory(new PropertyValueFactory<Atributie,String>("descriere"));
        clmAn.setCellValueFactory(new PropertyValueFactory<Atributie,Integer>("year_of_publication"));
        for(Atributie atributie : service.getNotPresentAtributii()){
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
    ObservableList<Atributie> selectedAtributii = FXCollections.observableArrayList();

    @FXML
    void finishPresentClick(MouseEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/PresentFormularView.fxml"));
        Parent root = fxmlLoader.load();
        PresentFormularController ctrl = fxmlLoader.getController();
        ctrl.setService(service);
        
        // transmitem o lista de imprumuri
        List<AtributiePresent> presents = new ArrayList<AtributiePresent>();
        for(Atributie atributie : selectedAtributii){
//            System.err.println(atributie);
            AtributiePresent atributiePresent = new AtributiePresent(voluntar.getId(), atributie.getId(),0,0);
            presents.add(atributiePresent);
        }

        Stage st = (Stage) tabelAtributii.getScene().getWindow();
        st.close();
        
        ctrl.setPresents(presents);
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void pickPresentAtributie(MouseEvent event) {
        Atributie atributie = tabelAtributii.getSelectionModel().getSelectedItem();
        selectedAtributii.add(atributie);
    }

    @FXML
    void showPresentAtributiiButton(MouseEvent event) {
        atributii.clear();
        for(Atributie b: selectedAtributii)
            atributii.add(b);
        tabelAtributii.setItems(atributii);
    }

}
