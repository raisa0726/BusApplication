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
import androidx.fragment.app.FragmentTransaction;

import com.example.busapplication.R;

import java.util.Objects;

/**
 * ride on
 */
public class RideOnFragment extends Fragment {
    String qr;
    TextView aNum;
    TextView cNum;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        qr = getArguments().getString("QR");
        View view = inflater.inflate(R.layout.fragment_ride_on, container, false);
        TextView mTextView = view.findViewById(R.id.text_ride_bus);
        mTextView.setText(qr + "に乗車しました");
        aNum = view.findViewById(R.id.adult_num);
        cNum = view.findViewById(R.id.child_num);
        Button button = view.findViewById(R.id.button_ride_on);
        button.setOnClickListener(onClickListener);
        Log.d("RideActivity", "ride on");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int adultNum = Integer.parseInt(aNum.getText().toString());
            int childNum = Integer.parseInt(cNum.getText().toString());
            // data
            Bundle bundle2 = new Bundle();
            bundle2.putInt("adultNum", adultNum);
            bundle2.putInt("childNum", childNum);
            bundle2.putString("qr",qr);
            getParentFragmentManager().setFragmentResult("ride_on_key", bundle2);
            // fragment
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            // BackStackを設定
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.replace(R.id.ride_container,new RideWaitFragment()).commit();
        }
    };
}
