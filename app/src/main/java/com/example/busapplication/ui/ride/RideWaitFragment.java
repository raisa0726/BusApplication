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

/**
 * ride waiting
 */
public class RideWaitFragment extends Fragment {
    int adultNum;
    int childNum;
    String qr;
    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final String[] dText = new String[1];
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String key, @NonNull Bundle bundle) {
                Bundle args = getArguments();
                // We use a String here, but any type that can be put in a Bundle is supported
                adultNum = args.getInt("adultNum");
                childNum = args.getInt("childNum");
                qr = args.getString("QR");
                //
                dText[0] = "大人" + adultNum + "人、子供" + childNum + "人で利用しています。";
            }
        });
        View view = inflater.inflate(R.layout.fragment_ride_wait, container, false);
        TextView mTextView = view.findViewById(R.id.text_dialog);
        mTextView.setText(dText[0]);
        Button button = view.findViewById(R.id.button_ride_wait);
        button.setOnClickListener(onClickListener);
        Log.d("RideWaitFragment", "ride wait");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // data
            Bundle bundle2 = new Bundle();
            bundle2.putInt("adultNum", adultNum);
            bundle2.putInt("childNum", childNum);
            bundle2.putString("QR",qr);
            int cnt = 2;
            bundle2.putInt("cnt", cnt);
            getParentFragmentManager().setFragmentResult("ride_wait_key", bundle2);
            // fragment
            Intent intent = new Intent(getActivity(), RideActivity.class);
            intent.putExtra("cnt",cnt);
            intent.putExtra("QR",qr);
            intent.putExtra("adultNum",adultNum);
            intent.putExtra("childNum",childNum);
            Log.d("now cnt", String.valueOf(cnt));
            startActivity(intent);
        }
    };
}
