PROJECT_ROOT = $(dir $(abspath $(lastword $(MAKEFILE_LIST))))


FILES = $(wildcard *.cpp)
OFILES = $(patsubst %.cpp, %.o, $(FILES))

CXX = g++
CC = gcc
PRE =   rm -r out;   mkdir out;   if [ ! -d ../linux]; then mkdir -p ../linux;  fi


WARN = -Wall -Wextra -Wfloat-equal -Wundef -Wcast-align -Wwrite-strings -Wlogical-op -Wmissing-declarations -Wredundant-decls -Wshadow -Woverloaded-virtual

IFLAGS = -I"vendor/Java/include"

ifeq ($(OS), Windows_NT)
	IFLAGS += -I"vendor/Java/include/win32" 
	OUT = ../win/$@.dll
	CCFLAGS += -D WIN32 -shared
else
    UNAME_S := $(shell uname -s)
	ifeq ($(UNAME_S),Linux)
		IFLAGS += -I"vendor/Java/include/linux"
		OUT = ../linux/lib$@.so 
        CCFLAGS += -D LINUX -shared
    endif
    ifeq ($(UNAME_S),Darwin)
		IFLAGS += -I"vendor/Java/include/osx"
		OUT = ../linux/lib$@.dylib
        CCFLAGS += -D OSX -dynamiclib
    endif
endif
LFLAGS += -L ../linux -lglfw -lGLEW -ljvm -lGLU -lGL -lm -lX11 -lpthread -lXi -lXrandr -ldl
CFLAGS = -fPIC

ifeq ($(BUILD_MODE),debug)
	CFLAGS += -g 
else ifeq ($(BUILD_MODE),run)
	CFLAGS += -O2
else ifeq ($(BUILD_MODE),linuxtools)
	CFLAGS += -g -pg -fprofile-arcs -ftest-coverage
	LDFLAGS += -pg -fprofile-arcs -ftest-coverage
else
    CFLAGS += -g
endif

all:	Mokka
	

Mokka:	
	$(CXX) $(CFLAGS) $(WARN) $(IFLAGS) $(FILES) $(CCFLAGS) -o $(OUT) $(LFLAGS)

 %.o:	%.cpp
	$(CXX) $(WARN) $(CFLAGS) $(CPPFLAGS) $(IFLAGS) -c $< -o out/$@ 

 %.o:	%.c
	$(CC) -c $(WARN) $(CFLAGS) $(CPPFLAGS) $(IFLAGS) -o out/$@ $<

clean:
	rm -fr out/Mokka $(OBJS)
