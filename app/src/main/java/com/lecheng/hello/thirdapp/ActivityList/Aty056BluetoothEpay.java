package com.lecheng.hello.thirdapp.ActivityList;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lecheng.hello.thirdapp.R;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Set;
import java.util.UUID;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty056BluetoothEpay extends Activity {

    @Bind(R.id.etData)
    EditText etData;
    @Bind(R.id.tvScreen)
    TextView tvScreen;
    private final UUID PRINTER_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    private final String Innerprinter_Address = "00:11:22:33:44:55";
    private String text = "118114便民平台\n" +
            "\n" +
            "产品名称：莆田电信缴费\n" +
            "\n" +
            "------------------------\n" +
            "\n" +
            "订单号：15180116135928230310\n" +
            "\n" +
            "流水号：15180116140157085058\n" +
            "\n" +
            "交易金额：5.00\n" +
            "\n" +
            "缴费号码：18959506618\n" +
            "\n" +
            "交易时间：2018-01-16 13:59:28\n" +
            "\n" +
            "操作员：李涛个人营业厅\n" +
            "\n" +
            "------------------------\n" +
            "\n" +
            "门店名称：个人营业厅章三\n" +
            "\n" +
            "客服电话：0591-83356611\n" +
            "\n" +
            "------------------------\n" +
            "\n" +
            "感谢您使用118114便民 ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty056_bluetoothepay);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn1,})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                print();
                break;
        }
    }

    private void print() {
        BluetoothAdapter btAdpt = BluetoothAdapter.getDefaultAdapter();
        if (btAdpt == null) {
            Toast.makeText(this, "请打开蓝牙", Toast.LENGTH_LONG).show();
            return;
        }
        BluetoothDevice device = getDevice(btAdpt);
        if (device == null) {
            Toast.makeText(this, "请确保蓝牙允许已有的打印机", Toast.LENGTH_LONG).show();
            return;
        }
        String strData = etData.getText().toString();
        strData = text;

        byte[] data = null;
        try {
            data = strData.getBytes("GBK");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        BluetoothSocket socket = null;
        try {
            socket = getSocket(device);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "蓝牙socket-io异常", Toast.LENGTH_LONG).show();
        }
        try {
            sendData(data, socket);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "蓝牙发送data-io异常", Toast.LENGTH_LONG).show();
        }
    }

    private BluetoothDevice getDevice(BluetoothAdapter bluetoothAdapter) {
        BluetoothDevice innerpriner_device = null;
        Set<BluetoothDevice> devices = bluetoothAdapter.getBondedDevices();
        for (BluetoothDevice device : devices) {
            System.out.println("device.getName()=" + device.getName());
            System.out.println("device.getAddress()=" + device.getAddress());
            if (device.getAddress().equals(Innerprinter_Address)) {
                innerpriner_device = device;
                break;
            }
        }
        return innerpriner_device;
    }

    private BluetoothSocket getSocket(BluetoothDevice device) throws IOException {
        BluetoothSocket socket = device.createRfcommSocketToServiceRecord(PRINTER_UUID);
        socket.connect();
        return socket;
    }

    private void sendData(byte[] bytes, BluetoothSocket socket) throws IOException {
        OutputStream out = socket.getOutputStream();
        out.write(bytes, 0, bytes.length);
        out.close();
    }
}
