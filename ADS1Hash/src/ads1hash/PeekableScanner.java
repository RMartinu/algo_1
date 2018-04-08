/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Robert Martinu
 * @author Julia Pichler
 */
public class PeekableScanner {

    private final Scanner sc;
    private String next;

    public PeekableScanner(File in) throws FileNotFoundException {
        sc = new Scanner(in);
        next = sc.hasNextLine() ? sc.nextLine() : null;
    }

    public PeekableScanner(String in) {
        sc = new Scanner(in);
        next = sc.hasNextLine() ? sc.nextLine() : null;
    }

    public boolean hasNext() {
        return next != null;
    }

    public boolean hasNextLine() {
        return next != null;
    }

    public String next() {
        String current = next;
        next = sc.hasNextLine() ? sc.nextLine() : null;
        return current;
    }

    public String peek() {
        return next;
    }

    public String nextLine() {
        //Dulicates next to avoid excessive rewrites
        String current = next;
        next = sc.hasNextLine() ? sc.nextLine() : null;
        return current;
    }

    public void close() {
        sc.close();
    }

}
