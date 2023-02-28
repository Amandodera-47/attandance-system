package com.example.attandancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.database.Cursor;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class attandance extends AppCompatActivity {

    ListView txtnm;
    RadioButton rdp, rda;
    RadioGroup rdg;
    Button submit;
    SQLite sqlite4;
    TextView tvw;
    EditText eText;
    CustomAdapter cdp;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attandance);

        rdg = findViewById(R.id.radioGroup);
        txtnm = findViewById(R.id.student_name_adapter);
        rda = findViewById(R.id.radio_absent);
        rdp = findViewById(R.id.radio_present);
        submit = findViewById(R.id.submit);
        sqlite4 = new SQLite(this);
        tvw=(TextView)findViewById(R.id.textView1);
        eText=(EditText) findViewById(R.id.editText1);
        eText.setInputType(InputType.TYPE_NULL);
        Button btnGet;



        ArrayList<String> arrayList1 = new ArrayList<String>();

        Cursor cursor = sqlite4.readattandData();
        while (cursor.moveToNext()) {
            arrayList1.add(cursor.getString(0));

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this,
                    R.layout.attandance2,
                    R.id.txtnmatnd2,
                    arrayList1);


            cdp = new CustomAdapter(attandance.this,arrayList1);
            txtnm.setAdapter(cdp);

        }


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String date = eText.getText().toString();
                String name = txtnm.getAdapter().toString();
                String attand = "";


                if (date.equals("") && name.equals("") && attand.equals("")) {
                    Toast.makeText(attandance.this, "Data is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    boolean data = sqlite4.insertattandData(date,name,attand);
                    Toast.makeText(attandance.this, data+"", Toast.LENGTH_SHORT).show();
                    if (data == false) {
                        Toast.makeText(attandance.this, "Something is wrong in Database.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(attandance.this, "Record Inserted Successfully.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        eText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                DatePickerDialog picker = new DatePickerDialog(attandance.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                eText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();
            }
        });
        btnGet=(Button)findViewById(R.id.button1);
        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Toast.makeText(attandance.this, "asjdasjkd", Toast.LENGTH_SHORT).show();
                tvw.setText("Selected Date: "+ eText.getText());
            }
        });
    }
}