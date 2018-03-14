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
public class HashTableTest {
    
    public HashTableTest() {
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
     * Test of requestResize method, of class HashTable.
     */
    @Test
    public void testRequestResize() {
        System.out.println("requestResize");
        int newCapacity = 0;
        HashTable instance = null;
        boolean expResult = false;
        boolean result = instance.requestResize(newCapacity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    
    /**
     * Test of getQuadraticProbing method, of class HashTable.
     */
    @Test
    public void testGetQuadraticProbing() {
        System.out.println("getQuadraticProbing");
        int hashCode = 0;
        int iteration = 0;
        HashTable instance = null;
        int expResult = 0;
        int result = instance.getQuadraticProbing(hashCode, iteration);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class HashTable.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "";
        HashTable instance = null;
        StockData expResult = null;
        StockData result = instance.findByName(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of findByAbbreviation method, of class HashTable.
     */
    @Test
    public void testFindByAbbreviation() {
        System.out.println("findByAbbreviation");
        String abbreviation = "";
        HashTable instance = null;
        StockData expResult = null;
        StockData result = instance.findByAbbreviation(abbreviation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
