package com.example.busapplication.ui.ride;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.busapplication.R;
import com.example.busapplication.ui.home.HomeActivity;

public class RideOffActivity extends AppCompatActivity {
    String[] qr1;
    String[] qr2;
    private int sum;
    private int adultNum;
    private int childNum;


    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_off);

        Intent args = getIntent();
        qr1 = args.getStringArrayExtra("QR1");
        qr2 = args.getStringArrayExtra("QR2");
        adultNum = args.getIntExtra("adultNum",0);
        childNum = args.getIntExtra("childNum",0);
        Log.d("RideOff", String.valueOf(adultNum));
        Log.d("RideOff", String.valueOf(childNum));
        //
        sum = getSum(qr1, qr2, adultNum, childNum);
        TextView rideOn = findViewById(R.id.text_ride_on_ans);
        TextView rideOff = findViewById(R.id.text_ride_off_ans);
        TextView mTextView = findViewById(R.id.text_ans);
        rideOn.setText("乗車したバス：" + qr1[1] + '\n' +"乗車したバス停：" + qr1[2]);
        rideOff.setText("降車したバス：" + qr2[1] + '\n' +"降車したバス停：" + qr2[2]);
        mTextView.setText("大人: " + adultNum + "人　子供: " + childNum + "人\n" + "合計:" + sum + "円の支払いです");
        Log.d("RideActivity", "ride off");
    }

    protected int getSum(String[] on, String[] off, int num1, int num2) {
        int price;
        // 数字は仮の乗車料金
        switch (on[1]) {
            case "ふらっとバス":
                price = 100 * num1 + 50 * num2;
                break;
            case "まちバス":
                price = 100 * num1 + 50 * num2;
                break;
            case "北鉄バス":
                price = 200 * num1 + 100 * num2;
                break;
            case "JRバス":
                price = 100 * num1 + 50 * num2;
                break;
            default:
                throw new IllegalStateException("Error: " + on[1]);
        }
        return price;
    }

    public void RideOff(View view) {
        // data
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("QR1",qr1);
        intent.putExtra("QR2",qr2);
        intent.putExtra("adultNum", adultNum);
        intent.putExtra("childNum", childNum);
        intent.putExtra("sum",sum);
        startActivity(intent);
        // fragment

    }
    @Override
    public void onBackPressed() {

    }
}