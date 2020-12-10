package agh.cs.project.simulation;

import agh.cs.project.utility.Area;
import agh.cs.project.utility.Vector2;

public class SimulationConfig {
    public Vector2 size;
    public float jungleRatio;
    public Area jungleArea;
    public Area worldArea;

    public int startEnergy;
    public int moveEnergy;
    public int plantEnergy;

    public int startAnimalsAmount;

    public SimulationConfig() {
        setSize(new Vector2(15, 15));
        setStartEnergy(100);
        setMoveEnergy(4);
        setPlantEnergy(70);
        setJungleRatio(0.3f);

        setStartAnimalsAmount(8);
    }

    public SimulationConfig setSize(Vector2 size) {
        this.size = size;
        worldArea = new Area(new Vector2(0, 0), size);
        return this;
    }

    public SimulationConfig setJungleRatio(float jungleRatio) {
        this.jungleRatio = jungleRatio;

        Vector2 jungleSize = size.multiply(jungleRatio);
        Vector2 junglePosition = new Vector2( (size.x-jungleSize.x)/2, (size.y-jungleSize.y)/2 );
        jungleArea = new Area(junglePosition, jungleSize);

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
