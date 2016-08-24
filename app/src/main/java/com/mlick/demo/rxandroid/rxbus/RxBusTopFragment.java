package com.mlick.demo.rxandroid.rxbus;

import android.os.Handler;
import android.view.View;

import com.mlick.base.BaseFragment;
import com.mlick.demo.R;

import butterknife.OnClick;

/**
 * Created by lxx on 2016/8/23 14:26
 */
public class RxBusTopFragment extends BaseFragment {

    RxBus rxBus;

    @Override
    public void initViewData() {
        rxBus = RxBus.getInstance();
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_rxbus_top;
    }

    @OnClick(R.id.onclick)
    public void onClick(View view) {
        rxBus.send("you onclick it!");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rxBus.send(1);
            }
        }, 500);
//        Observable.timer(1, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
//                  .subscribe(new Observer<Long>() {
//                      @Override
//                      public void onCompleted() {
//
//                      }
//
//                      @Override
//                      public void onError(Throwable e) {
//
//                      }
//
//                      @Override
//                      public void onNext(Long aLong) {
//                          rxBus.send(1);
//                      }
//                  });
    }
}
