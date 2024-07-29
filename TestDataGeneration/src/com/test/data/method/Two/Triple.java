package com.test.data.method.Two;

import java.math.BigInteger;

public class Triple {
    BigInteger x;
    BigInteger y;
    boolean b;

    Triple(BigInteger x, BigInteger y, boolean b) {
        this.x = x;
        this.y = y;
        this.b = b;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s)", this.x, this.y, this.b);
    }
}
