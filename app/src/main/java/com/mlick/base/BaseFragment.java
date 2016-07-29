package com.mlick.base;

import com.lite.library.base.BaseDaoFragment;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lxx on 2016/5/19 15:48
 */
public abstract class BaseFragment extends BaseDaoFragment {

    private Unbinder unbinder;

    @Override
    public void viewInject() {
        unbinder = ButterKnife.bind(this, baseView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null) unbinder.unbind();
    }

}
