package com.example.busapplication.ui.ride;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;

import com.example.busapplication.R;
import com.example.busapplication.ui.home.HomeActivity;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class CameraActivity extends AppCompatActivity {
    private CaptureManager capture;
    private DecoratedBarcodeView barcodeScannerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        setContentView(R.layout.activity_scan);

        Toolbar toolbar = findViewById(R.id.my_awesome_toolbar);
        toolbar.setTitle("QRコードをスキャン");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        super.onCreate(savedInstanceState);

        barcodeScannerView = findViewById(R.id.zxing_barcode_scanner);

        capture = new CaptureManager(this, barcodeScannerView);
        capture.initializeFromIntent(getIntent(), savedInstanceState);
        capture.decode();
    }
    // 再開
    @Override
    protected void onResume() {
        super.onResume();
        capture.onResume();
    }
    // 止まる
    @Override
    protected void onPause() {
        super.onPause();
        capture.onPause();
    }
    // 強制終了
    @Override
    protected void onDestroy() {
        super.onDestroy();
        capture.onDestroy();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        capture.onSaveInstanceState(outState);
    }
    // 戻る
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return barcodeScannerView.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }
}