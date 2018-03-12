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

    private StockData byName[];
    private StockData byAbbrev[];

    HashTable(int desiredCapacity) {

        capacity = desiredCapacity;
        byName = new StockData[desiredCapacity];
        byAbbrev = new StockData[desiredCapacity];
        size = 0;

    }

    boolean requestResize(int newCapacity) {
        return false;
    }

    int getHashCode(String hashMe) {
        int currentChar;
        int hashCode = 0;
        for (int i = 0; i < hashMe.length(); i++) {
            currentChar = hashMe.charAt(i);
            hashCode = (hashCode * 256 + currentChar) % capacity;
        }

        return hashCode;

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
