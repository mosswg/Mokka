package Mokka;

import java.io.File;

public class Shader {
    String filepath;
    int RendererID;

    public Shader(File filepath) {
        if (!filepath.isFile())
            throw Init.MokkaException.ObjectCreation.NonexistentFile;

        this.filepath = filepath.getPath();


        this.RendererID = genRID(this.filepath);
    }

    protected Shader(String filepath, int rendererID) {
        this.filepath = filepath;
        this.RendererID = rendererID;
    }


    public native void bind();

    public native void unbind();

    public static native int genRID(String filePath);

    public String getFilepath() {
        return filepath;
    }

    public native void setUniform(String name, int value);

    public native void setUniform(String name, float value);

    public native void setUniform(String name, float v0, float v1, float v2, float v3);

    public native void setUniformMat4f(String name, long ptr);

}