package Mokka.Maths;

import java.util.Objects;

public class Vector4f {
    public static final Mokka.Maths.Vector4f ZERO = new Mokka.Maths.Vector4f();

    public float x;
    public float y;
    public float z;
    public float w;

    public Vector4f() {}

    public Vector4f(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
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

    public float getW() {
        return w;
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

    public void setW(float w) {
        this.w = w;
    }

    public void set(float x, float y, float z, float w) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w;
    }

    public void set(Mokka.Maths.Vector4f tbs) {
        this.x = tbs.x;
        this.y = tbs.y;
        this.z = tbs.z;
        this.w = tbs.w;
    }

    float length() {
        return (float)Math.sqrt((x * x) + (y * y) + (z * z) + (w + w));
    }

    public void normalize() {
        float l = x * x + y * y + z * z + w * w;
        if (l != 0) {
            l = (float)Math.sqrt(l);
            x /= l;
            y /= l;
            z /= l;
            w /= l;
        }
    }

    Mokka.Maths.Vector4f copy() {
        return new Mokka.Maths.Vector4f(x, y, z, w);
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
        if (Math.abs(this.w) > max) {
            this.w = max * Math.signum(this.w);
        }
    }


    public Mokka.Maths.Vector4f add(Mokka.Maths.Vector4f a) {
        return new Mokka.Maths.Vector4f(x + a.x, y + a.y, z + a.z, w + a.w);
    }

    public Mokka.Maths.Vector4f add(float a) {
        return new Mokka.Maths.Vector4f(x + a, y + a, z + a, w + a);
    }

    public Mokka.Maths.Vector4f sub(Mokka.Maths.Vector4f s) {
        return new Mokka.Maths.Vector4f(x - s.x, y - s.y, z - s.z, w - s.w);
    }

    public Mokka.Maths.Vector4f sub(float s) {
        return new Mokka.Maths.Vector4f(x - s, y - s, z - s, w - s);
    }

    public Mokka.Maths.Vector4f mult(Mokka.Maths.Vector4f m) {
        return new Mokka.Maths.Vector4f(x * m.x, y * m.y, z * m.z, w * m.w);
    }

    public Mokka.Maths.Vector4f mult(float m) {
        return new Mokka.Maths.Vector4f(x * m, y * m, z * m, w * m);
    }

    public Mokka.Maths.Vector4f div(Mokka.Maths.Vector4f d) {
        return new Mokka.Maths.Vector4f(x / d.x, y / d.y, z / d.z, w / d.w);
    }

    public Mokka.Maths.Vector4f div(float d) {
        if (d != 0)
            return new Mokka.Maths.Vector4f(x / d, y / d, z / d, w / d);
        else return this.copy();
    }

    public Mokka.Maths.Vector4f moveToward(Mokka.Maths.Vector4f to, float delta) {
        Mokka.Maths.Vector4f v = this.copy();
        Mokka.Maths.Vector4f vd = to.copy();
        vd = vd.sub(v);
        float len = vd.length();

        return len <= delta || len < 0.000001 ? to : v.add(vd.div(len).mult(delta));
    }

    public Mokka.Maths.Vector4f invert() {
        return new Mokka.Maths.Vector4f(-x, -y, -z, -w);
    }

    public boolean isZero() {
        return this.x == 0 && this.y == 0 && this.z == 0 && this.w == 0;
    }

    public float[] toArray() {
        return new float[] {x, y, z, w};
    }

    @Override
    public boolean equals(Object o) {
        return o != null && getClass() == o.getClass() && (((Mokka.Maths.Vector4f) o).x == x && ((Mokka.Maths.Vector4f) o).y == y && ((Vector4f) o).z == z && ((Vector4f) o).w == w);
    }

    @Override
    public String toString() {
        return x +
                ", " + y +
                ", " + z +
                ", " + w;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, w);
    }
}
