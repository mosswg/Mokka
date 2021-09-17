#ifndef RENDERER_H
#define RENDERER_H
#include <iostream>
#include "../External/includes/GLEW/include/GL/glew.h"
#include <GLFW/glfw3.h>
#include <Java/include/jni.h>
#include "mka_IndexBuffer.h"



struct ShaderProgramSource
{
    std::string VertexSource;
    std::string FragmentSource;
};


#ifdef __cplusplus
extern "C" {
#endif

    void rendererDraw(JNIEnv*, jobject, const IndexBuffer&, jobject);

/* #define ASSERT(x); //if (!(x)) __debugbreak();

 #define GLCall(x) x;\
 ASSERT(GLLogCall(#x, __FILE__, __LINE__)) 
 */


bool GLLogCall(const char* function, const char* file, int line);

jint getRID(JNIEnv* env, jobject This);

inline void CheckJNIError(JNIEnv* env) {
    if (env->ExceptionCheck()) {
    glfwTerminate();
    env->ExceptionDescribe();
    exit(-7);
    }
}



#ifdef __cplusplus
}
#endif
#endif
