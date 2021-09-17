#include "mka_Shape.h"

/*
 * Class:     Mokka_Shape
 * Method:    translate
 * Signature: (FFF)V
 */
JNIEXPORT void JNICALL Java_Mokka_Shape_translate
  (JNIEnv *, jobject, jfloat, jfloat, jfloat) {
      std::cout << "METHOD translate IS DEPRECARED" << std::endl;

    // jfieldID transFid = env->GetFieldID(env->GetObjectClass(This), "translation", "LMokka/Maths/Matrix4f;");

    // jobject transMat = env->GetObjectField(This, transFid);

    // jclass matrixCls = env->FindClass("Mokka/Maths/Matrix4f");

    // jfieldID dataFid = env->GetFieldID(matrixCls, "data", "[F");

    // jfloatArray dataJava = (jfloatArray)env->GetObjectField(transMat, dataFid);

    // float* data = env->GetFloatArrayElements(dataJava, NULL);

    // glm::mat4 trans = glm::mat4(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11], data[12], data[13], data[14], data[15]);
    
    // trans = glm::translate(trans, glm::vec3(x, y, z));

    // for (int i = 0; i < 4; i++) {
    //     for (int j = 0; j < 4; j++) {
    //         data[j + (i * 4)] = trans[i][j];
    //     }
    // }

    // env->ReleaseFloatArrayElements(dataJava, data, 0);
    
    // transMat = env->NewObject(matrixCls, env->GetMethodID(matrixCls, "<init>", "([F)V"), dataJava);

    // env->SetObjectField(This, transFid, transMat); 
}

glm::mat4 getShapeTranlationMatrix(JNIEnv* env, jobject obj) {

    jobject transMat = env->GetObjectField(obj, env->GetFieldID(env->GetObjectClass(obj), "translation", "LMokka/Maths/Matrix4f;"));

    jfloatArray dataJava = (jfloatArray)env->CallObjectMethod(transMat,  env->GetMethodID(env->GetObjectClass(transMat), "getDataArray", "()[F"));

    CheckJNIError(env);

    float* data = env->GetFloatArrayElements(dataJava, NULL);

    glm::mat4 out = glm::mat4(data[0], data[1], data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11], data[12], data[13], data[14], data[15]);
    
    env->ReleaseFloatArrayElements(dataJava, data, 0);

    return out;
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
    jintArray mvdata = (jintArray)env->GetObjectField(obj, fid);
    return IndexBuffer(env, mvdata);
}

void bindShapeIb(JNIEnv* env, jobject obj) {
    jfieldID fid = env->GetFieldID(env->GetObjectClass(obj), "indices", "[I");
    jintArray mvdata = (jintArray)env->GetObjectField(obj, fid);
    IndexBuffer(env, mvdata).Bind();
}

jobject getShapeMaterial(JNIEnv* env, jobject obj) {
    jfieldID fid = env->GetFieldID(env->GetObjectClass(obj), "material", "LMokka/Material;");
    jobject mat = env->GetObjectField(obj, fid);
    return mat;
}

void bindShapeMaterial(JNIEnv* env, jobject obj) {
    jfieldID fid = env->GetFieldID(env->GetObjectClass(obj), "material", "LMokka/Material;");
    jobject mat = env->GetObjectField(obj, fid);
    env->CallVoidMethod(mat, env->GetMethodID(env->GetObjectClass(mat), "Bind", "()V"));
    CheckJNIError(env);
}