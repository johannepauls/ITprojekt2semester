
import java.sql.*;
import java.util.*;

/*DataStorage klassen er vores database, og fungere i praksis ved at gemme sensorværdierne i en fil*/
public class DataStorage {

    private Connection conn;
    private Statement stmt;
    private PreparedStatement stmt2;
    private ArrayList<Double> data = new ArrayList<Double>();
    private ResultSet rset;

/*Konstruktør der opretter forbindelse til serveren og opretter en tabel 
    * som vi gør klar til at sætte ind i*/
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
/*Indsætter værdier i Databasen*/
    public void gemData(Double value, String type) {
        try {
            stmt2.setDouble(1, value);											// klargoer indsaettelse
            stmt2.setString(2, type);
            stmt2.executeUpdate();

        } catch (Exception e) {
            System.out.println("jtest undtagelse: " + e.getMessage());					// udskriv fejlmeddelelse
            e.printStackTrace();
        }
    }
/*Henter de senste 10 data ind fra databasen der har typen t*/
    public ArrayList<Double> hentData(String T) {
        data.clear();
        try {
           rset = stmt.executeQuery("SELECT * FROM maaling WHERE type = '"+T+"' ORDER BY id DESC LIMIT 10");
            while(rset.next()){
                
                
                    data.add(0,rset.getDouble(2));
                    System.out.println(data.toString());
            }
           
        } catch (Exception e) {
            System.out.println("jtest undtagelse: " + e.getMessage());					// udskriv fejlmeddelelse
            e.printStackTrace();
        }
       
       return data;
    }    

}
