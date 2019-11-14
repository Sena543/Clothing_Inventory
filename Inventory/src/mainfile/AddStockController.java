package mainfile;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddStockController {
    public void cancelProcess(ActionEvent event) throws Exception {
        Parent mainParent = FXMLLoader.load(getClass().getResource("../scenes/mainScene.fxml"));
        Scene mainScene = new Scene(mainParent);
        Stage mainSceneWindow = (Stage) ((Node) event.getSource()).getScene().getWindow();
        mainSceneWindow.setScene((mainScene));
        mainSceneWindow.show();
    }
}
