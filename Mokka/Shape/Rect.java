package Mokka.Shape;

import Mokka.*;
import Mokka.Maths.Vector2f;

public class Rect extends AbstractShape {
    private final Vector2f pos;
    private final Vector2f size;
    MovementController2D controller;
    final int[] indices = {
            0, 1, 2,
            2, 3, 0
    };


    public Rect(float x, float y, float width, float height) {
        super();
        this.pos = new Vector2f(x, y);
        this.size = new Vector2f(width, height);
    }

    public Rect(float x, float y, float width, float height, Material material) {
        super();
        this.pos = new Vector2f(x, y);
        this.size = new Vector2f(width, height);
        this.material = material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Material getMaterial() {
        return this.material;
    }

    public void setController(MovementController2D Controller) {
        this.controller = Controller;
    }

    public MovementController2D getController() {
        return controller;
    }

    public Vector2f getPos() {
        return new Vector2f(pos.x + translation.getData(0).getX(), pos.y + translation.getData(1).y);
    }

    public Vector2f getSize() {
        return size;
    }


    public VertexArray getVertexArray() {
        if (va == null)
            va = VertexArray.Create(new float[]{
                    (-size.x) + pos.x, (-size.y) + pos.y, 0.0f, 0.0f,
                    (size.x) + pos.x, (-size.y) + pos.y, 1.0f, 0.0f,
                    (size.x) + pos.x, (size.y) + pos.y, 1.0f, 1.0f,
                    (-size.x) + pos.x, (size.y) + pos.y, 0.0f, 1.0f
            });


        return va;
    }

    public void draw() {
        if (controller != null)
            controller.move(this);

        Window.draw(this);
    }

    public Rect copy() {
        Rect out = new Rect(pos.x, pos.y, size.x, size.y);
        out.material = this.material.copy();
        out.controller = this.controller.copy();
        out.translation = this.translation.copy();
        return out;
    }

}
