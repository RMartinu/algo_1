/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
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
public class StockDataTest {
    
    public StockDataTest() {
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
     * Test of insertDayData method, of class StockData.
     */
    @Test
    public void testInsertDayData() {
        System.out.println("insertDayData");
        DayData dataPoint=null;
        StockData instance = new StockData("Dummy", "DUM", "123456");
        
                File inputFile=new File("D:\\msft.csv");
        DataReader dr = new DataReader(inputFile);
        
        for(int i =0; i<5; i++)
        {
            dataPoint=dr.getDayData();
            instance.insertDayData(dataPoint);}
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
        instance.insertDayData(dataPoint);
        System.out.println(Arrays.toString(instance.getOpeningCourse()));
    }

    /**
     * Test of getOpeningCourse method, of class StockData.
     */
    @Test
    public void testGetOpeningCourse() {
        System.out.println("getOpeningCourse");
        fail("The test case is a prototype.");
        StockData instance = null;
        double[] expResult = null;
        double[] result = instance.getOpeningCourse();
        assertArrayEquals(expResult, result, 0.1);

        // TODO review the generated test code and remove the default call to fail.
        
    }
    
    @Test
    public void testEquals()
    {
          fail("The test case is a prototype.");
    }      

    /**
     * Test of getHashCode method, of class StockData.
     */
    @Test
    public void testGetHashCode() {
        System.out.println("getHashCode");
        String hashMe = "";
        int expResult = 0;
        int result = StockData.getHashCode(hashMe);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAbbrevHash method, of class StockData.
     */
    @Test
    public void testGetAbbrevHash() {
        System.out.println("getAbbrevHash");
        String hashMe = "";
        int expResult = 0;
        int result = StockData.getAbbrevHash(hashMe);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class StockData.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        fail("The test case is a prototype.");
        StockData instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAbbreviation method, of class StockData.
     */
    @Test
    public void testGetAbbreviation() {
        System.out.println("getAbbreviation");
        fail("The test case is a prototype.");
        StockData instance = null;
        String expResult = "";
        String result = instance.getAbbreviation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWKN method, of class StockData.
     */
    @Test
    public void testGetWKN() {
        System.out.println("getWKN");
        fail("The test case is a prototype.");
        StockData instance = null;
        String expResult = "";
        String result = instance.getWKN();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNameHash method, of class StockData.
     */
    @Test
    public void testGetNameHash() {
        System.out.println("getNameHash");
        fail("The test case is a prototype.");
        StockData instance = null;
        int expResult = 0;
        int result = instance.getNameHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        
    }

    /**
     * Test of getAbbreviationHash method, of class StockData.
     */
    @Test
    public void testGetAbbreviationHash() {
        System.out.println("getAbbreviationHash");
        fail("The test case is a prototype.");
        StockData instance = null;
        int expResult = 0;
        int result = instance.getAbbreviationHash();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOpeningCourse method, of class StockData.
     */
    @Test
    public void testGetOpeningCourse_0args() {
        System.out.println("getOpeningCourse");
        fail("The test case is a prototype.");
        StockData instance = null;
        double[] expResult = null;
        double[] result = instance.getOpeningCourse();
        //assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOpeningCourse method, of class StockData.
     */
    @Test
    public void testGetOpeningCourse_int() {
        System.out.println("getOpeningCourse");
        fail("The test case is a prototype.");
        int amount = 0;
        StockData instance = null;
        double[] expResult = null;
        double[] result = instance.getOpeningCourse(amount);
        //assertArrayEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLatestDataPoint method, of class StockData.
     */
    @Test
    public void testGetLatestDataPoint() {
        System.out.println("getLatestDataPoint");
        StockData instance = new StockData("Test", "tess", "nic");
        DayData td=new DayData(LocalDate.parse("2001-01-05"), 01, 02, 03, 04, 05, 06);
        instance.insertDayData(td);
        DayData expResult = td;
        DayData result = instance.getLatestDataPoint();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
