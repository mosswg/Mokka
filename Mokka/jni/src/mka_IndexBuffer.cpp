#include "mka_Renderer.h"

IndexBuffer::IndexBuffer(JNIEnv* env, const jintArray data) : m_Count(env->GetArrayLength(data)) {
    jint* dataArr = env->GetIntArrayElements(data, NULL);
    glGenBuffers(1, &m_RendererID);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, m_RendererID);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, m_Count * sizeof(jint), dataArr, GL_STATIC_DRAW);
    env->ReleaseIntArrayElements(data, dataArr, 0);
}

IndexBuffer::~IndexBuffer() {
    //glDeleteBuffers(GL_ELEMENT_ARRAY_BUFFER, &m_RendererID);
}

void IndexBuffer::Bind() const {
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, m_RendererID);
}


void IndexBuffer::UnBind() const  {
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
}
