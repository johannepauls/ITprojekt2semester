
import jssc.*;

public abstract class  Sensor {
    protected SerialPort serialPort;
    private static String result;
    private static double value;
    /*Sensor klassen er en superklasse derfor er den abstract. Pulssensoren og temperatursensoren
    nedarver derfor fra denne klasse
    *konstruktøren åbner serielport forbindelsen og nedarves af begge børneklasser*/
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
    
    
    public double getData() {
        return value;
    }

    
}
