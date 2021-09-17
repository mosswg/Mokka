#include "mka_Renderer.h"
#include "mka_VertexBuffer.h"


/*
 * Class:     moss_mokka_VertexBuffer
 * Method:    gen
 * Signature: ([FI)I
 */
JNIEXPORT jint JNICALL Java_Mokka_VertexBuffer_gen
  (JNIEnv* env, jobject, jfloatArray dataArr, jint size) {

    uint32_t rid;
    glGenBuffers(1, &rid); 
    glBindBuffer(GL_ARRAY_BUFFER, rid);
    
    jfloat* data = env->GetFloatArrayElements(dataArr, NULL);

    glBufferData(GL_ARRAY_BUFFER, size * sizeof(float), (const float*)data, GL_STATIC_DRAW);

    env->ReleaseFloatArrayElements(dataArr, data, 0);

    return rid;
  }

/*
 * Class:     moss_mokka_VertexBuffer
 * Method:    Destruct
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_Mokka_VertexBuffer_Destruct
  (JNIEnv* env, jobject This) {
    uint32_t rid = getRID(env, This);
    glDeleteBuffers(GL_ARRAY_BUFFER, &rid);
  }

/*
 * Class:     moss_mokka_VertexBuffer
 * Method:    Bind
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_Mokka_VertexBuffer_Bind
  (JNIEnv* env, jobject This) {
    uint32_t rid = getRID(env, This);
    glBindBuffer(GL_ARRAY_BUFFER, rid);
  }

/*
 * Class:     moss_mokka_VertexBuffer
 * Method:    UnBind
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_Mokka_VertexBuffer_UnBind
  (JNIEnv*, jobject) {
    glBindBuffer(GL_ARRAY_BUFFER, 0);
  }