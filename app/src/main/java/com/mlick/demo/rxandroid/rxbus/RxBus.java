package com.mlick.demo.rxandroid.rxbus;

import rx.Observable;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by lxx on 2016/8/23 14:21
 * 利用RxJava实现类似于EventBus、otto的效果
 */
public class RxBus {

    private final Subject<Object, Object> _bus = new SerializedSubject<>(PublishSubject.create());

    private static RxBus rxbus;

    public static RxBus getInstance() {
        if (rxbus == null) {
            synchronized (RxBus.class) {
                if (rxbus == null) {
                    rxbus = new RxBus();
                }
            }
        }
        return rxbus;
    }

    public void send(Object o) {
        _bus.onNext(o);
    }

    public Observable<Object> toObserverable() {
        return _bus;
    }

}
