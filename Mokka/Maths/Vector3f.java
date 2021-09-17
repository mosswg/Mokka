package Mokka.Maths;

import java.util.Objects;

public class Vector3f {
    public static final Mokka.Maths.Vector3f ZERO = new Mokka.Maths.Vector3f();

    public float x;
    public float y;
    public float z;

    public Vector3f() {}

    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public float getY() {
        return y;
    }

    public float getX() {
        return x;
    }

    public float getZ() {
    return z;
}


    public void setY(float y) {
        this.y = y;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setZ(float z) {
    this.z = z;
}

    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public void set(Mokka.Maths.Vector3f tbs) {
        this.x = tbs.x;
        this.y = tbs.y;
        this.z = tbs.z;
    }

    float length() {
        return (float)Math.sqrt((x * x) + (y * y) + (z * z));
    }

    public void normalize() {
        float l = x * x + y * y + z * z;
        if (l != 0) {
            l = (float)Math.sqrt(l);
            x /= l;
            y /= l;
            z /= l;
        }
    }

    Mokka.Maths.Vector3f copy() {
        return new Mokka.Maths.Vector3f(x, y, z);
    }

    public void clampabs(float max) {
        if (Math.abs(this.x) > max) {
            this.x = max * Math.signum(this.x);
        }
        if (Math.abs(this.y) > max) {
            this.y = max * Math.signum(this.y);
        }
        if (Math.abs(this.z) > max) {
            this.z = max * Math.signum(this.z);
        }
    }


    public Mokka.Maths.Vector3f add(Mokka.Maths.Vector3f a) {
        return new Mokka.Maths.Vector3f(x + a.x, y + a.y, z + a.z);
    }

    public Mokka.Maths.Vector3f add(float a) {
        return new Mokka.Maths.Vector3f(x + a, y + a, z + a);
    }

    public Mokka.Maths.Vector3f sub(Mokka.Maths.Vector3f s) {
        return new Mokka.Maths.Vector3f(x - s.x, y - s.y, z - s.z);
    }

    public Mokka.Maths.Vector3f sub(float s) {
        return new Mokka.Maths.Vector3f(x - s, y - s, z - s);
    }

    public Mokka.Maths.Vector3f mult(Mokka.Maths.Vector3f m) {
        return new Mokka.Maths.Vector3f(x * m.x, y * m.y, z * m.z);
    }

    public Mokka.Maths.Vector3f mult(float m) {
        return new Mokka.Maths.Vector3f(x * m, y * m, z * m);
    }

    public Mokka.Maths.Vector3f div(Mokka.Maths.Vector3f d) {
        return new Mokka.Maths.Vector3f(x / d.x, y / d.y, z / d.z);
    }

    public Mokka.Maths.Vector3f div(float d) {
        if (d != 0)
            return new Mokka.Maths.Vector3f(x / d, y / d, z / d);
        else return this.copy();
    }

    public Mokka.Maths.Vector3f moveToward(Mokka.Maths.Vector3f to, float delta) {
        Mokka.Maths.Vector3f v = this.copy();
        Mokka.Maths.Vector3f vd = to.copy();
        vd = vd.sub(v);
        float len = vd.length();

        return len <= delta || len < 0.000001 ? to : v.add(vd.div(len).mult(delta));
    }

    public Mokka.Maths.Vector3f invert() {
        return new Mokka.Maths.Vector3f(-x, -y, -z);
    }

    public boolean isZero() {
        return this.x == 0 && this.y == 0 && this.z == 0;
    }

    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass() && (((Mokka.Maths.Vector3f) o).x == x && ((Mokka.Maths.Vector3f) o).y == y && ((Vector3f) o).z == z);
    }

    @Override
    public String toString() {
        return "(" + x +
                ", " + y +
                ", " + z +
                ')';
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z);
    }
}
