/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testkode;

import java.util.logging.Level;
import java.util.logging.Logger;
import jssc.*;

public class Sensor {
    protected static SerialPort serialPort;
    public static String result;
    public static double value;
    public static double temp;
    /*konstruktør, der åbner serialportforbindelsen*/
    public Sensor() {

    }
    
    public static void main(String[] args){
          serialPort = new SerialPort("COM6");
        try {
            serialPort.openPort();
            serialPort.setParams(9600, 8, 1, 0);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);
            serialPort.setDTR(true);
            System.out.println("port åben");
        } catch (SerialPortException ex) {
            System.out.println("Serial Port Exception: " + ex);
        }
        try {
        while(true){
            temp = getData();
            
            System.out.println(temp);
            
              
            java.util.concurrent.TimeUnit.MILLISECONDS.sleep(1000);
             

        }
        } catch (InterruptedException ex) {
            Logger.getLogger(Sensor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    /*metode, der henter data fra sensoren*/
    public static double getData() {
        try {
            /*vi tjekker om der er en temperaturværdi at hente, eller om sensoren buffer
            *hvis sensoren buffer returneres -1 og dette fanger vi senere*/
            if (serialPort.getInputBufferBytesCount() > 0) {
                result = serialPort.readString();
                value = Double.parseDouble(result.substring(1,6));
            } else {
                value = -1.0;
            }
        } catch (SerialPortException ex) {
            System.out.println("Serial Port Exception: " + ex);
        }
        return value;
    }
}
