package cn.tju.scs;

public class Prime {
	/**
     * Finds and prints n prime integers
     * Jeff Offutt, Spring 2003
     */
    static void printPrimes(int n) {
        int curPrime; //Value currently considered for primeness
        int numPrimes; // Number of primes found so far;
        boolean isPrime; //Is curPrime prime?
        int[] primes = new int[1000];// The list of primes.
        
        // Initialize 2 into the list of primes.
        primes[0] = 2;
        numPrimes = 1;
        curPrime = 2;
        while(numPrimes < n) {
            curPrime++; // next number to consider...
            isPrime = true;
            for(int i = 0; i < numPrimes; i++ ) {
                //for each previous prime.
                if(curPrime%primes[i]==0) {
                    //Found a divisor, curPrime is not prime.
                    isPrime = false;
                    break;
                }
            }
            if(isPrime) {
                // save it!
                primes[numPrimes] = curPrime;
                numPrimes++;
            
            }
        }// End while
        
        // print all the primes out
        for(int i = 0; i < numPrimes; i++) {
            System.out.println("Prime: " + primes[i] );

        }
        
    }// End printPrimes.
}
