package Mokka;

import Mokka.VertexBuffer;

class VertexBuffer {
    int RendererID;

    protected VertexBuffer(float[] positions) {
        RendererID = gen(positions, positions.length);
    }

    protected native int gen(float[] positions, int size);

    protected native void Destruct();

    protected native void Bind();

    protected native void UnBind();


    protected static class Layout {
        int index;

        protected Layout() {
            index = CreateVertexBufferLayout();
        }

        protected native static int CreateVertexBufferLayout();

        protected native void push(int type, int value);
    }


}
