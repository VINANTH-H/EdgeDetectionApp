Real-Time Edge Detection App (Android + Web)

This project provides real-time camera-based edge detection using OpenGL ES, OpenCV, and JNI on Android, along with a companion web interface built using TypeScript.
It demonstrates GPU-accelerated image processing, native C++ integration, and cross-platform architecture.

🚀 Features Implemented
✅ Android App

Real-time camera feed using Camera2 API

OpenGL ES–based rendering pipeline

Native C++ edge detection using OpenCV

Zero-copy frame transfer using SurfaceTexture

JNI bridge to call C++ functions from Kotlin/Java

FPS-optimized processing

Custom shaders for edge visualization

🌐 Web App

TypeScript + WebGL implementation of edge detection

Live webcam capture in browser

GLSL shader-based Sobel edge filter

Clean UI with result preview

Same shader logic used on both platforms for consistency

Setup Instructions

🟩 Android Setup

1. Install NDK

Android Studio → Settings → SDK Manager → SDK Tools →
✔️ NDK (Side by side)

✔️ CMake

2. Add OpenCV

Download OpenCV Android SDK

https://opencv.org/releases/

OpenCV-android-sdk/sdk/native/jni

Place it inside your project:

app/src/main/cpp/opencv/

pdate CMakeLists.txt:

include_directories(src/main/cpp/opencv/include)

add_library(opencv SHARED IMPORTED)

set_target_properties(opencv PROPERTIES IMPORTED_LOCATION

    ${CMAKE_SOURCE_DIR}/src/main/cpp/opencv/lib/${ANDROID_ABI}/libopencv_java4.so)
target_link_libraries(native-lib opencv)


3. Build & Run

Android Studio → Run → Select Device → Build

🌐 Web (TypeScript) Setup

1. Install dependencies
   
npm install

2. Start dev server

npm run dev

3. Open browser
   
http://localhost:5173

Architecture Overview
📌 Frame Flow (Android)
Camera2 → SurfaceTexture → OpenGL Shader →
Texture → JNI → C++ (OpenCV) → Processed Frame → GL Renderer

Step-by-step

Camera2 writes frames into a SurfaceTexture

SurfaceTexture updates an external OES texture

Vertex & fragment shaders draw the camera texture

JNI passes the frame to C++ if CPU processing is needed

C++ uses OpenCV to perform edge detection (Sobel / Canny)

The processed texture is drawn back on the GLSurfaceView

Your shader fix (example):

gl_Position = vec4(aPos.y, -aPos.x, 0.0, 1.0);


This is applied in the vertex shader inside the Android OpenGL pipeline (e.g., camera_vertex.glsl) when orientation flips.

📌 JNI Architecture

Kotlin/Java ↔ JNI Layer ↔ C++ OpenCV Core


Java calls nativeProcessFrame()

JNI converts jlong → cv::Mat*

C++ processes image using OpenCV

Results are returned via cv::Mat or GPU texture handle

Example JNI definition:

extern "C"
JNIEXPORT void JNICALL
Java_com_example_edgedetection_NativeLib_processFrame(
        JNIEnv* env, jobject thiz, jlong matAddr) {
    cv::Mat& frame = *(cv::Mat*) matAddr;
    cv::Canny(frame, frame, 100, 200);
}

🧱 Tech Stack

Android

Kotlin / Java

C++ (JNI + OpenCV)

OpenGL ES 2.0

Camera2 API

Web

TypeScript

WebGL

Vite dev server
