package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Aty048WebService extends AppCompatActivity {

    @Bind(R.id.etPhone)
    EditText etPhone;
    @Bind(R.id.etCode)
    EditText etCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty048_webservice);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnSend, R.id.btnCheck})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnSend:
//                new AsyncTask<Void, Void, SoapObject>() {
//                    @Override
//                    protected SoapObject doInBackground(Void... params) {
//                        try {
//                            return null;
////                            return WebService.method("sendSMS", new String[]{"18606940624"});
//                        } catch (Exception e) {
//                            new MyToast(Aty048WebService.this, "网络出错了" + e, 3333);
//                            return null;
//                        }
//                    }
//
//                    @Override
//                    protected void onPostExecute(SoapObject soapObject) {
//                        super.onPostExecute(soapObject);
//                        if (soapObject == null) {
//                            new MyToast(Aty048WebService.this, "网络出错了", 3333);
////                            error();
//                        } else {
//                            new MyToast(getApplicationContext(), soapObject + "", 3333);
////                            if (!soapObject.toString().equals("loginResponse{loginReturn=用户名不存在！; }")
////                                    & !soapObject.toString().equals("loginResponse{loginReturn=密码错误！; }")) {
////                                Utils.putValue(LoginActivity.this, USERNAME, name);
////                                Utils.putValue(LoginActivity.this, PASSWORD, pass);
////                                Utils.putValue(LoginActivity.this, REMEMBER, login_remember.isChecked());
////                                object = soapObject;
////                                int len = object.getPropertyCount();
////                                //   rolecode=subString2(object.toString());
////                                for (int i = 0; i < len; i++) {
////                                    SoapObject child = (SoapObject) object.getProperty(i);
////                                    rolecode = subString(child.getPropertyAsString(0));
////                                    id = subString(child.getPropertyAsString(2));
////                                    versionCode = subString(child.getPropertyAsString(1));
////                                    userName = subString(child.getPropertyAsString(3));
////                                }
////                                Utils.putValue(LoginActivity.this, "rolecode", Utils.giveRoleCode(rolecode));
////                                Utils.putValue(LoginActivity.this, "rolecode3", rolecode);
////
////                           /* Utils.putValue(LoginActivity.this, "roleCode1", rolecode.substring(0,1));
////                            Utils.putValue(LoginActivity.this, "roleCode2", rolecode.substring(1,2));
////                            Utils.putValue(LoginActivity.this, "roleCode3", rolecode.substring(2,3));*/
////                                animalIntent();
////                            } else {
////                                Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
////                                error();
////                            }
//                        }
//                    }
//                }.execute();
                break;
            case R.id.btnCheck:
                break;
        }
    }
}
