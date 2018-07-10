package com.lecheng.hello.thirdapp.ActivityList;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.lecheng.hello.thirdapp.ActivityList.fragment.Frag068DataBinding;
import com.lecheng.hello.thirdapp.Bean.Bean068;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.FragmentUtil;
import com.lecheng.hello.thirdapp.Widgets.MyToast;
import com.lecheng.hello.thirdapp.databinding.Aty068DatabindingBinding;

public class Aty068DataBinding extends AppCompatActivity {
    private Bean068 user = new Bean068();
    private Aty068DatabindingBinding atyBind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.atyBind = DataBindingUtil.setContentView(this, R.layout.aty068_databinding);

        user.setAge(29);
        user.setName("乐城");
        user.setSex("男");
        user.setAddress("鼓楼区西二环");
        user.setHeight("20dp");
        atyBind.setUser(user);
        atyBind.btn1.setOnClickListener(view -> {
            new MyToast(Aty068DataBinding.this, "年龄加1", 1);
            user.setAge(user.getAge() + 1);
            atyBind.setUser(user);
        });
        atyBind.btn2.setOnClickListener(view -> {
            new MyToast(Aty068DataBinding.this, "年龄：" + user.getAge(), 1);
        });
        atyBind.btn3.setOnClickListener(view -> {
            FragmentUtil.startFragment(this, new Frag068DataBinding());
        });
    }

//    @OnClick({R.id.btn1, R.id.btn2})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.btn1:
//                new MyToast(this, "年龄加1", 1);
//                user.setAge(user.getAge() + 1);
//                atyBind.setUser(user);
//                break;
//            case R.id.btn2:
//                new MyToast(this, "2222", 1);
//                break;
//        }
//    }
}
