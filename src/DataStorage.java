
import java.sql.*;
import java.util.*;

/*DataStorage klassen er vores database, og fungere i praksis ved at gemme sensorværdierne i en fil*/
public class DataStorage {

    private Connection conn;
    private Statement stmt;
    private PreparedStatement stmt2;
    private ArrayList<Double> data = new ArrayList<Double>();
    private ResultSet rset;


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

    public void gemData(Double value, String type) {
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
        data.clear();
        try {
           rset = stmt.executeQuery("SELECT * FROM maaling WHERE type = '"+T+"' ORDER BY id DESC LIMIT 10");
            //virker-W
            
            //der skal laves noget med hvor mange målinger der skal indlæses
            while(rset.next()){
                
                
                    data.add(0,rset.getDouble(2));
                    System.out.println(data.toString());
                //tilfoej til data (på 0 plads, fortolket værdi
            }
           
        } catch (Exception e) {
            System.out.println("jtest undtagelse: " + e.getMessage());					// udskriv fejlmeddelelse
            e.printStackTrace();
        }
       
       return data;
    }
    
    

}
