package com.mlick.demo;

import org.junit.Test;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.schedulers.Schedulers;

/**
 * Created by lxx on 2016/8/4 18:34
 */
public class RxUnitTest {

    @Test
    public void testInterval() {

        System.out.println("start");
//        Observable.interval(0, 5, TimeUnit.SECONDS).subscribe(new Action1<Long>() {
//            @Override
//            public void call(Long aLong) {
//                System.out.println("------>along：" + aLong + " time:" + SystemClock
//                        .elapsedRealtime());
//            }
//        });

        Observable.interval(0, 3, TimeUnit.SECONDS).subscribeOn(Schedulers.io())
                  .subscribe(new Observer<Long>() {
                      @Override
                      public void onCompleted() {
                          System.out.println("onCompleted");
                      }

                      @Override
                      public void onError(Throwable e) {
                          System.out.println("onCompleted");
                      }

                      @Override
                      public void onNext(Long aLong) {
                          System.out.println("aLong: " + aLong);
                      }
                  });
    }
}
