package Mokka;

public class Color {
    float r;
    float g;
    float b;
    float a;

    public Color(float red, float green, float blue, float alpha) {
        r = red;
        g = green;
        b = blue;
        a = alpha;
    }

    public Color(String hex) {
        if (hex.charAt(0) != '#') {
            throw Init.MokkaException.ObjectCreation.InvalidArgument;
        }


        r = Integer.parseInt(hex.substring(1, 3), 16);
        g = Integer.parseInt(hex.substring(3, 5), 16);
        b = Integer.parseInt(hex.substring(5, 7), 16);
        float max = Math.max(Math.max(r, g), b);
        r /= max;
        g /= max;
        b /= max;
        a = (max / 256);

    }
}