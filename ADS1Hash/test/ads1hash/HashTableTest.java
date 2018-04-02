/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.io.File;
import java.util.ArrayList;
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
        boolean expResult = true;
        boolean result = instance.requestResize(newCapacity);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    @Test
    public void testInsert() {
        System.out.println("Testing insertion");
        HashTable instance;
        StockData sd1, sd2, sd3;
        StockData result;
        sd1 = new StockData("ABC", "abc", "abc");
        sd2 = new StockData("XYZ", "xyz", "xyz");
        sd3 = new StockData("NME", "foe", "yeah");

        instance = new HashTable(1000);
        instance.insert(sd1);
        instance.insert(sd2);
        instance.insert(sd3);
        result = instance.findByName("XYZ");
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
        StockData sd1, sd2, sd3;

        sd1 = new StockData("ABC", "abc", "abc");
        sd2 = new StockData("XYZ", "xyz", "xyz");
        sd3 = new StockData("NME", "foe", "yeah");

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
        fail("The test case is a prototype.");
        HashTable instance = null;
        float expResult = 0.0F;
        float result = instance.getLoadFActor();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of deleteByName method, of class HashTable.
     */
    @Test
    public void testDeleteByName() {

        System.out.println("Testing deletion");
        HashTable instance;
        StockData sd1, sd2, sd3;
        StockData result;
        sd1 = new StockData("ABC", "abc", "abc");
        sd2 = new StockData("XYZ", "xyz", "xyz");
        sd3 = new StockData("NME", "foe", "yeah");

        instance = new HashTable(1000);
        instance.insert(sd1);
        instance.insert(sd2);
        instance.insert(sd3);
        result = instance.findByName("XYZ");
        assertEquals(sd2, result);
        instance.deleteByName("XYZ");
        result = instance.findByName("XYZ");
        assertEquals(null, result);
        // TODO review the generated test code and remove the default call to fail.
        // fail("The test case is a prototype.");
    }

    @Test
    public void testLotsOfInsertions() {
        System.out.println("Lots of inserts");
        HashTable instance = new HashTable(100);
        StockData ds[] = new StockData[1750];
        StringBuilder sbName = new StringBuilder("13");
        for (int i = 0; i < ds.length; i++) {
            sbName.setCharAt(0, (char) ('A' + (i / 26)));
            sbName.setCharAt(1, (char) ('A' + (i % 26)));
            System.out.print(sbName.toString() + "_");
            ds[i] = new StockData("Name " + sbName.toString(), "AB" + sbName.toString(), "123");
            instance.insert(ds[i]);
        }
        for (StockData s : ds) {
            StockData retByName = instance.findByName(s.getName());
            StockData retByAbbreviation = instance.findByAbbreviation(s.getAbbreviation());

            assertEquals(retByName, s);
            assertEquals(retByAbbreviation, s);
        }

        System.out.println("Done w printo");
        //fail("nothing toworry about");
    }

    @Test
    public void testChainedOperations() {
        System.out.println("A Sequence of insertions, deletions interspresed with searches");

        HashTable instance = new HashTable(200);
        StockData insertable[] = new StockData[5000];

        StockData killedOf[] = new StockData[5000];

        StringBuffer sb = new StringBuffer("xBC");
        int firstDigit = 0, secondDigit = 0, thirdDigit = 0;
        for (int i = 0; i < 3834; i++) {

            thirdDigit %= 25;
            secondDigit %= 25;
            firstDigit %= 25;
            if (thirdDigit == 24) {
                secondDigit++;
            }
            if (secondDigit > 22) {
                firstDigit++;
            }

            sb.setCharAt(0, (char) ('A' + firstDigit));
            sb.setCharAt(1, (char) ('A' + secondDigit));
            sb.setCharAt(2, (char) ('A' + thirdDigit));
            thirdDigit++;
            StockData t = new StockData("Name " + sb.toString(), sb.toString(), Integer.toString(firstDigit * 26 * 26 + secondDigit * 26 + thirdDigit));
            int index = 0;
            while (insertable[index++] != null) {
                insertable[index] = t;
            }
            instance.insert(t);

        }

        for (StockData searchFor : insertable) {
            if (searchFor != null) {
                StockData retValName = instance.findByName(searchFor.getName());
                StockData retValAbbrev = instance.findByAbbreviation(searchFor.getAbbreviation());
                assertEquals(retValName, retValAbbrev);
                assertEquals(retValName, searchFor);
            }
        }

        for (int i = 0; i < insertable.length; i++) {
            if (Math.random() * insertable.length > 0.8) {
                if (insertable[i] == null) {
                    continue;
                }
                StockData transfer = insertable[i];
                insertable[i] = null;
                int index = 0;
                while (killedOf[index++] != null);
                killedOf[index] = transfer;
                assertNull("found abbev demon", instance.findByAbbreviation(transfer.getAbbreviation()));
                assertNull("found a demon", instance.findByName(transfer.getName()));

            }
        }

        for (StockData searchFor : insertable) {
            if (searchFor != null) {
                StockData retValName = instance.findByName(searchFor.getName());
                StockData retValAbbrev = instance.findByAbbreviation(searchFor.getAbbreviation());
                assertEquals(retValName, retValAbbrev);
                assertEquals(retValName, searchFor);
            }
        }

    }

    /**
     * Test of deleteByAbbreviation method, of class HashTable.
     */
    @Test
    public void testDeleteByAbbreviation() {
        System.out.println("deleteByAbbreviation");
        fail("The test case is a prototype.");
        String abbrev = "";
        HashTable instance = null;
        instance.deleteByAbbreviation(abbrev);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of delete method, of class HashTable.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        fail("The test case is a prototype.");
        StockData deleteMe = null;
        HashTable instance = null;
        instance.delete(deleteMe);
        // TODO review the generated test code and remove the default call to fail.

    }

    /**
     * Test of saveToFile method, of class HashTable.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSaveToFile() throws Exception {
        //fail();
        System.out.println("saveToFile");
        File saveTo = new File("D:\\httest1.txt");
        HashTable instance = new HashTable(30);
        instance.insert(new StockData("TestCo", "TC", "4321"));
        instance.saveToFile(saveTo);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    /**
     * Test of readFromFile method, of class HashTable.
     */
    @Test
    public void testReadFromFile() throws Exception {
        System.out.println("readFromFile");
        File readFrom = new File("D:\\httest.txt");
        HashTable instance = new HashTable(40);

        instance.readFromFile(readFrom);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

}
