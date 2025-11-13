package com.example.edgedetection.ui;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.opengl.GLSurfaceView;

import com.example.edgedetection.camera.CameraHelper;
import com.example.edgedetection.gl.SurfaceRenderer;

public class CameraGLSurfaceView extends GLSurfaceView {

    private final SurfaceRenderer renderer;
    private final CameraHelper cameraHelper;

    public CameraGLSurfaceView(Context context) {
        super(context);

        // OpenGL ES 2.0
        setEGLContextClientVersion(2);

        renderer = new SurfaceRenderer();
        setRenderer(renderer);

        cameraHelper = new CameraHelper(context);
        cameraHelper.startBackgroundThread();

        // Render only when needed
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);

        initSurfaceListener();
    }

    private void initSurfaceListener() {
        getHolder().addCallback(new SurfaceHolderCallback());
    }

    private class SurfaceHolderCallback implements android.view.SurfaceHolder.Callback {
        @Override
        public void surfaceCreated(android.view.SurfaceHolder holder) {
            SurfaceTexture texture = new SurfaceTexture(10);
            renderer.setCameraTexture(texture);

            cameraHelper.openCamera(texture, getWidth(), getHeight());
        }

        @Override
        public void surfaceChanged(android.view.SurfaceHolder holder, int format, int width, int height) {}

        @Override
        public void surfaceDestroyed(android.view.SurfaceHolder holder) {
            cameraHelper.closeCamera();
            cameraHelper.stopBackgroundThread();
        }
    }
}
