package agh.cs.project.simulation;

import agh.cs.project.utility.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WorldTest {
    @Test
    void generalTest() {
        World world = new World(
                new SimulationConfig()
                .setSize(new Vector2(2, 2))
        );


    }
}