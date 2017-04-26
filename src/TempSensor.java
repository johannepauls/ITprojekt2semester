
import java.util.Timer;
import java.util.TimerTask;
import jssc.*;

public class TempSensor extends Sensor {
    private static Timer timer = new Timer(true);
    private static String result;
    private static double value;
    private static double temp;

    public TempSensor(String port) {
        super(port);
        
        try {
            serialPort.setDTR(true);
        } catch (SerialPortException ex) {
            
        }

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    /*vi tjekker om der er en temperaturværdi at hente, eller om sensoren buffer
                    *hvis sensoren buffer returneres -1 og dette fanger vi senere*/
                    if (serialPort.getInputBufferBytesCount() > 0) {
                        result = serialPort.readString();
                        value = Double.parseDouble(result.substring(1,6));
                    } else {
                        value = -1.0;
                    }
                    //System.out.println(value); //til test
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
    public double getData() {
        //System.out.println(temp); //til Test
        return temp;
    }

}
