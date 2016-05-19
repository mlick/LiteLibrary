package com.lite.library.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by lxx on 2016/5/3 16:25
 */
public abstract class BaseDaoFragment extends Fragment implements View.OnClickListener {

    protected Toast toast;
    protected View baseView = null;
    protected LayoutInflater baseInflater;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (baseView == null) {
            baseView = inflater.inflate(getLayoutId(), container, false);
        }
        this.baseInflater = inflater;
        viewInject();
        return baseView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initViewData();
    }

    public abstract void initViewData();

    public abstract void viewInject();

    public abstract int getLayoutId();

    protected void showToast(String s) {
        if (getActivity() == null) {
            return;
        }
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT);
        toast.show();
    }

}
