package com.example.busapplication.ui.ride;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;
import com.example.busapplication.R;
import com.example.busapplication.ui.home.HomeActivity;
import com.google.zxing.client.android.Intents;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;
import java.util.Arrays;

public class RideActivity extends AppCompatActivity {
    String[] qr;
    String[] qr1;
    int adultNum;
    int childNum;
    int error_cnt = 0;

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if(result.getContents() == null) {
                    Intent originalIntent = result.getOriginalIntent();
                    if (originalIntent == null) {
                        Log.d("RideActivity", "Cancelled scan");
                        //Toast.makeText(RideActivity.this, "Cancelled", Toast.LENGTH_LONG).show();
                    } else if(originalIntent.hasExtra(Intents.Scan.MISSING_CAMERA_PERMISSION)) {
                        Log.d("RideActivity", "Cancelled scan due to missing camera permission");
                        //Toast.makeText(RideActivity.this, "Cancelled due to missing camera permission", Toast.LENGTH_LONG).show();
                    }
                    int n = qr.length;
                    if(n==0){
                        Intent home = new Intent(this, HomeActivity.class);
                        startActivity(home);
                    }else{
                        Intent wait = new Intent(this, RideWaitActivity.class);
                        wait.putExtra("adultNum",adultNum);
                        wait.putExtra("childNum",childNum);
                        wait.putExtra("QR1",qr);
                        startActivity(wait);
                    }
                } else {
                    Log.d("RideActivity", "Scanned");
                    //Toast.makeText(RideActivity.this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                    getInfo(result.getContents());

                }
            });

    public RideActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScanOptions options = new ScanOptions().setCaptureActivity(CameraActivity.class);
        barcodeLauncher.launch(options);
        Intent args = getIntent();
        adultNum = args.getIntExtra("adultNum",0);
        childNum = args.getIntExtra("childNum",0);
        qr1 = args.getStringArrayExtra("QR1");
    }
    public void scaned(View v) {

        if(error_cnt==1){
            Intent home = new Intent(this, HomeActivity.class);
            startActivity(home);
        }
        if(qr[0].equals("0")){
            // Do something in response to button click
            Intent intent1 = new Intent(this, RideOnActivity.class);
            intent1.putExtra("QR1", qr);
            startActivity(intent1);
        }else if (qr[0].equals("1")){
            Intent intent2 = new Intent(this, RideOffActivity.class);
            intent2.putExtra("QR1",qr1);
            intent2.putExtra("adultNum",adultNum);
            intent2.putExtra("childNum",childNum);
            intent2.putExtra("QR2", qr);
            startActivity(intent2);
        }
    }
    public void ErrorButton(View v){
        Intent home = new Intent(this, HomeActivity.class);
        startActivity(home);
    }
    @SuppressLint("SetTextI18n")
    private void getInfo(String info){
        qr = info.split(",", 0);
        String[] bus = getResources().getStringArray(R.array.Array_bus);
        if (Arrays.asList(bus).contains(qr[1])) {
            setContentView(R.layout.activity_scaned);
            TextView text = findViewById(R.id.text_scaned);
            TextView error = findViewById(R.id.text_error_scan);
            if(qr[0].equals("0")) {
                // 乗車の場合
                text.setText(qr[1] + "に乗車しました。\n" + "乗車したバス停：" + qr[2]);

            }else if(qr[0].equals("1")){
                // 降車の場合
                //Button button = findViewById(R.id.button_ride);
                if (qr1 == null){
                    error.setText("乗車処理がされていません。\n運転手にこの画面を提示してください。");
                    //button.setText("HOMEに戻る");
                    error_cnt = 1;
                }else if(!qr[1].equals(qr1[1])){
                    error.setText("乗車したバスと降車したバスが違います。\n運転手にこの画面を提示してください。");
                    //button.setText("HOMEに戻る");
                    error_cnt = 1;
                }else {
                    text.setText(qr[1] + "に乗車していました。\n" + "降車したバス停：" + qr[2] );
                }
            }
        } else {
            Toast.makeText(this ,"無効なQRコードです。やり直してください。", Toast.LENGTH_LONG).show();
            ScanOptions options = new ScanOptions().setCaptureActivity(CameraActivity.class);
            barcodeLauncher.launch(options);
        }
    }
    @Override
    public void onBackPressed() {

    }
}