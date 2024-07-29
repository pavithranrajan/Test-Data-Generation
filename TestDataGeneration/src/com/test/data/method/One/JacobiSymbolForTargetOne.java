package com.test.data.method.One;

public class JacobiSymbolForTargetOne implements JacobiSymbol{

    private static int approachLevel;
    private static double branchDistance;

    private static int jacobiSymbol(int k, int n) {
        int penaltyConst = 1;
        //target 1 approachLevel = 0
        // target 1 branch distance:
        // if k < 0 || abs(n%2) == 0 then 0 else Min((k+penaltyConst),(abs(n%2)+penaltyConst))

        approachLevel = 0;
        if(k < 0 || Math.abs(n%2) == 0){
            branchDistance = 0;
        } else {
            branchDistance = Math.min((k+penaltyConst),(Math.abs(n%2)+penaltyConst));
        }
        try {
        if ( k < 0 || n % 2 == 0 ) {
            throw new IllegalArgumentException("Invalid value. k = " + k + ", n = " + n); //target 1
        }
        }catch (IllegalArgumentException exception){

        }
        k %= n;
        int jacobi = 1;
        while ( k > 0 ) {
            while ( k % 2 == 0 ) {
                k /= 2;
                int r = n % 8;
                if ( r == 3 || r == 5 ) {
                    jacobi = -jacobi; //target 2
                }
            }
            int temp = n;
            n = k;
            k = temp;
            if ( k % 4 == 3 && n % 4 == 3 ) {
                jacobi = -jacobi; //target 3
            }
            k %= n;
        }
        if ( n == 1 ) {
            return jacobi; //target 4
        }
        return 0; //target 5
    }


    @Override
    public double jacobiSymbolFitnessFuntion(Individual individual) {
        JacobiSymbolForTargetOne.jacobiSymbol(individual.getK(),individual.getN());
        return approachLevel + (1-Math.pow(1.001,-branchDistance));
    }
}
