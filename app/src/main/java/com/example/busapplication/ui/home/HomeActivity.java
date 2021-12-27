package com.example.busapplication.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import com.example.busapplication.R;
import com.example.busapplication.ui.introduce.IntroduceActivity;
import com.example.busapplication.ui.map.MapsActivity;
import com.example.busapplication.ui.profile.ProfileActivity;
import com.example.busapplication.ui.ride.RideActivity;

public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }


    public boolean goMain(View view){
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        return true;
    }
    public boolean showMap(View view) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        return true;
    }
    public boolean scanQR(View view) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Intent intent = new Intent(this, RideActivity.class);
        startActivity(intent);
        return true;
    }
    public boolean showIntroduce(View view) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Intent intent = new Intent(this, IntroduceActivity.class);
        startActivity(intent);
        return true;
    }
    public boolean showProfile(View view) {
        // Inflate the menu; this adds items to the action bar if it is present.
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        return true;
    }
    @Override
    public void onBackPressed() {

    }
};
