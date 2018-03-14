/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;


import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Robert Martinu
 */
public class DataReader {
    
    private Scanner sc;
    private InputStream inStream;
    DataReader(InputStream inStream)
    {
    this.inStream=inStream;
    sc=new Scanner(this.inStream);
    sc.nextLine(); /*throw the first line, with the column names, away*/
    }
    
    DataReader(URL someLink)
    {
        try {
            sc=new Scanner(someLink.openStream());
        } catch (IOException ex) {
            Logger.getLogger(DataReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        sc.nextLine();
    }

    public DataReader(String fileName) {
        sc=new Scanner(fileName);
        sc.nextLine();
    }
    
    
    
    void close()
    {

        sc.close();
        
    }
    
    DayData getDayData()
    {
        //ToDo: someone should handle the potential exceptions
        String date;
        double open, high, low, close, volume, adjClose;
        date=sc.next();
        open=sc.nextDouble();
        high=sc.nextDouble();
        low=sc.nextDouble();
        close=sc.nextDouble();
        volume=sc.nextDouble();
        adjClose=sc.nextDouble();
        
        return new DayData(LocalDate.parse(date), open, high, low, close, volume, adjClose);
    }
    
    
    
}
