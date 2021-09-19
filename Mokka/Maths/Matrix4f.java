package Mokka.Maths;

import Mokka.Init;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class Matrix4f {
    Vector4f[] data = new Vector4f[4];

    public Matrix4f(float v0, float v1, float v2, float v3, float v4, float v5, float v6, float v7, float v8, float v9, float v10, float v11, float v12, float v13, float v14, float v15) {
        data = new Vector4f[] {new Vector4f(v0, v1, v2, v3), new Vector4f(v4, v5, v6, v7), new Vector4f(v8, v9, v10, v11), new Vector4f(v12, v13, v14, v15)};
    }

    public Matrix4f(float identity) {
        data = new Vector4f[] {
                new Vector4f(identity, 0, 0, 0),
                new Vector4f(0, identity, 0, 0),
                new Vector4f(0, 0, identity, 0),
                new Vector4f(0, 0, 0, identity)
        };
    }

    public Matrix4f(Vector4f[] data) {
        if (data.length != 4) {
            throw Init.MokkaException.ObjectCreation.InvalidArgument;
        }

        this.data = data;
    }

    public Matrix4f(Matrix4f data) {
        this.data = data.data;
    }

    public void setData(Vector4f[] data) {
        this.data = data;
    }

    public Vector4f[] getData() {
        return data;
    }

    public Vector4f getData(int x) {
        return data[x];
    }

    public float[] getDataArray() {
        float[] out = new float[16];


        System.arraycopy(data[0].toArray(), 0, out, 0, 4);
        System.arraycopy(data[1].toArray(), 0, out, 4, 4);
        System.arraycopy(data[2].toArray(), 0, out, 8, 4);
        System.arraycopy(data[3].toArray(), 0, out, 12, 4);


        return out;
    }

    public Matrix4f copy() {
        return new Matrix4f(this.data);
    }

    public void translate(Vector3f v) {
        this.data[3] = this.data[0].mult(v.x).add(this.data[1].mult(v.y)).add(this.data[2].mult(v.z)).add(this.data[3]);
    }

    public void translate(float x, float y, float z) {
        this.data[3] = this.data[0].mult(x).add(this.data[1].mult(y)).add(this.data[2].mult(z)).add(this.data[3]);
    }
    
    
    


    public static Matrix4f translate(Matrix4f m, Vector3f v) {
        Matrix4f Result = m.copy();
        Result.data[3] = m.data[0].mult(v.x).add(m.data[1].mult(v.y)).add(m.data[2].mult(v.z)).add(m.data[3]);
        return Result;
    }

    public static Matrix4f translate(Matrix4f m, float x, float y, float z) {
        Matrix4f Result = m.copy();
        Result.data[3] = m.data[0].mult(x).add(m.data[1].mult(y)).add(m.data[2].mult(z)).add(m.data[3]);
        return Result;
    }
    
    


    private static float[] mult(float[] array, float m) {
        for (int i = 0; i < array.length; i++) {
            array[i] *= m;
        }
        return array;
    }


    @Override
    public String toString() {
        return "{ " + data[0] + '\n' + data[1] + '\n' + data[2] + '\n' + data[3] + " }";
    }
}
