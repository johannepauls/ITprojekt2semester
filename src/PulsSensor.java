
import jssc.*;
import java.lang.*;
import java.util.*;
import java.io.*;

public class PulsSensor extends Sensor implements Runnable {

    static String data;
    static String rest = "";
    static String aldata;
    static String[] dataArray;
    static int last;
    static ArrayList<Double> valueArray = new ArrayList<Double>();
    static double value;
    static double pulseValue;
    static boolean found;
    static boolean a;
    static double n = 0;
    static double antalmalinger;
    static double time;
    static double pulseprsek;
    static double pulse;

    public PulsSensor() {
        super("COM8");

        try {
            if (serialPort.getInputBufferBytesCount() > 0) {
                data = serialPort.readString();
            }
        } catch (SerialPortException ex) {
            System.out.println("Serial Port Exception: " + ex);
        }
        last = data.lastIndexOf("!");
        if (last > -1 && last < data.length() - 1) {
            rest = data.substring(last + 1);
        }
    }

    @Override
    public void run() {
        try {
            if (serialPort.getInputBufferBytesCount() > 0) {
                data = serialPort.readString();
            }
        } catch (SerialPortException ex) {
            System.out.println("Serial Port Exception: " + ex);
        }

        aldata = rest + data;

        if (!aldata.endsWith("!")) {
            rest = aldata.substring(aldata.lastIndexOf("!") + 1);
            aldata = aldata.substring(0, aldata.lastIndexOf("!") + 1);
        } else {
            rest = "";
        }

        dataArray = aldata.split("!");

        for (int i = 0; i < dataArray.length - 1; i++) {
            Double value = null;
            try {
                value = new Double(dataArray[i]);
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
            a = found;
            if (pulseValue >= 3) {
                found = true;
            } else {
                found = false;
            }

            if (!a && found) {
                n++;
            }
        }
        /*puls beregning
		*tiden bestemmes ved antal målinger delt med 200, da vi tager 200 målinger i sekundet (se timer i Arduino)*/
        antalmalinger = valueArray.size();
        time = antalmalinger / 200.0;
        pulseprsek =  n / time;

        pulse = pulseprsek * 60;
    }

    @Override
    public double getData() {
        return pulse;

    }

}
