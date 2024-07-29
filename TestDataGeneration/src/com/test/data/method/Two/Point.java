package com.test.data.method.Two;

import java.math.BigInteger;

public class Point {
    BigInteger x;
    BigInteger y;

    Point(BigInteger x, BigInteger y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", this.x, this.y);
    }
}
