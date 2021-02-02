package persistenza;

import exceptions.DatabaseException;
import org.junit.Test;

import static org.junit.Assert.*;

public class DatabaseConnectionTest {

    @Test
    public void connectConNull() throws DatabaseException {
        DatabaseConnection.getInstance();
        assertNotEquals(null,DatabaseConnection.getInstance());
        DatabaseConnection.close();
    }

    @Test
    public void connectConNotNull() throws DatabaseException {
        DatabaseConnection.getInstance();
        DatabaseConnection.getInstance();
        assertNotEquals(null,DatabaseConnection.getInstanceTEST());
        DatabaseConnection.close();
    }

    @Test
    public void connectConClosed() throws DatabaseException {
        DatabaseConnection.getInstance();
        DatabaseConnection.close();
        DatabaseConnection.getInstance();
        assertNotEquals(null,DatabaseConnection.getInstanceTEST());
        DatabaseConnection.close();
    }

    @Test
    public void connectConNotClosed() throws DatabaseException {
        DatabaseConnection.getInstance();
        DatabaseConnection.getInstance();
        assertNotEquals(null,DatabaseConnection.getInstanceTEST());
        DatabaseConnection.close();
    }

}