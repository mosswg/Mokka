package Mokka;

import Mokka.Shape.AbstractShape;

public class Window {

    public static native void clear();

    public static native boolean shouldClose();

    public static native void setPos(int xpos, int ypos);

    public static native void pollAndSwap();

    public static native float getWidth();

    public static native float getHeight();

    public static native void terminate();

    public static native void draw(AbstractShape abstractShape);

    /**
     * Translates the View Matrix by the Given Values
     *
     * @param x - X Coordinate of the Camera
     * @param y - Y Coordinate of the Camera
     * @param z - Z Coordinate of the Camera
     */
    public static native void translateCamera(float x, float y, float z);

    public static native float getCameraX();

    public static native float getCameraY();

    /**
     * Sets the Projection Matrix to an Orthographic Value With the Coordinates Given
     *
     * @param xMin - Left-most Edge X Coordinate
     * @param xMax - Right-most Edge X Coordinate
     * @param yMin - Bottom Edge Y Coordinate
     * @param yMax - Top Edge Y Coordinate
     */
    public static native void setOrthoCoords(float xMin, float xMax, float yMin, float yMax);
}
