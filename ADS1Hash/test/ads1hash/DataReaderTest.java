/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Robert Martinu
 */
public class DataReaderTest {
    
    public DataReaderTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of close method, of class DataReader.
     */
    @Test
    public void testClose() {
        System.out.println("close");
        DataReader instance = null;
//        instance.close();
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
    }

    /**
     * Test of getDayData method, of class DataReader.
     */
    @Test
    public void testGetDayData() {
        System.out.println("getDayData");
        URL myLink=null;
        /*Path should refer to a existing file*/
        DataReader instance = new DataReader("D:\\msft.csv");
        
        
        /*Yahoo insists on returning HTTP 401...no joy, with nothing to download*/
//        try {
//             myLink = new URL("https://query1.finance.yahoo.com/v7/finance/download/MSFT?period1=1518832114&period2=1521251314&interval=1d&events=history&crumb=7sexJdtPPKW");
//        } catch (MalformedURLException ex) {
//            Logger.getLogger(DataReaderTest.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //DataReader instance = new DataReader(myLink);
        DayData expResult = null;
        DayData result = instance.getDayData();
        System.out.println(result);
                 result = instance.getDayData();
        System.out.println(result);
                 result = instance.getDayData();
        System.out.println(result);
                 result = instance.getDayData();
        System.out.println(result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
