package Mokka;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Mokka {

    /**
     *
     * Creates Window and Finds init and run functions. This
     *
     * @param x - X Size of the Window
     * @param y - Y Size of the Window
     * @param name - Title of the Window
     */
    public static void init(int x, int y, String name) {
        Init.initWindow(x, y, name);


        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        Object callingObject = Internal.getCallingClass(stackTraceElements[stackTraceElements.length - 1]);

        Init.findAndCallInit(callingObject);


        boolean found = false;
        for (final Method method : callingObject.getClass().getDeclaredMethods()) {
            if (method.getName().equalsIgnoreCase("run")) {
                found = true;
                try {
                    if (method.getParameterCount() != 0) {
                        System.out.println("Run Method Must Take No Arguments");
                        continue;
                    }
                    if (Modifier.isStatic(method.getModifiers()))
                        while (!Window.shouldClose()) {
                            Init.frame(method, null);
                        }
                    else
                        while (!Window.shouldClose()) {
                            Init.frame(method, callingObject);
                        }
                } catch (IllegalAccessException e) {
                    System.out.println("Run Method must be public");
                } catch (InvocationTargetException e) {
                    e.getCause().printStackTrace();
                    //e.printStackTrace();
                }
                break;
            }
        }
        if (!found) {
            if (!Options.AutoDraw) {
                throw Init.MokkaException.Initialization.RunNotFound;
            }
            else {
                while (!Window.shouldClose()) {
                    Init.frame();
                }
            }
        }


        Window.terminate();
    }
}
