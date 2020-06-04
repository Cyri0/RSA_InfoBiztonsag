package sample;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Random;

public class MyRSA {

    private Key k = null;
    private SecretKey sk = null;

    public MyRSA (){
        keyGeneration();
    }

    private BigInteger pickRandomBigInteger(){
        BigInteger num;

        BigInteger maxLimit = new BigInteger("500000");
        BigInteger minLimit = new BigInteger("250000");
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        Random randNum = new Random();
        int len = maxLimit.bitLength();
        num = new BigInteger(len, randNum);
        if (num.compareTo(minLimit) < 0)
            num = num.add(minLimit);
        if (num.compareTo(bigInteger) >= 0)
            num = num.mod(bigInteger).add(minLimit);
        return num;
    }


    private BigInteger generateE(BigInteger fi_n){
        Random rand = new SecureRandom();
        BigInteger e;
        do{
            e = new BigInteger(fi_n.bitLength(), rand);
        } while (e.compareTo(BigInteger.ONE) <= 0
                || e.compareTo(fi_n) >= 0
                || !e.gcd(fi_n).equals(BigInteger.ONE));
        BigInteger d = e.modInverse(fi_n);

        return e;
    }

    private void keyGeneration(){
        BigInteger p;
        BigInteger q;

        // P kiválasztása
        do{
            p = pickRandomBigInteger();
        }while(!MRTest.isPrime(p));

        // Q kiválasztása
        do{
            q = pickRandomBigInteger();
            if(q.equals(p)){
                while(q.equals(p)){
                    q = pickRandomBigInteger();
                }
            }
        }while(!MRTest.isPrime(q));

        BigInteger n = p.multiply(q);
        BigInteger fi_n = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE));

        BigInteger e = generateE(fi_n);

        // EEA alkalmazása
        BigInteger d = MyEEA.calculateD(e, fi_n);
        System.out.println("d: " + d);

        // https://elearning.unideb.hu/pluginfile.php/659757/mod_resource/content/1/EA_EEA.pdf
        // System.out.println("Teszt: " + MyEEA.calculateD(new BigInteger("100"), new BigInteger("35")));


        // TESZT!!!!

        MyFME.calculateFME(new BigInteger("7"), new BigInteger("256"), new BigInteger("13"));

        //!!!!!!!!!

        k = new Key(n, e);
        sk = new SecretKey(d, n);
    }

    public Key getTheKey(){
        return k;
    }

    public String encryption(String plainText){

        System.out.println("PlainText : " + plainText);
        byte[] bytes = plainText.getBytes();
        BigInteger plainNum = new BigInteger(bytes);
        BigInteger enc;
        if(plainNum.compareTo(k.n) == 1){
            return null;
        } else{
            enc = plainNum.modPow(k.e, k.n);
            //enc = MyFME.calculateFME(plainNum, k.e, k.n);
        }
        return enc.toString();
    }

    public BigInteger decryption(BigInteger enc){
        BigInteger dec = enc.modPow(sk.d, sk.n);

        //BigInteger dec = MyFME.calculateFME(enc, sk.d, sk.n);
        return dec;
    }
}
