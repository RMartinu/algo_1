/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.time.LocalDate;
import java.util.StringTokenizer;

/**
 *
 * @author Robert Martinu
 */
public class DayData implements Serialize {

    /*For educational purposes only! 
    *!!never use float/double in actual financial software!!*/
    static final String STARTTAG = "<DayData>";
    static final String ENDTAG = "</DayData>";
    private final LocalDate date;
    private final double open;
    private final double high;
    private final double low;
    private final double close;
    private final long volume;
    private final double adjClose;

    DayData(LocalDate dIn, double open, double high, double low, double close, long volume, double adjClose) {
        this.date = dIn;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.volume = volume;
        this.adjClose = adjClose;
    }

    public DayData(String input) {

        StringTokenizer Deserialize = new StringTokenizer(input);

        String temp = Deserialize.nextToken(" ");

        if (temp.startsWith(STARTTAG)) {
            this.date = LocalDate.parse(temp.substring(STARTTAG.length()));
            this.open = Double.parseDouble(Deserialize.nextToken());
            this.high = Double.parseDouble(Deserialize.nextToken());
            this.low = Double.parseDouble(Deserialize.nextToken());
            this.close = Double.parseDouble(Deserialize.nextToken());
            this.volume = Long.parseLong(Deserialize.nextToken());
            this.adjClose = Double.parseDouble(Deserialize.nextToken());
            String check = Deserialize.nextToken("\n");
            if (check.endsWith(ENDTAG)) {//System.out.println("everythings fine");

            } else {
                System.err.println("rest fail");
                throw new RuntimeException("restoration failed");
            }
        } else {
            System.err.println("Didn't start w proper Tag: " + temp);
            throw new RuntimeException("restore failed");
        }

    }

//TODO write Getters 
    LocalDate getDate() {
        return date;
    }

    double getOpenCourse() {
        /*allows using the "open"-course data without being able to alter it*/
        return open;
    }
    
    double getHighestCourse() {
        /*allows using the "open"-course data without being able to alter it*/
        return high;
    }
    
    double getLowestCourse() {
        /*allows using the "open"-course data without being able to alter it*/
        return low;
    }
    
    double getCloseCourse() {
        /*allows using the "open"-course data without being able to alter it*/
        return close;
    }
    
    double getVolume() {
        /*allows using the "open"-course data without being able to alter it*/
        return volume;
    }
    
    double getAdjustedCloseCourse() {
        /*allows using the "open"-course data without being able to alter it*/
        return adjClose;
    }

    @Override
    public String toString() {
        return "DayData{" + "date=" + date + ", open=" + open + ", high=" + high + ", low=" + low + ", close=" + close + ", volume=" + volume + ", adjClose=" + adjClose + '}';
    }

    /**
     * Overridden to allow for checks for equal date in standard containers
     *
     * @param o A Daydata object whichs timestamp will be matched to this object
     * @return Equality of Date, ignoring course values
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof DayData) {
            DayData d = (DayData) o;
            return d.date.isEqual(date);
        }
        return false;
    }

    @Override
    public String createStringRepresentation() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

        StringBuilder sb = new StringBuilder(STARTTAG);
        sb.append(date.toString()).append(" ");
        sb.append(open).append(" ");
        sb.append(high).append(" ");
        sb.append(low).append(" ");
        sb.append(close).append(" ");
        sb.append(volume).append(" ");
        sb.append(adjClose).append(" ");
        sb.append(ENDTAG).append("\n");

        return sb.toString();
    }

}
