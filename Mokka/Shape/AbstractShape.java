package Mokka.Shape;

import Mokka.Material;
import Mokka.Maths.Matrix4f;
import Mokka.Maths.Vector3f;
import Mokka.Options;
import Mokka.VertexArray;
import Mokka.Window;

import java.util.HashMap;

public abstract class AbstractShape {

    static public HashMap<AbstractShape, Boolean> ShapeList = new HashMap<>();

    Material material;
    VertexArray va;
    Matrix4f translation = new Matrix4f(1);
    final int[] indices = null;

    public AbstractShape() {
        ShapeList.put(this, Options.AutoDraw);
    }


    public void draw() {
        Window.draw(this);
    }

    public void translate(float x, float y, float z) {
        translation = Matrix4f.translate(translation, new Vector3f(x, y, z));
    }

    public void translate(Vector3f vec) {
        translation.translate(vec);
    }




}

