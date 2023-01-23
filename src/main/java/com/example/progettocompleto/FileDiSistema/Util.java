package com.example.progettocompleto.FileDiSistema;

import com.example.progettocompleto.Start;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;

public class Util {
    private static Scene previousScene;
    private static Callback previousController;
    private static Scene scene;
    private static Stage previousStage;
    private static Parent previousRoot;
    private static FXMLLoader previouseLoader;
    private static NavigationManager  navigationManager;
    public static void setScene(String fxml, Stage stage, Callback controller) {
        Parent root;
        try {

            navigationManager = NavigationManager.getInstance(stage);


            FXMLLoader loader = new FXMLLoader(Start.class.getResource(fxml));
            previouseLoader = loader;
            loader.setControllerFactory(controller);
            previousController = controller;
            root = loader.load();
            previousRoot = root;
            scene = new Scene(root);
            //NavigationManager.navigateTo(scene);
            previousStage = stage;
            previousScene = scene;
            navigationManager.navigateTo(scene);
            //lo stage che ho passato lo trasformo come main stage, ovvero lo inizializzo al mainstage
            //stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //Lo uso quando ho bisogno di utilizzare una scene nel codice
    public static <T> T setSpecificScene(String fxml, Stage stage, Callback controller) {

        Parent root;


        try {
            navigationManager = NavigationManager.getInstance(stage);

            //navigationManager.navigateBack();
            FXMLLoader loader = new FXMLLoader(Start.class.getResource(fxml));

            loader.setControllerFactory(controller);
            root = loader.load();
            scene = new Scene(root);

            //lo stage che ho passato lo trasformo come main stage, ovvero lo inizializzo al mainstage
            stage.setScene(scene);
            stage.show();
            navigationManager.navigateTo(scene);
            return loader.getController();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    /* public static void setScene(String fxml, Stage stage){

         Parent root;
         try {

             FXMLLoader loader = new FXMLLoader(Start.class.getResource(fxml));
             root = loader.load();
             scene = new Scene(root);
             //lo stage che ho passato lo trasformo come main stage, ovvero lo inizializzo al mainstage
             stage.setScene(scene);
             stage.show();
         } catch (IOException e) {
             throw new RuntimeException(e);
         }

     */
    public static void ritorno(String fxml) {

        navigationManager.navigateBack();
        navigationManager.getStage().setScene(navigationManager.getScene());
        navigationManager.getStage().show();

    }
}