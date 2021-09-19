package Mokka;

public class Options {
    public static boolean AutoDraw = false;
    protected static Material DefaultMaterial = new Material.BasicColor(new Color("#ffffff"));

    public static Material getDefaultMaterial() {
        return DefaultMaterial.copy();
    }

    public static void setDefaultMaterial(Material defaultMaterial) {
        DefaultMaterial = defaultMaterial;
    }


    public static void setScale(float xMin, float xMax, float yMin, float yMax) {
        System.out.println("This Function is Not Yet Implemented");
    }
}
