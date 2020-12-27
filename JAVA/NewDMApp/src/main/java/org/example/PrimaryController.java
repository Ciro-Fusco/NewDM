package org.example;

import java.io.IOException;

import Entity.Utente;
import db.DatabaseConnection;
import javafx.fxml.FXML;
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
    public void checkLogin(MouseEvent mouseEvent) throws Exception {
        if(DatabaseConnection.Connect())
        if(Utente.Login(us.getText(),pass.getText()))
            App.setRoot("Dashboard");
        else /*  Da inserire popup di errore a cura di Vincenzo*/;
    }

    public void Logout(MouseEvent mouseEvent) {

        DatabaseConnection.Close();
        Utente.clear();

    }
}
