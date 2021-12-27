package com.example.busapplication.ui.ride;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.busapplication.R;

public class RideWaitActivity extends AppCompatActivity {
    int adultNum;
    int childNum;
    String[] qr1;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_wait);

        Intent args = getIntent();
        adultNum = args.getIntExtra("adultNum",0);
        childNum = args.getIntExtra("childNum",0);
        qr1 = args.getStringArrayExtra("QR1");

        TextView mTextView1 = findViewById(R.id.text_dialog1);
        mTextView1.setText(qr1[1] + "に乗車しています");
        TextView mTextView2 = findViewById(R.id.text_dialog2);
        mTextView2.setText("大人：" + adultNum + "人\n" + "子供：" + childNum + "人");

        Log.d("RideWait", "ride wait");
    }

    public void RideWait(View view) {
        Intent intent = new Intent(this,RideActivity.class);
        intent.putExtra("QR1",qr1);
        intent.putExtra("adultNum",adultNum);
        intent.putExtra("childNum",childNum);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {

    }
}