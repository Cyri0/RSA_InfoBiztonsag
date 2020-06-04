package sample;

import java.math.BigInteger;

public class SecretKey {

    public BigInteger d;
    public BigInteger n;

    public SecretKey(BigInteger d, BigInteger n){
        this.d = d;
        this.n = n;
    }

    @Override
    public String toString() {
        return "d: " + this.d + " | n: " + this.n;
    }
}


