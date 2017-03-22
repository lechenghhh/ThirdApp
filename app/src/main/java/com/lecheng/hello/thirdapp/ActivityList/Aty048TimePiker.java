package com.lecheng.hello.thirdapp.ActivityList;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;

import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty048TimePiker extends AppCompatActivity {


    @Bind(R.id.etDate)
    TextView etDate;
    @Bind(R.id.btnDate)
    Button btnDate;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty048);
        ButterKnife.bind(this);

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        final Calendar ca = Calendar.getInstance();
        return new DatePickerDialog(this, mdateListener, ca.get(Calendar.YEAR),
                ca.get(Calendar.MONTH), ca.get(Calendar.DAY_OF_MONTH));
    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            String strTime = year + "";//取出的值；
            int realMonth = monthOfYear + 1;
            if (realMonth < 10) {
                strTime = strTime + "0" + realMonth;
                if (dayOfMonth < 10) {
                    strTime = strTime + "0" + dayOfMonth;
                } else {
                    strTime = strTime + dayOfMonth;
                }
            } else {
                strTime = strTime + realMonth;
            }
            etDate.setText(strTime);
        }
    };

    @OnClick(R.id.btnDate)
    public void onClick() {
        showDialog(1);
    }
}
