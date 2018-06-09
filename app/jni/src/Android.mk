LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)

LOCAL_MODULE := main

# SDL_PATH := ../SDL
SDL_PATH := /home/many/Downloads/private/sdlsrc/SDL2-2.0.8
FF_PATH = /home/many/Downloads/private/ffmpeg

LOCAL_C_INCLUDES := $(SDL_PATH)/include \
		$(FF_PATH) \
		$(SDL_PATH)/src/core/android/ \
		$(FF_PATH)/fftools

# Add your application source files here...
LOCAL_SRC_FILES := ffplay.c \
		$(FF_PATH)/fftools/cmdutils.c

LOCAL_SHARED_LIBRARIES := SDL2
LOCAL_SHARED_LIBRARIES += libavformat libavcodec libavdevice libavfilter libavutil libswresample libswscale

LOCAL_LDLIBS := -lGLESv1_CM -lGLESv2 -llog 

include $(BUILD_SHARED_LIBRARY)
