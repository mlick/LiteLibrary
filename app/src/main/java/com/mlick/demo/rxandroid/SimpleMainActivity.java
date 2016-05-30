package com.mlick.demo.rxandroid;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mlick.base.BaseActivity;
import com.mlick.demo.R;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by lxx on 2016/5/23 13:22
 */
public class SimpleMainActivity extends BaseActivity {

    // 观察事件发生
    Observable.OnSubscribe<String> mObservableAction = new Observable.OnSubscribe<String>() {
        @Override
        public void call(Subscriber<? super String> subscriber) {
            subscriber.onNext("say Hellow"); // 发送事件
            subscriber.onCompleted(); // 完成事件
            subscriber.onError(new Throwable("test error"));// 和onCompleted事件只是执行一个
        }
    };

    // 订阅者, 接收字符串, 修改控件
    Subscriber<String> mTextSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(String s) {
            helloTv.setText(s); // 设置文字
        }
    };

    // 订阅者, 接收字符串, 提示信息
    Subscriber<String> mToastSubscriber = new Subscriber<String>() {
        @Override
        public void onCompleted() {
            Toast.makeText(SimpleMainActivity.this, "onCompleted", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(Throwable e) {
            Toast.makeText(SimpleMainActivity.this, "onError : " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNext(String o) {
            Toast.makeText(SimpleMainActivity.this, o, Toast.LENGTH_SHORT).show();
        }
    };
    @BindView(R.id.hello_tv) TextView helloTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Observable<String> observable = Observable.create(mObservableAction);// 注册观察活动

        // 分发订阅信息
        observable.observeOn(AndroidSchedulers.mainThread());
        observable.subscribe(mTextSubscriber);
        observable.subscribe(mToastSubscriber);
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_demo;
    }

    @Override
    public void initViewData() {

    }

    @Override
    public void onClick(View v) {

    }
}