package com.mypackage.mobileproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import androidx.appcompat.app.AppCompatActivity;

public class RatingActivity extends AppCompatActivity {

    private final int[] images = {R.drawable.puppy, R.drawable.canari, R.drawable.curili};
    private final String[] sites = {
            "https://unsplash.com",
            "https://pexels.com",
            "https://pixabay.com"
    };
    private int current = 0;

    private ImageView imageView;
    private TextView tvSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.ratingRoot), (v, insets) -> {
            Insets bars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(bars.left + 16, bars.top + 16, bars.right + 16, bars.bottom + 16);
            return insets;
        });

        imageView = findViewById(R.id.imageView);
        tvSite = findViewById(R.id.tvSite);
        EditText edit = findViewById(R.id.edit_message);

        findViewById(R.id.button1).setOnClickListener(v ->
                Toast.makeText(this, "Поиск: " + edit.getText(), Toast.LENGTH_SHORT).show());

        findViewById(R.id.btnLike).setOnClickListener(v -> next("like"));
        findViewById(R.id.btnDislike).setOnClickListener(v -> next("dislike"));

        findViewById(R.id.btnOpenSite).setOnClickListener(v ->
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(sites[current]))));

        show();
    }

    private void next(String rating) {
        Toast.makeText(this, "Оценка: " + rating, Toast.LENGTH_SHORT).show();
        current = (current + 1) % images.length;
        show();
    }

    private void show() {
        imageView.setImageResource(images[current]);
        tvSite.setText(getString(R.string.site_prefix) + sites[current]);
    }
}