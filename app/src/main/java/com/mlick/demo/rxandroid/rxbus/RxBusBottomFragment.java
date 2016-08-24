package com.mlick.demo.rxandroid.rxbus;

import android.view.View;
import android.widget.TextView;

import com.mlick.base.BaseFragment;
import com.mlick.demo.R;

import butterknife.BindView;
import rx.functions.Action1;

/**
 * Created by lxx on 2016/8/23 14:26
 */
public class RxBusBottomFragment extends BaseFragment {

    @BindView(R.id.result_tv) TextView textView;

    @Override
    public void initViewData() {
        RxBus.getInstance().toObserverable().subscribe(new Action1<Object>() {
            @Override
            public void call(Object o) {
                if (o instanceof String) {
                    textView.setText((String) o);
                } else if (o instanceof Integer) {
                    textView.setText("");
                }
            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_rxbus_bottom;
    }

    @Override
    public void onClick(View view) {

    }
}
