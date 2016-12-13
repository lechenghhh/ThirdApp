package com.lecheng.hello.thirdapp.ActivityListItem;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.lecheng.hello.thirdapp.R;

public class Aty008TestTextViewCtrl extends Activity implements View.OnClickListener {
    private EditText input;
    private ToggleButton status;
    private TextView resultPlay;
    private Button command;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text_play);

        init();

    }

    private void init() {
        input = (EditText) findViewById(R.id.etCommand);
        status = (ToggleButton) findViewById(R.id.tbStatus);
        command = (Button) findViewById(R.id.btnCommand);
        resultPlay = (TextView) findViewById(R.id.tvResultPlay);
        command.setOnClickListener(this);
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (status.isChecked()) {
                    input.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD | InputType.TYPE_CLASS_TEXT);
                } else {
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        String check = input.getText().toString();
        resultPlay.setText(check);
        if (check.contentEquals("left")) {
            resultPlay.setGravity(Gravity.LEFT);
        } else if (check.contentEquals("right")) {
            resultPlay.setGravity(Gravity.RIGHT);
        } else if (check.contentEquals("center")) {
            resultPlay.setGravity(Gravity.CENTER);
        } else if (check.contentEquals("blue")) {
            resultPlay.setTextColor(Color.BLUE);
        } else if (check.contentEquals("black")) {
            resultPlay.setTextColor(Color.BLACK);
        } else if (check.contains("cool")) {
            Random crazyNum = new Random();
            resultPlay.setText("haha");
            resultPlay.setTextSize(crazyNum.nextInt(100));
            resultPlay.setTextColor(Color.rgb(crazyNum.nextInt(255), crazyNum.nextInt(255), crazyNum.nextInt(255)));
            switch (crazyNum.nextInt(3)) {
                case 0:
                    resultPlay.setGravity(Gravity.LEFT);
                    break;
                case 1:
                    resultPlay.setGravity(Gravity.RIGHT);
                    break;
                case 2:
                    resultPlay.setGravity(Gravity.CENTER);
                    break;
            }
        } else {
            resultPlay.setText("SORRY.");

        }
    }
}
