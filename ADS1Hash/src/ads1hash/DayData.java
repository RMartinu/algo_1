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
    
private LocalDate date;
private double open;
private double high;
private double low;
private double close;
private double volume;
private double adjClose;

DayData(LocalDate dIn, double open, double high, double low, double close, double volume, double adjClose){
    this.date = dIn;
    this.open = open;
    this.high = high;
    this.low = low;
    this.close = close;
    this.volume = volume;
    this.adjClose = adjClose;
}

//TODO write Getters 
LocalDate getDate()
{
    return date;
}

double getOpenCourse()
{
    /*allows using the "open"-course data without being able to alter it*/
    return open;
}

}
