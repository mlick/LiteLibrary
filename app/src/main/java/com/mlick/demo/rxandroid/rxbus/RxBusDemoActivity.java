package com.mlick.demo.rxandroid.rxbus;

import com.mlick.base.BaseActivity;
import com.mlick.demo.R;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Subscription;

/**
 * Created by lxx on 2016/8/23 14:24
 */
public class RxBusDemoActivity extends BaseActivity {
    @Override
    public int getLayoutId() {
        return R.layout.activity_rxbus_demo;
    }

    Subscription firstSubs;

    @Override
    public void initViewData() {
        Observable<Long> cold = Observable.interval(1, TimeUnit.SECONDS);
        firstSubs = cold.subscribe(new Observer<Long>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Long aLong) {
                System.out.println("aLong: " + aLong);
                if (aLong == 10 && !firstSubs.isUnsubscribed()) {
                    firstSubs.unsubscribe();
                }
            }
        });
    }
}
