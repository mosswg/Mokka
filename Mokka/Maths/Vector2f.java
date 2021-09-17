package Mokka.Maths;

import java.util.Objects;

public class Vector2f {
    public static final Vector2f ZERO = new Vector2f(0, 0);

    public float x;
    public float y;

    public Vector2f() {
        this.x = 0;
        this.y = 0;
    }

    public Vector2f(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }


    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void set(Vector2f tbs) {
        this.x = tbs.x;
        this.y = tbs.y;
    }

    float length() {
        return (float)Math.sqrt((x * x) + (y * y));
    }

    public void normalize() {
        float l = x * x + y * y;
        if (l != 0) {
            l = (float)Math.sqrt(l);
            x /= l;
            y /= l;
        }
    }

    Vector2f copy() {
        return new Vector2f(x, y);
    }

    public void clampabs(float max) {
        if (Math.abs(this.x) > max) {
            this.x = max * Math.signum(this.x);
        }
        if (Math.abs(this.y) > max) {
            this.y = max * Math.signum(this.y);
        }
    }


    public Vector2f add(Vector2f a) {
        return new Vector2f(x + a.x, y + a.y);
    }

    public Vector2f add(float a) {
        return new Vector2f(x + a, y + a);
    }

    public Vector2f sub(Vector2f s) {
        return new Vector2f(x - s.x, y - s.y);
    }

    public Vector2f sub(float s) {
        return new Vector2f(x - s, y - s);
    }

    public Vector2f mult(Vector2f m) {
        return new Vector2f(x * m.x, y * m.y);
    }

    public Vector2f mult(float m) {
        return new Vector2f(x * m, y * m);
    }

    public Vector2f div(Vector2f d) {
        return new Vector2f(this.x / d.x, this.y / d.y);
    }

    public Vector2f div(float d) {
        if (d != 0)
            return new Vector2f(this.x / d, this.y / d);
        else return this.copy();
    }

    public Vector2f moveToward(Vector2f to, float delta) {
        Vector2f v = this.copy();
        Vector2f vd = to.copy();
        vd = vd.sub(v);
        float len = vd.length();

        return len <= delta || len < 0.000001 ? to : v.add(vd.div(len).mult(delta));
    }

    public Vector2f invert() {
        return new Vector2f(-x, -y);
    }

    public boolean isZero() {
        return this.x == 0 && this.y == 0;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass() && (((Vector2f) o).x == x && ((Vector2f) o).y == y);
    }

    @Override
    public String toString() {
        return "(" + x +
                ", " + y +
                ')';
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
