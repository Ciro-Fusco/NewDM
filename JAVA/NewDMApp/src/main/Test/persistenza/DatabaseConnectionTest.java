package persistenza;

import exceptions.DatabaseException;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseConnectionTest {

    @Test
    public void connectConNull() throws DatabaseException {
        DatabaseConnection.connect();
        assertNotEquals(null,DatabaseConnection.getCon());
        DatabaseConnection.close();
    }

    @Test
    public void connectConNotNull() throws DatabaseException {
        DatabaseConnection.connect();
        DatabaseConnection.connect();
        assertNotEquals(null,DatabaseConnection.getCon());
        DatabaseConnection.close();
    }

    @Test
    public void connectConClosed() throws DatabaseException {
        DatabaseConnection.connect();
        DatabaseConnection.close();
        DatabaseConnection.connect();
        assertNotEquals(null,DatabaseConnection.getCon());
        DatabaseConnection.close();
    }

    @Test
    public void connectConNotClosed() throws DatabaseException {
        DatabaseConnection.connect();
        DatabaseConnection.connect();
        assertNotEquals(null,DatabaseConnection.getCon());
        DatabaseConnection.close();
    }

}