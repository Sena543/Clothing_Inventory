package mainfile;

import DB_file.Cloth_dbfile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class Login implements Initializable {
    @FXML
    private TextField usernamebox;
    @FXML
    private PasswordField passwordbox;
    @FXML
    private Label incorrect_label;
    Cloth_dbfile database = new Cloth_dbfile();
    String username;
    String password;
    Connection connection;
    PreparedStatement preparedStatement;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    public void allow_Access(ActionEvent event) {
        if(handleLogin().equals("Success")){
            //load the main scene
        try {
            Parent mainParent = FXMLLoader.load(getClass().getResource("../scenes/mainScene.fxml"));
            Scene mainScene = new Scene(mainParent);
            Stage mainSceneWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
            mainSceneWindow.setScene((mainScene));
            mainSceneWindow.show();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            System.out.println(ex.getCause());
            }
        }
    }

    public String handleLogin(){
        username = usernamebox.getText();
        password = passwordbox.getText();
        connection = database.create_Connection();
        String sql = "SELECT * FROM userTable WHERE username = ?";
        String status = "Success";

        if(username.isEmpty() || password.isEmpty()){
            incorrect_label.setText("Username and Password are required");
            status = "Empty details";
        }else {

            try{
                //statement = connection.createStatement();
                //ResultSet resultSet = statement.executeQuery(sql);
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                if(!resultSet.next()){
                    incorrect_label.setText("Username or Password is wrong");
                    status = "Incorrect Details";
                }
            }catch (SQLException ex){
                System.out.println(ex.getMessage());
                System.out.println(ex.getCause());
            }
        }
        return status;
    }
}
