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
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robert Martinu
 */
public class DataReader {

    private Scanner sc;
    private String readOrder[] = new String[12];

    DataReader(InputStream inStream) {

        sc = new Scanner(inStream);
        evalColumnOrder();
        /*throw the first line, with the column names, away*/
    }

    DataReader(URL someLink) {
        try {
            sc = new Scanner(someLink.openStream());
        } catch (IOException ex) {
            Logger.getLogger(DataReader.class.getName()).log(Level.SEVERE, null, ex);
        }

        evalColumnOrder();
    }

    /**
     * Discards first line (column names) Consecutive reads will come from this
     * file
     *
     * @param inputFile
     */
    public DataReader(File inputFile) {
        try {
            sc = new Scanner(inputFile);
            evalColumnOrder();

        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * Opens file specified by fileName Discards first line(containing column
     * names)
     *
     * @param fileName the Filename as String
     */
    public DataReader(String fileName) {

        System.out.println("Going for " + fileName);
        File inputFile;
        try {
            inputFile = new File(fileName);
            sc = new Scanner(inputFile);
            evalColumnOrder();
        } catch (FileNotFoundException e) {
            System.err.println(" File " + fileName + " not found");
        }

    }

    void close() {

        sc.close();

    }

    /**
     * Parses the Column order
     */
    private void evalColumnOrder() {
        String t = sc.nextLine();
        StringTokenizer st = new StringTokenizer(t, ",");
        for (int i = 0; i < readOrder.length; i++) {
            if (st.hasMoreTokens()) {
                readOrder[i] = st.nextToken();
            }
        }
//        for (String s : readOrder) {
//            System.out.println(s);
//        }
    }

    /**
     * Returns exactly on set of Data from the Data source associated with the
     * reader
     */
    DayData getDayData() {

        sc.useDelimiter(",|\\n");
        sc.useLocale(Locale.US);
//       System.err.println("start reading");
        if (!sc.hasNextLine()) {
            return null;
        }
        String date = "";
        double open = 0, high = 0, low = 0, close = 0, adjClose = 0;
        long volume = 0;
        LocalDate ld = null;

        /**
         * Apparently the column order in Yahoos stock CSVs has changed over
         * time, making the reader more convoluted then neccessary Lets hope at
         * leasr their names remain stable.
         */
        for (String Column : readOrder) {
            if (Column == null) {
                continue;
            }
            switch (Column) {
                case "Date": {
                    if (sc.hasNext()) {
                        date = sc.next();
                    } else {
                        return null;
                    }
                    if (date.length() < 8) {
                        System.err.println("TRouble: " + date);
                        return null;
                    }
                    ld = LocalDate.parse(date);
                    if (ld == null) {
                        System.err.println("TRouble: " + date);
                        return null;
                    }
                    break;
                }
                case "Open": {
                    if (sc.hasNextDouble()) {
                        open = sc.nextDouble();
                    } else {
                        return null;
                    }
                    break;
                }
                case "High": {
                    if (sc.hasNextDouble()) {
                        high = sc.nextDouble();
                    } else {
                        return null;
                    }
                    break;
                }
                case "Low": {
                    if (sc.hasNextDouble()) {
                        low = sc.nextDouble();
                    } else {
                        return null;
                    }
                    break;
                }
                case "Close": {
                    if (sc.hasNextDouble()) {
                        close = sc.nextDouble();
                    } else {
                        return null;
                    }
                    break;
                }
                case "Volume": {
                    if (sc.hasNextLong()) {
                        volume = sc.nextLong();
                    } else {
                        return null;
                    }
                    break;
                }
                case "Adj Close": {
                    if (sc.hasNextDouble()) {
                        adjClose = sc.nextDouble();
                    } else {
                        return null;
                    }
                    break;
                }
                default: {
                    System.err.println("I've never been here: " + Column);
                }

            }
        }
        return new DayData(ld, open, high, low, close, volume, adjClose);
        /*
        if (sc.hasNext()) {
            try {
                date = sc.next();
                open = sc.nextDouble();
                high = sc.nextDouble();
                low = sc.nextDouble();
                close = sc.nextDouble();
                
                if(readOrder[6].equals("Volume"))
                        {adjClose = sc.nextDouble();volume = sc.nextLong();
                }else
                {
                volume = sc.nextLong();
                adjClose = sc.nextDouble();}
            

                return new DayData(LocalDate.parse(date), open, high, low, close, volume, adjClose);
            } catch (Exception e) {
                System.err.println("Significant data shortfall: " + e.getMessage());
            }
        }
        return null;*/
    }

}
