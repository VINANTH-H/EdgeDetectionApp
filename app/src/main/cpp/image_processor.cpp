#include "image_processor.h"
#include <jni.h>
#include <vector>
#include <cmath>

extern "C"
JNIEXPORT jbyteArray JNICALL
Java_com_example_edgedetection_MainActivity_nativeProcessImage(
        JNIEnv *env,
        jobject thiz,
        jbyteArray input_,
        jint width,
        jint height
) {

    jbyte* input = env->GetByteArrayElements(input_, nullptr);
    int size = width * height;

    std::vector<unsigned char> output(size);

    // Simple Sobel-like edge detection
    for (int y = 1; y < height - 1; y++) {
        for (int x = 1; x < width - 1; x++) {

            int i = y * width + x;

            int gx =
                    -input[i - width - 1] - 2 * input[i - 1] - input[i + width - 1] +
                    input[i - width + 1] + 2 * input[i + 1] + input[i + width + 1];

            int gy =
                    -input[i - width - 1] - 2 * input[i - width] - input[i - width + 1] +
                    input[i + width - 1] + 2 * input[i + width] + input[i + width + 1];

            int mag = sqrt(gx * gx + gy * gy);
            if (mag > 255) mag = 255;

            output[i] = (unsigned char)mag;
        }
    }

    jbyteArray result = env->NewByteArray(size);
    env->SetByteArrayRegion(result, 0, size, reinterpret_cast<jbyte*>(output.data()));

    env->ReleaseByteArrayElements(input_, input, 0);

    return result;
}
