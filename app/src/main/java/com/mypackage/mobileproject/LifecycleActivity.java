package com.mypackage.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LifecycleActivity extends AppCompatActivity {

    private static final String TAG = "LIFECYCLE";
    private TextView tvLog;
    private final StringBuilder log = new StringBuilder();
    private int counter = 0;

    // показывает Toast, пишет в Logcat и в журнал на экране
    private void note(String name) {
        counter++;
        Log.d(TAG, name);
        Toast.makeText(this, name + "()", Toast.LENGTH_SHORT).show();
        log.append(counter).append(". ").append(name).append("\n");
        if (tvLog != null) {
            tvLog.setText(log.toString());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lifecycle);
        setTitle("Состояния Activity");

        tvLog = findViewById(R.id.tvLog);
        if (savedInstanceState != null) {
            // журнал пережил пересоздание Activity (например, поворот экрана)
            log.append(savedInstanceState.getString("log", ""));
            counter = savedInstanceState.getInt("counter", 0);
        }

        findViewById(R.id.btnOther).setOnClickListener(v ->
                startActivity(new Intent(this, HelloActivity.class)));
        findViewById(R.id.btnDialog).setOnClickListener(v ->
                startActivity(new Intent(this, HelloDialogActivity.class)));

        note("onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        note("onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        note("onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();
        note("onResume");
    }

    @Override
    protected void onPause() {
        note("onPause");
        super.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        note("onSaveInstanceState");
        outState.putString("log", log.toString());
        outState.putInt("counter", counter);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onStop() {
        note("onStop");
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        note("onRestart");
    }

    @Override
    protected void onDestroy() {
        note("onDestroy");
        super.onDestroy();
    }
}