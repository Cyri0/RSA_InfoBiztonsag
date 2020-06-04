package sample;

import java.math.BigInteger;

public class SecretKey {

    public BigInteger d;
    public BigInteger n;

    public SecretKey(BigInteger d, BigInteger n){
        this.d = d;
        this.n = n;
    }
}


