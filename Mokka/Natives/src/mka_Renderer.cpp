#include "mka_Renderer.h"
#include "mka_Shader.h"
#include "mka_VertexArray.h"
#include <iomanip>
#include <iostream>


bool GLLogCall(const char* function, const char* file, int line) {
    while (GLenum error = glGetError()) {
        std::cout << "openGL ERROR: " << std::dec << error << "\n Hex: " << std::hex << error << " " << function << " in " << file << " on line " << line << "\n";
        return false;
    }
    return true;
}


void rendererDraw(JNIEnv* , jobject , const IndexBuffer& , jobject ) {

}


jint getRID(JNIEnv* env, jobject This) {
    jclass mvclass = env->GetObjectClass(This);

	jfieldID fid = env->GetFieldID(mvclass, "RendererID", "I");

    if (fid == NULL) {
        glfwTerminate();
        printf("RendererID was not found");
        exit(-3);
    }


	return env->GetIntField(This, fid);
}