package com.test.data.method.One;

public class JacobiSymbolForTargetThree implements JacobiSymbol{
    private static int approachLevel;
    private static double branchDistance;
    private static int jacobiSymbol(int k, int n) {
        int penaltyConst = 1;
        if ( k < 0 || n % 2 == 0 ) {
            //throw new IllegalArgumentException("Invalid value. k = " + k + ", n = " + n); //target 1
        }
        k %= n;
        int jacobi = 1;
        //target 3 approachLevel = 1
        //target 3 branch distance:
        // if k>0 then 0 else (-k+penaltyConst)
        approachLevel = 1;
        if(k > 0){
            branchDistance = 0;
        } else {
            branchDistance = -k+penaltyConst;
        }
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
            //target 3 approachLevel = 0
            //target 3 branch distance:
            // if abs(k%4-3) == 0 && abs(n%4-3) == 0 then 0 else (abs(k%4-3)+penaltyConst)+(abs(n%4-3)+penaltyConst)
            approachLevel = 0;
            if(Math.abs((k%4)-3) == 0 && Math.abs((n%4)-3) == 0){
                branchDistance = 0;
            } else {
                branchDistance = (Math.abs((k%4)-3)+penaltyConst)+(Math.abs((n%4)-3)+penaltyConst);
            }
            if ( k % 4 == 3 && n % 4 == 3 ) {
                jacobi = -jacobi; //target 3
            }
            k %= n;
        }
        if ( n == 1 ) {
            return jacobi; //target 4
        }
        //target 5 approachLevel = 0
        return 0; //target 5
    }

    @Override
    public double jacobiSymbolFitnessFuntion(Individual individual) {
        JacobiSymbolForTargetThree.jacobiSymbol(individual.getK(),individual.getN());
        return approachLevel + (1-Math.pow(1.001,-branchDistance));
    }
}
