package ui;

import controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import repository.orm.hibernate.AtributiePresentRepository;
import repository.orm.hibernate.AtributieRepository;
import repository.orm.hibernate.VoluntarRepository;
import repository.orm.hibernate.LiderRepository;
import service.Service;

public class MainFX extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        AtributieRepository atributieRepository = new AtributieRepository();
        VoluntarRepository voluntarRepository = new VoluntarRepository();
        LiderRepository liderRepository = new LiderRepository();
        AtributiePresentRepository atributiePresentRepository = new AtributiePresentRepository();

        Service service = new Service(atributieRepository,voluntarRepository,liderRepository, atributiePresentRepository);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/LoginView.fxml"));
            Parent root = loader.load();
            LoginController ctrl = loader.getController();
            ctrl.setService(service);
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Hello");
            primaryStage.show();
        }catch(Exception e){
            System.out.println(e);
        }

    }
}
