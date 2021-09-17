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
        r = Integer.parseInt(hex.substring(1, 2));
        g = Integer.parseInt(hex.substring(3, 4));
        b = Integer.parseInt(hex.substring(5, 6));
        r /= 256;
        g /= 256;
        b /= 256;

        System.out.println(r);
        System.out.println(g);
        System.out.println(b);

    }
}