package org.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class PrimaryController {

    
    public TextField us;
    public PasswordField pass;

    @FXML
    private void switchToSecondary() throws IOException {
        System.out.println("ciao");
    }

    @FXML
    public void checkLogin(MouseEvent mouseEvent) throws IOException {

        System.out.println(us.getText()+" "+pass.getText());
        App.setRoot("Dashboard");

    }
}
