
import java.util.*;
import javax.swing.*;

public class Examination {

    private static List<Double> data = DataStorage.readFromFile();
    private static Gui pan = null;
    private static Sensor tempSensor = new TempSensor();
    private static double temp = 0.0;
    private static Sensor pulsSensor = new PulsSensor();
    private static double puls = 0.0;

    public static void main(String[] args) {
        Thread T = new Thread((Runnable) pulsSensor);
        T.start();
        /*vindue til Gui oprettes og tilpasses:
        *størrelsen sættes automatisk med pack()?
        *programmet afsluttes når vinduet lukkes*/
        JFrame ramme = new JFrame();
        pan = new Gui();
        ramme.add(pan);
        ramme.pack();
        ramme.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ramme.setVisible(true);
        try {
            /*programmet vil pga. løkke køre til vi lukker det*/
            while (true) {
                /*programmet tester hvorvidt vi har trykket på start*/
                if (pan.isStartet()) {
                    /*hvis vi har trykket på start knappen kaldes evaluer-metoden med de grænseværdier der er valgt på grænsefladen*/
                    evaluateTemp(pan.getMax(), pan.getMin());
                }
                /*vi ved at sensor cirka måler en værdi hvert 10. sekund, vi venter derfor 10 sekunder før vi ønsker at kører igen*/
                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(10000);
            }
        } catch (Exception ex) {
            System.out.println("Serial Port Exception: " + ex);
        }
        //getSensorData();//til test

    }

    /*
    /*metode, der evaluere den temperatur der er hentet fra sensoren
    *har to paramenter som er vores grænseværdier*/
    public static void evaluateTemp(double maxgr, double mingr) {
        /*data hentes ind*/
        double temp = tempSensor.getData();
        /*værdien gemmes i 'ddouble temp = tempSensor.getData();atabasen', der ligenu er en fil*/
        DataStorage.writeToFile(temp);
        /*label på MyPanel der vises ved alarm nulstilles. 
        *Så alarm beskeden fjernes når temperaturen ikke længere er udenfor normalområdet*/
        pan.resetAlarm();
        /*label på MyPanel, der viser den temperatur vi arbejder med*/
        pan.setTemp(temp);
        System.out.println(temp);
        /*løkke, der viser en alarm på MyPanel når grænseværdierne overskrides*/
        if (temp > maxgr || temp < mingr) {
            pan.setAlarm();
            System.out.println("ALARM");
        }
    }
}
