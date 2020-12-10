package agh.cs.project.gui;

import agh.cs.project.simulation.SimulationsManager;
import agh.cs.project.simulation.Statistic;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;

import java.util.ArrayList;
import java.util.List;

public class GensDisplay extends Widget {
    private SimulationsManager simulationsManager;
    public GensDisplay(Vector2 size, SimulationsManager simulationsManager) {
        super(size);
        this.simulationsManager = simulationsManager;
    }

    @Override
    protected void drawPawn(PApplet context) {
        ArrayList<Statistic> statistics = simulationsManager.getStatistics();
        if(statistics == null || statistics.size() == 0)
            return;
        List<Integer> gens = statistics.get(statistics.size() - 1).getMostCommonGen();
        if(gens == null)
            return;
        context.stroke(255);
        context.rect(-1, -1, getSize().x+2, getSize().y+2);
        context.noStroke();
        Integer current = gens.get(0);
        int lineOffset = (int)context.textAscent();
        int offset = 0;
        for(int i = 0; i<gens.size(); ++i) {
            if(!gens.get(i).equals(current)) {
                current = gens.get(i);
                lineOffset += context.textAscent();
                offset=0;
            }
            context.text(Integer.toString(gens.get(i)), 20*offset, lineOffset);
            offset++;
        }
    }
}
