package Mokka;

import Mokka.Shape.AbstractShape;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.nio.file.*;

public class Init {
    public static long delta;
    protected static long lastFrameTime = System.nanoTime();


    static {
        try {
            System.load(new File("Mokka/Natives/External/Modules/glfw/build/src" + mLib.getFileName("glfw")).getAbsolutePath());
            System.load(new File("Mokka/Natives/" + mLib.getFileName("GLEW")).getAbsolutePath());
            System.load(new File("build" + mLib.getFileName("Mokka")).getAbsolutePath());
        } catch (UnsatisfiedLinkError) {
            try {
                System.load(new File("Mokka/Natives/build" + mLib.getFileName("Mokka")).getAbsolutePath());
            } catch (UnsatisfiedLinkError | mLib.OSNotIdentifiedException e) {
            try {
                mLib.loadLibraryFromJar("/Mokka/Natives/" + mLib.getFileName("GLEW")); // during runtime. .DLL/.so/.dylib within .JAR
                mLib.loadLibraryFromJar("/Mokka/Natives/" + mLib.getFileName("glfw")); // during runtime. .DLL/.so/.dylib within .JAR
                mLib.loadLibraryFromJar("/Mokka/Natives/" + mLib.getFileName("Mokka")); // during runtime. .DLL/.so/.dylib within .JAR
            } catch (IOException | mLib.OSNotIdentifiedException e1) {
                e1.printStackTrace();
                throw new RuntimeException(e1);
            }
        }
    }



    protected static native void initWindow(int x, int y, String name);

    /**
     *
     * Creates Window and Finds init and run functions. This
     *
     * @param x - X Size of the Window
     * @param y - Y Size of the Window
     * @param name - Title of the Window
     */
    public static void init(int x, int y, String name) {
        initWindow(x, y, name);


        StackTraceElement[] stackTraceElements = Thread.currentThread().getStackTrace();
        Object callingObject = Internal.getCallingClass(stackTraceElements[stackTraceElements.length - 1]);

        findAndCallInit(callingObject);


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
                            frame(method, null);
                        }
                    else
                        while (!Window.shouldClose()) {
                            frame(method, callingObject);
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
                throw MokkaException.Initialization.RunNotFound;
            }
            else {
                while (!Window.shouldClose()) {
                    frame();
                }
            }
        }


        Window.terminate();
    }

    private static void autoDraw() {
        if (Options.AutoDraw)
            for (AbstractShape s : AbstractShape.ShapeList.keySet())
                if (AbstractShape.ShapeList.get(s))
                    s.draw();
    }

    private static void frame() {
        delta = System.nanoTime() - lastFrameTime;
        Window.clear();
        autoDraw();
        Window.pollAndSwap();
        lastFrameTime = System.nanoTime();
    }

    private static void frame(Method run, Object CallingObject) throws InvocationTargetException, IllegalAccessException {
        delta = System.nanoTime() - lastFrameTime;
        Window.clear();
        autoDraw();
        run.invoke(CallingObject);

        Window.pollAndSwap();
        lastFrameTime = System.nanoTime();
    }

    private static void findAndCallInit(Object callingObject) {
        boolean found = false;
        for (final Method method : callingObject.getClass().getDeclaredMethods()) {
            if (method.getName().equalsIgnoreCase("init")) {
                found = true;
                try {
                    if (Modifier.isStatic(method.getModifiers()))
                        method.invoke(null);
                    else
                        method.invoke(callingObject);
                } catch (IllegalAccessException e) {
                    System.out.println("Init Method must be public");
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }

        if (!found) {
            throw MokkaException.Initialization.InitNotFound;
        }
    }

    static class MokkaTypes {
        final static int unsigned_int = 1;
        final static int unsigned_byte = 2;
        final static int Float = 3;
    }

    public static class MokkaException extends RuntimeException {
        static public class Initialization extends MokkaException {
            public static final Initialization RunNotFound = new Initialization(-8,"Run Function Was Not Found");
            public static final Initialization InitNotFound = new Initialization(-8, "Init Function Was Not Found");


            Initialization(int errorType, String message) {
                super(errorType, message);
            }
        }

        static public class ObjectCreation extends MokkaException {
            public static final ObjectCreation InvalidArgument = new ObjectCreation(-8,"Invalid Argument Given");
            public static final ObjectCreation NonexistentFile = new ObjectCreation(-8,"File Does Not Exist");

            ObjectCreation(int errorType, String message) {
                super(errorType, message);
            }
        }

        static public class Function extends MokkaException {
            public static final ObjectCreation InvalidArgument = new ObjectCreation(-8,"Invalid Argument Given");
            public static final ObjectCreation NonexistentFile = new ObjectCreation(-8,"File Does Not Exist");

            Function(int errorType, String message) {
                super(errorType, message);
            }
        }


        int errorID;
        String message;

        MokkaException(int errorID, String message) {
            this.errorID = errorID;
            this.message = message;
        }

        @Override
        public void printStackTrace() {
            System.err.println("ERROR: " + message);
            Window.terminate();
            System.exit(errorID);
        }
    }



    private static class mLib{

    /**
     * Loads library from current JAR archive
     * <p>
     * The file from JAR is copied into system temporary directory and then loaded. The temporary file is deleted after
     * exiting.
     * Method uses String as filename because the pathname is "abstract", not system-dependent.
     *
     * @param path The path of file inside JAR as absolute path (beginning with '/'), e.g. /package/File.ext
     * @throws IOException If temporary file creation or read/write operation fails
     * @throws IllegalArgumentException If source file (param path) does not exist
     * @throws IllegalArgumentException If the path is not absolute or if the filename is shorter than three characters
     * (restriction of {@link File#createTempFile(java.lang.String, java.lang.String)}).
     * @throws FileNotFoundException If the file could not be found inside the JAR.
     */
    private static final int MIN_PREFIX_LENGTH = 3;
    public static final String NATIVE_FOLDER_PATH_PREFIX = "nativeutils";
    private static File temporaryDir;

    public static void loadLibraryFromJar(String path) throws IOException {

        if (null == path || !path.startsWith("/")) {
            throw new IllegalArgumentException("The path has to be absolute (start with '/').");
        }

        // Obtain filename from path
        String[] parts = path.split("/");
        String filename = (parts.length > 1) ? parts[parts.length - 1] : null;

        // Check if the filename is okay
        if (filename == null || filename.length() < MIN_PREFIX_LENGTH) {
            throw new IllegalArgumentException("The filename has to be at least 3 characters long.");
        }

        // Prepare temporary file
        if (temporaryDir == null) {
            temporaryDir = createTempDirectory(NATIVE_FOLDER_PATH_PREFIX);
            temporaryDir.deleteOnExit();
        }

        File temp = new File(temporaryDir, filename);

        try (InputStream is = mLib.class.getResourceAsStream(path)) {
            Files.copy(is, temp.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            temp.delete();
            throw e;
        } catch (NullPointerException e) {
            temp.delete();
            throw new FileNotFoundException("File " + path + " was not found inside JAR.");
        }

        try {
            System.load(temp.getAbsolutePath());
        } finally {
            if (isPosixCompliant()) {
                // Assume POSIX compliant file system, can be deleted after loading
                temp.delete();
            } else {
                // Assume non-POSIX, and don't delete until last file descriptor closed
                temp.deleteOnExit();
            }
        }
    }

    public static String getFileName(String libname) throws OSNotIdentifiedException {
        String osName = System.getProperty("os.name").toLowerCase();
        String filePrefix = getLibPath();
        if (osName.contains("win"))
            return filePrefix + libname + ".dll";
        else if (osName.contains("mac"))
            return filePrefix + libname + ".dylib";
        else if (osName.contains("nux"))
            return filePrefix + "lib" + libname + ".so";

        throw new OSNotIdentifiedException(osName);
    }

    public static String getLibPath() throws OSNotIdentifiedException {
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win"))
            return "win/";
        else if (osName.contains("mac"))
            return "macOS/";
        else if (osName.contains("nux"))
            return "linux/";

        throw new OSNotIdentifiedException(osName);
    }


    private static boolean isPosixCompliant() {
        try {
            return FileSystems.getDefault()
                    .supportedFileAttributeViews()
                    .contains("posix");
        } catch (FileSystemNotFoundException
                | ProviderNotFoundException
                | SecurityException e) {
            return false;
        }
    }

    private static File createTempDirectory(String   prefix) throws IOException {
        String tempDir = System.getProperty("java.io.tmpdir");
        File generatedDir = new File(tempDir, prefix + System.nanoTime());

        if (!generatedDir.mkdir())
            throw new IOException("Failed to create temp directory " + generatedDir.getName());

        return generatedDir;
    }


    static class OSNotIdentifiedException extends Exception {
        String osName;

        OSNotIdentifiedException(String osName) {
            this.osName = osName;
        }

        @Override
        public String toString() {
            return "OSNotIdentifiedException{" +
                    "osName='" + osName + '\'' +
                    '}';
        }
    }
}
}

