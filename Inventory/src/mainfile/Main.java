package mainfile;

import DB_file.Cloth_dbfile;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("../scenes/Login.fxml"));
        primaryStage.setTitle("Store Name");
        primaryStage.setScene(new Scene(root, 800, 800));
        primaryStage.show();
        new Cloth_dbfile().create_Connection();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
