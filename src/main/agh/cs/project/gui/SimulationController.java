package agh.cs.project.gui;

import agh.cs.project.simulation.SimulationsManager;
import agh.cs.project.simulation.Statistic;
import agh.cs.project.utility.AppStyle;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;

import java.util.function.Consumer;

public class SimulationController extends GUI {
    private int offset;
    public SimulationController(PApplet context, SimulationsManager simulationsManager) {
        super(new Vector2(AppStyle.SIMULATION_CONTROLLER_WIDTH, AppStyle.WINDOW_SIZE.y-AppStyle.UPPER_BAR_HEIGHT - 2*AppStyle.GUI_MARGIN));
        setPosition(new Vector2(AppStyle.GUI_MARGIN, AppStyle.UPPER_BAR_HEIGHT + AppStyle.GUI_MARGIN));

        Widget slowDown = addWidget(new Button(context, "<<", (Button b)->{
            simulationsManager.slowDown();
        }));

        Widget speedUp = addWidget(new Button(context, ">>", (Button b)->{
            simulationsManager.speedUp();
        }));

        Widget resume = addWidget(new Button(context, "Play", (Button b)->{
            simulationsManager.resume();
        }));
        Widget pause = addWidget(new Button(context, "Pause", (Button b)->{
            simulationsManager.pause();
        }));
        Widget step = addWidget(new Button(context, "Step", (Button b)->{
            simulationsManager.step();
        }));
        slowDown.setPosition(new Vector2(0,0));

        resume.setPosition(slowDown.getPosition());
        resume.move(new Vector2( slowDown.getSize().x + AppStyle.GUI_MARGIN, 0 ));

        pause.setPosition(resume.getPosition());
        pause.move(new Vector2( resume.getSize().x + AppStyle.GUI_MARGIN, 0 ));

        step.setPosition(pause.getPosition());
        step.move(new Vector2( pause.getSize().x + AppStyle.GUI_MARGIN, 0 ));

        speedUp.setPosition(step.getPosition());
        speedUp.move(new Vector2( step.getSize().x + AppStyle.GUI_MARGIN, 0 ));

        int sumSize = slowDown.getSize().x + resume.getSize().x + pause.getSize().x + speedUp.getSize().x + step.getSize().x + 4*AppStyle.GUI_MARGIN;
        int offset = (AppStyle.SIMULATION_CONTROLLER_WIDTH - sumSize)/2;
        Vector2 vOffset = new Vector2(offset, AppStyle.GUI_MARGIN);
        resume.move(vOffset);
        pause.move(vOffset);
        speedUp.move(vOffset);
        slowDown.move(vOffset);
        step.move(vOffset);


        Widget graph = addWidget(new Graph(new Vector2(AppStyle.SIMULATION_CONTROLLER_WIDTH - 2*AppStyle.GUI_MARGIN, 200), simulationsManager));
        graph.setPosition(new Vector2(AppStyle.GUI_MARGIN, slowDown.getPosition().y + slowDown.getSize().y + 4*AppStyle.GUI_MARGIN));

        Widget gens = addWidget(new GensDisplay(new Vector2(
                AppStyle.SIMULATION_CONTROLLER_WIDTH - 2*AppStyle.GUI_MARGIN,
                (int)(context.textAscent() * 7 + context.textDescent()*7)
        ), simulationsManager));
        gens.setPosition(new Vector2(AppStyle.GUI_MARGIN, graph.getPosition().y + graph.getSize().y + 4*AppStyle.GUI_MARGIN));

        Widget avgEnergy = addWidget(new DynamicText((Consumer<String> setText)->{
            Statistic statistic = simulationsManager.getCurrentStatistic();
            if(statistic != null)
                setText.accept("Average energy: " + Integer.toString(statistic.getAverageEnergy()));
            else
                setText.accept("");
        }));
        avgEnergy.setPosition(new Vector2(AppStyle.GUI_MARGIN, gens.getPosition().y + gens.getSize().y + 4*AppStyle.GUI_MARGIN));

        Widget avgLife = addWidget(new DynamicText((Consumer<String> setText)->{
            Statistic statistic = simulationsManager.getCurrentStatistic();
            if(statistic != null)
                setText.accept("Average lifespan of dead: " + Integer.toString(statistic.getAverageLifeSpanOfDead()));
            else
                setText.accept("");
        }));
        avgLife.setPosition(new Vector2(AppStyle.GUI_MARGIN, avgEnergy.getPosition().y + avgEnergy.getSize().y + 4*AppStyle.GUI_MARGIN));

        Widget avgChildren = addWidget(new DynamicText((Consumer<String> setText)->{
            Statistic statistic = simulationsManager.getCurrentStatistic();
            if(statistic != null)
                setText.accept("Average amount of children: " + Integer.toString(statistic.getAverageChildrenAmount()));
            else
                setText.accept("");
        }));
        avgChildren.setPosition(new Vector2(AppStyle.GUI_MARGIN, avgLife.getPosition().y + avgLife.getSize().y + 4*AppStyle.GUI_MARGIN));

    }
}