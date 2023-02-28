package com.example.attandancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Matrix;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class student_info extends AppCompatActivity {

    Button btn_add,btn_edit,btn_del;
    EditText edt_enroll,edt_name,edt_stream,edt_semester,edt_mobile,edt_mailid,edt_address;
    SQLite sqlite2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_info);



        btn_add = findViewById(R.id.studinfo_insert);
        btn_del = findViewById(R.id.studinfo_delete);
        btn_edit = findViewById(R.id.studinfo_update);
        edt_enroll = findViewById(R.id.studinfo_enroll);
        edt_name = findViewById(R.id.studinfo_name);
        edt_stream = findViewById(R.id.studinfo_stream);
        edt_semester = findViewById(R.id.studinfo_semester);
        edt_mobile = findViewById(R.id.studinfo_mobile);
        edt_mailid = findViewById(R.id.studinfo_gmail);
        edt_address = findViewById(R.id.studinfo_adderss);
        sqlite2 = new SQLite(this);




        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String enrollment_no = edt_enroll.getText().toString();
                String name = edt_name.getText().toString();
                String stream = edt_stream.getText().toString();
                String semester = edt_semester.getText().toString();
                String mobile_no = edt_mobile.getText().toString();
                String mail_id = edt_mailid.getText().toString();
                String address = edt_address.getText().toString();
                if (enrollment_no.equals("") && name.equals("") && stream.equals("") && semester.equals("") && mobile_no.equals("") && mail_id.equals("") && address.equals("")) {
                    Toast.makeText(student_info.this, "Data is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    boolean data = sqlite2.studinsertData(enrollment_no,name,stream,semester, mobile_no,mail_id, address);
//                    Log.d("DATA", enrollment_no + ":" + name + ":" + stream + ":" + phone + ":" + semester + ":" + division + ":" + address);
                    Toast.makeText(student_info.this, data+"", Toast.LENGTH_SHORT).show();
                    if (data == false) {
                        Toast.makeText(student_info.this, "Something is wrong in Database.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(student_info.this, "Record Inserted Successfully.", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        btn_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String id = edt_enroll.getText().toString();
                if (id.equals("")) {
                    Toast.makeText(student_info.this, "Data is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean data = sqlite2.delData(id);
                    if (data == false) {
                        Toast.makeText(student_info.this, "Something is wrong in Database.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(student_info.this, "Record Deleted Successfully.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        btn_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String enrollment_no = edt_enroll.getText().toString();
                String name = edt_name.getText().toString();
                String stream = edt_stream.getText().toString();
                String semester = edt_semester.getText().toString();
                String mobile_no = edt_mobile.getText().toString();
                String mail_id = edt_mailid.getText().toString();
                String address = edt_address.getText().toString();

                if (enrollment_no.equals("") && name.equals("") && stream.equals("") && semester.equals("") && mobile_no.equals("") && mail_id.equals("") && address.equals("")) {
                    Toast.makeText(student_info.this, "Data is Empty", Toast.LENGTH_SHORT).show();
                } else {
                    Boolean data = sqlite2.updateData(enrollment_no,name,stream,semester, mobile_no,mail_id, address);
                    if (data == false) {
                        Toast.makeText(student_info.this, "Something is wrong in Database.", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(student_info.this, "Record Updated Successfully.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}