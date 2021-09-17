package Mokka;

public class Internal {


    public static int getKeyState(char key) {
        return getKeyState((int)Character.toUpperCase(key));
    }

    public static native int getKeyState(int keyCode);

    public static boolean keyIsPressed(char key) {
        return keyIsPressed((int)Character.toUpperCase(key));
    }

    public static native boolean keyIsPressed(int keyCode);

    public static boolean keyIsHeld(char key) {
        return keyIsHeld((int)Character.toUpperCase(key));
    }

    public static native boolean keyIsHeld(int keyCode);

    public static boolean keyIsPressedOrHeld(char key) {
        return keyIsPressedOrHeld((int)Character.toUpperCase(key));
    }

    public static native boolean keyIsPressedOrHeld(int keyCode);

    public static boolean keyIsReleased(char key) {
        return keyIsReleased((int)Character.toUpperCase(key));
    }

    public static native boolean keyIsReleased(int keyCode);


    static native Object getCallingClass(StackTraceElement e);


    public static class Keys {
                public static final int UNKNOWN = -1;

                public static final int SPACE = 32;

                public static final int APOSTROPHE = 39; /* ' */

                public static final int COMMA = 44; /* , */

                public static final int MINUS = 45; /* - */

                public static final int PERIOD = 46; /* . */

                public static final int SLASH = 47; /* / */

                public static final int ZERO = 48;

                public static final int ONE = 49;

                public static final int TWO = 50;

                public static final int THREE = 51;

                public static final int FOUR = 52;

                public static final int FIVE = 53;

                public static final int SIX = 54;

                public static final int SEVEN = 55;

                public static final int EIGHT = 56;

                public static final int NINE = 57;

                public static final int SEMICOLON = 59; /* ; */

                public static final int EQUAL = 61; /* = */

                public static final int A = 65;

                public static final int B = 66;

                public static final int C = 67;

                public static final int D = 68;

                public static final int E = 69;

                public static final int F = 70;

                public static final int G = 71;

                public static final int H = 72;

                public static final int I = 73;

                public static final int J = 74;

                public static final int K = 75;

                public static final int L = 76;

                public static final int M = 77;

                public static final int N = 78;

                public static final int O = 79;

                public static final int P = 80;

                public static final int Q = 81;

                public static final int R = 82;

                public static final int S = 83;

                public static final int T = 84;

                public static final int U = 85;

                public static final int V = 86;

                public static final int W = 87;

                public static final int X = 88;

                public static final int Y = 89;

                public static final int Z = 90;

                public static final int LEFT_BRACKET = 91; /* [ */

                public static final int BACKSLASH = 92; /* \ */

                public static final int RIGHT_BRACKET = 93; /* ] */

                public static final int GRAVE_ACCENT = 96; /* ` */

                public static final int WORLD_1 = 161; /* non-US #1 */

                public static final int WORLD_2 = 162; /* non-US #2 */

                public static final int ESCAPE = 256;

                public static final int ENTER = 257;

                public static final int TAB = 258;

                public static final int BACKSPACE = 259;

                public static final int INSERT = 260;

                public static final int DELETE = 261;

                public static final int RIGHT = 262;

                public static final int LEFT = 263;

                public static final int DOWN = 264;

                public static final int UP = 265;

                public static final int PAGE_UP = 266;

                public static final int PAGE_DOWN = 267;

                public static final int HOME = 268;

                public static final int END = 269;

                public static final int CAPS_LOCK = 280;

                public static final int SCROLL_LOCK = 281;

                public static final int NUM_LOCK = 282;

                public static final int PRINT_SCREEN = 283;

                public static final int PAUSE = 284;

                public static final int F1 = 290;

                public static final int F2 = 291;

                public static final int F3 = 292;

                public static final int F4 = 293;

                public static final int F5 = 294;

                public static final int F6 = 295;

                public static final int F7 = 296;

                public static final int F8 = 297;

                public static final int F9 = 298;

                public static final int F10 = 299;

                public static final int F11 = 300;

                public static final int F12 = 301;

                public static final int F13 = 302;

                public static final int F14 = 303;

                public static final int F15 = 304;

                public static final int F16 = 305;

                public static final int F17 = 306;

                public static final int F18 = 307;

                public static final int F19 = 308;

                public static final int F20 = 309;

                public static final int F21 = 310;

                public static final int F22 = 311;

                public static final int F23 = 312;

                public static final int F24 = 313;

                public static final int F25 = 314;

                public static final int KP_0 = 320;

                public static final int KP_1 = 321;

                public static final int KP_2 = 322;

                public static final int KP_3 = 323;

                public static final int KP_4 = 324;

                public static final int KP_5 =325;

                public static final int KP_6 = 326;

                public static final int KP_7 = 327;

                public static final int KP_8 = 328;

                public static final int KP_9 = 329;

                public static final int KP_DECIMAL = 330;

                public static final int KP_DIVIDE = 331;

                public static final int KP_MULTIPLY = 332;

                public static final int KP_SUBTRACT = 333;

                public static final int KP_ADD = 334;

                public static final int KP_ENTER = 335;

                public static final int KP_EQUAL = 336;

                public static final int LEFT_SHIFT = 340;

                public static final int LEFT_CONTROL = 341;

                public static final int LEFT_ALT = 342;

                public static final int LEFT_SUPER = 343;

                public static final int RIGHT_SHIFT = 344;

                public static final int RIGHT_CONTROL = 345;

                public static final int RIGHT_ALT = 346;

                public static final int RIGHT_SUPER = 347;

                public static final int KEY_MENU = 348;
    }
}
