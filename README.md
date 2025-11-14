

--------------------------------------------------
https://github.com/VINANTH-H/EdgeDetectionApp (Access This Link for teh Project)
--------------------------------------------------



Real-Time Edge Detection App (Android + Web)

This project provides real-time camera-based edge detection using OpenGL ES, OpenCV, and JNI on Android, along with a companion web interface built using TypeScript.
It demonstrates GPU-accelerated image processing, native C++ integration, and cross-platform architecture.

🚀 Features Implemented
✅ Android App

1. Real-time camera feed using Camera2 API

2. OpenGL ES–based rendering pipeline

3. Native C++ edge detection using OpenCV

4 .Zero-copy frame transfer using SurfaceTexture

5 .JNI bridge to call C++ functions from Kotlin/Java

6 .FPS-optimized processing

7. Custom shaders for edge visualization

🌐 Web App

1.TypeScript + WebGL implementation of edge detection

2.Live webcam capture in browser

3.GLSL shader-based Sobel edge filter

4.Clean UI with result preview

5.Same shader logic used on both platforms for consistency

6.Setup Instructions

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

1.Camera2 writes frames into a SurfaceTexture

2.SurfaceTexture updates an external OES texture

3.Vertex & fragment shaders draw the camera texture

4.JNI passes the frame to C++ if CPU processing is needed

5.C++ uses OpenCV to perform edge detection (Sobel / Canny)

6.The processed texture is drawn back on the GLSurfaceView

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
