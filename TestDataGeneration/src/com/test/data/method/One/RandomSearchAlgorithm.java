package com.test.data.method.One;

import java.time.Duration;
import java.time.Instant;

public class RandomSearchAlgorithm implements Algorithm {

    private int populationSize;

    private static JacobiSymbol jacobiSymbol;

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
            generationCount++;
            population = newPopulation;
        }
        System.out.println("Solution found from RandomSearch Algorithm");
        System.out.println("Generation: " + generationCount);
        Individual individual = population.getFittest();
        System.out.println("Fittest individuals fitnesee value for "+target+ " is "+individual.getFitnessValue());
        System.out.println("Fittest data generated for "+target+" are k : "+individual.getK()+" and n: "+individual.getN());
        Duration timeElapsed = Duration.between(startTime, Instant.now());
        System.out.println("Time taken: "+ timeElapsed.toMillis()+" milliseconds");
        System.out.println("-----------------------------------------------------------------------------------------------------------------");
        Result evaluation = new Result();
        evaluation.setDuration(timeElapsed.toMillis());
        evaluation.setIterationCount(generationCount);
        evaluation.setGoalAchieved(true);
        evaluation.setK(individual.getK());
        evaluation.setN(individual.getN());
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
        jacobiSymbol = AlgorithmUtil.getClassObject(target);
        double fitness = jacobiSymbol.jacobiSymbolFitnessFuntion(individual);
        return fitness;
    }
}
