package com.example.progettocompleto.FileDiSistema;

import com.example.progettocompleto.Start;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Stack;

public class NavigationManager {
    private static NavigationManager istance;
    public  Stack<Scene> scenes = new Stack<>();
    private  Stage stage= Start.mainStage;

    private NavigationManager(Stage stage) {
        this.stage = stage;
    }
    public static NavigationManager getInstance(Stage stage){
        if(istance==null){
            istance = new NavigationManager(stage);
        }
        return istance;
    }
    public  void setStage(Stage stage){
        this.stage = stage;
    }

    public  void navigateTo(Scene scene) {
        scenes.push(scene);
        stage.setScene(scene);
    }

    public  void navigateBack() {
        System.out.println("Le dimension:"+ scenes.size());
        if (scenes.size() > 1) {
            scenes.pop();
            stage.setScene(scenes.peek());

        }
    }
    public  Scene getScene(){
        return scenes.peek();
    }
    public  Stage getStage(){
        return stage;
    }
}