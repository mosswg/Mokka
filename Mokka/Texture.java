package Mokka;

import java.io.File;
import Mokka.Init.MokkaException;

public class Texture {
    String filepath;
    public final int RendererID;
    int width;
    int height;
    int BPP;

    public Texture(File filePath) {
        if (!filePath.isFile()) {
            throw MokkaException.ObjectCreation.NonexistentFile;
        }

        Texture t = createTexture(filePath.getPath());
        this.filepath = t.filepath;
        this.RendererID = t.RendererID;
        this.width = t.width;
        this.height = t.height;
        this.BPP = t.BPP;
    }

    protected Texture(String filepath, int RendererID, int width, int height, int BPP) {
        this.filepath = filepath;
        this.RendererID = RendererID;
        this.width = width;
        this.height = height;
        this.BPP = BPP;
    }

    public void bind() {
        bind(0);
    }

    public native void bind(int slot);

    public native void unbind();

    public String getFilepath() {
        return filepath;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }



    public static native Texture createTexture(String filePath);

}
