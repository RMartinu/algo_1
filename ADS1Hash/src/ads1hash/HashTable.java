/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 *
 * @author Robert Martinu
 * @author Julia Pichler
 */
public class HashTable {

    final static String STARTTAG = "<HashTable>";
    final static String ENDTAG = "</HashTable>";
    private int size; //number of elements in map
    private int capacity; //maximum capacity

    /*Stockdata hold the references to Stockdata elements
    *Counter Arrays help managing collisions and probing during deletion*/
    private StockData byName[];
    private int byNameCounter[];
    private StockData[] byAbbreviation;
    private int byAbbreviationCounter[];
    private final PrimeGenerator primeGen;

    HashTable(int desiredCapacity) {
        primeGen = new PrimeGenerator();
        /*Get a prime number marginally large then the desired size,
        *as we want the map to have an undividable amount of elements*/
        capacity = primeGen.findClosestPrime(desiredCapacity * 2);
        byName = new StockData[capacity];
        byNameCounter = new int[capacity];
        byAbbreviation = new StockData[capacity];
        byAbbreviationCounter = new int[capacity];
        size = 0;

    }

    float getLoadFActor() {
        return (float) size / capacity;
    }

    boolean requestResize(int newCapacity) {
        

        int actualNewCap = this.primeGen.findClosestPrime(newCapacity);
        if(this.size>=actualNewCap)
            return false;

        //System.out.print("Resizing");
        // Keep a handle on the current map
        StockData[] temp = byName;

        //create new storage facilities
        //Lets ignore the possibility of failed allocations for now
        byName = new StockData[actualNewCap];
        byNameCounter = new int[actualNewCap];
        byAbbreviation = new StockData[actualNewCap];
        byAbbreviationCounter = new int[actualNewCap];
        size = 0;
        capacity = actualNewCap;

        for (StockData t : temp) {
            this.insert(t);
        }

        //System.out.println("...successfull");
        return true;
    }

    void printState() {
        for (int i = 0; i < capacity; i++) {
            if (byName[i] != null || byNameCounter[i] != 0 || byAbbreviation[i] != null || byAbbreviationCounter[i] != 0) {
                if (byName[i] != null) {
                    System.out.print(byName[i].getName());
                } else {
                    System.out.print("null");
                }
                System.out.print(byAbbreviationCounter[i]);

                if (byAbbreviation[i] != null) {
                    System.out.print(byAbbreviation[i].getAbbreviation());
                } else {
                    System.out.print("null");
                }
                System.out.println(byAbbreviationCounter[i]);
            }
        }
    }

    boolean insert(StockData sd) {

        /*do we even have an object to insert?*/
        if (sd == null) {
            return false;
        }
        /*is there ambiguity?*/
        if ((findByName(sd.getName()) != null) || (findByAbbreviation(sd.getAbbreviation()) != null)) {
            System.err.println("Stock already in DB: " + sd.getName() + " " + sd.getAbbreviation());
            return false;
        }

        //can we even insert the new element?
        //should we try to insert into a table beyond a certain laod factor?
        if (getLoadFActor() > 0.75) {
            //Lets create some additional real estate
            if (requestResize(capacity * 3)) {
                /*Resize worked just as planned, nothing to see, continue*/
            } else {
                //just in case the option to actually fail gracefully at reallocation is implemented
                if(size<capacity)
                {
                    //Still inserting, despite overruning the max load 
                    System.err.println("capacity problems");
                }
                else
                /*Sorry, can't do that, returning*/
                    System.err.println("insertion impossibe");
                return false;
            }
        }

        //fetch the hash of the Dataset to be inserted
        int baseIndex = sd.getNameHash();
        for (int i = 0; i < byName.length; i++) {
            //calculate the index from hash&iteration count
            int actualIndex = getQuadraticProbing(baseIndex, i) % this.capacity;
            //We used this cell, so we increase the usage counter
            byNameCounter[actualIndex]++;
            //if the cell s free: use it
            if (byName[actualIndex] == null) {
                byName[actualIndex] = sd;
                size++;
                break;
            }

            //otherwise probe another cell in the next iteration
            //System.out.println("Had a name collision: "+sd.getName());
        }

        //insert into byAbbreviation table
        //TODO same as above, just different
        baseIndex = sd.getAbbreviationHash();
        for (int i = 0; i < byAbbreviation.length; i++) {
            int actualIndex = getQuadraticProbing(baseIndex, i) % this.capacity;
            byAbbreviationCounter[actualIndex]++;
            if (byAbbreviation[actualIndex] == null) {
                byAbbreviation[actualIndex] = sd;
                break;
            }
            //System.out.println("Had an abbev collision: "+sd.getAbbreviation());
        }

        return true;
    }

    /**
     * removes an object from hastable by reference
     */
    private void delete(StockData deleteMe) {

        int baseIndex = StockData.getHashCode(deleteMe.getName());
        int modifiedIndex;

        for (int i = 0; i < this.capacity; i++) {
            modifiedIndex = getQuadraticProbing(baseIndex, i) % this.capacity;
            byNameCounter[modifiedIndex]--;
            /*use of short circuiting: 
            * if the first condition fails, i.e. cell is empty, the second one will
            * not be evaluated. This prevents NullPointerExceptions*/
            if (byName[modifiedIndex] != null && byName[modifiedIndex].getName().equals(deleteMe.getName())) {
                byName[modifiedIndex] = null;
                break;
            }
        }

        /*removing the entry also from the byAbbreviation table*/
        baseIndex = StockData.getAbbrevHash(deleteMe.getAbbreviation());
        for (int i = 0; i < this.capacity; i++) {
            modifiedIndex = getQuadraticProbing(baseIndex, i) % this.capacity;
            byAbbreviationCounter[modifiedIndex]--;
            if (byAbbreviation[modifiedIndex] != null && byAbbreviation[modifiedIndex].getAbbreviation().equals(deleteMe.getAbbreviation())) {
                byAbbreviation[modifiedIndex] = null;
                size--;
                break;
            }
        }
    }

    /**
     * Searches for an entry by its Name and if found removes it from both of
     * Name and Abbreviation tables
     */
    void deleteByName(String name) {

        StockData deleteMe = findByName(name);
        if (deleteMe == null) {
            return;
        }
        this.delete(deleteMe);

    }

    /**
     * Searches for an entry by Abbreviation and if found removes it from Name
     * and Abbreviation table
     */
    void deleteByAbbreviation(String abbrev) {

        StockData deleteMe = findByAbbreviation(abbrev);
        if (deleteMe == null) {
            return;
        }
        this.delete(deleteMe);
    }

    /**
     * Takes the index of where a object would be inserted by default Adds an
     * offset appopriate to the nth iteration of quadratic probing result needs
     * to be modulo'd to the actual table size.
     *
     * @param baseIndex Where the object should have gone in the first place
     * @param iteration number of previously failed attempt
     */
    int getQuadraticProbing(int baseIndex, int iteration) {
        int newIndex = baseIndex;
        newIndex += Math.pow(iteration, 2); //Just in case someone is pondering higher order probing
        if (newIndex < 0) {
            //System.err.println("overflowm base: " + baseIndex + " iteration " + iteration);
            //should never happen

            return 0;
        }
        return newIndex;
    }

    /**
     * finds an object by name, returns reference if found, null otherwise
     */
    StockData findByName(String name) {

        //System.out.println("Searching for: " + name);
        int baseHash = StockData.getHashCode(name);
        for (int i = 0; i < this.capacity; i++) {
            int modifiedHash = getQuadraticProbing(baseHash, i) % this.capacity;
            if (byName[modifiedHash] != null && byName[modifiedHash].getName().equals(name)) {
                //System.err.println("Found it");
                return byName[modifiedHash];
            }
            if (byNameCounter[modifiedHash] == 0) {
                return null;
            }
        }

        return null;
    }

    //ToDO: find the same way as by name
    StockData findByAbbreviation(String abbreviation) {
        //System.out.println("Searching for: " + abbreviation);
        int baseHash = StockData.getHashCode(abbreviation);
        for (int i = 0; i < this.capacity; i++) {
            int modifiedHash = getQuadraticProbing(baseHash, i) % this.capacity;
            if (byAbbreviation[modifiedHash] != null && byAbbreviation[modifiedHash].getAbbreviation().equals(abbreviation)) {
                //System.err.println("Found it");
                return byAbbreviation[modifiedHash];
            }
            if (byAbbreviationCounter[modifiedHash] == 0) {
                return null;
            }
        }

        return null;
    }

    void saveToFile(File saveTo) throws FileNotFoundException {
        //System.out.println("Saving");
        try (PrintWriter p = new PrintWriter(saveTo)) {
            p.append(STARTTAG).append("\n");
            p.append(Integer.toString(size)).append("\n");
            //Here go the indvidual StockData entries
            for (StockData sd : this.byName) {
                if (sd == null) {//System.err.println("null");

                } else {
                    p.append(sd.createStringRepresentation());
                }
            }

            p.append(ENDTAG);
            p.flush();
            p.close();
        }

    }

    void readFromFile(File readFrom) throws FileNotFoundException {
        PeekableScanner sc = new PeekableScanner(readFrom);
        String t = sc.nextLine();
        if (!t.equals(HashTable.STARTTAG)) {
            System.err.println("Not a valid file");
            sc.close();
            return;
        }
        t = sc.nextLine();
        int prospectiveSize = Integer.parseInt(t);
        capacity = primeGen.findClosestPrime(3 * prospectiveSize);

        this.byAbbreviation = new StockData[capacity];
        this.byAbbreviationCounter = new int[capacity];

        this.byName = new StockData[capacity];
        this.byNameCounter = new int[capacity];

        while (sc.peek().equals(StockData.STARTTAG)) {

            StockData tsd = new StockData(sc);
            System.out.println("Inserting: " + tsd.getName() + " " + tsd.getAbbreviation());
            this.insert(tsd);

        }

        t = sc.nextLine();
        if (!t.equals(HashTable.ENDTAG)) {
            System.err.println("File malformed");
        }
        sc.close();
    }

}
