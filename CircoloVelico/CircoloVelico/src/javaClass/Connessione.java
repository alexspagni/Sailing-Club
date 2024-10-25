package javaClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 * Class used to connect to the database
 * @author Spagni Alex e Arianna Zuelli
 *
 */
public class Connessione {
	 private static final String DBURL ="jdbc:mysql://localhost:3306/circolovelico";
	  private static final String LOGIN = "root";
	  private static final String PASSWORD = "";
/**
 * Class constructor
 */
    private Connessione() {}
/**
 * It is used to establish the connection to the server.
 * @return
 * It return an object to use to make query to the server.
 */
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(DBURL, LOGIN, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
           
        }
        return null;
    }
}