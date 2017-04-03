/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testkode;

import java.io.*;
import java.util.*;

/*DataStorage klassen er vores database, og fungere i praksis ved at gemme sensorværdierne i en fil*/
public class DataStorage {
     
    public static String fileName = "TempFile.txt";
    
    public static void main(String[] args){
        double[] temp= {8,19,38,46,24.2,28,74.6,1};
        List<Double> vardier = new ArrayList<>();
        vardier = readFromFile();
        /*for(int i=0; i<temp.length;i++){
            writeToFile(temp[i]);
        
        }*/
        System.out.println(""+vardier.toString());
       /* for(int n=0; n<vardier.size()-1; n++){
            String a = (String) ; 
        }*/
        
    
    }
    
    /*metode, der gemmer værdierne i filen ved navn TempFile.txt. De gemmes med et linje skift og temperaturdataen*/
    public static void writeToFile(double temp) {
        
        try {
            FileWriter file = new FileWriter(fileName, true);
            file.write("\n" + temp);
            file.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    /*metode, der henter en list af doubles, der bruges til at tegne grafen med*/
    public static List<Double> readFromFile() {
        /*værdi der bestemmer antallet af punkter på grafen*/
        int numberOfValue = 10;
        try {
            /*alle værdier i filen, hentes ind, og tilføjes til en arraylist*/
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            List<String> allLines = new ArrayList<String>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                allLines.add(line);
            }
            /*arraylisten allLines vendes så værdierne nu står i omvendt rækkefølge.*/
            Collections.reverse(allLines);
        
            /*filen lukkes og vi arbejder nu videre med en nu arraylist
            *vi ønsker at tilføje 'numberOfValue' - antal elementer (i dette tilfælde 10)
            *vi henter værdierne ind og konvertere dem til doubles*/
            fileReader.close();
            List<Double> values = new ArrayList<>();

            for (int i = 0; i < numberOfValue; i++) {
                values.add(Double.parseDouble(allLines.get(i)));
            }
            /*vi vender nu arraylisten med sensorværdierne, så den nyeste værdi tilføjes i enden af grafen og ikke i starten*/
            Collections.reverse(values);
            return values;
        } catch (Exception e) {
            System.out.println(e);
            return new ArrayList<Double>();
        }
    }
}
