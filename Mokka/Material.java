package Mokka;


import java.io.File;

public abstract class Material {
    Shader matShader;

    public abstract void Bind();

    /**
     *
     * Basic Material That Uses A Single Set Color
     *
     */
    public static final class BasicColor extends Material {
        Shader matShader = new Shader("Mokka/res/Shaders/BasicColor.shader");
        Color c;

        public BasicColor() {}

        public BasicColor(Color color) {
            this.c = color;
        }

        public void setColor(Color color) {
            this.c = color;
        }

        @Override
        public void Bind() {
            matShader.bind();
            matShader.setUniform("u_Color", c.r, c.g, c.b, c.a);
        }
    }

    /**
     *
     * Material That Generates A Random Base Color and Adds or Subtracts a Constant Random Value Every Frame;
     *
     */
    public static final class Rainbow extends Material {
        Shader matShader = new Shader("Mokka/res/Shaders/BasicColor.shader");
        Color c;
        float rPlus = (float)Math.random() / 25f;
        float gPlus = (float)Math.random() / 25f;
        float bPlus = (float)Math.random() / 25f;

        public Rainbow() {
            this.c = new Color((float)Math.random(), (float)Math.random(), (float)Math.random(), 1f);
        }

        public void setColor(Color color) {
            this.c = color;
        }

        @Override
        public void Bind() {
            matShader.bind();
            if (c.r >= 1f || c.r <= 0f) {
                rPlus = -rPlus;
            }
            if (c.g >= 1f || c.g <= 0f) {
                gPlus = -gPlus;
            }
            if (c.b >= 1f || c.b <= 0f) {
                bPlus = -bPlus;
            }

            c.r += rPlus;
            c.g += gPlus;
            c.b += bPlus;
            matShader.setUniform("u_Color", c.r, c.g, c.b, c.a);
        }
    }

    /**
     *
     * Basic Material That Uses A Single Set Texture
     *
     */
    public static final class BasicTexture extends Material {
        Shader matShader = new Shader("Mokka/res/Shaders/BasicTexture.shader");
        Texture t;

        public BasicTexture() {}

        /**
         *
         * @param tex - The Texture The Material Uses
         */
        public BasicTexture(Texture tex) {
            this.t = tex;
        }

        public void setTexture(Texture tex) {
            this.t = tex;
        }

        @Override
        public void Bind() {
            matShader.bind();
            t.bind();
            matShader.setUniform("u_Texture", 0);
        }
    }
}