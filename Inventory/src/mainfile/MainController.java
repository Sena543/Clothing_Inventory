package mainfile;

import DB_file.Cloth_dbfile;
import DB_file.Clothes;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    Connection connection;
    PreparedStatement preparedStatement;
    @FXML
    private Button add_stock_button;
    @FXML
    private TextField addToCartBox;
    @FXML
    private TableView<Clothes> cartTable;
    @FXML private TableColumn<Clothes, String>cloth_nameColumn;
    @FXML private TableColumn<Clothes, Integer> cloth_quantityColumn;
    @FXML private TableColumn<Clothes, Double> cloth_priceColumn;
    Cloth_dbfile cdb = new Cloth_dbfile();


    /*
    type in the name of an item to add to the cart
    increase or decrease the quantity in the cart
    make quantity column editable to enter the quantity of items to be sold
     */


    public void addStockScene(ActionEvent event) throws Exception{
        Parent add_stockParent = FXMLLoader.load(getClass().getResource("../scenes/AddStockController.fxml"));
        Scene add_stockScene = new Scene(add_stockParent);
        Stage addStockWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStockWindow.setScene((add_stockScene));
        addStockWindow.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        cloth_nameColumn.setCellValueFactory(new PropertyValueFactory<Clothes, String>("name"));
        cloth_quantityColumn.setCellValueFactory(new PropertyValueFactory<Clothes, Integer>("quantity"));
        cloth_priceColumn.setCellValueFactory(new PropertyValueFactory<Clothes, Double>("price"));

    }

     public void addItemToCart(){
//         cloth_nameColumn.setCellValueFactory(new PropertyValueFactory<Clothes, String>("Item Name"));
//         cloth_quantityColumn.setCellValueFactory(new PropertyValueFactory<Clothes, Integer>("Quantity"));
//         cloth_priceColumn.setCellValueFactory(new PropertyValueFactory<Clothes, Double>("Price"));
         //give the duplicate error
         //   cartTable.getColumns().addAll(cloth_nameColumn, cloth_quantityColumn, cloth_priceColumn);

        if(!addToCartBox.getText().isEmpty()){
            //stop executing the function and return something
            cartTable.setItems(getDatafromDB());
        }
    }


     public ObservableList<Clothes> getItems(){
         ObservableList<Clothes> itemList = FXCollections.observableArrayList();

         return itemList;
     }

     //method gets the data from the database and passes it to
     public ObservableList<Clothes> getDatafromDB(){
         ObservableList<Clothes> itemList = FXCollections.observableArrayList();

         connection = cdb.create_Connection();
         ResultSet data = null;
         String sqlStatement = "SELECT * FROM clothing_table WHERE name = ?";
         try {
             preparedStatement = connection.prepareStatement(sqlStatement);
             preparedStatement.setString(1, addToCartBox.getText());
             data = preparedStatement.executeQuery();

             if (data.next()) {
                 System.out.println("ID---NAME--QUANTITY---PRICE");
                 int id = data.getInt("id");
                 String name = data.getString("name");
                 int quantity = data.getInt("quantity");
                 double price = data.getDouble("price");
                 System.out.println(id+"---"+name+"---"+quantity+"---"+price);
                 itemList.add(new Clothes(name,quantity, price));

             }
         } catch (SQLException e) {
             e.getCause();
             e.getMessage();
             e.printStackTrace();
         }
         return itemList;
    }
}
