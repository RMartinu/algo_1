/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert Martinu
 */
public class DataReader {

    private Scanner sc;


    DataReader(InputStream inStream) {
        
        sc = new Scanner(inStream);
        sc.nextLine();
        /*throw the first line, with the column names, away*/
    }

    DataReader(URL someLink) {
        try {
            sc = new Scanner(someLink.openStream());
        } catch (IOException ex) {
            Logger.getLogger(DataReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        sc.nextLine();
    }

    /**
     *
     * @param inputFile
     */
    public DataReader(File inputFile) {
        try {
            sc = new Scanner(inputFile);
            sc.nextLine();
        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Opens file specified by fileName
     * Discards first line(containing column names)
     * @param fileName the Filename as String
     */
    public DataReader(String fileName) {
     
        System.out.println("Going for " + fileName);
        File inputFile;
        try {
            inputFile = new File(fileName);
            sc = new Scanner(inputFile);
            System.out.println("Discard first line: " + sc.nextLine());
        } catch (FileNotFoundException e) {
            System.err.println(" File " + fileName + " not found");
        }

    }

    void close() {

        sc.close();

    }

    DayData getDayData() {

        sc.useDelimiter(",|\\n");
        sc.useLocale(Locale.US);
        //ToDo: someone should handle the potential exceptions
        String date;
        double open, high, low, close, adjClose;
        long volume;
        try {
            date = sc.next();
            open = sc.nextDouble();
            high = sc.nextDouble();
            low = sc.nextDouble();
            close = sc.nextDouble();
            volume = sc.nextLong();
            adjClose = sc.nextDouble();

            return new DayData(LocalDate.parse(date), open, high, low, close, volume, adjClose);
        } catch (Exception e) {
            System.err.println("Significant data shortfall: " + e.getMessage());
        }
        return null;
    }

}