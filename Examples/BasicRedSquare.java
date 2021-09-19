package Examples;

import Mokka.*;
import Mokka.Shape.*;

public class BasicRedSquare {
    static Rect square;
    static Rect child1;
    static Rect child2;



    public static void init() {
        Material red = new Material.BasicTexture(new Texture("Mokka/res/Textures/java.jpeg"));

        square = new Rect(0, 0, 0.5f,0.5f);

        square.setMaterial(red);

        child1 = new Rect(.9f, 0, .2f, .2f);

        child1.setMaterial(red);

        child2 = new Rect(0, 0.5f, .25f, .25f);

        child2.setMaterial(red);

        square.addChild(child1);

        square.addChild(child2);

        square.setController(new PlayerController2D(.005f));

        child2.setController(new PlayerController2D(.01f));
    }

    public static void run() {

        square.draw();

        child1.draw();

        child2.draw();
    }



    public static void main(String[] args) {
        Mokka.init(800, 800, "SQUARE");
    }
}
