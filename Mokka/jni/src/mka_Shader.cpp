#include "mka_Shader.h"
#include "Mokka.h"
#include "mka_Maths.h"

extern glm::mat4 u_MVP;


std::unordered_map<std::string, int> m_LocationCache;
jint shaderRid;

/*
 * Class:     moss_mokka_Shader
 * Method:    bind
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_Mokka_Shader_bind
  (JNIEnv* env, jobject This) {
    uint32_t rid = getRID(env, This);
    glUseProgram(rid);

    int loc = GetUniformLocation("u_MVP", rid);

    if (loc != -1) 
        glUniformMatrix4fv(loc, 1, GL_FALSE, &u_MVP[0][0]);
}

/*
 * Class:     moss_mokka_Shader
 * Method:    unbind
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_Mokka_Shader_unbind
  (JNIEnv*, jobject) {
    glUseProgram(0);
}

/*
 * Class:     moss_mokka_Shader
 * Method:    genRID
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_Mokka_Shader_genRID
  (JNIEnv* env, jclass, jstring filePath) {
    const char* pathChars = env->GetStringUTFChars(filePath, nullptr);

    ShaderProgramSource source = parseShader(pathChars);

    env->ReleaseStringUTFChars(filePath, pathChars);

    return CreateShader(source.VertexSource, source.FragmentSource);

}

/*
 * Class:     moss_mokka_Shader
 * Method:    setUniform
 * Signature: (Ljava/lang/String;I)V
 */
JNIEXPORT void JNICALL Java_Mokka_Shader_setUniform__Ljava_lang_String_2I
  (JNIEnv* env, jobject This, jstring name, jint v0) {
    bindShader(env, This);
    const char* nameChars = env->GetStringUTFChars(name, NULL);

    glUniform1i(GetUniformLocation(nameChars, getRID(env, This)), v0);

    env->ReleaseStringUTFChars(name, nameChars);
}

/*
 * Class:     moss_mokka_Shader
 * Method:    setUniform
 * Signature: (Ljava/lang/String;F)V
 */
JNIEXPORT void JNICALL Java_Mokka_Shader_setUniform__Ljava_lang_String_2F
  (JNIEnv* env, jobject This, jstring name, jfloat v0) {
    bindShader(env, This);
    const char* nameChars = env->GetStringUTFChars(name, NULL);

    glUniform1f(GetUniformLocation(nameChars, getRID(env, This)), v0);
    env->ReleaseStringUTFChars(name, nameChars);
}

/*
 * Class:     moss_mokka_Shader
 * Method:    setUniform
 * Signature: (Ljava/lang/String;FFFF)V
 */
JNIEXPORT void JNICALL Java_Mokka_Shader_setUniform__Ljava_lang_String_2FFFF
  (JNIEnv* env, jobject This, jstring name, jfloat v0, jfloat v1, jfloat v2, jfloat v3) {
    bindShader(env, This);
    const char* nameChars = env->GetStringUTFChars(name, NULL);

    glUniform4f(GetUniformLocation(nameChars, getRID(env, This)), v0, v1, v2, v3);
    env->ReleaseStringUTFChars(name, nameChars);
}


// /*
//  * Class:     moss_mokka_Shader
//  * Method:    setUniformMat4f
//  * Signature: (Ljava/lang/String;J)v
//  */
// JNIEXPORT void JNICALL Java_Mokka_Shader_setUniformMat4f
//   (JNIEnv* env, jobject This, jstring name, jlong ptr) {
//     bindShader(env, This);
//     const char* nameChars = env->GetStringUTFChars(name, NULL);

//     glm::mat4 uniformMatrix = *(glm::mat4*)ptr;

//     glUniformMatrix4fv(GetUniformLocation(nameChars, getRID(env, This)), 1, GL_FALSE, &uniformMatrix[0][0]);
//     env->ReleaseStringUTFChars(name, nameChars);
//   }


void bindShader(JNIEnv* env, jobject shader) {
    Java_Mokka_Shader_bind(env, shader);
}
void unbindShader(JNIEnv* env, jobject shader) {
    Java_Mokka_Shader_unbind(env, shader);
}

void setUniform1i(JNIEnv* env, jobject shader, jstring name, int value) {
    Java_Mokka_Shader_setUniform__Ljava_lang_String_2I(env, shader, name, value);
}


unsigned int GetUniformLocation(const std::string& name, unsigned int rid) {
    if (m_LocationCache.find(name) != m_LocationCache.end()) {
        return m_LocationCache[name];
    }

    int location = glGetUniformLocation(rid, name.c_str());
    if (location == -1) {
        std::cout << "Warning: uniform " << name << " Doesn't exist" << std::endl;
    }

    m_LocationCache[name] = location;

    return location;
}





ShaderProgramSource parseShader(const std::string& filePath) {
    std::ifstream shaderFile(filePath);

    enum class shaderType { NONE = -1, VERTEX = 0, FRAGMENT = 1 };

    std::string line;
    std::stringstream ss[2];
    shaderType type = shaderType::NONE;
    while (getline(shaderFile, line)) {

        if (line.find("#shader") != std::string::npos) {
            if (line.find("vertex") != std::string::npos) {
                type = shaderType::VERTEX;
            }
            else if (line.find("fragment") != std::string::npos) {
                type = shaderType::FRAGMENT;

            }

        }
        else {
            ss[(int)type] << line << '\n';
        }


    }

    return { ss[0].str(), ss[1].str() };
}



unsigned int CompileShader(unsigned int type, const std::string& source) {

    unsigned int id = glCreateShader(type);
    const char* src = source.c_str();
    glShaderSource(id, 1, &src, nullptr);
    glCompileShader(id);

    int result;
    glGetShaderiv(id, GL_COMPILE_STATUS, &result);
    if (result == GL_FALSE) {
        int length;
        glGetShaderiv(id, GL_INFO_LOG_LENGTH, &length);
        char* message = (char*)malloc(length * sizeof(char));
        glGetShaderInfoLog(id, length, &length, message);
        std::cout << "ERROR: Failed to compile shader: " << message << std::endl;
        glDeleteShader(id);
        return 0;
    }
    return id;
}

unsigned int CreateShader(const std::string& vertexShader, const std::string& fragmentShader) {
    unsigned int program = glCreateProgram();
    unsigned int vs = CompileShader(GL_VERTEX_SHADER, vertexShader);
    // std::cout << "vs Shader compiled\n";
    unsigned int fs = CompileShader(GL_FRAGMENT_SHADER, fragmentShader);
    // std::cout << "fs Shader compiled\n";

    glAttachShader(program, vs);
    glAttachShader(program, fs);
    glLinkProgram(program);
    glValidateProgram(program);

    glDeleteShader(vs);
    glDeleteShader(fs);

    return program;
}
