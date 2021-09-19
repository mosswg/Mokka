#include "mka_Window.h"

glm::mat4 projMatrix = glm::ortho(-1.0f, 1.0f, -1.0f, 1.0f, -1.0f, 1.0f);
glm::mat4 view  = glm::mat4(1.0f);
glm::mat4 u_MVP;
extern GLFWwindow* window;


/*
* Class:     Mokka_Window
* Method:    setOrthoCoords
* Signature: (FFFF)V
*/
JNIEXPORT void JNICALL Java_Mokka_Window_setOrthoCoords
    (JNIEnv*, jclass, jfloat xMin, jfloat xMax, jfloat yMin, jfloat yMax) {
        projMatrix = glm::ortho(xMin, xMax, yMin, yMax, -1.0f, 1.0f); 
    }

/*
* Class:     Mokka_Window
* Method:    setOrthoCoords
* Signature: (FFF)V
*/
JNIEXPORT void JNICALL Java_Mokka_Window_translateCamera
    (JNIEnv*, jclass, jfloat x, jfloat y, jfloat z) {
        view = glm::translate(view, glm::vec3(-x, -y, -z)); 
}

 /*
 * Class:     Mokka_Window
 * Method:    getCameraX
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_Mokka_Window_getCameraX
  (JNIEnv* , jclass)
{
    return -view[3][0];
}

 /*
 * Class:     Mokka_Window
 * Method:    getCameraY
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_Mokka_Window_getCameraY
  (JNIEnv* , jclass)
{
    return -view[3][1];
}



/*
* Class:     Mokka_Window
* Method:    shouldClose
* Signature: ()Z
*/
    JNIEXPORT jboolean JNICALL Java_Mokka_Window_shouldClose
    (JNIEnv*, jclass) {
        return glfwWindowShouldClose(window);
    }

 /*
 * Class:     Mokka_Window
 * Method:    setPos
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_Mokka_Window_setPos
  (JNIEnv* , jclass, jint xpos, jint ypos) {
      glfwSetWindowPos(window, xpos, ypos);
  }


 /*
 * Class:     Mokka_Window
 * Method:    GetWidth
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_Mokka_Window_getWidth
  (JNIEnv* , jclass)
{
    int width;
    glfwGetWindowSize(window, &width, nullptr);

    return (float)width;
}

 /*
 * Class:     Mokka_Window
 * Method:    GetHeight
 * Signature: ()F
 */
JNIEXPORT jfloat JNICALL Java_Mokka_Window_getHeight
  (JNIEnv* , jclass)
{
    int height;
    glfwGetWindowSize(window, nullptr, &height);

    return (float)height;
}


     /*
 * Class:     Mokka_Window
 * Method:    clear
 * Signature: ()V
 */
    JNIEXPORT void JNICALL Java_Mokka_Window_clear
    (JNIEnv*, jclass) {
        rendererClear();
    }

    /*
 * Class:     Mokka_Window
 * Method:    pollAndSwap
 * Signature: ()V
 */
    JNIEXPORT void JNICALL Java_Mokka_Window_pollAndSwap
    (JNIEnv*, jclass) {
        glfwSwapBuffers(window);
        glfwPollEvents();
    }


    /*
    * Class:     Mokka_Window
    * Method:    terminate
    * Signature: ()V
    */
    JNIEXPORT void JNICALL Java_Mokka_Window_terminate
    (JNIEnv*, jclass) {
        validateInstance();
        glfwDestroyWindow(window);
        glfwTerminate();
        exit(1);
    }


/*
 * Class:     Mokka_Window
 * Method:    draw
 * Signature: (LMokka/Shape;)V
 */
JNIEXPORT void JNICALL Java_Mokka_Window_draw
  (JNIEnv * env, jclass, jobject shape){
    u_MVP = projMatrix * (getShapeTranlationMatrix(env, shape) * view);
    bindShapeMaterial(env, shape);
    bindShapeVa(env, shape);


    IndexBuffer ib = getShapeIb(env, shape);
    ib.Bind();

    glDrawElements(GL_TRIANGLES, ib.GetCount(), GL_UNSIGNED_INT, nullptr);
}