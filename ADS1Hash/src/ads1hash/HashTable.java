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
    }

    void deleteByAbbreviation(String abbrev) {
    }

    int getQuadraticProbing(int hashCode, int iteration) {
        int newIndex = hashCode;
        newIndex += Math.pow(iteration, 2); //Just in case someone ponders higher order probing

        return newIndex;
    }

    StockData findByName(String name) {
        return null;
    }

    StockData findByAbbreviation(String abbreviation) {
        return null;
    }

}
