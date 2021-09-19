#include "mka_Shape.h"

/*
 * Class:     Mokka_Shape
 * Method:    translate
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_Mokka_Shape_translate
  (JNIEnv *, jobject, jfloat, jfloat, jfloat) {
      std::cout << "METHOD translate IS DEPRECARED" << std::endl;
}

glm::mat4 getShapeTranlationMatrix(JNIEnv* env, jobject obj) {
    if (obj == nullptr) {
        return glm::mat4(1);
    }

    jobject transMat = env->GetObjectField(obj, env->GetFieldID(env->GetObjectClass(obj), "translation", "LMokka/Maths/Matrix4f;"));

    auto dataJava = (jfloatArray)env->CallObjectMethod(transMat,  env->GetMethodID(env->GetObjectClass(transMat), "getDataArray", "()[F"));

    CheckJNIError(env);

    float* data = env->GetFloatArrayElements(dataJava, nullptr);

    glm::mat4 out = glm::mat4(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11], data[12], data[13], data[14], data[15]);
    
    env->ReleaseFloatArrayElements(dataJava, data, 0);

    glm::mat4 parentTranslation = getShapeTranlationMatrix(env, getShapeParent(env, obj));

    return out;
}

jobject getShapeParent(JNIEnv* env, jobject obj) {
    jfieldID fid = env->GetFieldID(env->GetObjectClass(obj), "parent", "LMokka/Shape/AbstractShape;");

    CheckJNIError(env);

    return env->GetObjectField(obj, fid);
}


jobject getShapeVa(JNIEnv* env, jobject obj) {

    jobject va = env->CallObjectMethod(obj, env->GetMethodID(env->GetObjectClass(obj), "getVertexArray", "()LMokka_VertexArray;"));
    
    CheckJNIError(env);

    return va;
}

void bindShapeVa(JNIEnv* env, jobject obj) {

    jobject va = env->CallObjectMethod(obj, env->GetMethodID(env->GetObjectClass(obj), "getVertexArray", "()LMokka/VertexArray;"));
    
    CheckJNIError(env);

    Java_Mokka_VertexArray_Bind(env, va);
}


IndexBuffer getShapeIb(JNIEnv* env, jobject obj) {
    jfieldID fid = env->GetFieldID(env->GetObjectClass(obj), "indices", "[I");
    auto mvdata = (jintArray)env->GetObjectField(obj, fid);
    return {env, mvdata};
}

void bindShapeIb(JNIEnv* env, jobject obj) {
    jfieldID fid = env->GetFieldID(env->GetObjectClass(obj), "indices", "[I");
    auto mvdata = (jintArray)env->GetObjectField(obj, fid);
    IndexBuffer(env, mvdata).Bind();
}

jobject getShapeMaterial(JNIEnv* env, jobject obj) {
    jfieldID fid = env->GetFieldID(env->GetObjectClass(obj), "material", "LMokka/Material;");
    jobject mat = env->GetObjectField(obj, fid);
    if (mat == nullptr) {
        std::cout << "ERROR: Shape Must Have Material To Be Drawn" << std::endl;
        glfwTerminate();
        exit(-8);
    }
    return mat;
}

void bindShapeMaterial(JNIEnv* env, jobject obj) {
    jfieldID fid = env->GetFieldID(env->GetObjectClass(obj), "material", "LMokka/Material;");
    jobject mat = env->GetObjectField(obj, fid);
    if (mat == nullptr) {
        std::cout << "ERROR: Shape Must Have Material To Be Drawn" << std::endl;
        glfwTerminate();
        exit(-8);
    }
    env->CallVoidMethod(mat, env->GetMethodID(env->GetObjectClass(mat), "Bind", "()V"));
    CheckJNIError(env);
}