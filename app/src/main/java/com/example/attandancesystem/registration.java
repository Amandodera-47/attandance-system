package com.example.attandancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class registration extends AppCompatActivity {
    Button btnreg;
    EditText edunm,edpwd;
    SQLite sqlite1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        sqlite1 = new SQLite(this);
        btnreg = findViewById(R.id.fcrb_reg);
        edunm = findViewById(R.id.fcre_unm);
        edpwd = findViewById(R.id.fcre_pwd);

        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edunm.getText().toString();
                String password = edpwd.getText().toString();
                if (username.equals("") && password.equals("")) {
                    Toast.makeText(registration.this, "Data is Empty", Toast.LENGTH_SHORT).show();
                }
                else {
                    sqlite1.insertData(username,password);
                    Toast.makeText(registration.this, "Record Inserted Successfully.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(registration.this,login_page.class);
                    startActivity(i);
                }

            }
        });
    }
}