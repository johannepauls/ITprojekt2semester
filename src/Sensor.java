
import jssc.*;

public class Sensor {
    protected SerialPort serialPort;
    public static String result;
    public static double value;
    
    /*konstruktør, der åbner serialportforbindelsen*/
    public Sensor() {
        serialPort = new SerialPort("COM6");
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            serialPort.setDTR(true);
        } catch (SerialPortException ex) {
            System.out.println("Serial Port Exception: " + ex);
        }
    }
    
    /*metode, der henter data fra sensoren*/
    public double getData() {
        try {
            /*vi tjekker om der er en temperaturværdi at hente, eller om sensoren buffer
            *hvis sensoren buffer returneres -1 og dette fanger vi senere*/
            if (serialPort.getInputBufferBytesCount() > 0) {
                result = serialPort.readString();
                value = Double.parseDouble(result.substring(1, 6));
            } else {
                value = -1.0;
            }
        } catch (SerialPortException ex) {
            System.out.println("Serial Port Exception: " + ex);
        }
        return value;
    }
}
