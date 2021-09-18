package Mokka;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;

public class Shader {
    String filepath;
    int RendererID;

    public Shader(String filepath) {
        if (!Files.exists(Path.of(filepath))) {
            filepath = Internal.getFileFromJar(filepath).getPath();
            if (!Files.exists(Path.of(filepath))) {
                throw Init.MokkaException.ObjectCreation.NonexistentFile;
            }
        }

        this.filepath = filepath;


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