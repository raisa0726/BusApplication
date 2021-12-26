package com.example.busapplication.ui.ride;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.busapplication.R;

public class RideMoveActivity extends AppCompatActivity {
    String[] qr1;
    int adultNum;
    int childNum;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_move);
        Intent args = getIntent();
        adultNum = args.getIntExtra("adultNum",0);
        childNum = args.getIntExtra("childNum",0);
        qr1 = args.getStringArrayExtra("QR1");
        TextView text = findViewById(R.id.text_ride_move);
        text.setText(qr1[1] + "に乗車しています。\n" + "大人" + adultNum + "人\n" + "子供" + childNum + "人");
    }
    public void RideMove(View v){
        Intent intent = new Intent(this, RideActivity.class);
        intent.putExtra("QR1",qr1);
        intent.putExtra("adultNum",adultNum);
        intent.putExtra("childNum",childNum);
        startActivity(intent);
    }
}