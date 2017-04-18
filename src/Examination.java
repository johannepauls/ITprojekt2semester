
import java.util.*;
import javax.swing.*;

public class Examination {

    private static List<Double> data = DataStorage.readFromFile();
    private static Gui pan = null;
    private static Sensor tempSensor = new TempSensor("COM6");
    private static double temp = 0.0;
    private static Sensor pulsSensor = new PulsSensor("COM4");
    private static double puls = 0.0;

    public static void main(String[] args) {
        (new Thread((Runnable) pulsSensor)).start();
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
                    evaluatePuls(20.0, 100.0);
                }
                /*vi ved at sensor cirka måler en værdi hvert 10. sekund, vi venter derfor 10 sekunder før vi ønsker at kører igen*/
                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(15000);
            }
        } catch (Exception ex) {
            System.out.println("Serial Port Exception: " + ex);
        }
    }

    /*metode, der evaluere den temperatur der er hentet fra sensoren
    *har to paramenter som er vores grænseværdier*/
    public static void evaluateTemp(double maxgr, double mingr) {
        /*data hentes ind*/
        temp = tempSensor.getData();
        /*værdien gemmes i 'databasen', der ligenu er en fil*/
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

    public static void evaluatePuls(double maxgr, double mingr) {
        puls = pulsSensor.getData();
        System.out.println("puls: "+puls);
                if (puls > maxgr || puls < mingr) {
            /*pan.setAlarm();
            System.out.println("ALARM");*/
        }
    }

}
