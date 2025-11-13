package com.example.edgedetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.example.edgedetection.ui.CameraGLSurfaceView;

public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("edgedetection");   // Load native C++ library
    }

    private CameraGLSurfaceView glSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        glSurfaceView = new CameraGLSurfaceView(this);

        FrameLayout layout = new FrameLayout(this);
        layout.addView(glSurfaceView);

        setContentView(layout);
    }

    // Native function implemented in C++
    public native byte[] nativeProcessImage(byte[] input, int width, int height);

    @Override
    protected void onResume() {
        super.onResume();
        glSurfaceView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        glSurfaceView.onPause();
    }
}
