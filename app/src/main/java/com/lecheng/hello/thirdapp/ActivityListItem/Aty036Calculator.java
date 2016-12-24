package com.lecheng.hello.thirdapp.ActivityListItem;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.content.ClipData.Item;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.os.Build;

import com.lecheng.hello.thirdapp.R;

public class Aty036Calculator extends Activity implements OnClickListener {
    private TextView tvscreen1;
    private int i = 0, x = 0;
    private double sum = 0, a = 0, b = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty036_calculator);
        findViewById(R.id.btn0).setOnClickListener(this);
        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.btn8).setOnClickListener(this);
        findViewById(R.id.btn9).setOnClickListener(this);
        findViewById(R.id.btnadd).setOnClickListener(this);
        findViewById(R.id.btnsub).setOnClickListener(this);
        findViewById(R.id.btnmul).setOnClickListener(this);
        findViewById(R.id.btndiv).setOnClickListener(this);
        findViewById(R.id.btnequ).setOnClickListener(this);
        findViewById(R.id.btnclear).setOnClickListener(this);
        tvscreen1 = (TextView) findViewById(R.id.tvscreen1);


    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btn0:
                a = sum + 0;
                sum = a;
                tvscreen1.setText(a + "");
                break;
            case R.id.btn1:
                a = sum + 1;
                sum = a;
                tvscreen1.setText(a + "");
                sum = 10 * sum;
                break;
            case R.id.btn2:
                a = sum + 2;
                sum = a;
                tvscreen1.setText(a + "");
                sum = 10 * sum;
                break;
            case R.id.btn3:
                a = sum + 3;
                sum = a;
                tvscreen1.setText(a + "");
                sum = 10 * sum;
                break;
            case R.id.btn4:
                a = sum + 4;
                sum = a;
                tvscreen1.setText(a + "");
                sum = 10 * sum;
                break;
            case R.id.btn5:
                a = sum + 5;
                sum = a;
                tvscreen1.setText(a + "");
                sum = 10 * sum;
                break;
            case R.id.btn6:
                a = sum + 6;
                sum = a;
                tvscreen1.setText(a + "");
                sum = 10 * sum;
                break;
            case R.id.btn7:
                a = sum + 7;
                sum = a;
                tvscreen1.setText(a + "");
                sum = 10 * sum;
                break;
            case R.id.btn8:
                a = sum + 8;
                sum = a;
                tvscreen1.setText(a + "");
                sum = 10 * sum;
                break;
            case R.id.btn9:
                a = sum + 9;
                sum = a;
                tvscreen1.setText(a + "");
                sum = 10 * sum;
                break;
            case R.id.btnadd:
                x = 1;
                b = a;
                sum = 0;
                a = 0;
                break;
            case R.id.btnsub:
                x = 2;
                b = a;
                sum = 0;
                a = 0;
                break;
            case R.id.btnmul:
                x = 3;
                b = a;
                sum = 0;
                a = 0;
                break;
            case R.id.btndiv:
                x = 4;
                b = a;
                sum = 0;
                a = 0;
                break;
            case R.id.btnequ:
                compute();
                tvscreen1.setText(a + "");
                b = 0;
                sum = 0;
                break;
            case R.id.btnclear:
                b = 0;
                a = 0;
                sum = 0;
                x = 0;
                tvscreen1.setText(a + "");
                break;
        }

    }

    private void compute() {
        // TODO Auto-generated method stub
        if (x == 1) {
            a = (b + a);
        } else if (x == 2) {
            a = (b - a);
        } else if (x == 3) {
            a = b * a;
        } else if (x == 4) {
            a = b / a;
        }
    }
}
