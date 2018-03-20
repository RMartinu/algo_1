/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Robert Martinu
 */
public class StockData {

    private final String Name;
    private final String Abbrev;
    private final String WKN;
    private final int cachedNameHash;
    private final int cachedAbbreviationHash;

//    private DayData data[];
//    private int fillIndex;
    private ArrayList<DayData> data;

    StockData(String name, String Abbrevation, String WKN) {
        this.Name = name;
        this.Abbrev = Abbrevation;
        this.WKN = WKN;
        data = new ArrayList<>();
        //fillIndex = 0;
        cachedNameHash = StockData.getHashCode(Name);
        cachedAbbreviationHash = StockData.getAbbrevHash(Abbrev);
    }

    /**
     * inserts new Datapoint into Dataset Will not insert if there is already a
     * Datapoint for the specific date
     */
    void insertDayData(DayData dataPoint) {

        //check if there is already an entry for this date
        if (data.contains(dataPoint)) {
            System.out.println("Already in there");
            return;
        }
        data.add(dataPoint);
        Collections.sort(data, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
    }

    /**
     * inserts new Datapoints
     * replaces Datapoints that already exists at the date of the new Datapoint
     */
    void replaceDayData(DayData dataPoint) {
        if (data.contains(dataPoint)) {
            System.out.println("Replacing");
            data.remove(data.indexOf(dataPoint));
        }

        data.add(dataPoint);
        Collections.sort(data, (o1, o2) -> o2.getDate().compareTo(o1.getDate()));
    }

    static int getHashCode(String hashMe) {
        int currentChar;
        int hashCode = 0;
        for (int i = 0; i < hashMe.length(); i++) {
            currentChar = hashMe.charAt(i);
            hashCode = (hashCode * 257 + currentChar) % 1405695061;
            /* 1405695061 is a (markov) prime,close to the max value int can represent in java*/
        }

        return hashCode;

    }

    /*the abbreviation is short and has a very limited character set. 
    *we can pack the information more densely the with arbitrary strings,
    *thus increasing hash quality*/
    static int getAbbrevHash(String hashMe) {
        if (hashMe.length() > 6)//to much String, fall back to the default
        {
            return getHashCode(hashMe);
        }

        int hashCode = 0;
        /**
         * Shift the value range of the input string's characters from A to Z to
         * 0-25, we can represent each character by 5 bits and assemble those
         * blocks by shifting them according to their position in the string and
         * adding them together;
         */
        for (int i = 0; i < hashMe.length(); i++) {
            hashCode += ((Character.toUpperCase(hashMe.charAt(i) - 'A') << (5 * i)));
        }

        return hashCode;
    }

    String getName() {
        return this.Name;
    }

    String getAbbreviation() {
        return this.Abbrev;
    }

    String getWKN() {
        return this.WKN;
    }

    int getNameHash() {
        return cachedNameHash;
    }

    int getAbbreviationHash() {
        return cachedAbbreviationHash;
    }

    /**
     * Construct an array of course value(ie. return the content of a specific
     * column in the CSV), will be handed to the plotter
     */
    //TODO: same for all data fields to be plotted
    double[] getOpeningCourse() {
        double values[] = new double[data.size()];
        for (int i = 0; i < data.size(); i++) {
            values[i] = data.get(i).getOpenCourse();
        }

        return values;
    }

    /**
     * Get a specific number of Datapoints
     */
    double[] getOpeningCourse(int amount) {
        amount = (amount < data.size()) ? amount : data.size(); //Do we have enough Data to fullfill the request? Best Effort
        double values[] = new double[amount];
        for (int i = 0; i < amount; ++i) {
            values[i] = data.get(i).getOpenCourse();
        }
        return values;
    }

    /**
     * retrives the most recent Set of datapoints
     */
    DayData getLatestDataPoint() {
        if (data.isEmpty()) {
            return null;
        }
        return this.data.get(0);
    }

    /**
     * compares two Stock entries based on their designation; ignores course
     * data
     */
    boolean equals(StockData other) {
        if (!this.Abbrev.equals(other.Abbrev)) {
            return false;
        }
        if (!this.Name.equals(other.Name)) {
            return false;
        }
        if (!this.WKN.equals(other.WKN)) {
            return false;
        }
        return true;
    }

}
