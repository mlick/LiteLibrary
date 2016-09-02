package com.lite.library.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


/**
 * Created by lxx on 2016/5/3 16:47
 * Activity基类 封装基本的操作
 */
public abstract class BaseDaoActivity extends AppCompatActivity implements View.OnClickListener {

    private Toast toast;
    protected Context baseCtx;//基类中通用的context

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
            viewInject();
        }
        baseCtx = this;
        initViewData();
    }

    protected abstract void viewInject();//框架注入,根据不同框架进行相应的注入比如XUtils,butterKnife等

    public abstract int getLayoutId();

    public abstract void initViewData();

    protected void finishActivity() {
        finish();
    }

    protected void showToast(String s) {
        if (toast != null) {// 防止多个重复显示
            toast.cancel();
        }
        toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void setFragment(Fragment home, int activity_content) {
        getSupportFragmentManager().beginTransaction().add(activity_content, home)
                                   .commitAllowingStateLoss();
    }

    /**
     * fragment 切换
     *
     * @param from 当前的Fragment
     * @param to   跳转的下个Framgent
     */
    protected Fragment switchContent(Fragment from, Fragment to, int activity_content) {
        Fragment mContent = from;
        if (to == null || from == null) {
            return mContent;
        }
        if (from != to) {
            mContent = to;
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                transaction.hide(from).add(activity_content, to)
                           .commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
        return mContent;
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
