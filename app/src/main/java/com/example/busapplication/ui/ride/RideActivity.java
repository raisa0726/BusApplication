package com.example.busapplication.ui.ride;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import com.example.busapplication.R;
import com.example.busapplication.ui.home.HomeActivity;
import com.google.zxing.client.android.Intents;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import java.util.ArrayList;

public class RideActivity extends AppCompatActivity {
    public int cnt;
    public int adultNum;
    public int childNum;
    Button button;
    ProgressBar progressBar;
    // todo: arraylist -> 一時キャッシュで保存
    ArrayList<String> resultContents = new ArrayList<>();
    @SuppressLint("SetTextI18n")
    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                Log.d("RideActivity", "camera");
                if(result.getContents() == null) {
                    cnt-=2;
                    Intent originalIntent = result.getOriginalIntent();
                    if (originalIntent == null) {
                        Log.d("RideActivity", "Cancelled scan");
                        Toast.makeText(RideActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                    } else if(originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                        Log.d("RideActivity", "Cancelled scan due to missing camera permission");
                        Toast.makeText(RideActivity.this, "Cancelled due to missing camera permission", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Log.d("RideActivity", "Scanned");
                    resultContents.add(result.getContents());
                }
            });

    public RideActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ride);
        Intent intent = getIntent();
        cnt = intent.getIntExtra("cnt",0);
        if(cnt>=2) {
            String qr = intent.getStringExtra("QR");
            resultContents.add(qr);
            adultNum = intent.getIntExtra("adultNum",0);
            childNum = intent.getIntExtra("childNum",0);
        }
        progressBar = findViewById(R.id.progressBar);
        // 水平プログレスバーの最大値を設定します
        progressBar.setMax(4);
        progressBar.setProgress(cnt);
        button = findViewById(R.id.button_ride);
        button.setOnClickListener(onClickListener);
    }
    private final View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Log.d("onclickCnt", String.valueOf(cnt));
            switch (cnt){
                case 1:
                    // scan -> ride on
                    FragmentManager fragmentManager1 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction1 = fragmentManager1.beginTransaction();
                    RideOnFragment onFragment = new RideOnFragment();
                    Bundle bundle1= new Bundle();
                    bundle1.putString("QR", resultContents.get(0));
                    bundle1.putInt("cnt",cnt);
                    onFragment.setArguments(bundle1);
                    fragmentTransaction1.replace(R.id.ride_container, onFragment).commit();
                    cnt++;
                    button.setVisibility(View.INVISIBLE);
                    // launch ride wait
                    break;
                case 0:
                case 2:
                    button.setVisibility(View.VISIBLE);
                    cnt++;
                    ScanOptions options = new ScanOptions().setCaptureActivity(CameraActivity.class);
                    barcodeLauncher.launch(options);
                    break;
                case 3:
                    // scan -> ride off
                    FragmentManager fragmentManager5 = getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction5 = fragmentManager5.beginTransaction();
                    RideOffFragment offFragment = new RideOffFragment();
                    Bundle bundle3 = new Bundle();
                    bundle3.putString("QR", resultContents.get(0));
                    bundle3.putInt("adultNum",adultNum);
                    bundle3.putInt("childNum",childNum);
                    Log.d("adultNum", String.valueOf(adultNum));
                    offFragment.setArguments(bundle3);
                    fragmentTransaction5.replace(R.id.ride_container, offFragment).commit();
                    button.setVisibility(View.INVISIBLE);
                    Log.d("case 3","ride off call!");
                    cnt++;
                    break;
                case 4:
                    Intent intent = new Intent(RideActivity.this, HomeActivity.class);
                    startActivity(intent);
            }
            progressBar.setProgress(cnt);
        }
    };
}