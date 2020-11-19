package agh.cs.project;


import agh.cs.project.windows.SettingsWindow;
import processing.core.PApplet;

public class Application  {
    public Application() {
        settingsWindow = new SettingsWindow();
    }

    public void run() {
        PApplet.runSketch(new String[]{"Simulator"}, settingsWindow);
    }
    private SettingsWindow settingsWindow;
}
