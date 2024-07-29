package com.test.data.method.Two;

import java.math.BigInteger;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Evaluation {
    private static final BigInteger BIG = BigInteger.TEN.pow(50).add(BigInteger.valueOf(151));
    private static final BigInteger BIG_TWO = BigInteger.valueOf(2);

    public static void main(String[] args) {
       /* GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();
        Result dataForTargetOne = geneticAlgorithm.runAlgorithm(4,"target1");

        Result dataForTargetTwo = geneticAlgorithm.runAlgorithm(4,"target2");

        Result dataForTargetThree = geneticAlgorithm.runAlgorithm(4,"target3");

        //Result dataForTargetFour = geneticAlgorithm.runAlgorithm(4,"target4");

        Result dataForTargetFive = geneticAlgorithm.runAlgorithm(4,"target5");


        Evaluation.c(dataForTargetOne.getNs(), dataForTargetOne.getPs());
        Evaluation.c(dataForTargetTwo.getNs(), dataForTargetTwo.getPs());
        Evaluation.c(dataForTargetThree.getNs(), dataForTargetThree.getPs());
        //Evaluation.c(dataForTargetFour.getNs(), dataForTargetFour.getPs());
        Evaluation.c(dataForTargetFive.getNs(), dataForTargetFive.getPs());
*/
        RandomSearchAlgorithm randomSearchAlgorithm = new RandomSearchAlgorithm();
        Result dataForTargetOneRandom = randomSearchAlgorithm.runAlgorithm(4,"target1");

        Result dataForTargetTwoRandom = randomSearchAlgorithm.runAlgorithm(4,"target2");

        Result dataForTargetThreeRandom = randomSearchAlgorithm.runAlgorithm(4,"target3");

        Result dataForTargetFourRandom = randomSearchAlgorithm.runAlgorithm(4,"target4");

        Result dataForTargetFiveRandom = randomSearchAlgorithm.runAlgorithm(4,"target5");


        Evaluation.c(dataForTargetOneRandom.getNs(), dataForTargetOneRandom.getPs());
        Evaluation.c(dataForTargetTwoRandom.getNs(), dataForTargetTwoRandom.getPs());
        Evaluation.c(dataForTargetThreeRandom.getNs(), dataForTargetThreeRandom.getPs());
        Evaluation.c(dataForTargetFourRandom.getNs(), dataForTargetFourRandom.getPs());
        Evaluation.c(dataForTargetFiveRandom.getNs(), dataForTargetFiveRandom.getPs());

        //System.out.println(c("3131", "10007"));
        /*System.out.println(c("139479840900", "211"));
        System.out.println(c("170344401984", "37"));
        System.out.println(c("126747392256", "79"));*/
        //System.out.println(c("331575", "1000003"));
        //System.out.println(c("665165880", "1000000007"));
        /*System.out.println(c("881398088036", "1000000000039"));
        System.out.println(c("34035243914635549601583369544560650254325084643201", ""));
*/

    }

    private static Triple c(String ns, String ps) {
        BigInteger n = new BigInteger(ns);
        BigInteger p = !ps.isEmpty() ? new BigInteger(ps) : BIG;
        // Legendre symbol, returns 1, 0 or p - 1
        Function<BigInteger, BigInteger> ls = (BigInteger a)
                -> a.modPow(p.subtract(BigInteger.ONE).divide(BIG_TWO), p);
        // Step 0, validate arguments
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
        if (!r.y.equals(BigInteger.ZERO)) {
            return new Triple(BigInteger.ZERO, BigInteger.ZERO, false); //target4
        }
        if (!r.x.multiply(r.x).mod(p).equals(n)) {
            return new Triple(BigInteger.ZERO, BigInteger.ZERO, false); //target5
        }
        // Step 4, solutions
        return new Triple(r.x, p.subtract(r.x), true); //target6
    }
}
