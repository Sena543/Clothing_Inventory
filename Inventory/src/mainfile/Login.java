package mainfile;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class Login{
    @FXML
    private TextField usernamebox;
    @FXML
    private PasswordField passwordbox;
    @FXML
    private Button loginbutton;
    String username;
    String password;

    @FXML
    public void allow_Access(ActionEvent event){
        username = usernamebox.getText();
        password = passwordbox.getText();
        System.out.println(username);
        System.out.println(password);
    }

}
