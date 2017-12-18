package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class Aty060RxJava extends AppCompatActivity {

    @Bind(R.id.tvScreen)
    TextView tvScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty060);
        ButterKnife.bind(this);
//        myObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(mySubscriber);
    }

//    //被观察者
//    Observable<String> sender = Observable.create(new Observable.OnSubscribe<String>() {
//        @Override
//        public void call(Subscriber<? super String> subscriber) {
//            subscriber.onNext("Hi，Weavey!");  //发送数据"Hi，Weavey!"
//        }
//    });
//
//    //观察者
//    Observer<String> receiver = new Observer<String>() {
//        @Override
//        public void onCompleted() {//数据接收完成时调用
//        }
//
//        @Override
//        public void onError(Throwable e) {//发生错误调用
//        }
//
//        @Override
//        public void onNext(String s) {//正常接收数据调用
//            new MyToast(Aty060RxJava.this, s, 1);//将接收到来自sender的问候"Hi，Weavey!
//            tvScreen.setText(s);
//        }
//    };

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
//                sender.subscribe(receiver);
                break;
            case R.id.btn2:
//                Observable<String> sender2 = Observable.create(new Observable.OnSubscribe<String>() {
//                    @Override
//                    public void call(Subscriber<? super String> subscriber) {
//                        subscriber.onNext("这是第二个事件");
//                        subscriber.onCompleted();
//                    }
//                });
//                sender2.subscribe(new Observer<String>() {
//                    @Override
//                    public void onCompleted() {
//                    }
//
//                    @Override
//                    public void onError(Throwable throwable) {
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        new MyToast(Aty060RxJava.this, s, 1);
//                        tvScreen.setText(s);
//                    }
//                });
                break;
            case R.id.btn3:
                Observable<String> sender3 = Observable.create(new ObservableOnSubscribe<String>() {
                    @Override
                    public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
                        observableEmitter.onNext("RxJava 2 Test");
                        observableEmitter.onComplete();
                    }
                });
                sender3.subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable disposable) {

                    }

                    @Override
                    public void onNext(String s) {
                        tvScreen.setText("Hello " + s);
                    }

                    @Override
                    public void onError(Throwable throwable) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
                break;
        }
    }
}
