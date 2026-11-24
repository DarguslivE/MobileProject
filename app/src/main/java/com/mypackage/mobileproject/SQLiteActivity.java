package com.mypackage.mobileproject;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SQLiteActivity extends AppCompatActivity {
    MyOpenHelper myHelper = null;
    EditText field1, field2;
    TextView result;
    SQLiteDatabase DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        setTitle("Lab7_1-SQLite");

        myHelper = new MyOpenHelper(this, "myDB", null, 1);
        field1 = findViewById(R.id.field1);
        field2 = findViewById(R.id.field2);
        result = findViewById(R.id.dbResult);
    }

    // кнопка «Ввод данных»
    public void insertIntoDatabase(View v) {
        String s1 = field1.getText().toString();
        String s2 = field2.getText().toString();
        if (!s1.equals("") && !s2.equals("")) {
            Log.d("myLogs", "INSERT INTO DB (" + s1 + ", " + s2 + ")");
            DB = myHelper.getWritableDatabase();
            ContentValues CV = new ContentValues();
            CV.put(myHelper.FIELD_NAME_1, s1);
            CV.put(myHelper.FIELD_NAME_2, s2);
            DB.insert(myHelper.TABLE_NAME, null, CV);
            DB.close();
            field1.setText("");
            field2.setText("");
        }
    }

    // кнопка «Вывод данных»
    public void readDatabase(View v) {
        result.setText("");
        Log.d("myLogs", "READ FROM DB");
        DB = myHelper.getReadableDatabase();
        String[] columns = {"_id", myHelper.FIELD_NAME_1, myHelper.FIELD_NAME_2};
        Cursor cursor = DB.query(myHelper.TABLE_NAME, columns,
                null, null, null, null, "_id");
        StringBuilder sb = new StringBuilder();
        if (cursor.moveToFirst()) {
            do {
                sb.append(cursor.getString(0)).append(") ")
                        .append(cursor.getString(1)).append(", ")
                        .append(cursor.getString(2)).append("\n");
            } while (cursor.moveToNext());
        }
        cursor.close();
        DB.close();
        result.setText(sb.toString());
    }

    // кнопка «Delete»
    public void deleteDatabase(View v) {
        Log.d("myLogs", "Delete Database");
        DB = myHelper.getWritableDatabase();
        DB.delete(myHelper.TABLE_NAME, null, null);
        DB.close();
        result.setText("");
    }
}