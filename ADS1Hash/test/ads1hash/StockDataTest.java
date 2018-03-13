/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

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
        DayData dataPoint = null;
        StockData instance = null;
        instance.insertDayData(dataPoint);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOpeningCourse method, of class StockData.
     */
    @Test
    public void testGetOpeningCourse() {
        System.out.println("getOpeningCourse");
        StockData instance = null;
        double[] expResult = null;
        double[] result = instance.getOpeningCourse();
        assertArrayEquals(expResult, result, 0.1);

        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
