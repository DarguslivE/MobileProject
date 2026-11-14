package com.mypackage.mobileproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

public class IslandsActivity extends ListActivity {

    private final String[] islands = {"Канары", "Курилы", "Мальдивы", "Филиппины"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.vacation_title);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, islands);
        setListAdapter(adapter);

        // чтобы список не уезжал под строку состояния
        getListView().setFitsSystemWindows(true);

        getListView().setOnItemClickListener((parent, view, position, id) -> {
            Class<?> target;
            switch (position) {
                case 0: target = Canari.class; break;
                case 1: target = Curili.class; break;
                case 2: target = Maldivi.class; break;
                default: target = Philippini.class; break;
            }
            startActivity(new Intent(IslandsActivity.this, target));
            Toast.makeText(getApplicationContext(),
                    "Вы выбрали " + parent.getItemAtPosition(position),
                    Toast.LENGTH_SHORT).show();
        });
    }
}