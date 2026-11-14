package com.mypackage.mobileproject;

import android.gesture.Gesture;
import android.gesture.GestureLibraries;
import android.gesture.GestureLibrary;
import android.gesture.GestureOverlayView;
import android.gesture.Prediction;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class GuessActivity extends AppCompatActivity
        implements GestureOverlayView.OnGesturePerformedListener {

    private static final double MIN_SCORE = 2.0;   // порог уверенности распознавания
    private static final int MAX_DIGITS = 3;       // не больше трёх цифр в числе

    private GestureLibrary lib;
    private final Random rnd = new Random();
    private final StringBuilder input = new StringBuilder();
    private int secret;
    private int attempts;
    private boolean finished;

    private TextView tvHint, tvInput, tvAttempts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess);
        setTitle("Угадайка");

        tvHint = findViewById(R.id.tvHint);
        tvInput = findViewById(R.id.tvInput);
        tvAttempts = findViewById(R.id.tvAttempts);

        lib = GestureLibraries.fromRawResource(this, R.raw.gestures);
        if (!lib.load()) {
            Toast.makeText(this, "Файл жестов не загружен. Сначала запишите жесты (B1) и скопируйте файл в res/raw",
                    Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        GestureOverlayView overlay = findViewById(R.id.gestureOverlay);
        overlay.addOnGesturePerformedListener(this);
        findViewById(R.id.btnNew).setOnClickListener(v -> newGame());
        newGame();
    }

    private void newGame() {
        secret = rnd.nextInt(100) + 1;   // число от 1 до 100
        attempts = 0;
        finished = false;
        input.setLength(0);
        tvHint.setText("Я загадал число от 1 до 100. Рисуйте цифры, жест S завершает ввод.");
        update();
    }

    private void update() {
        tvInput.setText(input.length() == 0 ? "—" : input.toString());
        tvAttempts.setText("Попыток: " + attempts);
    }

    @Override
    public void onGesturePerformed(GestureOverlayView overlay, Gesture gesture) {
        if (finished) {
            tvHint.setText("Игра окончена. Нажмите «Новая игра».");
            return;
        }
        ArrayList<Prediction> predictions = lib.recognize(gesture);
        if (predictions.isEmpty() || predictions.get(0).score < MIN_SCORE) {
            tvHint.setText("Жест не распознан, повторите");
            return;
        }
        String name = predictions.get(0).name;

        if (name.equals("S")) {
            checkNumber();
        } else if (name.length() == 1 && Character.isDigit(name.charAt(0))) {
            if (input.length() < MAX_DIGITS) {
                input.append(name);
                tvHint.setText("Цифра " + name + " принята");
            } else {
                tvHint.setText("Максимум " + MAX_DIGITS + " цифры. Завершите ввод жестом S");
            }
            update();
        } else {
            tvHint.setText("Неизвестный жест: " + name);
        }
    }

    private void checkNumber() {
        if (input.length() == 0) {
            tvHint.setText("Сначала введите число жестами");
            return;
        }
        int guess = Integer.parseInt(input.toString());
        attempts++;
        if (guess == secret) {
            finished = true;
            tvHint.setText("Угадали! Это число " + secret + ". Попыток: " + attempts);
        } else if (guess < secret) {
            tvHint.setText(guess + ": загаданное число БОЛЬШЕ");
        } else {
            tvHint.setText(guess + ": загаданное число МЕНЬШЕ");
        }
        input.setLength(0);
        update();
    }
}