/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

/**
 *
 * @author Robert Martinu
 */
public class StockData {
        private String Name;
    private String Abbrev;
    private String WKN;
    
   private DayData data[];
   private int fillIndex;
   
   StockData(String name, String Abbreb, String WKN)
   {
       data=new DayData[30];
       fillIndex=0;
   }
   void insertDayData(DayData dataPoint)
   {}
    
}
