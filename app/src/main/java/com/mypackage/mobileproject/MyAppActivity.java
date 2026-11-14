package com.mypackage.mobileproject;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

public class MyAppActivity extends ListActivity {

    static final String[] PLACES = {"Центральный парк", "Набережная", "Музей истории"};
    static final String[] INFO = {
            "Большой парк в центре города, подходит для прогулок.",
            "Красивое место для вечерней прогулки у воды.",
            "Экспозиции о прошлом города и региона."
    };
    static final int[] PICS = {R.drawable.park, R.drawable.embankment, R.drawable.museum};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(R.string.guide_title);
        getListView().setFitsSystemWindows(true);
        setListAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, PLACES));

        getListView().setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(this, PlaceActivity.class);
            intent.putExtra("index", position);
            startActivity(intent);
        });
    }
}