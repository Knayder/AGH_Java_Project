package agh.cs.project;

import agh.cs.project.windows.RenderWindow;
import processing.core.PApplet;

import java.util.ArrayList;

public class WindowsManager {
    public WindowsManager() {
        windows = new ArrayList<>();
    }

    public void openNewWindow(RenderWindow newWindow) {
        if(newWindow != null) {
            windows.add(newWindow);
            PApplet.runSketch(new String[]{"Simulator"}, newWindow);
        }
    }

    public void killWindows() {
        for(RenderWindow window : windows) {
            window.exit();
        }
        windows.clear();
    }


    private ArrayList<RenderWindow> windows;
}
