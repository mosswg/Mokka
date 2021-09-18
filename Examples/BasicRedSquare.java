package Examples;

import Mokka.*;
import Mokka.Shape.*;

public class BasicExample {
    static Rect square;
    static float[] c = {0.3f, 0.8f, 1f, 1};



    public static void init() {
        Material red = new Material.BasicColor(new Color(1, 0, 0, 1));

        square = new Rect(0, 0, 0.5f,0.5f);

        square.setMaterial(red);
    }

    public static void run() {

        square.draw();

    }



    public static void main(String[] args) {
        Init.init(800, 800, "SQUARE");
    }
}
