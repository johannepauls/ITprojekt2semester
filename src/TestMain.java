
import java.util.*;


public class TestMain {

    private static Sensor tempSensor = new TempSensor();
    private static double temp = 0.0;
    private static Sensor pulsSensor = new PulsSensor();
    private static double puls = 0.0;

    public static void main(String[] args) {

        Thread T = new Thread((Runnable) pulsSensor);
        T.start(); 
        try {
            while (true) {
                temp = tempSensor.getData();
                puls = pulsSensor.getData();
                System.out.println("temperatur: " + temp + " puls: " + puls);

                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(10000);
            }
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException Exception: " + ex);
        }
    }
}
