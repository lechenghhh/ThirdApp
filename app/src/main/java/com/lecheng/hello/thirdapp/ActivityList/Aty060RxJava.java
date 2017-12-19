package com.lecheng.hello.thirdapp.ActivityList;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.lecheng.hello.thirdapp.R;
import com.lecheng.hello.thirdapp.Utils.MyToast;

import org.reactivestreams.Subscriber;

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
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class Aty060RxJava extends AppCompatActivity {

    @Bind(R.id.tvScreen)
    TextView tvScreen;
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty060);
        ButterKnife.bind(this);
//        myObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(mySubscriber);
    }

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

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3})
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
        }
    }
}
