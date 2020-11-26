package agh.cs.project.simulation;

import agh.cs.project.utility.Vector2;

public class SimulationConfig {
    public Vector2 size;
    public float jungleRatio;

    public SimulationConfig() {
        size = new Vector2(30, 30);
        jungleRatio = 0.5f;
    }

    public SimulationConfig setSize(Vector2 size) {
        this.size = size;
        return this;
    }

    public SimulationConfig setJungleRatio(float jungleRatio) {
        this.jungleRatio = jungleRatio;
        return this;
    }
}
