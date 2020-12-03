package agh.cs.project.simulation;

import agh.cs.project.utility.Vector2;

public class SimulationConfig {
    public Vector2 size;
    public float jungleRatio;

    public int startEnergy;
    public int moveEnergy;
    public int plantEnergy;

    public int startAnimalsAmount;

    public SimulationConfig() {
        size = new Vector2(50, 54);
        jungleRatio = 0.3f;
        startEnergy = 100;
        moveEnergy = 2;
        plantEnergy = 70;

        startAnimalsAmount = 80;
    }

    public SimulationConfig setSize(Vector2 size) {
        this.size = size;
        return this;
    }

    public SimulationConfig setJungleRatio(float jungleRatio) {
        this.jungleRatio = jungleRatio;
        return this;
    }

    public SimulationConfig setStartEnergy(int startEnergy) {
        this.startEnergy = startEnergy;
        return this;
    }
    public SimulationConfig setMoveEnergy(int moveEnergy) {
        this.moveEnergy = moveEnergy;
        return this;
    }
    public SimulationConfig setPlantEnergy(int plantEnergy) {
        this.plantEnergy = plantEnergy;
        return this;
    }
    public SimulationConfig setStartAnimalsAmount(int startAnimalsAmount) {
        this.startAnimalsAmount = startAnimalsAmount;
        return this;
    }
}
