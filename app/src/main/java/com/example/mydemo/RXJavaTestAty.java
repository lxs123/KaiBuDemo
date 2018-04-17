package com.example.mydemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class RXJavaTestAty extends Activity {
    private final String TAG = "<Text>";

    private TextView tvHello;
    private Button btn;
    private Subscriber<String> subscriber;
    private Observable<String> observable;
    private Action1<String> onNextAction1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.aty_rxjava_test);
        tvHello = (TextView) findViewById(R.id.hello);
        btn = (Button) findViewById(R.id.test1);
        createSubscriber();
        createSubscriberByAction();
    }

    public void testOnClick(View view) {
        createObservableByJust();
//        createObservableByMap();
    }

    private void createSubscriber() {
        subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
                Log.d(TAG, "onCompleted");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String s) {
                Log.d(TAG, "onNext");
                tvHello.setText(s);
            }
        };
    }

    private void createObservable() {
        observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                Log.d(TAG, "onCall");
                subscriber.onNext("test success");
                subscriber.onCompleted();
            }
        });
        observable.subscribe(subscriber);
    }

    private void createObservableByJust() {
        Log.d(TAG, "onJust");
        observable = Observable.just("on just text");
        observable.subscribe(onNextAction1);
    }

    private void createSubscriberByAction() {
        onNextAction1 = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d(TAG, "onAction1");
                tvHello.setText(s);
            }
        };
    }

    private void createObservableByMap(){
        Observable.just("onMap").map(new Func1<String, String>() {
            @Override
            public String call(String s) {
                return s+"-test";
            }
        }).subscribe(onNextAction1);
    }
}
