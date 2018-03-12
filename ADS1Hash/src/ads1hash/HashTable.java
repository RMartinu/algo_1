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

}
