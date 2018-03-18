/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

/**
 *
 * @author Robert Martinu
 */
public class HashTable {

    private int size; //number of elements in map
    private int capacity; //maximum capacity

    /*Stockdata hold the references to Stockdata elements
    *Counter Arrays help managing collisions and probing during deletion*/
    private StockData byName[];
    private int byNameCounter[];
    private StockData[] byAbbreviation;
    private int byAbbreviationCounter[];
    private PrimeGenerator primeGen;

    HashTable(int desiredCapacity) {
        primeGen = new PrimeGenerator();
        /*Get a prime number marginally large then the desired size,
        *as we want the map to have an undividable amount of elements*/
        capacity = primeGen.findClosestPrime(desiredCapacity);
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
        return false;
    }

    boolean insert(StockData sd) {

        /*do we even have an object to insert*/
        if (sd == null) {
            return false;
        }

        //can we even insert the new element?
        if (size == capacity) {
            //ToDO: trigger relocation to a larger hashtable
            return false;
        }
        //insert into byName table

        int IndexFromHash = sd.getNameHash() % this.capacity;
        if (byName[IndexFromHash] == null) {
            byName[IndexFromHash] = sd;
            byNameCounter[IndexFromHash]++;
        } else {
            int counter = 1;
            while (true) {
                int modifiedIndexFromHash = this.getQuadraticProbing(sd.getNameHash(), counter) % this.capacity;
                if (byName[modifiedIndexFromHash] == null) {
                    byName[modifiedIndexFromHash] = sd;
                    byNameCounter[modifiedIndexFromHash]++;
                    break;
                } else {
                    counter++;
                }
            }
        }

        //insert into byAbbreviation table
        //TODO same as above, just different
        return true;
    }

    void delete(StockData del) {
    }

    void deleteByName(String name) {
        /*does the object even exist?
        *Otherwise the decrements during deletion would mess up the table
        *the returned reference will be useful to retrieve the Abbreviation 
        *so we can remove the Objects entry from the byAbbreviation table*/

        StockData deleteMe = findByName(name);
        if (deleteMe == null) {
            return;
        }

        int baseIndex = StockData.getHashCode(name) % this.capacity;
        int modifiedIndex;

        for (int i = 0; i < this.capacity; i++) {
            modifiedIndex = getQuadraticProbing(baseIndex, i) % this.capacity;
            byNameCounter[modifiedIndex]--;
            if (byName[modifiedIndex] != null && byName[modifiedIndex].getName().equals(name)) {
                byName[modifiedIndex] = null;
                break;
            }
        }

        /*removing the entry also from the byAbbreviation table*/
        baseIndex = StockData.getAbbrevHash(deleteMe.getAbbreviation()) % this.capacity;
        for (int i = 0; i < this.capacity; i++) {
            modifiedIndex = getQuadraticProbing(baseIndex, i) % this.capacity;
            byAbbreviationCounter[modifiedIndex]--;
            if (byAbbreviation[modifiedIndex] != null && byAbbreviation[modifiedIndex].getAbbreviation().equals(deleteMe.getAbbreviation())) {
                byAbbreviation[modifiedIndex] = null;
                break;
            }
        }

    }

    void deleteByAbbreviation(String abbrev) {
    }

    int getQuadraticProbing(int baseIndex, int iteration) {
        int newIndex = baseIndex;
        newIndex += Math.pow(iteration, 2); //Just in case someone is pondering higher order probing

        return newIndex;
    }

    StockData findByName(String name) {

        int baseHash = StockData.getHashCode(name) % this.capacity;
        for (int i = 0; i < this.capacity; i++) {
            int modifiedHash = getQuadraticProbing(baseHash, i) % this.capacity;
            if (byName[modifiedHash] != null && byName[modifiedHash].getName().equals(name)) {
                return byName[modifiedHash];
            }
            if (byNameCounter[modifiedHash] == 0) {
                return null;
            }
        }

        return null;
    }

    StockData findByAbbreviation(String abbreviation) {
        return null;
    }

}
