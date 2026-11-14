package com.mypackage.mobileproject;

import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

public class GestAllActivity extends AppCompatActivity
        implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private TextView tvOutput;
    private GestureDetectorCompat mDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gest_all);
        setTitle("Все жесты");

        tvOutput = findViewById(R.id.textView1);
        mDetector = new GestureDetectorCompat(this, this);
        mDetector.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mDetector.onTouchEvent(event);
        // обязательно вызываем реализацию родительского класса
        return super.onTouchEvent(event);
    }

    private void show(String name, String details) {
        tvOutput.setText(name + ": " + details);
        Log.d("GEST", name);
    }

    @Override
    public boolean onDown(MotionEvent event) {
        show("onDown", event.toString());
        return false;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        show("onFling", e1 + "\n" + e2 + "\nvelocityX=" + velocityX + " velocityY=" + velocityY);
        return true;
    }

    @Override
    public void onLongPress(MotionEvent event) {
        show("onLongPress", event.toString());
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        show("onScroll", e1 + "\n" + e2);
        return true;
    }

    @Override
    public void onShowPress(MotionEvent event) {
        show("onShowPress", event.toString());
    }

    @Override
    public boolean onSingleTapUp(MotionEvent event) {
        show("onSingleTapUp", event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTap(MotionEvent event) {
        show("onDoubleTap", event.toString());
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent event) {
        show("onDoubleTapEvent", event.toString());
        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent event) {
        show("onSingleTapConfirmed", event.toString());
        return true;
    }
}