package sample;

import java.math.BigInteger;
import java.util.ArrayList;

public class MyFME {

    public static BigInteger calculateFME(BigInteger A, BigInteger B, BigInteger C){

        //Step 1
        String bin = B.toString(2); // binárisra váltja

        ArrayList<Integer> hatvanyok = new ArrayList<Integer>();

        int count = 0;
        for(int i = bin.length()-1; i >= 0; i--){
            if(bin.charAt(i) == '1'){
                Double num = Math.pow(2,count);
                hatvanyok.add(num.intValue());
            }
            count++;
        }

        //Step 2

        ArrayList<BigInteger> szorzok = new ArrayList<BigInteger>();
        for(int i : hatvanyok){
            szorzok.add(    A.pow(i).mod(C)   );
        }

        //Step 3

        BigInteger szorzat = BigInteger.ONE;
        for(BigInteger i : szorzok){
            szorzat = szorzat.multiply(i);
        }

        return szorzat.mod(C);
    }



}
