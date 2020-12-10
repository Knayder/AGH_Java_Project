package agh.cs.project.simulation;

import agh.cs.project.render.Pawn;
import agh.cs.project.utility.AppStyle;
import agh.cs.project.utility.Area;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;

import java.util.*;

public class AnimalsManager extends Pawn {
    private Random random;

    private SimulationConfig config;

    private List<Integer> mostCommonGen;

    private HashMap<Vector2, ArrayList<Animal>> animals;

    int howManyDied;
    int sumOfDeadAge;


    public AnimalsManager(SimulationConfig config) {
        if(config.startAnimalsAmount > config.size.x * config.size.y)
            throw new IllegalArgumentException("Simulation Config is wrong, there are more start animals than possible field!");

        this.config = config;
        animals = new HashMap<>();

        howManyDied = 0;
        sumOfDeadAge = 0;

        this.mostCommonGen = null;

        ArrayList<Vector2> uniqueChecker = new ArrayList<>();

        random = new Random();
        for(int i = 0; i<config.startAnimalsAmount; ++i) {
            Vector2 worldPosition;
            do {
                worldPosition = new Vector2(
                        random.nextInt(config.size.x),
                        random.nextInt(config.size.y)
                );
            } while( uniqueChecker.contains(worldPosition) );
            uniqueChecker.add(worldPosition);

            Animal animal = new Animal(0)
                    .generateNewGens()
                    .setEnergy(config.startEnergy);

            animal.setWorldPosition(worldPosition);
            animal.setWorldRotation(random.nextInt(8));

            ArrayList<Animal> herd = new ArrayList<>();
            herd.add(animal);
            animals.put(worldPosition, herd);
        }
    }

    public int getAnimalsAmount() {
        int amount = 0;
        for(Map.Entry<Vector2, ArrayList<Animal>> entry : animals.entrySet())
            amount += entry.getValue().size();
        return amount;
    }

    public List<Integer> getMostCommonGen() {
        if(mostCommonGen != null)
            return mostCommonGen;
        HashMap<List<Integer>, Integer> counter = new HashMap<>();
        for(Map.Entry<Vector2, ArrayList<Animal>> entry : animals.entrySet()) {
            for (Animal animal : entry.getValue()) {
                List<Integer> gens = new ArrayList<Integer>(animal.getGens().length);
                for (int i : animal.getGens())
                    gens.add(i);

                Integer value = counter.get(gens);
                counter.put(gens, value != null ? value + 1 : 1);

            }
        }
        int max = -1;
        List<Integer> result = null;
        for(Map.Entry<List<Integer>, Integer> entry : counter.entrySet()) {
            if(entry.getValue() > max) {
                result = entry.getKey();
                max = entry.getValue();
            }
        }
        mostCommonGen = result;
        return result;
    }

    public int getAverageEnergy() {
        int count = 0;
        int energy = 0;
        for(Map.Entry<Vector2, ArrayList<Animal>> entry : animals.entrySet()) {
            for (Animal animal : entry.getValue()) {
                count++;
                energy += animal.getEnergy();
            }
        }
        return energy/count;
    }

    public int getAverageLifeSpanOfDead() {
        if(howManyDied > 0)
            return sumOfDeadAge/howManyDied;
        return 0;
    }


    public void moveAnimals() {
        HashMap<Vector2, ArrayList<Animal>> newAnimals = new HashMap<>();

        for(Map.Entry<Vector2, ArrayList<Animal>> entry : animals.entrySet()) {
            for(Animal animal : entry.getValue()) {
                animal.randomGenMove(config.moveEnergy);
                Vector2 oldPosition = animal.getWorldPosition();
                Vector2 newPosition = new Vector2( // Modulo of position ( in java (-1)%n = -1. I want (-1)%n=n-1  )
                        (oldPosition.x % config.size.x + config.size.x) % config.size.x,
                        (oldPosition.y % config.size.y + config.size.y) % config.size.y
                );
                animal.setWorldPosition(newPosition);

                ArrayList<Animal> herd = newAnimals.computeIfAbsent(animal.getWorldPosition(), k -> new ArrayList<>());
                herd.add(animal);
            }
        }
        animals = newAnimals;

    }

    public boolean hasBeenEaten(Vector2 position, int plantEnergy) {
        ArrayList<Animal> herd = animals.get(position);
        if(herd == null)
            return false;


        int maxEnergyAnimalsCount = 1;
        int maxEnergy = herd.get(0).getEnergy();
        for(int i = 1; i<herd.size(); ++i) {
            int energy = herd.get(i).getEnergy();
            if(energy > maxEnergy) {
                maxEnergy = energy;
                maxEnergyAnimalsCount = 1;
            }
            else if(energy == maxEnergy)
                maxEnergyAnimalsCount++;
        }
        plantEnergy /= maxEnergyAnimalsCount;
        for(Animal animal : herd) {
            if (animal.getEnergy() == maxEnergy)
                animal.addEnergy(plantEnergy);
        }
        return true;
    }

    public void reproduce(int days, int minEnergy, Area area) {
        mostCommonGen = null;
        HashMap<Vector2, ArrayList<Animal>> animalsToAdd = new HashMap<>();
        for(Map.Entry<Vector2, ArrayList<Animal>> entry : animals.entrySet()) {
            ArrayList<Animal> herd = entry.getValue();
            if(herd == null || herd.size() < 2)
                continue;
            Animal animal1 = herd.get(0);
            Animal animal2 = herd.get(1);
            if(animal2.getEnergy() > animal1.getEnergy()) {
                Animal swapTemp = animal1;
                animal1 = animal2;
                animal2 = swapTemp;
            }
            for(int i = 2; i<herd.size(); ++i) {
                Animal animal = herd.get(i);
                if(animal.getEnergy() >= animal1.getEnergy() ) {
                    animal2 = animal1;
                    animal1 = animal;
                }
                else if(animal.getEnergy() > animal2.getEnergy()) {
                    animal2 = animal;
                }
            }
            if(animal1.getEnergy() >= minEnergy && animal2.getEnergy() >= minEnergy) {
                Animal animal = new Animal(days)
                        .setParents(animal1, animal2)
                        .copyGensFromParents()
                        .extractEnergyFromParents();
                animal.setWorldRotation(random.nextInt(8));

                Vector2 parentPosition = entry.getKey();
                ArrayList<Vector2> possiblePositions = new ArrayList<>(Arrays.asList(
                        parentPosition.add(new Vector2(0, -1)),
                        parentPosition.add(new Vector2(1, -1)),
                        parentPosition.add(new Vector2(1, 0)),
                        parentPosition.add(new Vector2(1, 1)),
                        parentPosition.add(new Vector2(0, 1)),
                        parentPosition.add(new Vector2(-1, 1)),
                        parentPosition.add(new Vector2(-1, 0)),
                        parentPosition.add(new Vector2(-1, -1))
                ));
                Collections.shuffle(possiblePositions);

                ArrayList<Animal> possibleHerd = null;
                for(Vector2 possiblePosition : possiblePositions) {
                    if(area.contains(possiblePosition)) {
                        animal.setWorldPosition(possiblePosition);
                        possibleHerd = animals.get(possiblePosition);
                        if(possibleHerd == null) {
                            possibleHerd = new ArrayList<>();
                            animalsToAdd.put(possiblePosition, possibleHerd);
                            break;
                        }
                    }
                }
                if( possibleHerd != null)
                    possibleHerd.add(animal);

            }
        }
        for(Map.Entry<Vector2, ArrayList<Animal>> entry : animalsToAdd.entrySet()) {
            ArrayList<Animal> herd = animals.computeIfAbsent(entry.getKey(), k -> new ArrayList<>());
            herd.addAll(entry.getValue());
        }
    }

    public void removeDead() {
        mostCommonGen = null;
        HashMap<Vector2, ArrayList<Animal>> newAnimals = new HashMap<>();
        for(Map.Entry<Vector2, ArrayList<Animal>> entry : animals.entrySet()) {
            ArrayList<Animal> herd = entry.getValue();
            for(int i = 0; i<herd.size(); ++i) {
                if(herd.get(i).getEnergy() <= 0) {
                    //howManyDied++;
                    //sumOfDeadAge += herd.get(i).getAge();

                    herd.remove(i);
                }
            }
            if(herd.size() > 0)
                newAnimals.put(entry.getKey(), herd);
        }
        animals = newAnimals;
    }

    @Override
    protected void drawPawn(PApplet context) {
        //mostCommonGen = null;
        List<Integer> mostCommonGen = getMostCommonGen();

        for(Map.Entry<Vector2, ArrayList<Animal>> entry : animals.entrySet()) {
            Animal highestEnergyAnimal = null;
            for(Animal animal : entry.getValue())
                if(highestEnergyAnimal == null || animal.getEnergy() > highestEnergyAnimal.getEnergy())
                    highestEnergyAnimal = animal;
            highestEnergyAnimal.draw(context);

            boolean isCommonGened = true;
            int[] gen = highestEnergyAnimal.getGens();
            for(int i = 0; i<gen.length; ++i) {
                if(gen[i] != mostCommonGen.get(i)) {
                    isCommonGened = false;
                    break;
                }
            }
            if(isCommonGened) {
                context.noFill();
                context.stroke(255, 255, 0);
                context.rect(highestEnergyAnimal.getPosition().x, highestEnergyAnimal.getPosition().y, AppStyle.TILE_PIXEL_SIZE, AppStyle.TILE_PIXEL_SIZE);
                context.noStroke();
            }
        }
    }
}
