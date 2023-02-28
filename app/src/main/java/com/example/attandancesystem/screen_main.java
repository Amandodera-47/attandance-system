package com.example.attandancesystem;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class screen_main extends AppCompatActivity {

    ImageButton imgprofile,imgstudinfo,imgattand;
    TextView txtprofile,txtstudinfo,txtattand;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_main);

        imgprofile =findViewById(R.id.scm_profile);
        imgstudinfo =  findViewById(R.id.scm_studinfo);
        imgattand =  findViewById(R.id.scm_attand);

        imgstudinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(screen_main.this,student_info.class);
                startActivity(intent);
            }
        });

        imgattand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent3 = new Intent(screen_main.this,attandance.class);
                startActivity(intent3);
            }
        });

        imgprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(screen_main.this,profile.class);
                startActivity(intent2);
            }
        });
    }
}