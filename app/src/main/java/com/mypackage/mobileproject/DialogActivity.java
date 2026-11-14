package com.mypackage.mobileproject;

import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class DialogActivity extends AppCompatActivity {

    private RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);

        relativeLayout = findViewById(R.id.relativelayout);
        findViewById(R.id.background_button).setOnClickListener(v -> showColorDialog());
    }

    private void showColorDialog() {
        final CharSequence[] items = {
                getText(R.string.red),
                getText(R.string.yellow),
                getText(R.string.green)
        };

        new AlertDialog.Builder(this)
                .setTitle(R.string.message)
                .setItems(items, (dialog, item) -> {
                    switch (item) {
                        case 0:
                            relativeLayout.setBackgroundResource(R.color.redColor);
                            Toast.makeText(this, R.string.red, Toast.LENGTH_LONG).show();
                            break;
                        case 1:
                            relativeLayout.setBackgroundResource(R.color.yellowColor);
                            Toast.makeText(this, R.string.yellow, Toast.LENGTH_LONG).show();
                            break;
                        case 2:
                            relativeLayout.setBackgroundResource(R.color.greenColor);
                            Toast.makeText(this, R.string.green, Toast.LENGTH_LONG).show();
                            break;
                    }
                })
                .show();
    }
}