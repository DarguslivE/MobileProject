package com.mypackage.mobileproject;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.util.TreeSet;

public class GestureRecorderActivity extends AppCompatActivity {

    private GestureLibrary lib;
    private File file;
    private Gesture current;
    private EditText etName;
    private TextView tvStatus, tvInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gesture_recorder);
        setTitle("Запись жестов");

        etName = findViewById(R.id.etName);
        tvStatus = findViewById(R.id.tvStatus);
        tvInfo = findViewById(R.id.tvInfo);
        GestureOverlayView overlay = findViewById(R.id.overlay);

        // файл в папке приложения: Android/data/<пакет>/files/gestures
        file = new File(getExternalFilesDir(null), "gestures");
        lib = GestureLibraries.fromFile(file);
        if (file.exists()) {
            lib.load();
        }

        overlay.addOnGesturePerformedListener((o, gesture) -> {
            current = gesture;
            tvStatus.setText("Жест нарисован. Введите имя и нажмите «Сохранить»");
        });

        findViewById(R.id.btnSave).setOnClickListener(v -> save());
        findViewById(R.id.btnReset).setOnClickListener(v -> {
            current = null;
            overlay.clear(false);
            tvStatus.setText("Нарисуйте жест пальцем (жёлтая линия)");
        });
        findViewById(R.id.btnDelete).setOnClickListener(v -> {
            String name = etName.getText().toString().trim();
            if (name.isEmpty() || !lib.getGestureEntries().contains(name)) {
                Toast.makeText(this, "Такого имени нет", Toast.LENGTH_SHORT).show();
                return;
            }
            lib.removeEntry(name);
            lib.save();
            refresh();
        });
        refresh();
    }

    private void save() {
        String name = etName.getText().toString().trim();
        if (current == null) {
            Toast.makeText(this, "Сначала нарисуйте жест", Toast.LENGTH_SHORT).show();
            return;
        }
        if (name.isEmpty()) {
            Toast.makeText(this, "Введите имя жеста", Toast.LENGTH_SHORT).show();
            return;
        }
        lib.addGesture(name, current);
        if (lib.save()) {
            Toast.makeText(this, "Сохранено: " + name, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Ошибка сохранения", Toast.LENGTH_LONG).show();
        }
        current = null;
        tvStatus.setText("Нарисуйте следующий жест");
        refresh();
    }

    private void refresh() {
        StringBuilder sb = new StringBuilder("Файл: " + file.getAbsolutePath() + "\n\nЖесты (имя: образцов):\n");
        for (String n : new TreeSet<>(lib.getGestureEntries())) {
            sb.append(n).append(": ").append(lib.getGestures(n).size()).append("   ");
        }
        tvInfo.setText(sb.toString());
    }
}