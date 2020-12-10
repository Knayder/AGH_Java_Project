package agh.cs.project.gui;

import agh.cs.project.assetsManager.AssetsManager;
import agh.cs.project.render.Sprite;
import agh.cs.project.simulation.SimulationsManager;
import agh.cs.project.simulation.Statistic;
import agh.cs.project.utility.AppStyle;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.util.ArrayList;

public class Graph extends Widget {
    private SimulationsManager simulationsManager;

    public Graph(Vector2 size, SimulationsManager simulationsManager) {
        super(size);
        this.simulationsManager = simulationsManager;

    }

    @Override
    protected void drawPawn(PApplet context) {
        ArrayList<Statistic> statistics = simulationsManager.getStatistics();
        if(statistics == null)
            return;
        int statisticsSize = statistics.size();
        int maxAmount = simulationsManager.getTilesAmount();
        int startIndex = statisticsSize-getSize().x;
        if(startIndex < 0)
            startIndex = 0;
        context.stroke(255,255,255);
        context.rect(-1, -1, getSize().x+2, getSize().y+2);
        for(int i = startIndex; i<statisticsSize; ++i) {
            context.stroke(255,255,255);
            context.point(
                    (float)i-startIndex,
                    (float)getSize().y * ( 1.0f - (float)statistics.get(i).getAnimalsAmount() / (float)maxAmount )
            );
            context.stroke(0,255,0);
            context.point(
                    (float)i-startIndex,
                    (float)getSize().y * ( 1.0f - (float)statistics.get(i).getGrassAmount() / (float)maxAmount )
            );
        }
        context.noStroke();
        context.fill(255);
        context.textSize(10.f);
        context.text(Integer.toString(maxAmount), 0, context.textAscent() );
        context.text("0", 0, getSize().y);

        context.textSize(AppStyle.BUTTON_FONT_SIZE);
        context.noFill();
    }
}
