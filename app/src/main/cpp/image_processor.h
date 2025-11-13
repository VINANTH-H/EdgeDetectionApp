#ifndef IMAGE_PROCESSOR_H
#define IMAGE_PROCESSOR_H

#include <jni.h>
#include <vector>

extern "C" {

// JNI function for processing image data
JNIEXPORT jbyteArray JNICALL
Java_com_example_edgedetection_MainActivity_nativeProcessImage(
        JNIEnv *env,
        jobject thiz,
        jbyteArray input_,
        jint width,
        jint height
);

}

#endif // IMAGE_PROCESSOR_H
