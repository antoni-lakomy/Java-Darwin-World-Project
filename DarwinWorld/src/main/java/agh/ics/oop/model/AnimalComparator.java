package agh.ics.oop.model;

import agh.ics.oop.organisms.Animal;

import java.util.Comparator;

public class AnimalComparator implements Comparator<Animal> {

    private AnimalComparator(){};

    private static volatile AnimalComparator instance;

    @Override
    public int compare(Animal animal1, Animal animal2) {
        if (animal1.getEnergy() != animal2.getEnergy()) return animal2.getEnergy() - animal1.getEnergy();
        if (animal1.getAge() != animal2.getAge()) return animal2.getAge() - animal1.getAge();
        return animal2.getChildren().size() - animal1.getChildren().size();
    }

    public static AnimalComparator getInstance(){
        if (instance == null){
            synchronized (AnimalComparator.class){
                if (instance == null){
                    instance = new AnimalComparator();
                }
            }
        }
        return instance;
    }
}
