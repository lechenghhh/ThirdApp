package com.lecheng.hello.thirdapp.ActivityList.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.lecheng.hello.thirdapp.ActivityList.Aty014Fragment_Navi;
import com.lecheng.hello.thirdapp.ActivityList.Aty014Fragment_Tabed;
import com.lecheng.hello.thirdapp.Bean.EventBusMsg.MsgFragment14;
import com.lecheng.hello.thirdapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.greenrobot.event.EventBus;

/**
 * A placeholder fragment containing a simple view.
 */
public class Fragment1 extends Fragment {


    @Bind(R.id.btnSend)
    Button btnSend;

    public Fragment1() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View fragment2 = inflater.inflate(R.layout.fragment014, container, false);

        fragment2.findViewById(R.id.btn2anothersFAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().addToBackStack(null)
                        .add(R.id.fragment, new FragmentAnothers()).commit();
            }
        });

        fragment2.findViewById(R.id.btn2navigationAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Aty014Fragment_Navi.class));

            }
        });
        fragment2.findViewById(R.id.btn2tabAty).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), Aty014Fragment_Tabed.class));

            }
        });

        ButterKnife.bind(this, fragment2);
        return fragment2;

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btnSend)
    public void onClick() {
        EventBus.getDefault().post(new MsgFragment14("hello"));
        btnSend.setText("已发送");
    }

    public void onEvent(String str) {
        btnSend.setText("已收到");
    }
}
