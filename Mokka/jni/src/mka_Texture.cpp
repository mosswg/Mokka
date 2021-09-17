#include "mka_Texture.h"
// #include "Mokka.h"
#include "mka_Renderer.h"
#define STB_IMAGE_IMPLEMENTATION
#include "vendor/stb_image/stb_image.h"


/*
 * Class:     moss_mokka_Texture
 * Method:    bind
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_Mokka_Texture_bind
(JNIEnv* env, jobject This, jint slot) {
    glActiveTexture(GL_TEXTURE0 + slot);
    glBindTexture(GL_TEXTURE_2D, getRID(env, This));
}

/*
 * Class:     moss_mokka_Texture
 * Method:    unbind
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_Mokka_Texture_unbind
(JNIEnv*, jobject) {
    glUseProgram(0);
}


/*
* Class:     Mokka_Texture
* Method:    createTexture
* Signature: (Ljava/lang/String;)Lmoss/mokka/Texture;
*/
    JNIEXPORT jobject JNICALL Java_Mokka_Texture_createTexture
    (JNIEnv* env, jclass cls, jstring path) {
        const char* pathChars = env->GetStringUTFChars(path, NULL);
        jobject out = createTexture(env, cls, pathChars);
        env->ReleaseStringUTFChars(path, pathChars);
        return out;
    }

jobject createTexture(JNIEnv* env, jclass texCLS, const char* filePath) {
    int width;
    int height;
    int BPP;
    uint32_t textureRid;
    stbi_set_flip_vertically_on_load(1);


    unsigned char* m_LocalBuffer = stbi_load(filePath, &width, &height, &BPP, 4);
    glGenTextures(1, &textureRid);
    glBindTexture(GL_TEXTURE_2D, textureRid);

    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);

    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, width, height, 0, GL_RGBA, GL_UNSIGNED_BYTE, m_LocalBuffer);
    glBindTexture(GL_TEXTURE_2D, 0);

    if (m_LocalBuffer)
        stbi_image_free(m_LocalBuffer);


    jmethodID midInit = env->GetMethodID(texCLS, "<init>", "(Ljava/lang/String;IIII)V");

    if (!midInit) {
        printf("Texture <init> was not found");
        return NULL;
    }

    return env->NewObject(texCLS, midInit, env->NewStringUTF(filePath), textureRid, width, height, BPP);
}

void bindTexture(JNIEnv* env, jobject This, int slot) {
    Java_Mokka_Texture_bind(env, This, slot);
}
void unbindTexture(JNIEnv* env, jobject This) {
    Java_Mokka_Texture_unbind(env, This);
}
