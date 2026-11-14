package com.mypackage.mobileproject;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.GestureOverlayView.OnGesturePerformedListener;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class GesturesActivity extends AppCompatActivity implements OnGesturePerformedListener {

    private GestureLibrary gLib;
    private GestureOverlayView gestures;
    private TextView tvOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestures);
        setTitle("Распознавание жестов");

        tvOut = findViewById(R.id.textView1);

        // загрузка жестов из res/raw/gestures
        gLib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!gLib.load()) {
            Toast.makeText(this, "Файл жестов не загружен. Запишите жесты (B1) и скопируйте файл в res/raw",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        gestures = findViewById(R.id.gestureOverlayView1);
        gestures.addOnGesturePerformedListener(this);
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        ArrayList<Prediction> predictions = gLib.recognize(gesture);
        if (predictions.size() > 0) {
            Prediction prediction = predictions.get(0);
            if (prediction.score > 1.0) {
                if (prediction.name.equals("one")) {
                    tvOut.setText("1");
                } else if (prediction.name.equals("stop")) {
                    tvOut.setText("stop");
                } else if (prediction.name.equals("two")) {
                    tvOut.setText("2");
                } else {
                    tvOut.setText(prediction.name);
                }
            } else {
                tvOut.setText("Жест неизвестен");
            }
        }
    }
}