package controller;

import domain.Atributie;
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

public class ReturnAtributiiController {
    public Service service;
    public domain.Voluntar Voluntar;

    public void setService(Service service) {
        this.service = service;
    }

    public void setVoluntar(domain.Voluntar Voluntar) {
        this.Voluntar = Voluntar;

        clmId.setCellValueFactory(new PropertyValueFactory<Atributie,Integer>("id"));
        clmTitle.setCellValueFactory(new PropertyValueFactory<Atributie,String>("title"));
        clmCatre.setCellValueFactory(new PropertyValueFactory<Atributie,String>("catre"));
        clmDescriere.setCellValueFactory(new PropertyValueFactory<Atributie,String>("descrierea"));
        clmAn.setCellValueFactory(new PropertyValueFactory<Atributie,Integer>("year_of_publication"));
        for(Atributie atributie : service.getPresentAtributii(Voluntar)){
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
    private TableColumn<Atributie, String> clmDescriere;

    @FXML
    private TableColumn<Atributie, String> clmCatre;

    @FXML
    private TableColumn<Atributie, Integer> clmAn;

    ObservableList<Atributie> atributii = FXCollections.observableArrayList();
    ObservableList<Atributie> selectedAtributii = FXCollections.observableArrayList();

    @FXML
    void pickAtributie(MouseEvent event) {
        Atributie atributie = tabelAtributii.getSelectionModel().getSelectedItem();
        selectedAtributii.add(atributie);
    }

    @FXML
    void returnAtributiiClick(MouseEvent event) throws IOException {
        for(Atributie atributie : selectedAtributii){
            service.returnAtributie(atributie,Voluntar);
//            System.err.println(atributie + " " + Voluntar);
        }
        Stage st = (Stage) tabelAtributii.getScene().getWindow();
        st.close();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
        Parent root = fxmlLoader.load();
        MessageController ctrl = fxmlLoader.getController();
        ctrl.setMessage("Returnarea cartilor s-a efectuat cu succes!");
        Scene scene = new Scene(root);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }

}
