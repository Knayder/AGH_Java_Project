package agh.cs.project.simulation;

import agh.cs.project.utility.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Statistic {
    private int animalsAmount;
    private int grassAmount;
    private int averageEnergy;
    private int averageLifeSpanOfDead;
    private int averageChildrenAmount;
    private List<Integer> mostCommonGen;

    public Statistic(int animalsAmount, int grassAmount, int averageEnergy, int averageLifeSpanOfDead, int averageChildrenAmount, List<Integer> mostCommonGen) {
        this.animalsAmount = animalsAmount;
        this.grassAmount = grassAmount;
        this.mostCommonGen = mostCommonGen;
        this.averageLifeSpanOfDead = averageLifeSpanOfDead;
        this.averageChildrenAmount = averageChildrenAmount;
        this.averageEnergy = averageEnergy;
    }

    public void saveToFile() {
        try {
            FileWriter myWriter = new FileWriter("assets/output.txt");
            myWriter.write("Animals amount: " + Integer.toString(animalsAmount));
            myWriter.write("\nGrass amount: " + Integer.toString(grassAmount));
            myWriter.write("\nAverage lifespan of dead: " + Integer.toString(averageLifeSpanOfDead));
            myWriter.write("\nAverage children amount: " + Integer.toString(averageChildrenAmount));
            myWriter.write("\nAverage energy: " + Integer.toString(averageEnergy));
            myWriter.write("\nMost common gen: " + mostCommonGen);
            myWriter.close();
        } catch (IOException e) {
            Logger.log("An error occurred with saving to file");
        }
    }

    public int getAnimalsAmount() {
        return animalsAmount;
    }
    public int getGrassAmount() {
        return grassAmount;
    }
    public int getAverageEnergy() {
        return averageEnergy;
    }
    public int getAverageLifeSpanOfDead() {
        return averageLifeSpanOfDead;
    }
    public int getAverageChildrenAmount() {
        return averageChildrenAmount;
    }
    public List<Integer> getMostCommonGen() {
        return mostCommonGen;
    }

}
