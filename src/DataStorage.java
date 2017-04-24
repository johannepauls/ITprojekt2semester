
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*DataStorage klassen er vores database, og fungere i praksis ved at gemme sensorværdierne i en fil*/
public class DataStorage {

    Connection conn;
    Statement stmt;
    PreparedStatement stmt2;
    ArrayList<Double> data = new ArrayList<Double>();

    public DataStorage() {
        try {
            

            Class.forName("com.mysql.jdbc.Driver").newInstance();						// tilknyt driver

            String url = "jdbc:mysql://server3.eduhost.dk/suit219";							// URL: "JDBC:DBMS://maskinnavn/databasenavn"
            String userName = "suit219";												// MySQL brugernavn
            String password = "dtu165269F17";												// MySQL kodeord

            conn = DriverManager.getConnection(url, userName, password);
            stmt = conn.createStatement();
            stmt2 = conn.prepareStatement("INSERT INTO maaling VALUES (?,?)");
            stmt.executeUpdate("DROP TABLE maaling");
            stmt.executeUpdate("CREATE TABLE maaling(value DOUBLE, type VARCHAR(10))");

        } catch (Exception e) {
            System.out.println("jtest undtagelse: " + e.getMessage());					// udskriv fejlmeddelelse
            e.printStackTrace();
        }
    }

    public void gemData(double value, String type) {
        try {
            stmt2.setDouble(1, value);											// klargoer indsaettelse
            stmt2.setString(2, type);
            //timestamp ?
            stmt2.executeUpdate();

        } catch (Exception e) {
            System.out.println("jtest undtagelse: " + e.getMessage());					// udskriv fejlmeddelelse
            e.printStackTrace();
        }
    }

    public ArrayList<Double> hentData(String T) {
        try {
            ResultSet rset = stmt.executeQuery("SELECT * FROM maaling WHERE type = T ");

            //der skal laves noget med hvor mange målinger der skal indlæses
            while (rset.next()) {
                data.add(rset.getDouble(1));
            }

        } catch (Exception e) {
            System.out.println("jtest undtagelse: " + e.getMessage());					// udskriv fejlmeddelelse
            e.printStackTrace();
        }
        return data;
    }



}
