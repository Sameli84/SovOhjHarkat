package com.example.lamppu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private boolean flashOn = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void flashOnOff(View view) {
        CameraManager cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            for ( String id : cameraManager.getCameraIdList()) {
                CameraCharacteristics cameraCharacteristics = cameraManager.getCameraCharacteristics( id );
                if( cameraCharacteristics.get( CameraCharacteristics.FLASH_INFO_AVAILABLE)) {
                    flashOn = !flashOn;
                    cameraManager.setTorchMode(id, flashOn);
                }
            }
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }
    }
}


































