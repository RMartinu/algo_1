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
        HashTable instance = new HashTable(137);
        boolean expResult = false;
        boolean result = instance.requestResize(newCapacity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
    
    @Test
    public void testDeletionByName()
    {
        
        System.out.println("Testing deletion");
        HashTable instance =null;
        StockData sd1=null, sd2=null, sd3=null;
        StockData result=null;
        sd1=new StockData("ABC", "abc", "abc");
        sd2=new StockData("XYZ", "xyz", "xyz");
        sd3=new StockData("NME", "foe", "yeah");
        
        instance=new HashTable(1000);
        instance.insert(sd1);
        instance.insert(sd2);
        instance.insert(sd3);
        result=instance.findByName("XYZ");
        assertEquals(sd2, result);
        instance.deleteByName("XYZ");
        result=instance.findByName("XYZ");
        assertEquals(null, result);
    }
    
    @Test
    public void testInsert()
    {
        System.out.println("Testing insertion");
        HashTable instance =null;
        StockData sd1=null, sd2=null, sd3=null;
        StockData result=null;
        sd1=new StockData("ABC", "abc", "abc");
        sd2=new StockData("XYZ", "xyz", "xyz");
        sd3=new StockData("NME", "foe", "yeah");
        
        instance=new HashTable(1000);
        instance.insert(sd1);
        instance.insert(sd2);
        instance.insert(sd3);
        result=instance.findByName("XYZ");
        assertEquals(sd2, result);
            
    }
    


    
    /**
     * Test of getQuadraticProbing method, of class HashTable.
     */
    @Test
    public void testGetQuadraticProbing() {
        System.out.println("getQuadraticProbing");
        int hashCode = 0;
        int iteration = 0;
        HashTable instance = new HashTable(1234);
        int expResult = 0;
        int result = instance.getQuadraticProbing(hashCode, iteration);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of findByName method, of class HashTable.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        String name = "";
        HashTable instance = new HashTable(43);
                StockData sd1=null, sd2=null, sd3=null;
        
                        sd1=new StockData("ABC", "abc", "abc");
        sd2=new StockData("XYZ", "xyz", "xyz");
        sd3=new StockData("NME", "foe", "yeah");
        
       
        instance.insert(sd1);
        instance.insert(sd2);
        instance.insert(sd3);
                
        StockData expResult = sd3;
        StockData result = instance.findByName("NME");
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of findByAbbreviation method, of class HashTable.
     */
    @Test
    public void testFindByAbbreviation() {
        System.out.println("findByAbbreviation");
        String abbreviation = "";
        HashTable instance = new HashTable(79);
        StockData expResult = null;
        StockData result = instance.findByAbbreviation(abbreviation);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
       // fail("The test case is a prototype.");
    }

    /**
     * Test of getLoadFActor method, of class HashTable.
     */
    @Test
    public void testGetLoadFActor() {
        System.out.println("getLoadFActor");
        HashTable instance = null;
        float expResult = 0.0F;
        float result = instance.getLoadFActor();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class HashTable.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        StockData del = null;
        HashTable instance = null;
        instance.delete(del);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByName method, of class HashTable.
     */
    @Test
    public void testDeleteByName() {
        System.out.println("deleteByName");
        String name = "";
        HashTable instance = null;
        instance.deleteByName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteByAbbreviation method, of class HashTable.
     */
    @Test
    public void testDeleteByAbbreviation() {
        System.out.println("deleteByAbbreviation");
        String abbrev = "";
        HashTable instance = null;
        instance.deleteByAbbreviation(abbrev);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
