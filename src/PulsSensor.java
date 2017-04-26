
import jssc.*;
import java.lang.*;
import java.util.*;

/*Nedarver fra sensorklassen*/
public class PulsSensor extends Sensor implements Runnable {

    private String data;
    private String rest = "";
    private String alldata;
    private String[] stringArray;
    private ArrayList<Double> valueArray = new ArrayList<Double>();
    private Double value;
    private double pulseValue;
    private boolean found;
    private boolean foundBefore;
    private double n = 0;
    private double pulse;

    /*Der oprettes forbindelse og der indlæses en måling. */
    public PulsSensor(String port) {
        super(port);

        try {
            if (serialPort.getInputBufferBytesCount() > 0) {
                data = serialPort.readString();
            }
        } catch (SerialPortException ex) {
            System.out.println("Serial Port Exception: " + ex);
        }
        /*Derefter klippes den sidste bid af fra skilletegnet, og sætter det på den første reelle måling*/
        int last = data.lastIndexOf("!");
        if (last > -1 && last < data.length() - 1) {
            rest = data.substring(last + 1);
        }
    }

    @Override
    public void run() {
        /*Henter data ind og beregner pulsen, og derefter "sover" den i 10 sekunder*/
        for (;;) {
            try {
                if (serialPort.getInputBufferBytesCount() > 0) {
                    data = serialPort.readString();
                }
            } catch (SerialPortException ex) {
                System.out.println("Serial Port Exception: " + ex);
            }

            alldata = rest + data;

            if (!alldata.endsWith("!")) {
                rest = alldata.substring(alldata.lastIndexOf("!") + 1);
                alldata = alldata.substring(0, alldata.lastIndexOf("!") + 1);
            } else {
                rest = "";
            }

            stringArray = alldata.split("!");

            for (int i = 0; i < stringArray.length - 1; i++) {
                value = null;
                try {
                    value = new Double(stringArray[i]);
                    if (value > 5.0) {
                        value = null;
                    }
                } catch (NumberFormatException ex) {
                    value = null;
                }
                if (value == null) {
                    if (i > 0) {
                        value = valueArray.get(i - 1);
                    } else {
                        value = 0.0;
                    }
                }

                valueArray.add(value);
            }
            /*for-løkke der tæller antallet af pulsslag
            *denne leder efter overgangen over værdien 3, vha. to boolske værdier*/
            for (int t = 0; t < valueArray.size() - 1; t++) {
                pulseValue = valueArray.get(t);
                foundBefore = found;
                if (pulseValue >= 3) {
                    found = true;
                } else {
                    found = false;
                }

                if (!foundBefore && found) {
                    n++;
                }
            }
            pulse = (n / ((valueArray.size()) / 200.0)) * 60;

            n = 0;
            valueArray.clear();
            try {
                Thread.sleep(10000);
            } catch (Exception e) {
            }
        }
    }

    @Override
    public double getData() {
        /*Returnere en pulsværdi*/
        return pulse;
    }

}
