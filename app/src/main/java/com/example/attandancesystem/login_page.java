package com.example.attandancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login_page extends AppCompatActivity {

    EditText fedt1,fedt2;
    Button bf_login,bf_reg;
    SQLite sqlite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        fedt1 = findViewById(R.id.fe_username);
        fedt2 = findViewById(R.id.fe_password);
        bf_login = findViewById(R.id.fb_login);
        bf_reg = findViewById(R.id.fb_reg);
        sqlite = new SQLite(this);



        bf_reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(), registration.class);
                startActivity(intent1);
            }
        });


        bf_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = fedt1.getText().toString();
                String password = fedt2.getText().toString();
                if (username.equals("") && password.equals("")) {
                    Toast.makeText(login_page.this, "Data is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean data = sqlite.readData(username, password);
                    if (data == false) {
                        Toast.makeText(login_page.this, "incorrect username or password.", Toast.LENGTH_SHORT).show();
                    } else {
                        Intent i = new Intent(login_page.this, screen_main.class);
                        startActivity(i);
                    }
                }

            }
        });
    }
}