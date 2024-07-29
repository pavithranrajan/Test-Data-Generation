package com.test.data.method.Two;

import com.test.data.method.One.AlgorithmUtil;
public class Individual  {
    private double fitnessValue;
    private String ns;
    private String ps;


    public String getNs() {
        return ns;
    }

    public void setNs(String ns) {
        this.ns = ns;
    }

    public String getPs() {
        return ps;
    }

    public void setPs(String ps) {
        this.ps = ps;
    }

    public double getFitnessValue() {
        return fitnessValue;
    }

    public void setFitnessValue(double fitness) {
        this.fitnessValue = fitnessValue;
    }
    public Individual() {
        ns = AlgorithmUtil.getRandomNumberForNs().toString();
        ps = AlgorithmUtil.getRandomNumberForPs().toString();
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
