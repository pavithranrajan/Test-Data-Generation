package com.test.data.method.One;

import java.util.Random;
public class Individual  {
    private double fitnessValue;
    private int k;
    private int n;
    Random rand = new Random();


    public int getK() {
        return k;
    }

    public void setK(int k) {
        this.k = k;
    }

    public void setN(int n) {
        this.n = n;
    }

    public int getN() {
        return n;
    }
    public double getFitnessValue() {
        return fitnessValue;
    }

    public void setFitnessValue(double fitness) {
        this.fitnessValue = fitnessValue;
    }
    public Individual() {
        k = rand.nextInt();
        n= rand.nextInt();
    }


    public double getFitnessForGA() {
        if (fitnessValue == 0) {
            fitnessValue = GeneticAlgorithm.getFitness(this);
        }
        return fitnessValue;
    }

    public double getFitnessForRandom() {
        if (fitnessValue == 0) {
            fitnessValue = RandomSearchAlgorithm.getFitness(this);
        }
        return fitnessValue;
    }
}
