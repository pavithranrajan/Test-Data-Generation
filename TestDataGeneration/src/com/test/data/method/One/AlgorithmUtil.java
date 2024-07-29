package com.test.data.method.One;

import com.test.data.method.Two.*;

import java.math.BigInteger;
import java.util.Random;

public class AlgorithmUtil {

    public static double findAverageFitness(Population myPop) {
        double total = 0;
        for(Individual individual : myPop.getIndividuals()){
            total += individual.getFitnessForGA();
        }
        return total/myPop.getIndividuals().size();
    }

    public static JacobiSymbol getClassObject(String target) {
        JacobiSymbol jacobiSymbol = null;
        if(target.equalsIgnoreCase("target1")){
            jacobiSymbol = new JacobiSymbolForTargetOne();
        } else if(target.equalsIgnoreCase("target2")){
            jacobiSymbol = new JacobiSymbolForTargetTwo();
        } else if (target.equalsIgnoreCase("target3")) {
            jacobiSymbol = new JacobiSymbolForTargetThree();
        } else if (target.equalsIgnoreCase("target4")) {
            jacobiSymbol = new JacobiSymbolForTargetFour();
        }
        return jacobiSymbol;
    }

    public static BigInteger getRandomNumberForPs() {
        BigInteger maxLimit = new BigInteger("500000000");
        BigInteger minLimit = new BigInteger("10");
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        int len = maxLimit.bitLength();
        int num = 0;
        Random rand = new Random();
        // generate a random number
        num = rand.nextInt(1000) + 1;
        while (!isPrime(num)) {
            num = rand.nextInt(1000) + 1;
        }
        BigInteger res = new BigInteger(String.valueOf(num));
        if (res.compareTo(minLimit) < 0)
            res = res.add(minLimit);
        if (res.compareTo(bigInteger) >= 0)
            res = res.mod(bigInteger).add(minLimit);
        return res;
    }

    private static boolean isPrime(int inputNum){
        if (inputNum <= 3 || inputNum % 2 == 0)
            return inputNum == 2 || inputNum == 3; //this returns false if number is <=1 & true if number = 2 or 3
        int divisor = 3;
        while ((divisor <= Math.sqrt(inputNum)) && (inputNum % divisor != 0))
            divisor += 2; //iterates through all possible divisors
        return inputNum % divisor != 0; //returns true/false
    }

    public static BigInteger getRandomNumberForNs() {
        BigInteger maxLimit = new BigInteger("500000");
        BigInteger minLimit = new BigInteger("10");
        BigInteger bigInteger = maxLimit.subtract(minLimit);
        int len = maxLimit.bitLength();
        Random rand = new Random();
        BigInteger res = new BigInteger(len,rand);
        if (res.compareTo(minLimit) < 0)
            res = res.add(minLimit);
        if (res.compareTo(bigInteger) >= 0)
            res = res.mod(bigInteger).add(minLimit);
        return res.multiply(res);
    }

    public static CipollasAlgorithm getClassObjectForCipollas(String target) {
        CipollasAlgorithm cipollasAlgorithm = null;
        if(target.equalsIgnoreCase("target1")){
            cipollasAlgorithm = new CipollasAlgorithmForTargetOne();
        } else if(target.equalsIgnoreCase("target2")){
            cipollasAlgorithm = new CipollasAlgorithmForTargetTwo();
        } else if (target.equalsIgnoreCase("target3")) {
            cipollasAlgorithm = new CipollasAlgorithmForTargetThree();
        } else if (target.equalsIgnoreCase("target4")) {
            cipollasAlgorithm = new CipollasAlgorithmForTargetFour();
        } else if (target.equalsIgnoreCase("target5")) {
            cipollasAlgorithm = new CipollasAlgorithmForTargetFive();
        }
        return cipollasAlgorithm;
    }
}
