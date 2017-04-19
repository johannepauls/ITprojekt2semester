
import jssc.*;

public class Sensor {
    protected SerialPort serialPort;
    public static String result;
    public static double value;
    
    /*konstruktør, der åbner serialportforbindelsen*/
    public Sensor(String port) {
        serialPort = new SerialPort(port);
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
        } catch (SerialPortException ex) {
            System.out.println("Serial Port Exception: " + ex);
        }
    }
    
    /*metode, der henter data fra sensoren*/
    public double getData() {
        return value;
    }

    
}
