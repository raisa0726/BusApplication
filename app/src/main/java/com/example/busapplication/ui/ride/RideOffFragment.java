package com.example.busapplication.ui.ride;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;

import com.example.busapplication.R;
import com.example.busapplication.ui.home.HomeActivity;

import java.util.Objects;

/**
 * ride off
 */

public class RideOffFragment extends Fragment {
    String qr;
    private int sum;
    private int adultNum;
    private int childNum;

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // We use a String here, but any type that can be put in a Bundle is supported
        adultNum = getArguments().getInt("adultNum");
        childNum = getArguments().getInt("childNum");
        Log.d("RideOff", String.valueOf(adultNum));
        Log.d("RideOff", String.valueOf(childNum));
        Log.d("RideOff", qr);
        qr = getArguments().getString("QR");
        //
        sum = getSum(qr, adultNum, childNum);
        View view = inflater.inflate(R.layout.fragment_ride_off, container, false);
        TextView mTextView1 = view.findViewById(R.id.text_ans1);
        TextView mTextView2 = view.findViewById(R.id.text_ans2);
        mTextView1.setText("大人: " + adultNum + "人　子供: " + childNum + "人");
        mTextView2.setText("合計:" + sum + "円の支払いです");
        Button button = view.findViewById(R.id.button_ride_off);
        button.setOnClickListener(onClickListener);
        Log.d("RideActivity", "ride off");
        return view;
    }

    protected int getSum(String bus, int num1, int num2) {
        int sum;
        // 数字は仮の乗車料金
        switch (bus) {
            case "ふらっとバス":
                sum = 100 * num1 + 50 * num2;
                break;
            case "まちバス":
                sum = 100 * num1 + 50 * num2;
                break;
            case "北鉄バス":
                sum = 200 * num1 + 100 * num2;
                break;
            case "JRバス":
                sum = 100 * num1 + 50 * num2;
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + bus);
        }
        return sum;
    }
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // data
            Bundle bundle2 = new Bundle();
            bundle2.putInt("adultNum", adultNum);
            bundle2.putInt("childNum", childNum);
            getParentFragmentManager().setFragmentResult("ride_on_key", bundle2);
            // fragment
            Intent intent = new Intent(getActivity(), HomeActivity.class);
            //int cnt = 4;
            //intent.putExtra("cnt",cnt);
            intent.putExtra("QR",qr);
            intent.putExtra("adultNum",adultNum);
            intent.putExtra("childNum",childNum);
            startActivity(intent);
        }
    };
}
