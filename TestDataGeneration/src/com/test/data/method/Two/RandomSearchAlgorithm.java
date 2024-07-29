package com.test.data.method.Two;

import com.test.data.method.Two.Algorithm;
import com.test.data.method.One.AlgorithmUtil;
import com.test.data.method.Two.Result;

import java.time.Duration;
import java.time.Instant;

public class RandomSearchAlgorithm implements Algorithm {

    private int populationSize;

    private static CipollasAlgorithm cipollasAlgorithm;

    private static String target;

    @Override
    public Result runAlgorithm(int populationSize, String target) {
        Instant startTime = Instant.now();
        this.populationSize = populationSize;
        this.target = target;
        int generationCount = 1;
        Population population = new Population(populationSize, true);
        population.getFitnessForRandom();
        while (population.getFittest().getFitnessValue() > 0.0) {
                Population newPopulation = new Population(populationSize, true);
                newPopulation.getFitnessForRandom();
                // if average fitness is lesser for new, new replaces the old population
            generationCount++;
            if (findAverageFitness(newPopulation) < findAverageFitness(population)) {
                population = newPopulation;
            }
        }
        System.out.println("Solution found from RandomSearch Algorithm");
        System.out.println("Generation: " + generationCount);
        Individual individual = population.getFittest();
        System.out.println("Fittest individuals fitnesee value for "+target+ " is "+individual.getFitnessValue());
        System.out.println("Fittest data generated for "+target+" are ns : "+individual.getNs()+" and ps: "+individual.getPs());
        Duration timeElapsed = Duration.between(startTime, Instant.now());
        System.out.println("Time taken: "+ timeElapsed.toMillis()+" milliseconds");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        Result evaluation = new Result();
        evaluation.setDuration(timeElapsed.toMillis());
        evaluation.setIterationCount(generationCount);
        evaluation.setGoalAchieved(true);
        evaluation.setNs(individual.getNs());
        evaluation.setPs(individual.getPs());
        return evaluation;
    }
    private double findAverageFitness(Population population) {
        double totalFitness = 0.0;
        for(Individual individual : population.getIndividuals()){
            totalFitness += individual.getFitnessValue();
        }
        return totalFitness/populationSize;
    }

    public static double getFitness(Individual individual) {
        cipollasAlgorithm = AlgorithmUtil.getClassObjectForCipollas(target);
        double fitness = cipollasAlgorithm.cipollasAlgorithmFitness(individual);
        return fitness;
    }
}
