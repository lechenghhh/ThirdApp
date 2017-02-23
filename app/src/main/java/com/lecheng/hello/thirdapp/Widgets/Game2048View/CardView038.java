package com.lecheng.hello.thirdapp.Widgets.Game2048View;

import android.content.Context;
import android.widget.FrameLayout;
import android.widget.TextView;

public class CardView038 extends FrameLayout {

    public CardView038(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        label = new TextView(getContext());
        label.setTextSize(32);
        label.setBackgroundColor(0x33ffffff);
        LayoutParams lp = new LayoutParams(-1, -1);
        lp.setMargins(10, 10, 0, 0);
        addView(label, lp);
        setNum(0);
    }

    private int num = 0;

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
        if (num <= 0) {
            label.setText("");
        } else {
            label.setText(num + "");
        }
    }

    public boolean equals(CardView038 o) {
        // TODO Auto-generated method stub
        return getNum() == o.getNum();
    }

    private TextView label;

}
