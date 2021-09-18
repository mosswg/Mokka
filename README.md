# Mokka

# Building 
Mokka uses CMake to Build. For People Inexperienced in CMake there are detailed Instruction on the [wiki](https://github.com/mossx-dev/Mokka/wiki/Building). <br>
<br>
See [Build Options](https://github.com/mossx-dev/Mokka/wiki/Building#Options) for How to Enable/Disable These Features.<br>

Running CMake in the project directory will get submodules. Then build them into shared libraries. The libs will be moved to `Mokka/Natives/libs`. <br> 
<br>
After Getting and Building all dependencies it will create the mokka shared library in the CMake build directory. <br> 
<br>
To Manually Build A Jar the mokka lib must be in the `Mokka/Natives/libs` folder. Jars can be build automatically with the MOKKA_BUILD_JAR option. <br>
<br>

# How to Use

## Initial Setup
Any Mokka Executable Must Have a Public Init Function Which Will be Called Once When The Program Runs. <br>
<br>
There must also be a Public Run Function Which Will be Called Every Frame. <br>
<br>
To Start Mokka Call the init Function with Width, Height, and Window Title Within a Java main Function. <br>
<br>

## Creating Shapes 
Shapes are Created Like Any Other Object in Java. To be drawn they need to be assigned a material. <br>
<br>
There are three builtin materials BasicColor, BasicTexture, and BasicRainbow. <br>
<br>
BasicColor Takes in a Color Object and Will be Drawn as the Set Color. The Color can Changed with the setColor Method <br>
<br>
BasicRainbow is Similar to BasicColor But There Are no Arguments. Every Time It's Drawn It's Color Will Automatically Change by a Small Random Amount. <br>
<br>
BasicTexture Takes a Texture Object and It Will be Drawn With the Set Texture. The Texture Can be Change With the setTexture Method. <br>
<br>

## Drawing Shapes
Shapes Can be Drawn in the Run Function With the Draw Function <br>
e.g. If You Have A Shape Object Called Shape
```Java
shape.draw();
```



# Examples

## Basic Red Square
### Code
```Java
package Examples;

import Mokka.*;
import Mokka.Shape.*;

public class Basic {
    static Rect square;

    public static void init() {
        Material red = new Material.BasicColor(new Color(1, 0, 0, 1));

        square = new Rect(0, 0, 0.5f,0.5f);

        square.setMaterial(red);
    }

    public static void run() {

        square.draw();

    }
    
    public static void main(String[] args) {
        Init.init(800, 800, "Example Square");
    }
}
```
### Output <br>
![](Examples/Images/BasicRedSquare.png)
<br>
<br>