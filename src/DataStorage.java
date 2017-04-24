
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/*DataStorage klassen er vores database, og fungere i praksis ved at gemme sensorværdierne i en fil*/
public class DataStorage {

    static Connection conn;
    static Statement stmt;
    PreparedStatement stmt2;
    static ArrayList<Double> data = new ArrayList<Double>();
    static ResultSet rset;


    public DataStorage() {
        try {

            Class.forName("com.mysql.jdbc.Driver").newInstance();						// tilknyt driver

            String url = "jdbc:mysql://server3.eduhost.dk/suit219";							// URL: "JDBC:DBMS://maskinnavn/databasenavn"
            String userName = "suit219";												// MySQL brugernavn
            String password = "dtu165269F17";												// MySQL kodeord

            conn = DriverManager.getConnection(url, userName, password);
            stmt = conn.createStatement();
            stmt2 = conn.prepareStatement("INSERT INTO maaling (value,type,tid) VALUES (?,?,NOW())");

            try {
                ResultSet test = stmt.executeQuery("SELECT * FROM maaling");
                stmt.executeUpdate("DROP TABLE maaling");
            } catch (Exception e) {
            }
            stmt.executeUpdate("CREATE TABLE maaling(id INT PRIMARY KEY AUTO_INCREMENT, value DOUBLE, type VARCHAR(10), tid TIMESTAMP)");
            
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

    public static ArrayList<Double> hentData(String T) {
        data.clear();
        try {
           rset = stmt.executeQuery("SELECT * FROM maaling WHERE type = '"+T+"' ORDER BY id DESC LIMIT 10");
            
            
            //der skal laves noget med hvor mange målinger der skal indlæses
            while(rset.next()){
                
                
                    data.add(0,rset.getDouble(1));
                
            }
           
        } catch (Exception e) {
            System.out.println("jtest undtagelse: " + e.getMessage());					// udskriv fejlmeddelelse
            e.printStackTrace();
        }
        return data;
    }

}
