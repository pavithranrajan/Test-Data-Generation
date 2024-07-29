package com.test.data.method.Two;

import com.test.data.method.One.AlgorithmConstants;
import com.test.data.method.One.AlgorithmUtil;
import com.test.data.method.Two.*;

import java.time.Duration;
import java.time.Instant;
import java.util.Random;

public class GeneticAlgorithm implements Algorithm {
    static Random rand = new Random();
    private int approachLevel;
    private double branchDistance;
    private int sampleSize;
    private static String target;

    private static CipollasAlgorithm cipollasAlgorithm;
    @Override
    public Result runAlgorithm(int populationSize, String target) {
        Instant startTime = Instant.now();
        this.sampleSize = populationSize;
        this.target = target;
        Population myPop = new Population(populationSize, true);
        myPop.getFitness();
        int generationCount = 1;
        while (myPop.getFittest().getFitnessValue() > 0.0) {
            myPop = evolvePopulation(myPop);
            myPop.getFitness();
            generationCount++;
        }
        System.out.println("Solution found from Genetic Algorithm");
        System.out.println("Generation: " + generationCount);
        Individual individual = myPop.getFittest();
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

    public Population evolvePopulation(Population pop) {
        int elitismOffset;
        Population newPopulation = new Population(pop.getIndividuals().size(), false);

        if (AlgorithmConstants.ELITISM) {
            newPopulation.getIndividuals().add(0, pop.getFittest());
            elitismOffset = 1;
        } else {
            elitismOffset = 0;
        }

        for (int i = elitismOffset; i < pop.getIndividuals().size(); i++) {
            Individual indiv1 = tournamentSelection(pop);
            Individual indiv2 = tournamentSelection(pop);
            Individual newIndiv = crossover(indiv1, indiv2);
            newPopulation.getIndividuals().add(i, newIndiv);
        }

        for (int i = elitismOffset; i < newPopulation.getIndividuals().size(); i++) {
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    private Individual crossover(Individual indiv1, Individual indiv2) {
        Individual newSol = new Individual();
        for (int i = 0; i < sampleSize; i++) {
            if (Math.random() <= AlgorithmConstants.CROSSOVERRATE) {
                newSol.setNs(indiv1.getNs());
                newSol.setPs(indiv2.getPs());
            } else {
                newSol.setNs(indiv2.getNs());
                newSol.setPs(indiv1.getPs());
            }
        }
        return newSol;
    }

    private void mutate(Individual indiv) {
            if (Math.random() <= AlgorithmConstants.MUTATIONRATE) {
                indiv.setNs(AlgorithmUtil.getRandomNumberForNs().toString());
                indiv.setPs(AlgorithmUtil.getRandomNumberForPs().toString());
            }
    }

    private Individual tournamentSelection(Population pop) {
        Population tournament = new Population(AlgorithmConstants.TOURNAMENTSIZE, false);
        for (int i = 0; i < AlgorithmConstants.TOURNAMENTSIZE; i++) {
            int randomId = (int) (Math.random() * pop.getIndividuals().size());
            tournament.getIndividuals().add(pop.getIndividual(randomId));
        }
        Individual fittest = tournament.getFittest();
        return fittest;
    }

    public static double getFitness(Individual individual) {
        cipollasAlgorithm = AlgorithmUtil.getClassObjectForCipollas(target);
        double fitness = cipollasAlgorithm.cipollasAlgorithmFitness(individual);
        return fitness;
    }
}
