package mainfile;

import DB_file.Cloth_dbfile;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddStockController {
    @FXML
    TextField addItemQuantity;
    @FXML
    TextField addItemName;
    @FXML
    TextField addItemPrice;
    @FXML
    Label item_Added;
    Cloth_dbfile add_db = new Cloth_dbfile();
    Connection connection;

    /*TO DO
    check if item already exists in database and updates the quantity
     */
    public void cancelProcess(ActionEvent event) throws Exception {
        Parent mainParent = FXMLLoader.load(getClass().getResource("../scenes/mainScene.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainSceneWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainSceneWindow.setScene((mainScene));
        mainSceneWindow.show();
    }

    public void addItems(){
        connection = add_db.create_Connection();
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO clothing_table (name, quantity, price) VALUES (?,?,?)";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, addItemName.getText());
            preparedStatement.setInt(2, Integer.parseInt(addItemQuantity.getText()));
            preparedStatement.setDouble(3, Double.parseDouble(addItemPrice.getText()));
            preparedStatement.executeUpdate();
            item_Added.setText("Items have been added successfullly");

        }catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println(e.getCause());
            item_Added.setText("Failed to add items");
        }
    }
}
