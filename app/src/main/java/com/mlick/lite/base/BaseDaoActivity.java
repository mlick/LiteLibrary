package com.mlick.lite.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.ButterKnife;


/**
 * Created by lxx on 2016/5/3 16:47
 * Activity基类 封装基本的操作
 */
public abstract class BaseDaoActivity extends FragmentActivity implements View.OnClickListener {

    private Toast toast;
    protected Context baseCtx;//基类中通用的context

    //    private Unbinder unbinder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        baseCtx = this;
        initViewData();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {//添加window的标题为统一颜色,
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(android.R.color.transparent);//通知栏所需颜色
        }
    }

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
//    protected abstract void ViewUtilsInject();

    public abstract int getLayoutId();

    public abstract void initViewData();

    protected void finishActivity() {
        finish();
    }

    protected void showToast(String s) {
        if (toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        toast.show();
    }

    protected void setFragment(Fragment home, int activity_content) {
        getSupportFragmentManager().beginTransaction().add(activity_content, home).commitAllowingStateLoss();
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
                transaction.hide(from).add(activity_content, to).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
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
