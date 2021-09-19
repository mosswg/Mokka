package Mokka.Shape;

import Mokka.*;
import Mokka.Maths.Matrix4f;
import Mokka.Maths.Vector2f;

public class Rect extends AbstractShape {
    private Vector2f pos;
    private float width;
    private float height;
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

    public Vector2f getPos() {
        return new Vector2f(pos.x + translation.getData(0).getX(), pos.y + translation.getData(1).y);
    }

    public Vector2f getSize() {
        return new Vector2f(width, height);
    }


    public VertexArray getVertexArray() {
        if (va == null)
            va = VertexArray.Create(new float[]{
                    (-width) + pos.x, (-height) + pos.y, 0.0f, 0.0f,
                    (width) + pos.x, (-height) + pos.y, 1.0f, 0.0f,
                    (width) + pos.x, (height) + pos.y, 1.0f, 1.0f,
                    (-width) + pos.x, (height) + pos.y, 0.0f, 1.0f
            });


        return va;
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
