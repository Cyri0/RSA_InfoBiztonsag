package sample;

import java.math.BigInteger;

public class MRTest {

    private static BigInteger biPow(BigInteger base, BigInteger exponent) {
        BigInteger result = BigInteger.ONE;
        while (exponent.signum() > 0) {
            if (exponent.testBit(0)) result = result.multiply(base);
            base = base.multiply(base);
            exponent = exponent.shiftRight(1);
        }
        return result;
    }

    private static boolean doMillerRabin(BigInteger n, BigInteger a) {

        if (n.remainder(BigInteger.TWO).equals(BigInteger.ZERO)){
            return false;
        }

        BigInteger s = BigInteger.ZERO;
        BigInteger d = n.subtract(BigInteger.ONE);   // n-1
        do{
            d = d.divide(BigInteger.TWO);
            s = s.add(BigInteger.ONE);
        }while(d.remainder(BigInteger.TWO).equals(BigInteger.ZERO));

        // TEST 1 - a^d mod n
        BigInteger ad = biPow(a,d);

        BigInteger test1 = ad.mod(n);
        if(test1.equals(n.subtract(BigInteger.ONE))) {
            return true;
        }

        // TEST 2 - a^(2^i)*d

        BigInteger i = BigInteger.ONE;
        BigInteger previousResult = BigInteger.ZERO;

        while(!i.equals(s)){
            BigInteger result;
            if(previousResult.equals(BigInteger.ZERO)) {
                result = biPow(a, biPow(BigInteger.TWO, i).multiply(d));
                previousResult = result;
            } else {
                result = previousResult.pow(2);
                previousResult = result;
            }

            BigInteger test2 = result.mod(n);

            if(test2.equals(n.subtract(BigInteger.ONE))) {
                return true;
            }
            i = i.add(BigInteger.ONE);
        }
        return false;
    }

    protected static boolean isPrime(BigInteger n){

        int[] testNumbers = {2,3,5};

        for(int num : testNumbers){
            if(! MRTest.doMillerRabin(n, new BigInteger(Integer.toString(num))))
                return false;
        }
        return true;
    }

}