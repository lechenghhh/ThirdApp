package com.lecheng.hello.thirdapp.ActivityList.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.lecheng.hello.thirdapp.Bean.RxBusMsg.EventMsg;
import com.lecheng.hello.thirdapp.Utils.Interface.RxBus;
import com.lecheng.hello.thirdapp.R;

import butterknife.Bind;
import butterknife.ButterKnife;

public class Frag060RxBus extends Fragment {
    @Bind(R.id.etSend)
    EditText etSend;
    @Bind(R.id.btn5)
    Button btn5;
    @Bind(R.id.btn6)
    Button btn6;
    @Bind(R.id.ll1)
    LinearLayout ll1;
    @Bind(R.id.ll2)
    LinearLayout ll2;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.aty060, null, false);
        ButterKnife.bind(this, root);
        ll2.setVisibility(View.VISIBLE);
        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager().popBackStack();
            }
        });
        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EventMsg eventMsg = new EventMsg();
                eventMsg.setMsg(etSend.getText().toString());
                RxBus.getInstance().post(eventMsg);
            }
        });
        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().sendBroadcast(new Intent("BroadcastReceiverAction")
                        .putExtra("BroadcastReceiverKey", etSend.getText().toString()));
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
