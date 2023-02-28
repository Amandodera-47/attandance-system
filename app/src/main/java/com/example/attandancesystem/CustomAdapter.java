package com.example.attandancesystem;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.constraintlayout.helper.widget.Layer;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String attand = "";
    ArrayList<String> arrayList1;
    public CustomAdapter(Context context, ArrayList<String> arrayList1) {
        this.context = context;
        this.arrayList1 = arrayList1;
    }

    @Override
    public int getCount() {
        return arrayList1.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.attandance2,null);


        TextView tv = new TextView(context);
        tv = convertView.findViewById(R.id.txtnmatnd2);
        RadioGroup rdg = new RadioGroup(context);
        rdg = convertView.findViewById(R.id.radioGroup);

        rdg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.radio_absent){
                    attand = "absent";
                }
                else{
                    attand = "present";
                }
            }
        });

        tv.setText(arrayList1.get(position));



        return convertView;
    }
}
