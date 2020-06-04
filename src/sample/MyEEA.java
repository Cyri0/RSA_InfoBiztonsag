package sample;

import java.math.BigInteger;

public class MyEEA {

    public static BigInteger calculateD(BigInteger e, BigInteger fi_n) {
         /*
         *   Bemenet: két egész szám, a és b
         *   Kimenet: lnko(a,b), x, y
         * */

        EEAResult res = doEEA(e, fi_n);
        System.out.println(res);

        BigInteger d = res.getX();

        // 1 < d < fi_n
        // ha nem teljesül hozzá kell adni fi_n-t

        if(! (d.compareTo(BigInteger.ONE) == 1 && d.compareTo(fi_n) == -1) ){
            d = d.add(fi_n);
        }

        return d;
    }

    private static EEAResult doEEA(BigInteger a, BigInteger b) {
        if (b.equals(BigInteger.ZERO)) {
            return new EEAResult(a, BigInteger.ONE, BigInteger.ZERO);
        } else {
            EEAResult extension = doEEA(b, a.mod(b));
            return new EEAResult(   extension.lnko,
                                    extension.y,
                                    extension.x.subtract(a.divide(b).multiply(extension.y))
            );
        }
    }
}
