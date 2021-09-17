package Mokka.Shape;

import Mokka.*;
import Mokka.Maths.Matrix4f;
import Mokka.Maths.Vector2f;

public class Rect extends AbstractShape {
    public Vector2f pos;
    public float width;
    public float height;
    MovementController2D Controller;
    final int[] indices = {
            0, 1, 2,
            2, 3, 0
    };


    public Rect(float x, float y, float width, float height) {
        super();
        this.pos = new Vector2f(x, y);
        this.width = width;
        this.height = height;
    }

    public Rect(float x, float y, float width, float height, Material material) {
        super();
        this.pos = new Vector2f(x, y);
        this.width = width;
        this.height = height;
        this.material = material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setController(MovementController2D Controller) {
        this.Controller = Controller;
    }

    public MovementController2D getController() {
        return Controller;
    }

    public VertexArray getVertexArray() {
        if (va == null)
            va = VertexArray.Create(new float[]{
                    (-width / 2) + pos.x, (-height / 2) + pos.y, 0.0f, 0.0f,
                    (width / 2) + pos.x, (-height / 2) + pos.y, 1.0f, 0.0f,
                    (width / 2) + pos.x, (height / 2) + pos.y, 1.0f, 1.0f,
                    (-width / 2) + pos.x, (height / 2) + pos.y, 0.0f, 1.0f
            });


        return va;
    }

    public Matrix4f getTranslation() {
        return translation;
    }

    public void draw() {
        if (Controller != null)
            Controller.Move(this);

        Window.draw(this);
    }

    public Rect copy() {
        return new Rect(pos.x, pos.y, width, height);
    }

}
