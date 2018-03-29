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
public class PrimeGeneratorTest {
    
    public PrimeGeneratorTest() {
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
     * Test of isPrime method, of class PrimeGenerator.
     */
    @Test
    public void testIsPrime() {
        System.out.println("isPrime");
        
        //ToDo: Test exhaustive
        int Candidate = 2017;
        PrimeGenerator instance = new PrimeGenerator();
        boolean expResult = true;
        boolean result = instance.isPrime(Candidate);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of isMersennePrime method, of class PrimeGenerator.
     */
    @Test
    public void testIsMersennePrime() {
        System.out.println("isMersennePrime");
        int input = 2017;
        PrimeGenerator instance = new PrimeGenerator();
        boolean expResult = false;
        boolean result = instance.isMersennePrime(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of findClosestPrime method, of class PrimeGenerator.
     */
    @Test
    public void testFindClosestPrime() {
        System.out.println("findClosestPrime");
        int input = 400;
        PrimeGenerator instance = new PrimeGenerator(234500);
        int expResult = 401;
        int result = instance.findClosestPrime(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }
    
}
