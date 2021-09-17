#include "mka_VertexBufferLayout.h"



std::vector<VertexBufferLayout> vbl;


/*
 * Class:     moss_mokka_VertexBuffer_Layout
 * Method:    CreateVertexBufferLayout
 * Signature: ()J
 */
JNIEXPORT jint JNICALL Java_Mokka_VertexBuffer_00024Layout_CreateVertexBufferLayout
  (JNIEnv*, jclass) {
      vbl.push_back(VertexBufferLayout());

      return vbl.size() - 1;
  }



/*
 * Class:     moss_mokka_VertexBuffer_Layout
 * Method:    push
 * Signature: (II)V
 */
JNIEXPORT void JNICALL Java_Mokka_VertexBuffer_00024Layout_push
  (JNIEnv* env, jobject This, jint type, jint count) {

        jclass cls = env->GetObjectClass(This);
      
        jfieldID fid = env->GetFieldID(cls, "index", "I");

        jint index = env->GetIntField(This, fid);

        vbl[index].Push(type, count);
}

