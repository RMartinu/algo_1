/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.time.LocalDate;
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
public class DayDataTest {
    
    public DayDataTest() {
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
     * Test of getDate method, of class DayData.
     */
    @Test
    public void testGetDate() {
        System.out.println("getDate");
        DayData instance = new DayData(LocalDate.parse("2015-03-02"), 0, 0, 0, 0, 0, 0);
        LocalDate expResult = LocalDate.parse("2015-03-02");
        LocalDate result = instance.getDate();
        //assertEquals(expResult, result);
        assertTrue(expResult.equals(result));
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getOpenCourse method, of class DayData.
     */
    @Test
    public void testGetOpenCourse() {
        System.out.println("getOpenCourse");
        DayData instance = new DayData(LocalDate.parse("2011-01-15"), 30, 0, 0, 0, 0, 0);
        double expResult = 30.0;
        double result = instance.getOpenCourse();
        assertEquals(expResult, result, 0.0001);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
}
