#include "Mokka.h"
#include <cstdio>
#include <list>


GLFWwindow* window;
char keys[148];
const bool ScreenSaverMode = false;




/*
Exit Codes:

 1: Program Ran and Exited Successfully
-1: glfw Function Was Called Before Intialization
-2: Shader or Texture Not Created Before Running createWindow
-3: Object Variable Not Found (internal)
-4: Incorrect Variable Type Found
-5: Function Could Not be Found
-6: glfw Failed to Initalize
-7: JNI Execption
-8: User Error
*/

/*
* Class:     Mokka_Init
* Method:    initWindow
* Signature: (IILjava/lang/String;)V
*/
JNIEXPORT void JNICALL Java_Mokka_Init_initWindow
(JNIEnv* env, jclass, jint x, jint y, jstring name) {

    /* Initialize the library */
    if (!glfwInit()) {
        std::cout << "ERROR: Glfw Failed to Initialize\n" << std::endl;
        const char* error;
        glfwGetError(&error);
        std::cout << error << "\nExiting" << std::endl;
        glfwTerminate();
        exit(-6);
    }

    glfwWindowHint(GLFW_SAMPLES, 4);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GL_TRUE); // To make MacOS happy; should not be needed
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);


    const char* nameChars = env->GetStringUTFChars(name, nullptr);

    /* Create a windowed mode window and its OpenGL context */
    window = glfwCreateWindow(x, y, nameChars, nullptr, nullptr);

    if (!window)
    {
        glfwTerminate();
    }


    /* Make the window's context current */
    glfwMakeContextCurrent(window);


    glfwSwapInterval(1);


    if (glewInit() != GLEW_OK) {
        printf("ERROR: GlewInit was called before OpenGl context defined \n");
    }
    else {
        std::cout << "Glew Initialized Successfully Using GL Version: " << glGetString(GL_VERSION) << std::endl;
    }

    glfwSetInputMode(window, GLFW_STICKY_KEYS, GL_TRUE);
    glEnableDefaultBlending();

    glfwSetKeyCallback(window, key_callback);

    if (ScreenSaverMode)
        glfwSetWindowPosCallback(window, WindowDVD);

    glDebugMessageCallback(OpenGlLogMessageCallback, nullptr);
    glEnable(GL_DEBUG_OUTPUT);

    
    env->ReleaseStringUTFChars(name, nameChars);
}


int xMod = 1;
int yMod = 1;
void WindowDVD(GLFWwindow*, int xpos, int ypos) {
    
    int width, height;

    glfwGetWindowSize(window, &width, &height);

    if (xpos >= 1919 - width) {
        xMod = -2;
    }
    else if (xpos <= 0) {
        xMod = 2;
    }
    if (ypos >= 1055 - height) {
        yMod = -2;
    }
    else if (ypos <= 59) {
        yMod = 2;
    }

    glfwSetWindowPos(window, xpos + xMod, ypos + yMod);
}



void OpenGlLogMessageCallback(GLenum,
            GLenum,
            GLuint,
            GLenum,
            GLsizei,
            const GLchar* message,
            const void*) {


            std::cout << message << std::endl;


            }




void key_callback(GLFWwindow*, int key, int, int action, int)
{
    if (key != GLFW_KEY_UNKNOWN) {
        if (action == GLFW_PRESS)
            keys[getKeyArrayLocation(key)] = 1;
        else if (action == GLFW_REPEAT)
            keys[getKeyArrayLocation(key)] = 2;
        else if (action == GLFW_RELEASE) 
            keys[getKeyArrayLocation(key)] = 3;
    }
}


int getKeyArrayLocation(int keyCode) {
    switch(keyCode) {
        case 32:
            return 0;
        case 39:
            return 1;
        case 161:
            return 55;
        case 162:
            return 56;
        default:
            if (keyCode >= 44 && keyCode <= 96)
                return keyCode - 42;
            else if (keyCode >= 256 && keyCode <= 348) 
                return keyCode - 201;
    }
    std::cout << "Unknown Key Code: " << keyCode << std::endl;

    return 148;
}


    /*
* Class:     Mokka_Internal
* Method:    getKeyState
* Signature: (I)I
*/
    JNIEXPORT jint JNICALL Java_Mokka_Internal_getKeyState
    (JNIEnv*, jclass, jint keyCode) {
        return keys[getKeyArrayLocation(keyCode)];
    }

    /*
* Class:     Mokka_Internal
* Method:    keyIsPressed
* Signature: (I)Z
*/
    JNIEXPORT jboolean JNICALL Java_Mokka_Internal_keyIsPressed
    (JNIEnv*, jclass, jint keyCode) {
        return keys[getKeyArrayLocation(keyCode)] == 1;
    }

        /*
* Class:     Mokka_Internal
* Method:    keyIsPressedOrHeld
* Signature: (I)Z
*/
    JNIEXPORT jboolean JNICALL Java_Mokka_Internal_keyIsPressedOrHeld
    (JNIEnv*, jclass, jint keyCode) {
        char keyValue = keys[getKeyArrayLocation(keyCode)];

        return keyValue == 1 || keyValue == 2;
    }


            /*
* Class:     Mokka_Internal
* Method:    keyIsHeld
* Signature: (I)Z
*/
    JNIEXPORT jboolean JNICALL Java_Mokka_Internal_keyIsHeld
    (JNIEnv*, jclass, jint keyCode) {
        return keys[getKeyArrayLocation(keyCode)] == 2;
    }



        /*
* Class:     Mokka_Internal
* Method:    keyIsReleased
* Signature: (I)Z
*/
    JNIEXPORT jboolean JNICALL Java_Mokka_Internal_keyIsReleased
    (JNIEnv*, jclass, jint keyCode) {
        return keys[getKeyArrayLocation(keyCode)] == 3;
    }






 

    /*
    * Class:     Mokka_Internal
    * Method:    getCallingClass
    * Signature: (Ljava/lang/StackTraceElement;)Ljava/lang/Class;
    */
    JNIEXPORT jobject JNICALL Java_Mokka_Internal_getCallingClass (JNIEnv* env, jclass, jobject STE) {
        jfieldID fid = env->GetFieldID(env->GetObjectClass(STE), "declaringClass", "Ljava/lang/String;");
        auto decCls = (jstring)env->GetObjectField(STE, fid);
        const char* clsChars = env->GetStringUTFChars(decCls, nullptr);
        int arrLen = (env->GetStringUTFLength(decCls) + 1);
        char* charMod = new char[arrLen];
        for (int i = 0; i < env->GetStringUTFLength(decCls); i++) {
            if (clsChars[i] == '.') {
                charMod[i] = '/';
                continue;
            }

            charMod[i] = clsChars[i];
        }
        charMod[arrLen - 1] = '\0';
        
        jclass objCls = env->FindClass(charMod);

        jmethodID mid = env->GetMethodID(objCls, "<init>", "()V");
        if (mid == nullptr) {
            //printf("Initalizer For Calling Object Not Found Ensure An Initalizer with No Arguments is available");
            validateInstance();
            glfwDestroyWindow(window);
            glfwTerminate();
            exit(-5);
        }
        jobject out = env->NewObject(objCls, mid);
        return out;
    }



    void validateInstance() {
        if (window == nullptr) {
            //printf("openGL must be initialize with the init method before this action can be performed");
            glfwTerminate();
            exit(-1);
        }

    }
    
    void rendererClear()
    {
        glClear(GL_COLOR_BUFFER_BIT);
    }