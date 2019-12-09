package mainfile;

import DB_file.Cloth_dbfile;
import DB_file.Clothes;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.IntegerStringConverter;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    Connection connection;
    PreparedStatement preparedStatement;
    @FXML Label priceSum;
    @FXML private TextField addToCartBox;
    @FXML private TableView<Clothes> cartTable;
    @FXML private TableColumn<Clothes, String>cloth_nameColumn;
    @FXML private TableColumn<Clothes, Integer> cloth_quantityColumn;
    @FXML private TableColumn<Clothes, Double> cloth_priceColumn;
    @FXML private MenuItem add_stock_menu;
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
        cartTable.setEditable(true);
        cloth_nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        cloth_quantityColumn.setCellValueFactory(
                new PropertyValueFactory<>("quantity"));
        cloth_quantityColumn.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        cloth_quantityColumn.setOnEditCommit(
                (TableColumn.CellEditEvent<Clothes, Integer> t) -> {
                    t.getTableView().getItems().get(
                            t.getTablePosition().getRow()).setQuantity(t.getNewValue());
                    priceSum.setText(String.valueOf(itemSum()));
                });

        cloth_priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

    }

    //addition method
    public double itemSum(){
        double total = 0;
        double value;
        for (int i= 0;i<cartTable.getItems().size();i++){
            value = Double.parseDouble(String.valueOf(cartTable.getColumns().get(2).getCellObservableValue(i).getValue())) * Double.parseDouble(String.valueOf(cartTable.getColumns().get(1).getCellObservableValue(i).getValue()));
            total = total+value;
        }
        System.out.println(total);
        return total;
    }

     //method gets the data from the database and adds it to the cart
     public void addItemToCart(){
         Clothes addClothes = new Clothes();
         connection = cdb.create_Connection();
         ResultSet data;
         String sqlStatement = "SELECT * FROM clothing_table WHERE name = ?";
         try {
             preparedStatement = connection.prepareStatement(sqlStatement);
             preparedStatement.setString(1, addToCartBox.getText());
             data = preparedStatement.executeQuery();

             if (data.next()) {
                 addClothes.setName(data.getString("name"));
                 //addClothes.setQuantity(data.getInt("quantity"));
                 addClothes.setPrice((data.getDouble("price")));
                 System.out.println("ID---NAME--QUANTITY---PRICE");
                 System.out.println(data.getInt("id")+"---"+data.getString("name")+"---"+data.getInt("quantity")+"---"+data.getDouble("price"));

                 if(!addToCartBox.getText().isEmpty()) {
                     cartTable.getItems().add(addClothes);
                     addToCartBox.clear();
                     priceSum.setText(String.valueOf(itemSum()));
                 }
             }
         } catch (SQLException e) {
             e.getCause();
             e.getMessage();
             e.printStackTrace();
         }
    }
}
