package app;

import javafx.application.Application;
import javafx.stage.Stage;
import model.FinalProjectPane;

public class Demo extends Application
{
    public static void main(String[] args)
    {
        launch(args);       
    }
    public void start(Stage primaryStage) throws Exception
    {
        FinalProjectPane.startFinalProject(primaryStage);
    }   
}