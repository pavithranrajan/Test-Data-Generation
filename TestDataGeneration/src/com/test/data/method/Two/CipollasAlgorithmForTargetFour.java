package com.test.data.method.Two;

import java.math.BigInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

public class CipollasAlgorithmForTargetFour implements CipollasAlgorithm{

    private static int approachLevel;
    private static double branchDistance;
    private static final BigInteger BIG = BigInteger.TEN.pow(50).add(BigInteger.valueOf(151));
    private static final BigInteger BIG_TWO = BigInteger.valueOf(2);

    private static Triple c(String ns, String ps) {
        int penaltyConst = 1;
        BigInteger n = new BigInteger(ns);
        BigInteger p = !ps.isEmpty() ? new BigInteger(ps) : BIG;

        // Legendre symbol, returns 1, 0 or p - 1
        Function<BigInteger, BigInteger> ls = (BigInteger a)
                -> a.modPow(p.subtract(BigInteger.ONE).divide(BIG_TWO), p);

        // Step 0, validate arguments
        /*if (!ls.apply(n).equals(BigInteger.ONE)) {
            return new Triple(BigInteger.ZERO, BigInteger.ZERO, false); //target1
        }*/

        // Step 1, find a, omega2
        BigInteger a = BigInteger.ZERO;
        BigInteger omega2;
        while (true) {
            omega2 = a.multiply(a).add(p).subtract(n).mod(p);
            if (ls.apply(omega2).equals(p.subtract(BigInteger.ONE))) {
                break; //target2
            }
            a = a.add(BigInteger.ONE);
        }

        // multiplication in Fp2
        BigInteger finalOmega = omega2;
        BiFunction<Point, Point, Point> mul = (Point aa, Point bb) -> new Point(
                aa.x.multiply(bb.x).add(aa.y.multiply(bb.y).multiply(finalOmega)).mod(p),
                aa.x.multiply(bb.y).add(bb.x.multiply(aa.y)).mod(p)
        );

        // Step 2, compute power
        Point r = new Point(BigInteger.ONE, BigInteger.ZERO);
        Point s = new Point(a, BigInteger.ONE);
        BigInteger nn = p.add(BigInteger.ONE).shiftRight(1).mod(p);
        while (nn.compareTo(BigInteger.ZERO) > 0) {
            if (nn.and(BigInteger.ONE).equals(BigInteger.ONE)) {
                r = mul.apply(r, s); //target3
            }
            s = mul.apply(s, s);
            nn = nn.shiftRight(1);
        }

        // Step 3, check x in Fp
        //target 4 approach level 0
        //target 4 branchDistance:
        //if abs(r.y - BigInteger.ZERO) != 0 then 0 else penaltyConstK
        approachLevel = 0;
        if(!(r.y.subtract(BigInteger.ZERO)).abs().equals(BigInteger.ZERO)){
            branchDistance = 0;
        }else {
            branchDistance = penaltyConst;
        }
        if (!r.y.equals(BigInteger.ZERO)) {
            return new Triple(BigInteger.ZERO, BigInteger.ZERO, false); //target4
        }

        // Step 5, check x * x = n
        if (!r.x.multiply(r.x).mod(p).equals(n)) {
            return new Triple(BigInteger.ZERO, BigInteger.ZERO, false); //target5
        }

        // Step 4, solutions
        return new Triple(r.x, p.subtract(r.x), true); //target6
    }

    @Override
    public double cipollasAlgorithmFitness(Individual individual) {
        CipollasAlgorithmForTargetFour.c(individual.getNs(),individual.getPs());
        return approachLevel + (1-Math.pow(1.001,-branchDistance));
    }
}
