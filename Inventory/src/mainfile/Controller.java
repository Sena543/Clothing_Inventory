package mainfile;

import DB_file.Cloth_dbfile;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import java.util.ResourceBundle;

public class Controller {
    @FXML
    private Button add_stock_button;
    @FXML
    private TextField clothID;
    @FXML
    private TableView<Cloth_dbfile> itemTable;
    @FXML private TableColumn<Cloth_dbfile, String>cloth_idColumn ;
    @FXML private TableColumn<Cloth_dbfile, String>cloth_priceColumn ;
    @FXML private TableColumn<Cloth_dbfile, String>cloth_typeColumn ;
    Cloth_dbfile cdb = new Cloth_dbfile();


    public void addStockScene(ActionEvent event) throws Exception{
        Parent add_stockParent = FXMLLoader.load(getClass().getResource("../scenes/AddStockController.fxml"));
        Scene add_stockScene = new Scene(add_stockParent);
        Stage addStockWindow = (Stage)((Node)event.getSource()).getScene().getWindow();
        addStockWindow.setScene((add_stockScene));
        addStockWindow.show();
    }


   public void initalizer(URL url, ResourceBundle rb){
      cloth_idColumn.setCellValueFactory(new PropertyValueFactory<Cloth_dbfile, String>("Cloth ID"));
      cloth_priceColumn.setCellValueFactory(new PropertyValueFactory<Cloth_dbfile, String>("Cloth Price"));
       ObservableList<Cloth_dbfile> items = itemTable.getItems();
   }
}
