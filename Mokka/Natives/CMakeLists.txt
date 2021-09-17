cmake_minimum_required(VERSION 3.20)
project(Mokka)

set(CMAKE_CXX_STANDARD 14)



# Get Submodules
find_package(Git QUIET)
# If the user is using git fetch the submodules
if(GIT_FOUND AND EXISTS "${PROJECT_SOURCE_DIR}/../../.git")
    # Option to disable fetching submodules on build
    option(GIT_SUBMODULE "Check submodules during build" ON)
    if(GIT_SUBMODULE)
        message(STATUS "Getting submodules")
        execute_process(COMMAND ${GIT_EXECUTABLE} submodule init
                RESULT_VARIABLE GIT_SUBMOD_RESULT OUTPUT_QUIET)
        execute_process(COMMAND ${GIT_EXECUTABLE} submodule update
                RESULT_VARIABLE GIT_SUBMOD_RESULT OUTPUT_QUIET)
        if (NOT GIT_SUBMOD_RESULT EQUAL "0")
            message(FATAL_ERROR "git submodule update failed with ${GIT_SUBMOD_RESULT}")
        endif()
    endif()
endif()



IF (WIN32)
    link_directories(External/win)
ELSE()
    link_directories(External/linux)
ENDIF()

include_directories(src)
include_directories(External/includes)

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
