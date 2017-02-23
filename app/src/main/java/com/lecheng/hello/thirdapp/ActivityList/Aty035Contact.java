package com.lecheng.hello.thirdapp.ActivityList;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.widget.ListView;

import com.lecheng.hello.thirdapp.Adapter.AdptGetNum;
import com.lecheng.hello.thirdapp.Bean.PhoneInfo;
import com.lecheng.hello.thirdapp.R;

public class Aty035Contact extends Activity {
    private ListView lv;
    private AdptGetNum adapter;
    private List<PhoneInfo> lists = new ArrayList<PhoneInfo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity035);
        getNumber(this);
        lv = (ListView) findViewById(R.id.lv);
        adapter = new AdptGetNum(lists, this);
        lv.setAdapter(adapter);
    }

    private String getNumber(Context context) {
        Cursor c = context.getContentResolver().query(Phone.CONTENT_URI, null, null, null, null);
        String phoneNumber;
        String phoneName;
        while (c.moveToNext()) {
            phoneNumber = c.getString(c.getColumnIndex(Phone.NUMBER));
            phoneName = c.getString(c.getColumnIndex(Phone.DISPLAY_NAME));
            PhoneInfo phoneInfo = new PhoneInfo(phoneName, phoneNumber);
            lists.add(phoneInfo);
            System.out.println(phoneName + phoneNumber);
        }
        return null;
    }
}
