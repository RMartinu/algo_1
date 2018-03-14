/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.time.LocalDate;

/**
 *
 * @author Robert Martinu
 */
public class DayData {

    private final LocalDate date;
    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final double volume;
    private final double adjClose;

    DayData(LocalDate dIn, double open, double high, double low, double close, double volume, double adjClose) {
        this.date = dIn;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.adjClose = adjClose;
    }

//TODO write Getters 
    LocalDate getDate() {
        return date;
    }

    double getOpenCourse() {
        /*allows using the "open"-course data without being able to alter it*/
        return open;
    }

}
