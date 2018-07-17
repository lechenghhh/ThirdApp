package com.lecheng.hello.thirdapp.ActivityList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.ActivityList.fragment.Frag060RxBus;
import com.lecheng.hello.thirdapp.Bean.RxBusMsg.EventMsg;
import com.lecheng.hello.thirdapp.Interface.RxBus;
import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Widgets.MyToast;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Aty060RxJava extends AppCompatActivity {

    @Bind(R.id.tvScreen)
    TextView tvScreen;
    @Bind(R.id.tvScreen2)
    TextView tvScreen2;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty060);
        ButterKnife.bind(this);
//        myObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(mySubscriber);
        //注册监听---RxBus2的使用
        RxBus.getInstance().toObservable().map(new Function<Object, EventMsg>() {
            @Override
            public EventMsg apply(Object o) throws Exception {
                return (EventMsg) o;
            }
        }).subscribe(new Consumer<EventMsg>() {
            @Override
            public void accept(EventMsg eventMsg) throws Exception {
                if (eventMsg != null) {
                    tvScreen2.append(eventMsg.getMsg() + "\n");
                }
            }
        });

        //动态注册广播接收器
        IntentFilter filter = new IntentFilter("BroadcastReceiverAction");
        BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                tvScreen2.append(intent.getStringExtra("BroadcastReceiverKey") + "\n");
            }
        };
        registerReceiver(broadcastReceiver, filter);
    }

    /******************Rxbus未被封装时的使用：******************/
    //被观察者
    Observable<String> sender = Observable.create(new ObservableOnSubscribe<String>() {
        @Override
        public void subscribe(ObservableEmitter<String> observableEmitter) throws Exception {
            observableEmitter.onNext("Hi，Weavey!");  //发送数据"Hi，Weavey!"
            observableEmitter.onComplete();
        }
    });

    //观察者
    Observer<String> receiver = new Observer<String>() {
        @Override
        public void onError(Throwable e) {//发生错误调用
        }

        @Override
        public void onComplete() {
        }

        @Override
        public void onSubscribe(Disposable disposable) {
        }

        @Override
        public void onNext(String s) {//正常接收数据调用
            new MyToast(Aty060RxJava.this, s, 1);//将接收到来自sender的问候"Hi，Weavey!
            tvScreen.setText(s);
        }
    };

    private void startDownload() {
        final Observable<Integer> observable = Observable.create(new ObservableOnSubscribe<Integer>() {

            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                for (int i = 0; i < 100; i++) {
                    if (i % 20 == 0) {
                        try {
                            Thread.sleep(500); //模拟下载的操作。
                        } catch (InterruptedException exception) {
                            if (!e.isDisposed()) {
                                e.onError(exception);
                            }
                        }
                        e.onNext(i);
                    }
                }
                e.onComplete();
            }

        });
        DisposableObserver<Integer> disposableObserver = new DisposableObserver<Integer>() {

            @Override
            public void onNext(Integer value) {
                Log.d("DownloadActivity", "onNext=" + value);
                tvScreen.setText("当前下载进度：" + value);
            }

            @Override
            public void onError(Throwable e) {
                Log.d("DownloadActivity", "onError=" + e);
                tvScreen.setText("下载失败");
            }

            @Override
            public void onComplete() {
                Log.d("DownloadActivity", "onComplete");
                tvScreen.setText("下载成功");
            }
        };
        observable.subscribeOn(Schedulers.single()).observeOn(AndroidSchedulers.mainThread()).subscribe(disposableObserver);
        mCompositeDisposable.add(disposableObserver);
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                sender.subscribe(receiver);
                break;
            case R.id.btn2:
                startDownload();
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
            case R.id.btn4://https://blog.csdn.net/donkor_/article/details/79709366
                getSupportFragmentManager()
                        .beginTransaction()
                        .addToBackStack(null)  //将当前fragment加入到返回栈中
//                        .setCustomAnimations(R.anim.bottom_in, R.anim.bottom_out)//设置动画效果
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)//设置动画效果
                        .replace(android.R.id.content, new Frag060RxBus())
                        .commit();
//                EventMsg eventMsg = new EventMsg();
//                eventMsg.setMsg(etSend.getText().toString());
//                RxBus.getInstance().post(eventMsg);
                break;
        }
    }

    /*RxBus1的使用：*/
//           RxBus.getInstance().post(rxEventSync);//发送消息
//           RxBus.getInstance().toObserverable(RxEventSync.class)//接收消息
//                .subscribe(new Action1<RxEventSync>() {
//                    @Override
//                    public void call(RxEventSync eventMsg) {
//                        if (eventMsg != null) {
//
//                        }
//                    }
//                });
    /*RxBus类：*/
    /**
     * import rx.Observable;
     * import rx.subjects.PublishSubject;
     * import rx.subjects.SerializedSubject;
     * import rx.subjects.Subject;
     */
//    public class RxBus {
//        private static volatile RxBus mInstance;
//        private final Subject bus;
//
//        public RxBus() {
//            bus = new SerializedSubject<>(PublishSubject.create());
//        }
//
//        public static RxBus getInstance() {//单例
//            RxBus rxBus2 = mInstance;
//            if (mInstance == null) {
//                synchronized (RxBus.class) {
//                    rxBus2 = mInstance;
//                    if (mInstance == null) {
//                        rxBus2 = new RxBus();
//                        mInstance = rxBus2;
//                    }
//                }
//            }
//            return rxBus2;
//        }
//
//        //发送消息
//        public void post(Object object) {
//            bus.onNext(object);
//        }
//
//        // 接收消息
//        public <T> Observable<T> toObserverable(Class<T> eventType) {
//            return bus.ofType(eventType);
//        }
//    }
}
