package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("MainPanel.fxml"));
        primaryStage.setTitle("TribalWarsPlanner");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) {
        DistanceCalculator dcalc = new DistanceCalculator("567|453","555|666");
        dcalc.convertCords("567|453","555|666");
        dcalc.calculateDistance(new int[]{530, 461}, new int[]{532, 463});
        launch(args);
    }
}
