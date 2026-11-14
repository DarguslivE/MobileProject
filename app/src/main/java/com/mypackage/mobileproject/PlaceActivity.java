package com.mypackage.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class PlaceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place);

        int index = getIntent().getIntExtra("index", 0);
        String name = MyAppActivity.PLACES[index];

        setTitle(name);
        ((TextView) findViewById(R.id.tvName)).setText(name);
        ((TextView) findViewById(R.id.tvInfo)).setText(MyAppActivity.INFO[index]);
        ((ImageView) findViewById(R.id.ivPlace)).setImageResource(MyAppActivity.PICS[index]);

        findViewById(R.id.btnActions).setOnClickListener(v -> showActions(name));
    }

    private void showActions(String name) {
        final CharSequence[] items = {
                getText(R.string.act_route),
                getText(R.string.act_fav),
                getText(R.string.act_photos)
        };
        new AlertDialog.Builder(this)
                .setTitle(name)
                .setItems(items, (dialog, which) -> {
                    if (which == 0) {
                        Toast.makeText(this, "Маршрут до: " + name, Toast.LENGTH_SHORT).show();
                    } else if (which == 1) {
                        Toast.makeText(this, name + " добавлено в избранное", Toast.LENGTH_SHORT).show();
                    } else {
                        startActivity(new Intent(this, SwipeActivity.class));
                    }
                })
                .show();
    }
}