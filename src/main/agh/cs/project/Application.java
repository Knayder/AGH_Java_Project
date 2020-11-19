package agh.cs.project;


import agh.cs.project.windows.SettingsWindow;
import processing.core.PApplet;

public class Application  {
    public Application() {
        windowsManager = new WindowsManager();
    }

    public void run() {
        windowsManager.openNewWindow(new SettingsWindow());
    }
    private WindowsManager windowsManager;
}
