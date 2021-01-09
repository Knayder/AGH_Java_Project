package agh.cs.project.simulation;

import agh.cs.project.assetsManager.AssetsManager;
import agh.cs.project.render.Sprite;
import agh.cs.project.utility.AppStyle;
import agh.cs.project.utility.Vector2;
import processing.core.PApplet;
import processing.core.PImage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Animal extends PawnWorldElement {
    private Sprite sprite;
    private int worldRotation;  //obrót świata? Kierunek jako int?

    int age;    // przemyślany modyfikator dostępu?
    int birthday;

    private Animal parent1;
    private Animal parent2;

    private ArrayList<Animal> children;

    private int[] gens;
    private int energy;

    private Random random;

    public Animal(int birthday) {
        random = new Random();

        age = 0;
        this.birthday = birthday;
        children = new ArrayList<>();

        sprite = new Sprite((PImage)AssetsManager.ASSETS.get(AppStyle.ANIMAL_ASSET_KEY));
        sprite.setCenterOrigin();
        worldRotation = 0;

        gens = new int[32];
        parent1 = null;
        parent2 = null;
        energy = 0;


    }

    public Animal setParents(Animal parent1, Animal parent2) {
        this.parent1 = parent1;
        this.parent2 = parent2;

        return this;
    }

    public void addChild(Animal child) {
        children.add(child);
    }

    public int getChildrenAmount() {
        return children.size();
    }

    public int getChildrenAmount(int bornSince, int bornUntil) {
        int count = 0;
        for(Animal child : children) {
            int birthday = child.getBirthday();
            if(birthday >= bornSince && birthday <= bornUntil)
                count++;
        }
        return count;
    }

    public int getDescendantsAmount() {
        int result = getChildrenAmount();
        for(Animal child : children)
            result += child.getDescendantsAmount();
        return result;
    }

    public Animal copyGensFromParents() {   // public? + po co zwracać zwierzę, skoro je Pan modyfikuje?
        if(parent1 == null || parent2 == null)
            throw new IllegalArgumentException("Animal doesn't have parents");
        int index1 = random.nextInt(31);
        int index2 = 1 + index1 + random.nextInt(32 - index1 - 1);

        int[] p1gens;
        int[] p2gens;
        if(parent1.getEnergy() >= parent2.getEnergy()) {
            p1gens = parent1.getGens();
            p2gens = parent2.getGens();
        }
        else {
            p1gens = parent2.getGens();
            p2gens = parent1.getGens();
        }

        for(int i = 0; i<index1; ++i)
            gens[i] = p1gens[i];
        for(int i = index1; i<index2; ++i)
            gens[i] = p2gens[i];
        for(int i = index2; i<32; ++i)
            gens[i] = p1gens[i];

        boolean[] check = new boolean[8];
        boolean validGens = false;
        while(!validGens) {
            for (int i = 0; i < 8; ++i)
                check[i] = false;
            for (int i = 0; i < 32; ++i)
                check[gens[i]] = true;

            validGens = true;
            for (int i = 0; i < 8; ++i) {
                if(!check[i]) {
                    validGens = false;
                    gens[random.nextInt(32)] = i;
                }
            }
        }
        Arrays.sort(gens);
        return this;
    }

    public Animal generateNewGens() {
        if(parent1 == null && parent2 == null) {
            for(int i = 0; i<8; ++i)
                gens[i] = i;
            for(int i = 8; i<32; ++i)
                gens[i] = random.nextInt(8);
        }
        Arrays.sort(gens);
        return this;
    }

    public Animal extractEnergyFromParents() {
        if(parent1 == null || parent2 == null)
            throw new IllegalArgumentException("Animal doesn't have parents");
        int p1energy = parent1.getEnergy()/4;
        int p2energy = parent2.getEnergy()/4;

        parent1.setEnergy(parent1.getEnergy()-p1energy);
        parent2.setEnergy(parent2.getEnergy()-p1energy);

        energy = p1energy + p2energy;
        return this;
    }

    public int[] getGens() {
        return gens;    // a tablica nie jest przypadkiem modyfikowalna?
    }

    public Animal setEnergy(int energy) {   // czy potrzebne jest set, skoro jest add?
        this.energy = energy;
        return this;
    }

    public void addEnergy(int energy) {
        this.energy += energy;
    }

    public int getEnergy() {
        return energy;
    }

    public int getAge() {
        return age;
    }

    public int getBirthday() {
        return birthday;
    }

    public void randomGenMove(int stepCost) {
        age++;
        setWorldRotation(
                (worldRotation + gens[random.nextInt(32)]) % 8
        );
        int x = 0;
        int y = 0;
        switch(worldRotation) {
            case 7: case 0: case 1:
                y = -1;
                break;
            case 3: case 4: case 5:
                y = 1;
                break;
        }
        switch(worldRotation) {
            case 1: case 2: case 3:
                x = 1;
                break;
            case 5: case 6: case 7:
                x = -1;
                break;
        }
        moveWorldPosition(new Vector2(x, y));

        addEnergy(-stepCost);
    }

    public void setWorldRotation(int worldRotation) {
        this.worldRotation = worldRotation;
        sprite.setRotation(worldRotation * PApplet.PI/4.f);
    }

    @Override
    protected void drawPawn(PApplet context) {
        sprite.draw(context);
        context.textSize(AppStyle.ANIMAL_ENERGY_FONT_SIZE);
        context.fill(0);

        context.text(Integer.toString(energy),0, AppStyle.TILE_PIXEL_SIZE);

        if(AppStyle.DEBUG_MODE) {
            String g = "";
            for (int i = 0; i < 32; ++i) {
                g = g + Integer.toString(gens[i]);
                if (i % 8 == 7)
                    g = g + '\n';
            }
            context.fill(255, 255, 255, 100);
            context.rect(0, 0, AppStyle.TILE_PIXEL_SIZE, AppStyle.TILE_PIXEL_SIZE);
            context.fill(0, 0, 0);
            context.textLeading(10);
            context.text(g, 0, 10);
        }


        context.noFill();
        context.textSize(AppStyle.BUTTON_FONT_SIZE);
    }
}
