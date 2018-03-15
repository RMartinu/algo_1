/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.util.ArrayList;

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
        cachedNameHash = this.getHashCode(Name);
        cachedAbbreviationHash = this.getHashCode(Abbrev);
    }

    void insertDayData(DayData dataPoint) {
        //data[fillIndex] = dataPoint;
        //fillIndex++;
        data.add(dataPoint);
    }

    private int getHashCode(String hashMe) {
        int currentChar;
        int hashCode = 0;
        for (int i = 0; i < hashMe.length(); i++) {
            currentChar = hashMe.charAt(i);
            hashCode = (hashCode * 257 + currentChar) % 1405695061;
            /* 1405695061 is a (markov) prime,close to the max value int can represent in java*/
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


    /*Construct an array of course value(ie. return the content of a specific column in the CSV), will be handed to the plotter*/
    //TODO: same for all data fields to be plotted
    double[] getOpeningCourse() {
        double values[] = new double[data.size()];
        for (int i = 0; i < data.size(); i++) {
            values[i] = data.get(i).getOpenCourse();
        }

        return values;
    }

    /*Get a specific number of Datapoints*/
    double[] getOpeningCourse(int amount) {
        amount = (amount < data.size()) ? amount : data.size(); //Do we have enough Data to fullfill the request? Best Effort
        double values[] = new double[amount];
        for (int i = 0; i < amount; ++i) {
            values[i] = data.get(i).getOpenCourse();
        }
        return values;
    }

}
