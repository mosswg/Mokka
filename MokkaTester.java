
import Mokka.*;
import Mokka.Shape.*;
import java.io.File;

public class NativesTester {
    static Rect sq;
    static Rect sq2;
    static float[] c = {0.3f, 0.8f, 1f, 1};



    public static void init() {

        PlayerController2D controller = new PlayerController2D(1f, .7f, 4f);

        Options.AutoDraw = true;

        Mokka.Window.TwoD.setOrthoCoords(0f, 800f, 0, 800f);

        Texture t = new Texture(new File("Mokka/res/Textures/java.jpeg"));

        Material java = new Material.BasicTexture(t);

        sq = new Rect(100f, 250f, 100f,100f);

        sq2 = new Rect(500f, 500f, 200f,200f);

        sq.setMaterial(java);

        sq2.setMaterial(java);

        sq.setController(controller);

        sq2.setController(controller.copy());
    }

    public static void run() {
        if (Internal.keyIsPressed(Internal.Keys.ESCAPE)) {
            Window.terminate();
        }
        if (Internal.keyIsPressed(Internal.Keys.ONE)) {
            sq.getController().setActive(true);
            sq2.getController().setActive(false);
        }
        if (Internal.keyIsPressed(Internal.Keys.TWO)) {
            sq.getController().setActive(false);
            sq2.getController().setActive(true);
        }


        /*
        if (Internal.keyIsPressedOrHeld(Internal.Keys.KP_6))
            Window.translateCamera(cameraSpeed, 0, 0);
        if (Internal.keyIsPressedOrHeld(Internal.Keys.KP_4))
            Window.translateCamera(-cameraSpeed, 0, 0);
        if (Internal.keyIsPressedOrHeld(Internal.Keys.KP_8))
            Window.translateCamera(0, cameraSpeed, 0);
        if (Internal.keyIsPressedOrHeld(Internal.Keys.KP_2))
            Window.translateCamera(0, -cameraSpeed, 0);



//        ((Material.BaseColor)sq.getMaterial()).setColor(new Color(c[0], c[1], c[2], c[3]));
//        for(int i = 0; i < 3; i++) {
//            if (c[i] >= 1f) {
//                p[i] = -1;
//            }
//            else if (c[i] <= 0f) {
//                p[i] = 1;
//            }
//
//            c[i] += 0.01f * p[i];
//        }
*/
    }



    public static void main(String[] args) {
        Init.init(800, 800, "SQUARE");
    }
}
