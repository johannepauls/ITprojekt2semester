
import java.sql.*;

/*DataStorage klassen er vores database, og fungere i praksis ved at gemme sensorværdierne i en fil*/
public class DataStorage {

    public DataStorage() {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();						// tilknyt driver

            String url = "jdbc:mysql://server3.eduhost.dk/suit219";							// URL: "JDBC:DBMS://maskinnavn/databasenavn"
            String userName = "suit219";												// MySQL brugernavn
            String password = "dtu165269F17";												// MySQL kodeord

            Connection conn = DriverManager.getConnection(url, userName, password);
        } catch (Exception e) {

        }
    }

    public void saveData() {

    }
}
