package sample;

import java.math.BigInteger;

public class EEAResult {

    public BigInteger lnko;
    public BigInteger x;
    public BigInteger y;

    public EEAResult(BigInteger lnko, BigInteger x, BigInteger y) {
        this.lnko = lnko;
        this.x = x;
        this.y = y;
    }
    @Override
    public String toString() {
        return "EEAResult{" +
                "lnko=" + lnko +
                ", x=" + x +
                ", y=" + y +
                '}';
    }

    public BigInteger getX(){
        return x;
    }
}
