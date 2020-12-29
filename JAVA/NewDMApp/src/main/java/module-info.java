module org.exemple {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.apache.commons.codec;
    requires mysql.connector.java;

    opens controller to javafx.fxml;
    exports controller;
}