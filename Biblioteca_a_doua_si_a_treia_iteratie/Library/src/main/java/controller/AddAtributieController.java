package controller;

import domain.Atributie;
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

public class AddAtributieController {
    private Service service;
    public void setService(Service service){
        this.service = service;
    }

    @FXML
    private TextField txtTitlu;

    @FXML
    private TextField txtCatre;

    @FXML
    private TextField txtDescriere;

    @FXML
    private TextField txtAn;

    @FXML
    void clickAddAtributie(MouseEvent event) throws IOException {
        if(Objects.equals(txtTitlu.getText(), "") || Objects.equals(txtCatre.getText(), "") || Objects.equals(txtDescriere.getText(), "") || Objects.equals(txtAn.getText(), "") ){
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
            Parent root = fxmlLoader.load();
            MessageController ctrl = fxmlLoader.getController();
            ctrl.setMessage("Trebuie completate toate campurile corect!");
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }else{
            String titlu = txtTitlu.getText();
            String autor = txtCatre.getText();
            String editura = txtDescriere.getText();
            int an_publ = 0;
            try{
                an_publ = Integer.parseInt(txtAn.getText());
                Atributie atributie = new Atributie(0,titlu,autor,editura,an_publ);
                service.addAtributie(atributie);
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
                Parent root = fxmlLoader.load();
                MessageController ctrl = fxmlLoader.getController();
                ctrl.setMessage("Atributia a fost adaugata cu succes!");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();

                Stage st = (Stage) txtTitlu.getScene().getWindow();
                st.close();

                txtTitlu.setText("");
                txtCatre.setText("");
                txtAn.setText("");
                txtDescriere.setText("");

            }catch (Exception e){
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/Message.fxml"));
                Parent root = fxmlLoader.load();
                MessageController ctrl = fxmlLoader.getController();
                ctrl.setMessage("Trebuie completate toate campurile corect!");
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.show();
            }
        }
    }
}
