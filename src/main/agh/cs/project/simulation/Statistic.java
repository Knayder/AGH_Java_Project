package agh.cs.project.simulation;

import java.util.List;

public class Statistic {
    private int animalsAmount;
    private int grassAmount;
    private int averageEnergy;
    private int averageLifeSpanOfDead;
    private List<Integer> mostCommonGen;

    public Statistic(int animalsAmount, int grassAmount, int averageEnergy, int averageLifeSpanOfDead, List<Integer> mostCommonGen) {
        this.animalsAmount = animalsAmount;
        this.grassAmount = grassAmount;
        this.mostCommonGen = mostCommonGen;
        this.averageLifeSpanOfDead = averageLifeSpanOfDead;
        this.averageEnergy = averageEnergy;
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
    public List<Integer> getMostCommonGen() {
        return mostCommonGen;
    }

}
