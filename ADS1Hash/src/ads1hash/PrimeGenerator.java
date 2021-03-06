/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads1hash;

import java.util.ArrayList;

/**
 *
 * @author Robert Martinu
 * @author Julia Pichler
 */
public class PrimeGenerator {
    
    int prime[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43,
        47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107,
        109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181,
        191, 193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263,
        269, 271, 277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349,
        353, 359, 367, 373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433,
        439, 443, 449, 457, 461, 463, 467, 479, 487, 491, 499, 503, 509, 521,
        523, 541, 547, 557, 563, 569, 571, 577, 587, 593, 599, 601, 607, 613,
        617, 619, 631, 641, 643, 647, 653, 659, 661, 673, 677, 683, 691, 701,
        709, 719, 727, 733, 739, 743, 751, 757, 761, 769, 773, 787, 797, 809,
        811, 821, 823, 827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887,
        907, 911, 919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997,
        1009, 1013, 1019, 1021, 1031, 1033, 1039, 1049, 1051, 1061, 1063, 1069, 1087, 1091,
        1093, 1097, 1103, 1109, 1117, 1123, 1129, 1151, 1153, 1163, 1171, 1181, 1187, 1193,
        1201, 1213, 1217, 1223, 1229, 1231, 1237, 1249, 1259, 1277, 1279, 1283, 1289, 1291,
        1297, 1301, 1303, 1307, 1319, 1321, 1327, 1361, 1367, 1373, 1381, 1399, 1409, 1423,
        1427, 1429, 1433, 1439, 1447, 1451, 1453, 1459, 1471, 1481, 1483, 1487, 1489, 1493,
        1499, 1511, 1523, 1531, 1543, 1549, 1553, 1559, 1567, 1571, 1579, 1583, 1597, 1601,
        1607, 1609, 1613, 1619, 1621, 1627, 1637, 1657, 1663, 1667, 1669, 1693, 1697, 1699,
        1709, 1721, 1723, 1733, 1741, 1747, 1753, 1759, 1777, 1783, 1787, 1789, 1801, 1811,
        1823, 1831, 1847, 1861, 1867, 1871, 1873, 1877, 1879, 1889, 1901, 1907, 1913, 1931,
        1933, 1949, 1951, 1973, 1979, 1987, 1993, 1997, 1999, 2003, 2011, 2017, 2027, 2029,
        2039, 2053, 2063, 2069, 2081, 2083, 2087, 2089, 2099, 2111, 2113, 2129, 2131, 2137,
        2141, 2143, 2153, 2161, 2179, 2203, 2207, 2213, 2221, 2237, 2239, 2243, 2251, 2267,
        2269, 2273, 2281, 2287, 2293, 2297, 2309, 2311, 2333, 2339, 2341, 2347, 2351, 2357,
        2371, 2377, 2381, 2383, 2389, 2393, 2399, 2411, 2417, 2423, 2437, 2441, 2447, 2459,
        2467, 2473, 2477, 2503, 2521, 2531, 2539, 2543, 2549, 2551, 2557, 2579, 2591, 2593,
        2609, 2617, 2621, 2633, 2647, 2657, 2659, 2663, 2671, 2677, 2683, 2687, 2689, 2693,
        2699, 2707, 2711, 2713, 2719, 2729, 2731, 2741, 2749, 2753, 2767, 2777, 2789, 2791,
        2797, 2801, 2803, 2819, 2833, 2837, 2843, 2851, 2857, 2861, 2879, 2887, 2897, 2903,
        2909, 2917, 2927, 2939, 2953, 2957, 2963, 2969, 2971, 2999, 3001};
    
    boolean isPrime(int Candidate) {
        if (prime[prime.length - 1] < Candidate) {
            //need a large Prime table
            throw new RuntimeException("Need to enlarge array");
        }

        //SlowBoat serch
//        for (int primeInt : prime) {
//            if (Candidate == primeInt) {
//                return true;
//            }
//            if (Candidate % primeInt == 0) {
//                return false;
//            }
//        }
        //Speedy Search
        int lowerBound = 0;
        int upperBound = prime.length;
        int index;
        while (lowerBound <= upperBound) {
            index = (lowerBound + upperBound) / 2;
            if (Candidate == prime[index]) {
                return true;
            }
            if (Candidate > prime[index]) {
                lowerBound = index + 1;
            } else {
                upperBound = index - 1;
            }
        }
        
        return false;
    }
    
    boolean isMersennePrime(int input) {
        int x = 0;
        for (int i = 0; x <= input; ++i) {
            if (x == input) {
                return true;
            }
            x += Math.pow(2, i);
        }
        return false;
    }
    
    int findClosestPrime(int input) {
        // TODO: implement non generic return value
        //System.out.println(prime.length);
        
        if (input >= prime[prime.length - 1]) {
            sieve(input * 2);
        }
        
        int runtime = (int) Math.round(Math.log(prime.length) / Math.log(2)) + 1;
        int x = (int) Math.round(prime.length / 2);
        boolean bigger = true;
        
        for (int i = 0; i < runtime; ++i) {
            if (input == prime[x]) {
                //System.out.println("return equal" + prime[x]);
                return prime[x];
            } else if (input < prime[x]) {
                //System.out.println("smaller than" + prime[x]);
                x -= (int) Math.round(prime.length / (Math.pow(2, i + 2)));
                bigger = true;
                
            } else {
                //System.out.println("bigger than" + prime[x]);
                x += (int) Math.round(prime.length / (Math.pow(2, i + 2)));
                bigger = false;
            }
        }
        int primeCandidate = bigger ? prime[x] : prime[x + 1];
        /*Lets check if its one of those patterny primes, pick the next larger if neccessary*/
        while (isMersennePrime(primeCandidate)) {
            
            primeCandidate = findClosestPrime(primeCandidate + 1);
        }
        return primeCandidate;
        
    }
    
    PrimeGenerator(int toInt) {
//        ArrayList<Integer> primes;
//        primes = new ArrayList<>();
//        for (int i = 0; i <= toInt; i++) {
//            if (isPrime(i) && (!isMersennePrime(i))) {
//                primes.add(i);
//            }
//        }

        sieve(toInt);
        
    }
    
    void sieve(int toInt) {
        /*Using the Sieve of Eratosthenes to create a table of prime numbers*/
        boolean sieve[] = new boolean[toInt + 1];
        for (int i = 0; i < sieve.length; i++) {
            sieve[i] = true;
        }
        /*Zero and One aren't primes by definition*/
        sieve[0] = false;
        sieve[1] = false;
        
        for (int i = 2; i < sieve.length; i++) {
            int runner = 2;
            if (sieve[i] == true) {
                
                while ((i * runner) < sieve.length) {
                    sieve[i * runner] = false;
                    runner++;
                }
            }
            
        }
        
        for (int i = 0; i < sieve.length; i++) {
            if (sieve[i] == true) {
                /*check if the prime in question is a Mersenne prime
                * and exclude it if neccessary*/
                if (isMersennePrime(i)) {
                    sieve[i] = false;
                }
            }
        }
        
        ArrayList<Integer> tempPrimeList = new ArrayList<>();
        for (int i = 0; i < sieve.length; i++) {
            if (sieve[i] == true) {
                tempPrimeList.add(i);
            }
        }
        
        prime = tempPrimeList.stream().mapToInt(i -> i).toArray();

//        for (int i : prime) {
//            System.out.print(i + " ");
//        }
        System.out.println(" done");
    }

    /*Need a default constructor in case the default array is sufficient*/
    public PrimeGenerator() {
    }
    
}
