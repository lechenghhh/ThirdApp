package com.lecheng.hello.thirdapp.ActivityList;

import android.annotation.TargetApi;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.R;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.UUID;

import butterknife.ButterKnife;
import butterknife.OnClick;


//https://www.jianshu.com/p/3a372af38103
public class Aty056BluetoothBLE extends AppCompatActivity {
    public static final int REQUEST_ENABLE_BT = 999;
    public static final String TAG = "Aty056BluetoothBLE_TAG";
    public static final String CHARACTERISTIC_UUID_SERVICE = "0000fff0-0000-1000-8000-00805f9b34fb";//主服务
    public static final String CHARACTERISTIC_UUID_READ = "0000fff1-0000-1000-8000-00805f9b34fb";   //读
    public static final String CHARACTERISTIC_UUID_WRITE = "0000fff3-0000-1000-8000-00805f9b34fb";  //写
    private BluetoothAdapter mBluetoothAdapter;         //蓝牙适配器
    private BluetoothDevice mDevice;                    //连接的蓝牙设备
    private BluetoothGatt mBluetoothGatt;               //建立的蓝牙协议
    private BluetoothGattCharacteristic mCharacteristicRead;//蓝牙传输特性
    private BluetoothGattCharacteristic mCharacteristicWrite;//蓝牙传输特性
    private UUID uuid;
    private boolean mScanning = false;
    private boolean btnScanning = true;
    private TextView tv1;
    private EditText etSend;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty056_ble);
        ButterKnife.bind(this);

        final BluetoothManager bluetoothManager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = bluetoothManager.getAdapter();

        tv1 = (TextView) findViewById(R.id.tv1);
        etSend = (EditText) findViewById(R.id.etSend);
        tv1.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                tv1.setText("");
                Toast.makeText(Aty056BluetoothBLE.this, "清屏成功！", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        checkBLEFeature();//检查ble功能
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn1://搜索
                scanLeDevice(btnScanning);
                btnScanning = !btnScanning;
                break;
            case R.id.btn2://连接
//                mBluetoothGatt.connect();
//                device.connectGatt(this, false, mGattCallback);
                mDevice.connectGatt(Aty056BluetoothBLE.this, false, mGattCallback);
                break;
            case R.id.btn3://断开
                mBluetoothGatt.disconnect();
                break;
            case R.id.btn4://发送
//                byte[] bytes = new byte[]{0, 54, 01, 43, 04, 01, 01, 01, 0, 0};
                String value = "CD5401430401010100DB";
                mCharacteristicWrite.setValue(value);//写入发送的数据
                mBluetoothGatt.writeCharacteristic(mCharacteristicWrite);//写入异步蓝牙设备之中

                mBluetoothGatt.setCharacteristicNotification(mCharacteristicWrite, true);
                BluetoothGattDescriptor descriptor = mCharacteristicWrite.getDescriptor(UUID.fromString(CHARACTERISTIC_UUID_WRITE));
                descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                mBluetoothGatt.writeDescriptor(descriptor);
                break;
        }
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
                mHandler.postDelayed(mScanRunnable, 10000);//五秒后关闭扫描
                mBluetoothAdapter.startLeScan(mLeScanCallback);
                tv1.append("--------------开始搜索--------------" + "\n");
            } else {
                Toast.makeText(this, " R.string.scan_bt_disabled", Toast.LENGTH_SHORT).show();
            }
        } else {
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
//            mRefreshLayout.setRefreshing(false);
            mHandler.removeCallbacks(mScanRunnable);
            mScanning = false;
            tv1.append("--------------停止搜索--------------" + "\n");
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
                    if (device.getName() != null) {
                        tv1.append("name=" + device.getName() + " address=" + device.getAddress() + "\n\n");
                        if (device.getAddress().toString().equals("18:7A:93:00:00:03")) {
                            Log.v(TAG, device.getName() + " " + device.getAddress());
                            mDevice = device;
                            tv1.append("已经搜索到:18:7A:93:00:00:03" + "\n");
                            scanLeDevice(false);
                        }
                    }
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
        RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions.request(android.Manifest.permission.ACCESS_FINE_LOCATION,
                android.Manifest.permission.ACCESS_COARSE_LOCATION,
                android.Manifest.permission.BLUETOOTH,
                android.Manifest.permission.BLUETOOTH_ADMIN,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                .subscribe();

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
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
    }

    //参数连接状态回调。
    private BluetoothGattCallback mGattCallback = new BluetoothGattCallback() {
        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
        @Override//连接状态改变的回调
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            mBluetoothGatt = gatt;
            Log.v(TAG, "连接状态改变的回调-status=" + status + "-newState=" + newState);
            if (newState == BluetoothProfile.STATE_CONNECTED) {
//                tv1.setText("已经连接到:18:7A:93:00:00:03");
                Log.e(TAG, "启动服务发现:" + mBluetoothGatt.discoverServices());
            } else {
                Log.e(TAG, "onConnectionStateChange-失败");
//                mBluetoothGatt.close();
//                tv1.setText("连接关闭");
            }
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)        //发现服务的回调
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            Log.v(TAG, "发现服务的回调-status=" + status);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.e(TAG, "成功发现服务:");
                for (BluetoothGattService service : gatt.getServices()) {
                    Log.v(TAG, "service-uuid=" + service.getUuid());
//                    if (CHARACTERISTIC_UUID_SERVICE.equals(service.getUuid().toString())) {
                    for (BluetoothGattCharacteristic c : service.getCharacteristics()) {
                        Log.v(TAG, "service-characteristic=" + c.getUuid());
//                            if (CHARACTERISTIC_UUID_READ.equals(c.getUuid().toString())) {
//                                mCharacteristicRead = c;
//                            }
//                            if (CHARACTERISTIC_UUID_WRITE.equals(c.getUuid().toString())) {
//                                mCharacteristicWrite = c;
//                            }
                    }
//                }
                }
                mBluetoothGatt = gatt;
            } else {
                Log.e(TAG, "服务发现失败，错误码为:" + status);
            }
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)//写操作的回调
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.v(TAG, "写操作的回调-status=" + status + "-mCharacteristic=" + characteristic);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.e(TAG, "写入成功" + characteristic.getValue());
            }
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)//读操作的回调
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            Log.v(TAG, "读操作的回调-status=" + status + "-mCharacteristic=" + characteristic);
            if (status == BluetoothGatt.GATT_SUCCESS) {
                Log.e(TAG, "读取成功" + characteristic.getValue());
            }
        }

        @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)//数据返回的回调（此处接收BLE设备返回数据）
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            Log.v(TAG, "数据返回的回调(此处接收BLE设备返回数据)-mCharacteristic=" + characteristic);
        }
    };
}
