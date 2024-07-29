package com.test.data.method.One;

public class JacobiSymbolForTargetTwo implements JacobiSymbol{
    private static int approachLevel;
    private static double branchDistance;
    private static int jacobiSymbol(int k, int n) {
        int penaltyConst = 1;
        if ( k < 0 || n % 2 == 0 ) {
            //throw new IllegalArgumentException("Invalid value. k = " + k + ", n = " + n); //target 1
        }
        k %= n;
        int jacobi = 1;
        //target 2 approachLevel = 2
        //target 2 branch distance:
        // if k>0 then 0 else (-k+penaltyConst)
        approachLevel = 2;
        if(k > 0){
            branchDistance = 0;
        } else {
            branchDistance = -k+penaltyConst;
        }
        while ( k > 0 ) {
            //target 2 approachLevel = 1
            //target 2 branch distance:
            // if abs(k%2) == 0 then 0 else (abs(k%2)+penaltyConst)
            approachLevel = 1;
            if(Math.abs(k%2) == 0){
                branchDistance = 0;
            }else{
                branchDistance = Math.abs(k%2)+penaltyConst;
            }
            while ( k % 2 == 0 ) {
                k /= 2;
                int r = n % 8;
                //target 2 approachLevel = 0
                //target 2 branch distance:
                // if abs(r-3) == 0 || abs(r-5) == 0 then 0 else Min((abs(r-3)+penaltyConst),(abs(r-5)+penaltyConst))
                approachLevel = 0;
                if(Math.abs(r-3) == 0 || Math.abs(r-5) == 0){
                    branchDistance = 0;
                }else{
                    branchDistance = Math.min((Math.abs(r-3)+penaltyConst),(Math.abs(r-5)+penaltyConst));
                }
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
        //target 5 approachLevel = 0
        return 0; //target 5
    }

    @Override
    public double jacobiSymbolFitnessFuntion(Individual individual) {
        JacobiSymbolForTargetTwo.jacobiSymbol(individual.getK(),individual.getN());
        return approachLevel + (1-Math.pow(1.001,-branchDistance));
    }
}
