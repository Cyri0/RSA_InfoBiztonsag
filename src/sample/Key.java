package sample;

import java.math.BigInteger;

public class Key {

    public BigInteger n;
    public BigInteger e;

    public Key(BigInteger n, BigInteger e){
        this.n = n;
        this.e = e;
    }

    @Override
    public String toString() {
        return "n: " + this.n + " | e: " + this.e;
    }
}


