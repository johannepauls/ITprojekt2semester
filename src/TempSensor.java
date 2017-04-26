
import java.util.Timer;
import java.util.TimerTask;
import jssc.*;
//TempSensor nedarver fra Sensor klassen
public class TempSensor extends Sensor {
    private static Timer timer = new Timer(true);
    private static String result;
    private static double value;
    private static double temp;
/*Konstruktør: Sensoren tilsluttes i COM6 og porten åbnes
    Når sensoren ikke er tilsluttet udskriver den "serial Port Exection(fejltype)"*/
    public TempSensor(String port) {
        super(port);
        
        try {
            serialPort.setDTR(true);
        } catch (SerialPortException ex) {
            
        }
        /*TimerTask metoden run() bliver kaldt en gang hvert sekund
        * Denne Timer gør at den nuværende temperatur også er den nyeste*/
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() { //
                try {
                   /*Læser værdier fra sensoren. Hvis der ikke ligger en ny værdi bliver value sat til -1
                    *Det er kun de reelle værdier der bliver sendt videre som temperatur data.
                    */
                    if (serialPort.getInputBufferBytesCount() > 0) {
                        result = serialPort.readString();
                        value = Double.parseDouble(result.substring(1,6));
                    } else {
                        value = -1.0;
                    }
                } catch (SerialPortException ex) {
                    System.out.println("Serial Port Exception: " + ex);
                }

                if (value > -1.0) {
                    temp = value;
                }
            }
        }, 0, 1000);
    }
    
    @Override
    public double getData() {  /*gør at Mainklassen kan hente temperaturdata uden at ødelægge vores timer*/ 
        return temp;
    }

}
