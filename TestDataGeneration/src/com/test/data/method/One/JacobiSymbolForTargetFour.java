package com.test.data.method.One;

public class JacobiSymbolForTargetFour implements JacobiSymbol{

    private static int approachLevel;
    private static double branchDistance;

    private static int jacobiSymbol(int k, int n) {
        int penaltyConst = 1;
        if ( k < 0 || n % 2 == 0 ) {
            //throw new IllegalArgumentException("Invalid value. k = " + k + ", n = " + n); //target 1
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
        //target 4 approachLevel = 0
        //target 4 branch distance:
        //if abs(n-1) == 0 then 0 else abs(n-1)+penaltyConst
        approachLevel = 0;
        if(Math.abs(n-1) == 0){
            branchDistance = 0;
        } else {
            branchDistance = Math.abs(n-1) + penaltyConst;
        }
        if ( n == 1 ) {
            return jacobi; //target 4
        }
        //target 5 approachLevel = 0
        return 0; //target 5
    }

    @Override
    public double jacobiSymbolFitnessFuntion(Individual individual) {
        JacobiSymbolForTargetFour.jacobiSymbol(individual.getK(),individual.getN());
        return approachLevel + (1-Math.pow(1.001,-branchDistance));
    }
}
