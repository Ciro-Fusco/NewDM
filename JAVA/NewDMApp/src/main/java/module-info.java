module org.exemple {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.codec;
    requires mysql.connector.java;

    opens start to javafx.fxml;
    exports start;
    opens utenza to javafx.fxml;
    exports utenza;
    opens cassa to javafx.fxml;
    exports cassa;
    opens assistenza to javafx.fxml;
    exports assistenza;
    opens magazzino to javafx.fxml;
    exports magazzino;
}