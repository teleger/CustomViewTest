package com.example.tele.custombleservice;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ViewGroup mLayout;


    /**
     * android 事件分发 入口, 即 点击 当前activity 响应的事件
     * @param ev
     * @return
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(ev.getAction() == MotionEvent.ACTION_DOWN){
            Log.i(TAG,"click i touch,DOWN ...");
        }
        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            Log.i(TAG,"click i touch,Move .....");
        }
        if(ev.getAction() == MotionEvent.ACTION_UP){
            Log.i(TAG,"click i touch,UP ...");
        }
        return super.dispatchTouchEvent(ev);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ScanResultbt = findViewById(R.id.scan_bt);
        showbtDevice = findViewById(R.id.show_bt_ble);
        stop_scan    = findViewById(R.id.stop_scan);
        mLayout      = findViewById(R.id.my_layout);
        //circleview   = findViewById(R.id.custom_circle);
        ScanResultbt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        stop_scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

        mLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG,"click i touch ,main Layout.....");
            }
        });

        final BluetoothManager bluetoothManager =
                (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);

        mBluetoothAdapter = bluetoothManager.getAdapter();
        if(mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()){
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableIntent,REQUEST_ENABLE_BT);
        }
        //开启位置服务，支持获取ble蓝牙扫描结果
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && !NetUtils.isLocationOpen(getApplicationContext())) {
            Intent enableLocate = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(enableLocate, REQUEST_LOCATION_PERMISSION);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // Android M Permission check
            if (this.checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, REQUEST_LOCATION_PERMISSION);
            }
        }
        mBluetoothlescanner = mBluetoothAdapter.getBluetoothLeScanner();
    }

    private class LeScanCallBack extends ScanCallback{
        @Override
        public void onScanResult(int callbackType, ScanResult result) {
            super.onScanResult(callbackType, result);
            if(result != null){
             //System.out.println("扫描到设备：" + result.getDevice().getName() + "  " + result.getDevice().getAddress());
                //showbtDevice.setText(result.getDevice().getAddress());
            }
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],@NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_LOCATION_PERMISSION://获取位置信息
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // TODO request success
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_LOCATION_PERMISSION) {
            if (NetUtils.isLocationOpen(getApplicationContext())) {
                Log.i(TAG, " request location permission success");
                //Android6.0需要动态申请权限
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
                    //请求权限
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_COARSE_LOCATION,
                                    Manifest.permission.ACCESS_FINE_LOCATION},
                            REQUEST_LOCATION_PERMISSION);
                    if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                            Manifest.permission.ACCESS_COARSE_LOCATION)) {
                    }
                }
            } else {
                //若未开启位置信息功能，则退出该应用
                finish();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private final static int REQUEST_ENABLE_BT = 0x01;
    private final static int REQUEST_LOCATION_PERMISSION = 0x02;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothlescanner;
    private ScanCallback mscancall = new LeScanCallBack();

    private Button ScanResultbt;
    private TextView showbtDevice;
    private Button stop_scan;
}
