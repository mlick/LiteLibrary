package com.mlick.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.lite.library.base.BaseDaoActivity;
import com.mlick.demo.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.umeng.analytics.MobclickAgent;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by lxx on 2016/5/19 15:46
 */
public abstract class BaseActivity extends BaseDaoActivity {

    private Unbinder unbinder;

    @Override
    protected void viewInject() {
        unbinder = ButterKnife.bind(this);
//        setWindowTitle(setwindowTitleColor());
    }

    protected int setwindowTitleColor() {//可以重载，自定义windowtitle的背景颜色
        return R.color.colorPrimary;
    }

    protected void setWindowTitle(int color) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//添加window的标题为统一颜色,
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintColor(color);//通知栏所需颜色
        }
    }
//    private void setWindowTitle() {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//添加window的标题为统一颜色,
//            setTranslucentStatus(true);
//            SystemBarTintManager tintManager = new SystemBarTintManager(this);
//            tintManager.setStatusBarTintEnabled(true);
//            tintManager.setStatusBarTintResource(android.R.color.transparent);//通知栏所需颜色
//        }
//    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    //可以重载实现该方式
    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 页面起始（每个Activity中都需要添加，如果有继承的父Activity中已经添加了该调用，那么子Activity中务必不能添加）
         * 不能与StatService.onPageStart一级onPageEnd函数交叉使用
         */
//        StatService.onResume(this);
        // 友盟统计
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        StatService.onPause(this);
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) unbinder.unbind();
    }
}