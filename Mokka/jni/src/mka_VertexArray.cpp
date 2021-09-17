#include "mka_VertexArray.h"
#include "mka_Renderer.h"
#include "mka_VertexBuffer.h"
#include "mka_VertexBufferLayout.h"



extern std::vector<VertexBufferLayout> vbl;

void glEnableDefaultBlending() {
	glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
}

/*
 * Class:     Mokka_VertexArray
 * Method:    glEnableDefaultBlending
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_Mokka_VertexArray_glEnableDefaultBlending
  (JNIEnv *, jclass) {
	glEnableDefaultBlending();
  }

/*
 * Class:     Mokka_VertexArray
 * Method:    GenRID
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_Mokka_VertexArray_GenID
(JNIEnv *, jclass) {
	unsigned int rid;
	glGenVertexArrays(1, &rid);
	return rid;
  }

/*
 * Class:     Mokka_VertexArray
 * Method:    AddBuffer
 * Signature: (LMokka/VertexBuffer;J)V
 */
JNIEXPORT void JNICALL Java_Mokka_VertexArray_AddBuffer
  (JNIEnv* env, jobject This, jobject vb, jlong layoutindex) {
	Java_Mokka_VertexArray_Bind(env, This);
	Java_Mokka_VertexBuffer_Bind(env, vb);

	VertexBufferLayout layout = vbl[layoutindex];

	const auto& elements = layout.GetElements();
	uint32_t offset = 0;
	for (unsigned int i = 0; i < elements.size(); i++) {
		const auto& element = elements[i];
		
		glEnableVertexAttribArray(i);

		glVertexAttribPointer(i, element.count, element.type, element.normalized, layout.GetStride(), (const void*)offset);

		offset += element.count * GetSizeOfType(element.type);
	}
}

/*
 * Class:     Mokka_VertexArray
 * Method:    Destruct
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_Mokka_VertexArray_Destruct
  (JNIEnv* env, jobject This) {
	const uint32_t rid = getRID(env, This);
	glDeleteVertexArrays(1, &rid);
  }

/*
 * Class:     Mokka_VertexArray
 * Method:    Bind
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_Mokka_VertexArray_Bind
  (JNIEnv* env, jobject This) {
	unsigned int rid = getRID(env, This);
	glBindVertexArray(rid);
  }

/*
 * Class:     Mokka_VertexArray
 * Method:    UnBind
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_Mokka_VertexArray_UnBind
  (JNIEnv *, jobject) {
	glBindVertexArray(0);
  }