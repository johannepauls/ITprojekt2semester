
import java.util.*;
import javax.swing.*;

public class Examination {

    
    public static DataStorage database = new DataStorage();
    private static Gui pan = new Gui(database);
    private static Sensor tempSensor = new TempSensor(/*"/dev/tty.usbserial"*/"COM6");
    private static double temp = 0.0;
    private static Sensor pulsSensor = new PulsSensor(/*"/dev/tty.usbmodem1421"*/"COM8");
    private static double puls = 0.0;
    private static JFrame ramme = new JFrame();

    public static void main(String[] args) {
        /*Starter pulssensor*/
        (new Thread((Runnable) pulsSensor)).start();
        
        ramme.add(pan);
        ramme.pack();
        ramme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramme.setVisible(true);
        try {
            /*programmet vil pga. løkke køre til vi lukker det*/
            while (true) {
                /*programmet tester hvorvidt vi har trykket på start*/
                if (pan.isStartet()) {
                    ramme.pack();
                    /*Hvis temperatur er tjekket af kalder vi evaluateTemp()*/
                    if (pan.isTemp()) {
                        evaluateTemp(pan.getTempMax(), pan.getTempMin());
                    }
                    /*Hvis puls er tjekket af kalder vi evalutePuls()*/
                    if (pan.isPuls()) {
                        evaluatePuls(pan.getPulsMax(),pan.getPulsMin());
                    }
                }
                /*Vi venter 15 sekunder*/
                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(15000);
            }
        } catch (Exception ex) {
            System.out.println("Serial Port Exception: " + ex);
        }
    }

    /*metode, der evaluere den temperatur der er hentet fra sensoren
    *har to paramenter som er vores grænseværdier
    *og gemmer denne værdi i databasen*/
    public static void evaluateTemp(double maxgr, double mingr) {
        temp = tempSensor.getData();
        database.gemData(temp, "Temperatur");
        pan.resetTempAlarm();
        pan.setTemp(temp);
        if (temp > maxgr || temp < mingr) {
            pan.setTempAlarm();
        }
    }
 /*metode, der afrunder og evaluere den puls der er hentet fra sensoren
    *har to paramenter som er vores grænseværdier
    *og gemmer denne værdi i databasen*/
    public static void evaluatePuls(double maxgr, double mingr) {
        puls = pulsSensor.getData();
        puls = Math.round(puls);
        database.gemData(puls, "Puls");
        pan.resetPulsAlarm();
        pan.setPuls(puls);
        if (puls > maxgr || puls < mingr) {
            pan.setPulsAlarm();
            
        }
    }
   

}
