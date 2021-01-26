package controller;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/** JavaFX App */
public class App extends Application {

  private static Scene scene;

  @Override
  public void start(Stage stage) throws IOException {
    stage.getIcons().add(new Image(App.class.getResourceAsStream("LogoIcon.png")));
    scene = new Scene(loadfxml("Login"), 800, 620);
    stage.setScene(scene);
    /*stage.getIcons().add(new Image(FXMLUtils.class.getClassLoader().
            getResourceAsStream("puffo.png")));*/
    stage.show();
  }

  public  static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadfxml(fxml));
  }

  private static Parent loadfxml(String fxml) throws IOException {
    FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
    return fxmlLoader.load();
  }


  public static void main(String[] args) {
    launch();
  }

}
