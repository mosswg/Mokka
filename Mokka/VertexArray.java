package Mokka;

public class VertexArray {
    int RendererID;

    private VertexArray(int rid) {
        RendererID = rid;
    }

    public static VertexArray Create(float[] positions) {
        glEnableDefaultBlending();

        VertexArray va = new VertexArray(GenID());

        VertexBuffer vb = new VertexBuffer(positions);

        VertexBuffer.Layout layout = new VertexBuffer.Layout();
        layout.push(Init.MokkaTypes.Float, 2);
        layout.push(Init.MokkaTypes.Float, 2);
        va.AddBuffer(vb, layout.index);

        return va;
    }

    public static native void glEnableDefaultBlending();

    public static native int GenID();

    public native void AddBuffer(VertexBuffer vb, long vblptr);

    public native void Destruct();

    public native void Bind();

    public native void UnBind();
}
