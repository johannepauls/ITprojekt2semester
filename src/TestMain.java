
import java.util.*;

public class TestMain {

    private static Sensor tempSensor = new TempSensor("COM6");
    private static double temp = 0.0;
    private static PulsSensor pulsSensor = new PulsSensor("COM4");
    private static double puls = 0.0;

    public static void main(String[] args) {

        (new Thread(pulsSensor)).start();
        try {
            while (true) {

                java.util.concurrent.TimeUnit.MILLISECONDS.sleep(20000);
                temp = tempSensor.getData();
                puls = pulsSensor.getData();
                System.out.println("temperatur: " + temp + " puls: " + puls);
            }
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException Exception: " + ex);
        }
    }
}
