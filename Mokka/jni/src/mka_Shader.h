/* DO NOT EDIT THIS FILE - it is machine generated */
#include "mka_Renderer.h"
#include "vendor/Java/include/jni.h"
#include <string>
#include <sstream>
#include <iostream>
#include <fstream>
#include <string>
#include <unordered_map>
/* Header for class moss_mokka_Shader */
  
  


#ifndef _Included_moss_mokka_Shader
#define _Included_moss_mokka_Shader

void bindShader(JNIEnv* env, jobject shader);

void unbindShader(JNIEnv*, jobject);
  
void setUniform1i(JNIEnv*, jobject, jstring, int);

unsigned int GetUniformLocation(const std::string&, unsigned int);

ShaderProgramSource parseShader(const std::string&);

unsigned int CompileShader(unsigned int, const std::string&);

unsigned int CreateShader(const std::string&, const std::string&); 

#ifdef __cplusplus
extern "C" {
#endif

/*
 * Class:     moss_mokka_Shader
 * Method:    bind
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_Mokka_Shader_bind
  (JNIEnv *, jobject);

/*
 * Class:     moss_mokka_Shader
 * Method:    unbind
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_Mokka_Shader_unbind
  (JNIEnv *, jobject);

  /*
 * Class:     moss_mokka_Shader
 * Method:    genRID
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_Mokka_Shader_genRID
  (JNIEnv* env, jclass, jstring filePath);

/*
 * Class:     moss_mokka_Shader
 * Method:    setUniform
 * Signature: (Ljava/lang/String;I)V
 */
JNIEXPORT void JNICALL Java_Mokka_Shader_setUniform__Ljava_lang_String_2I
  (JNIEnv *, jobject, jstring, jint);

/*
 * Class:     moss_mokka_Shader
 * Method:    setUniform
 * Signature: (Ljava/lang/String;F)V
 */
JNIEXPORT void JNICALL Java_Mokka_Shader_setUniform__Ljava_lang_String_2F
  (JNIEnv *, jobject, jstring, jfloat);

/*
 * Class:     moss_mokka_Shader
 * Method:    setUniform
 * Signature: (Ljava/lang/String;FFFF)V
 */
JNIEXPORT void JNICALL Java_Mokka_Shader_setUniform__Ljava_lang_String_2FFFF
  (JNIEnv *, jobject, jstring, jfloat, jfloat, jfloat, jfloat);


  /*
 * Class:     moss_mokka_Shader
 * Method:    setUniformMat4f
 * Signature: (Ljava/lang/String;J)v
 */
JNIEXPORT void JNICALL Java_Mokka_Shader_setUniformMat4f
  (JNIEnv*, jobject, jstring, jlong);

#ifdef __cplusplus
}
#endif
#endif
