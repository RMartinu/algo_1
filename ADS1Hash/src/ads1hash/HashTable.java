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
        byName = new StockData[desiredCapacity];
        byNameCounter = new int[desiredCapacity];
        byAbbreviation = new StockData[desiredCapacity];
        byAbbreviationCounter = new int[desiredCapacity];
        size = 0;

    }

    float getLoadFActor() {
        return (float) size / capacity;
    }

    boolean requestResize(int newCapacity) {
        return false;
    }

    boolean insert(StockData sd) {

        //can we even insert the new element?
        if (size == capacity) {
            return false;
        }
        //insert into byName table

        int IndexFromHash = sd.getNameHash() % this.capacity;
        if (byName[IndexFromHash] == null) {
            byName[IndexFromHash] = sd;
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
        *Otherwise the decrements during deletion would mess up the table*/
        if (findByName(name) == null) {
            return;
        }

        int baseIndex = StockData.getHashCode(name) % this.capacity;
        if (byName[baseIndex].getName().equals(name)) {
            byName[baseIndex] = null;
            byNameCounter[baseIndex]--;
        } else {
            byNameCounter[baseIndex]--;
            int counter = 1;
            int modifiedIndex;
            while (counter < this.capacity) {
                modifiedIndex = getQuadraticProbing(baseIndex, counter);
                byNameCounter[modifiedIndex]--;
                if (byName[modifiedIndex].getName().equals(name)) {
                    byName[modifiedIndex] = null;
                    break;
                }
            }

        }

    }

    void deleteByAbbreviation(String abbrev) {
    }

    int getQuadraticProbing(int baseIndex, int iteration) {
        int newIndex = baseIndex;
        newIndex += Math.pow(iteration, 2); //Just in case someone ponders higher order probing

        return newIndex;
    }

    StockData findByName(String name) {

        int baseIndex = StockData.getHashCode(name) % this.capacity;

        //this cell is neither container nor stepping stone
        if (this.byNameCounter[baseIndex] == 0) {
            return null;
        }

        /*if this cell holds a value and this value is the wanted one:
        *return it, we found the target*/
        if (this.byName[baseIndex] != null && this.byName[baseIndex].getName().equals(name)) {
            return this.byName[baseIndex];
        }
        int counter = 1;
        int modifiedIndex;
        while (counter < this.capacity) {
            modifiedIndex = getQuadraticProbing(baseIndex, counter) % this.capacity;
            if (this.byNameCounter[modifiedIndex] == 0) {
                return null;
            }
            if (this.byNameCounter[modifiedIndex] == 0) {
                break;
            }
            if (this.byName[modifiedIndex] != null && this.byName[modifiedIndex].getName().equals(name)) {
                return byName[modifiedIndex];
            }
        }

        return null;
    }

    StockData findByAbbreviation(String abbreviation) {
        return null;
    }

}
