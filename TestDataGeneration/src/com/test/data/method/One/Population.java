package com.test.data.method.One;

import java.util.ArrayList;
import java.util.List;


public class Population {

    private List<Individual> individuals;

    public List<Individual> getIndividuals() {
        return individuals;
    }

    public Population(int size, boolean createNew) {
        individuals = new ArrayList<>();
        if (createNew) {
            createNewPopulation(size);
        }
    }

    protected Individual getIndividual(int index) {
        return individuals.get(index);
    }

    protected Individual getFittest() {
        Individual fittest = individuals.get(0);
        for (int i = 0; i < individuals.size(); i++) {
            if (fittest.getFitnessValue() >= getIndividual(i).getFitnessValue()) {
                fittest = getIndividual(i);
            }
        }
        return fittest;
    }

    private void createNewPopulation(int size) {
        for (int i = 0; i < size; i++) {
            Individual newIndividual = new Individual();
            individuals.add(i, newIndividual);
        }
    }

    public void getFitness() {
        for(Individual individual : individuals){
            individual.getFitnessForGA();
        }
    }

    public void getFitnessForRandom() {
        for(Individual individual : individuals){
            individual.getFitnessForRandom();
        }
    }
}
