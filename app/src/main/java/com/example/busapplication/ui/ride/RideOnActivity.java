package com.example.busapplication.ui.ride;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.busapplication.R;

public class RideOnActivity extends AppCompatActivity {
    String[] qr1;
    EditText aNum;
    EditText cNum;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride_on);

        Intent args = getIntent();
        qr1 = args.getStringArrayExtra("QR1");
        TextView mTextView = findViewById(R.id.text_ride_bus);
        mTextView.setText(qr1[1] + "に乗車しました");
        aNum = findViewById(R.id.adult_num);
        cNum = findViewById(R.id.child_num);
        Log.d("RideActivity", "ride on");
    }

        public void RideOn(View view) {
            //cnt++;
            int adultNum = Integer.parseInt(aNum.getText().toString());
            int childNum = Integer.parseInt(cNum.getText().toString());

            Intent intent = new Intent(this, RideWaitActivity.class);
            intent.putExtra("adultNum", adultNum);
            intent.putExtra("childNum", childNum);
            intent.putExtra("QR1",qr1);
            startActivity(intent);
        }
    @Override
    public void onBackPressed() {

    }
}