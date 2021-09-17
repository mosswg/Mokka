cmake_minimum_required(VERSION 3.20)
project(Mokka)

set(CMAKE_CXX_STANDARD 14)


IF (WIN32)
    link_directories(External/win)
ELSE()
    link_directories(External/linux)
ENDIF()

include_directories(src)

add_library(Mokka
        src/mka_IndexBuffer.cpp
        src/mka_IndexBuffer.h
        src/mka_Maths.cpp
        src/mka_Maths.h
        src/mka_Renderer.cpp
        src/mka_Renderer.h
        src/mka_Shader.cpp
        src/mka_Shader.h
        src/mka_Shape.cpp
        src/mka_Shape.h
        src/mka_Texture.cpp
        src/mka_Texture.h
        src/mka_VertexArray.cpp
        src/mka_VertexArray.h
        src/mka_VertexBuffer.cpp
        src/mka_VertexBuffer.h
        src/mka_VertexBufferLayout.cpp
        src/mka_VertexBufferLayout.h
        src/mka_Window.cpp
        src/mka_Window.h
        src/Mokka.cpp
        src/Mokka.h)
