package agh.cs.project;

import agh.cs.project.utility.Area;
import agh.cs.project.utility.Vector2;

public class SimulationConfig {
    public Vector2 size;
    public Vector2 jungleSize;
    public Area drawArea;
    public String name;

    public SimulationConfig() {
        size = new Vector2(30, 30);
        jungleSize = new Vector2(10, 10);
        name = "Simulation";
        drawArea = new Area(
                new Vector2(0, 0),
                new Vector2(500, 500)
        );
    }

    public SimulationConfig setSize(Vector2 size) {
        this.size = size;
        return this;
    }
    public SimulationConfig setJungleSize(Vector2 jungleSize) {
        this.jungleSize = jungleSize;
        return this;
    }
    public SimulationConfig setDrawArea(Area drawArea) {
        this.drawArea = drawArea;
        return this;
    }

    public SimulationConfig setName(String name) {
        this.name = name;
        return this;
    }

}
