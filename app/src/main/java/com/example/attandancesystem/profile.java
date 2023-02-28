package com.example.attandancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class profile extends AppCompatActivity {

    TextView edt_name,edt_stream,edt_semester,edt_mobile,edt_mailid,edt_address;
    Spinner spn_enroll;
    SQLite sqlite3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        spn_enroll = findViewById(R.id.studinfo_enroll);
        edt_name = findViewById(R.id.studinfo_name);
        edt_stream = findViewById(R.id.studinfo_stream);
        edt_semester = findViewById(R.id.studinfo_semester);
        edt_mobile = findViewById(R.id.studinfo_mobile);
        edt_mailid = findViewById(R.id.studinfo_gmail);
        edt_address = findViewById(R.id.studinfo_adderss);
        sqlite3 = new SQLite(this);


        ArrayList<String> arrayList1 = new ArrayList<String>();

        Cursor cursor = sqlite3.enrolstud();


        while (cursor.moveToNext()) {
            arrayList1.add(cursor.getString(0));
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_spinner_dropdown_item,
                    arrayList1);
            spn_enroll.setAdapter(adapter);
            spn_enroll.setVisibility(View.VISIBLE);

        }

        spn_enroll.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Cursor cursor2 = sqlite3.namestud(item);
                cursor2.moveToFirst();

                edt_name.setText(cursor2.getString(0));
                edt_stream.setText(cursor2.getString(1));
                edt_semester.setText(cursor2.getString(2));
                edt_mobile.setText(cursor2.getString(3));
                edt_mailid.setText(cursor2.getString(4));
                edt_address.setText(cursor2.getString(5));

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


    }
}