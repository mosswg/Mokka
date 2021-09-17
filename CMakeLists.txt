cmake_minimum_required(VERSION 3.10)
project(Mokka)

set(CMAKE_CXX_STANDARD 14)



message(STATUS "Starting CMake")

# Get Submodules
find_package(Git QUIET)
# If the user is using git fetch the submodules
if(GIT_FOUND AND EXISTS "${PROJECT_SOURCE_DIR}/.git")
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

# Check if glfw submodules exists
if(EXISTS "${PROJECT_SOURCE_DIR}/Mokka/Natives/External/Modules/glfw")
    if(NOT EXISTS "${PROJECT_SOURCE_DIR}/Mokka/Natives/External/Modules/glfw/build")
        message(STATUS "Generating GLFW files")
        execute_process(COMMAND cmake -DBUILD_SHARED_LIBS=ON -DGLFW_BUILD_DOCS=OFF -DGLFW_BUILD_TESTS=OFF -DGLFW_BUILD_EXAMPLES=OFF -S ${PROJECT_SOURCE_DIR}/Mokka/Natives/External/Modules/glfw -B ${PROJECT_SOURCE_DIR}/Mokka/Natives/External/Modules/glfw/build)
        message(STATUS "Building GLFW")
        execute_process(COMMAND make -C ${PROJECT_SOURCE_DIR}/Mokka/Natives/External/Modules/glfw/build OUTPUT_QUIET)
        message(STATUS "GLFW Has Been Successfully Built")
    endif()
else()
    message(FATAL_ERROR "Required Submodule GLFW was not Found.\nDownload Manually and Place into External/Modules Folder or Run CMake with GIT_SUBMODULE set to ON")
endif()

# Check if glew submodules exists
if(EXISTS "${PROJECT_SOURCE_DIR}/Mokka/Natives/External/Modules/glew")
    if(NOT EXISTS "${PROJECT_SOURCE_DIR}/Mokka/Natives/External/Modules/glew/lib")
        message(STATUS "Generating GLEW files")
        execute_process(COMMAND make -C ${PROJECT_SOURCE_DIR}/Mokka/Natives/External/Modules/glew/auto OUTPUT_QUIET)
        message(STATUS "Building GLEW")
        execute_process(COMMAND make -C ${PROJECT_SOURCE_DIR}/Mokka/Natives/External/Modules/glew/ glew.lib.shared)
        message(STATUS "GLEW Has Been Successfully Built")
    endif()
else()
    message(FATAL_ERROR "Required Submodule GLFW was not Found.\nDownload Manually and Place into External/Modules Folder or Run CMake with GIT_SUBMODULE set to ON")
endif()




include_directories(Mokka/Natives/src)
include_directories(Mokka/Natives/External/Modules/glfw/include)
include_directories(Mokka/Natives/External/Modules/glew/include)
include_directories(Mokka/Natives/External/includes/)

if (WIN32)
    include_directories(Mokka/Natives/External/includes/Java/include/win32)
else()
    include_directories(Mokka/Natives/External/includes/Java/include/linux)
endif()


link_directories(Mokka/Natives/External/Modules/glfw/build/src)
link_directories(Mokka/Natives/External/Modules/glew/lib)


link_libraries(glfw)
link_libraries(GLEW)
link_libraries(GLU)
link_libraries(GL)
link_libraries(m)
link_libraries(X11)


add_library(Mokka SHARED
        Mokka/Natives/src/mka_IndexBuffer.cpp
        Mokka/Natives/src/mka_IndexBuffer.h
        Mokka/Natives/src/mka_Maths.cpp
        Mokka/Natives/src/mka_Maths.h
        Mokka/Natives/src/mka_Renderer.cpp
        Mokka/Natives/src/mka_Renderer.h
        Mokka/Natives/src/mka_Shader.cpp
        Mokka/Natives/src/mka_Shader.h
        Mokka/Natives/src/mka_Shape.cpp
        Mokka/Natives/src/mka_Shape.h
        Mokka/Natives/src/mka_Texture.cpp
        Mokka/Natives/src/mka_Texture.h
        Mokka/Natives/src/mka_VertexArray.cpp
        Mokka/Natives/src/mka_VertexArray.h
        Mokka/Natives/src/mka_VertexBuffer.cpp
        Mokka/Natives/src/mka_VertexBuffer.h
        Mokka/Natives/src/mka_VertexBufferLayout.cpp
        Mokka/Natives/src/mka_VertexBufferLayout.h
        Mokka/Natives/src/mka_Window.cpp
        Mokka/Natives/src/mka_Window.h
        Mokka/Natives/src/Mokka.cpp
        Mokka/Natives/src/Mokka.h)
