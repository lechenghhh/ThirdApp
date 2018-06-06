package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

import java.util.Set;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/*Android 蓝牙开发（一）蓝牙通信:http://blog.csdn.net/vnanyesheshou/article/details/51554852*/
public class Aty056Bluetooth extends Activity {
    BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();//获取蓝牙适配器
    @Bind(R.id.tv1)
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty056);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        Set<BluetoothDevice> devices = mBluetoothAdapter.getBondedDevices();        //获取已配对蓝牙设备
        String strBondDevice = "";
        for (BluetoothDevice bonddevice : devices) {
            strBondDevice = strBondDevice + "\n绑定的设备名：" + bonddevice.getName() +
                    "\nMAC：" + bonddevice.getAddress();
        }
        tv1.setText("蓝牙名称：" + mBluetoothAdapter.getName() +
                "\nMAC：" + mBluetoothAdapter.getAddress() +
                "\n已配对的设备数：" + devices.size() +
                "\n" + strBondDevice);

        //搜索蓝牙设备，该过程是异步的，通过下面注册广播接受者，可以监听是否搜到设备。
        IntentFilter filter = new IntentFilter();
//发现设备
        filter.addAction(BluetoothDevice.ACTION_FOUND);
//设备连接状态改变
        filter.addAction(BluetoothDevice.ACTION_BOND_STATE_CHANGED);
//蓝牙设备状态改变
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);
        registerReceiver(mBluetoothReceiver, filter);
    }

    private BroadcastReceiver mBluetoothReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            System.out.println("mBluetoothReceiver action =" + action);
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {//每扫描到一个设备，系统都会发送此广播。
                //获取蓝牙设备
                BluetoothDevice scanDevice = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                if (scanDevice == null || scanDevice.getName() == null) return;
                System.out.println("name=" + scanDevice.getName() +
                        "\n地址：" + scanDevice.getAddress());
                //蓝牙设备名称
                String name = scanDevice.getName();
              /*  if (name != null && name.equals(VnApplication.BLUETOOTH_NAME)) {
                    mBluetoothAdapter.cancelDiscovery();
                    //取消扫描
//                    mProgressDialog.setTitle(getResources().getString(R.string.progress_connecting));                   //连接到设备。
//                    mBlthChatUtil.connect(scanDevice);
                }*/
            } else if (BluetoothAdapter.ACTION_DISCOVERY_FINISHED.equals(action)) {

            }
        }

    };

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                if (!mBluetoothAdapter.isEnabled()) {//弹出对话框提示用户是后打开
                    Intent enabler = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                    startActivityForResult(enabler, 0);
                    // mBluetoothAdapter.enable();//不做提示，直接打开，不建议该方法，有的手机会有问题。
                }
                init();
                break;
            case R.id.btn2:
                if (mBluetoothAdapter.isEnabled()) {
                    if (mBluetoothAdapter.getScanMode() != BluetoothAdapter.SCAN_MODE_CONNECTABLE_DISCOVERABLE) {
                        Intent discoverableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                        discoverableIntent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION, 300);
                        startActivity(discoverableIntent);
                    }
                }
                break;
            case R.id.btn3:
                startActivity(new Intent(this, Aty056BluetoothEpay.class));
                break;
            case R.id.btn4:
                startActivity(new Intent(this, Aty056BluetoothBLE.class));
                break;
        }
    }
}
