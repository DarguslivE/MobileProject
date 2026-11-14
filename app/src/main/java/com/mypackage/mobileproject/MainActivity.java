package com.mypackage.mobileproject;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left + 24, bars.top + 24, bars.right + 24, bars.bottom + 24);
            return insets;
        });

        findViewById(R.id.btnRating).setOnClickListener(v ->
                startActivity(new Intent(this, RatingActivity.class)));
        findViewById(R.id.btnIslands).setOnClickListener(v ->
                startActivity(new Intent(this, IslandsActivity.class)));
        findViewById(R.id.btnDialog).setOnClickListener(v ->
                startActivity(new Intent(this, DialogActivity.class)));
        findViewById(R.id.btnSwipe).setOnClickListener(v ->
                startActivity(new Intent(this, SwipeActivity.class)));
        findViewById(R.id.btnMyApp).setOnClickListener(v ->
                startActivity(new Intent(this, MyAppActivity.class)));
        findViewById(R.id.btnGestAll).setOnClickListener(v ->
                startActivity(new Intent(this, GestAllActivity.class)));
        findViewById(R.id.btnGestSubset).setOnClickListener(v ->
                startActivity(new Intent(this, GestSubsetActivity.class)));
        findViewById(R.id.btnRecorder).setOnClickListener(v ->
                startActivity(new Intent(this, GestureRecorderActivity.class)));
        findViewById(R.id.btnGestures).setOnClickListener(v ->
                startActivity(new Intent(this, GesturesActivity.class)));
        findViewById(R.id.btnGuess).setOnClickListener(v ->
                startActivity(new Intent(this, GuessActivity.class)));
        findViewById(R.id.btnHello).setOnClickListener(v ->
                startActivity(new Intent(this, HelloActivity.class)));
        findViewById(R.id.btnLifecycle).setOnClickListener(v ->
                startActivity(new Intent(this, LifecycleActivity.class)));
        findViewById(R.id.btnGeolocation).setOnClickListener(v ->
                startActivity(new Intent(this, GeolocationActivity.class)));
        findViewById(R.id.btnPie).setOnClickListener(v ->
                startActivity(new Intent(this, PieChartActivity.class)));
    }
}