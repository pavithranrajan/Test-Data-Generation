package com.test.data.method.One;

public class Evaluation {
    public static void main(String[] args) {
        /*GeneticAlgorithm geneticAlgorithm = new GeneticAlgorithm();

        Result dataForTargetTwo = geneticAlgorithm.runAlgorithm(6,"target2");

        Result dataForTargetThree = geneticAlgorithm.runAlgorithm(6,"target3");

        Result dataForTargetFour = geneticAlgorithm.runAlgorithm(6,"target4");

        Result dataForTargetOne = geneticAlgorithm.runAlgorithm(4,"target1");

        Evaluation.jacobiSymbol(dataForTargetTwo.getK(), dataForTargetTwo.getN());
        Evaluation.jacobiSymbol(dataForTargetThree.getK(), dataForTargetThree.getN());
        Evaluation.jacobiSymbol(dataForTargetFour.getK(), dataForTargetFour.getN());
        Evaluation.jacobiSymbol(dataForTargetOne.getK(), dataForTargetOne.getN());*/

        RandomSearchAlgorithm randomSearchAlgorithm = new RandomSearchAlgorithm();

        Result dataForTargetTwoRandom = randomSearchAlgorithm.runAlgorithm(1,"target2");

        Result dataForTargetThreeRandom = randomSearchAlgorithm.runAlgorithm(1,"target3");

        Result dataForTargetFourRandom = randomSearchAlgorithm.runAlgorithm(1,"target4");

        Result dataForTargetOneRandom = randomSearchAlgorithm.runAlgorithm(1,"target1");


        Evaluation.jacobiSymbol(dataForTargetTwoRandom.getK(), dataForTargetTwoRandom.getN());
        Evaluation.jacobiSymbol(dataForTargetThreeRandom.getK(), dataForTargetThreeRandom.getN());
        Evaluation.jacobiSymbol(dataForTargetFourRandom.getK(), dataForTargetFourRandom.getN());
        Evaluation.jacobiSymbol(dataForTargetOneRandom.getK(), dataForTargetOneRandom.getN());

    }

    private static int jacobiSymbol(int k, int n) {
        /*if ( k < 0 || n % 2 == 0 ) {
            throw new IllegalArgumentException("Invalid value. k = " + k + ", n = " + n); //target 1
        }*/
        try{
            if(k < 0 || n % 2 == 0 ){
              //true branch
                throw new IllegalArgumentException("Invalid value. k = " + k + ", n = " + n);
            }
        }catch (IllegalArgumentException exception){ }
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
        //target 5
        return 0; //target 5
    }
}
