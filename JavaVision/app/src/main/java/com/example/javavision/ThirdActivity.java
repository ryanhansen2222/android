package com.example.javavision;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.ImageProxy;

public class ThirdActivity extends AppCompatActivity implements ImageAnalysis.Analyzer {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
    }

    @Override
    public void analyze(@NonNull ImageProxy image) {

    }
}