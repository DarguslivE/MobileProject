package com.mypackage.mobileproject;

import android.graphics.Color;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);
        setTitle("Fruit Salad");

        PieChart chart = findViewById(R.id.pieChart);

        // данные: значение и подпись для каждого сектора
        List<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(25f, "Бананы"));
        entries.add(new PieEntry(25f, "Киви"));
        entries.add(new PieEntry(25f, "Апельсин"));
        entries.add(new PieEntry(25f, "Крем"));

        // набор данных и цвета секторов
        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(Color.YELLOW, Color.GREEN, Color.RED, Color.LTGRAY);
        dataSet.setValueTextSize(16f);
        dataSet.setValueTextColor(Color.BLACK);

        PieData data = new PieData(dataSet);
        data.setValueFormatter(new PercentFormatter());

        // настройки внешнего вида
        chart.setUsePercentValues(true);
        chart.setEntryLabelColor(Color.BLACK);
        chart.setEntryLabelTextSize(14f);
        chart.setCenterText("Фруктовый салат");
        chart.setCenterTextSize(18f);
        chart.getDescription().setEnabled(false);
        chart.getLegend().setTextSize(16f);

        chart.setData(data);
        chart.animateY(1000);   // анимация появления
        chart.invalidate();     // перерисовка
    }
}