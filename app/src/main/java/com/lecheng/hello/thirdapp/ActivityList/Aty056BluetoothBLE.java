package com.lecheng.hello.thirdapp.ActivityList;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothManager;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import com.lecheng.hello.thirdapp.R;

import java.util.ArrayList;
import java.util.List;

public class Aty056BluetoothBLE extends AppCompatActivity {
    String TAG = "Aty056BluetoothBLE";
    BluetoothAdapter mBluetoothAdapter;
    //    LeDeviceListAdapter mLeDeviceListAdapter;
    List<BluetoothAdapter> list = new ArrayList<>();
    boolean mScanning = false;
    BluetoothGatt mBluetoothGatt;
    //    @Bind(R.id.tv1)
    TextView tv1;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        tv1 = (TextView) findViewById(R.id.tv1);
        findViewById(R.id.btn1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scanLeDevice(true);
            }
        });
    }

    //扫描BLE设备
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void scanLeDevice(final boolean enable) {
        if (enable) {
            if (mBluetoothAdapter.isEnabled()) {
                if (mScanning)
                    return;
                mScanning = true;
//                mLeDeviceListAdapter.clear();
                mHandler.postDelayed(mScanRunnable, 5000);//五秒后关闭扫描
                mBluetoothAdapter.startLeScan(mLeScanCallback);
            } else {
                Toast.makeText(this, " R.string.scan_bt_disabled", Toast.LENGTH_SHORT).show();
            }
        } else {
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
//            mRefreshLayout.setRefreshing(false);
            mHandler.removeCallbacks(mScanRunnable);
            mScanning = false;
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public boolean sendMessageAtTime(Message msg, long uptimeMillis) {
            return super.sendMessageAtTime(msg, uptimeMillis);
        }
    };

    //扫描后回掉
    private BluetoothAdapter.LeScanCallback mLeScanCallback = new BluetoothAdapter.LeScanCallback() {
        /**
         * 简单说一下这三个参数的含义：
         * @param device：识别的远程设备
         * @param rssi：  RSSI的值作为对远程蓝牙设备的报告; 0代表没有蓝牙设备;
         * @param scanRecord：远程设备提供的配对号(公告)
         */
        @Override
        public void onLeScan(final BluetoothDevice device, final int rssi, final byte[] scanRecord) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //保存到本地：用来展示扫描得到的内容
//                    mLeDeviceListAdapter.addDevice(new LeDevice(device.getName(), device.getAddress(), rssi, scanRecord));
//                    mLeDeviceListAdapter.notifyDataSetChanged();
                    if (device.getName() != null)
                        tv1.append("name=" + device.getName() + " address=" + device.getAddress() + "\n\n");
                }
            });
        }
    };
    //关闭扫描
    private final Runnable mScanRunnable = new Runnable() {
        @Override
        public void run() {
            scanLeDevice(false);
        }
    };

    //检查ble可用
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    private void checkBLEFeature() {
        //判断是否支持蓝牙4.0
        if (!getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE)) {
            Toast.makeText(this, "R.string.ble_not_supported", Toast.LENGTH_SHORT).show();
            finish();
        }
        //获取蓝牙适配器
        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();
        //判断是否支持蓝牙
        if (mBluetoothAdapter == null) {
            //不支持
            Toast.makeText(this, "R.string.error_bluetooth_not_supported", Toast.LENGTH_SHORT).show();
            finish();
            return;
        } else
            //打开蓝牙
            if (!mBluetoothAdapter.isEnabled()) {//判断是否已经打开
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, 999);
            }
    }

    //参数连接状态回调。
    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        //连接状态改变的回调
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            if (newState == BluetoothProfile.STATE_CONNECTED) {
                // 连接成功后启动服务发现
                Log.e("AAAAAAAA", "启动服务发现:" + mBluetoothGatt.discoverServices());
            }
        }

        //发现服务的回调
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.e(TAG, "成功发现服务");
            } else {
                Log.e(TAG, "服务发现失败，错误码为:" + status);
            }
        }

        //写操作的回调
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.e(TAG, "写入成功" + characteristic.getValue());
            }
        }

        //读操作的回调
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.e(TAG, "读取成功" + characteristic.getValue());
            }
        }

        //数据返回的回调（此处接收BLE设备返回数据）
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
        }

    };

}
