/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.time.LocalDate;
import java.util.StringTokenizer;
import javafx.util.converter.LocalDateStringConverter;
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

    /**
     * Test of toString method, of class DayData.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DayData instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class DayData.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object o = null;
        DayData instance = null;
        boolean expResult = false;
        boolean result = instance.equals(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createStringRepresentation method, of class DayData.
     */
    @Test
    public void testCreateStringRepresentation() {
        System.out.println("createStringRepresentation");
        DayData instance = new DayData(LocalDate.parse("2015-03-02"), 45.0,56.43,67.0,78.0,12345,3.14);
        String expResult = "";
        String result = instance.createStringRepresentation();
        System.out.println(result);
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testConstructionFromSerString()
    {
        String input="<DayData>\n 2015-03-02 45.0 56.43 67.0 78.0 12345 3.14 </DayData>\nplus something\nnothing else";
        StringTokenizer st= new StringTokenizer(input,"\n");
        System.out.println("Executing" + st.nextToken()+":\n");
        DayData instance = new DayData(st);
        System.out.println("got me: " + instance.toString());
        System.out.println("What remains: " + st.nextToken());

    }
    
}
