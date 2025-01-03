package agh.ics.oop.records;

public record SimParams(long seed,

                        int mapHeight,
                        int mapWidth,
                        int mapType,

                        int plantStartNumber,
                        int plantEnergy,
                        int plantGrowth,
                        int plantType,

                        int animalStartNumber,
                        int animalStartEnergy,
                        int animalFedThreshold,
                        int animalReproductionCost,
                        int animalGenomeLength,
                        int animalBehaviourType,

                        int animalMinMutation,
                        int animalMaxMutation,
                        int animalMutationType) { }
