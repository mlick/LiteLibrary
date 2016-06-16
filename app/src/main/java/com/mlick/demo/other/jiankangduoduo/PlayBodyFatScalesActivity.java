package com.mlick.demo.other.jiankangduoduo;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.lite.library.utils.MyCustomDialog;
import com.lite.library.utils.StrUtils;
import com.mlick.base.BaseActivity;
import com.mlick.demo.R;

import java.util.ArrayList;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by lxx on 2016/6/14 14:48
 */
public class PlayBodyFatScalesActivity extends BaseActivity implements SlidingTabStrip.SlidingTabStripListener {

    @BindView(R.id.sliding_tab_strip) SlidingTabStrip slidingTabStrip;

    private String[] strings = {"爸爸", "妈妈", "爷爷", "奶奶", "自己", "+"};
    private ArrayList<PersonTabBean> list;

    @Override
    public int getLayoutId() {
        return R.layout.activity_play_body_fat_scales;
    }

    @Override
    public void initViewData() {
        list = new ArrayList<>();
        setWindowTitle(Color.parseColor("#00E381"));
        Observable.from(strings)
                .map(new Func1<String, PersonTabBean>() {
                    @Override
                    public PersonTabBean call(String s) {
                        return new PersonTabBean(s);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<PersonTabBean>() {
                    @Override
                    public void onCompleted() {
                        slidingTabStrip.setViews(list, true);
                        slidingTabStrip.setListener(PlayBodyFatScalesActivity.this);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(PersonTabBean personTabBean) {
                        list.add(personTabBean);
                    }
                });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onAddTabStrip() {
        final EditText editText = new EditText(this);
        MyCustomDialog.showEditDialog(this, "添加家人", editText, "确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String res = editText.getText().toString();
                if (StrUtils.isNotEmpty(res) && !"+".equals(res)) {
                    list.add(list.size() == 1 ? 0 : list.size() - 1, new PersonTabBean(res));
                    slidingTabStrip.setViews(list, true);
                }
            }
        });
    }

    @Override
    public void onClickTabStrip(int postion) {
        Toast.makeText(this, list.get(postion).getName(), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, PersonFatDetailActivity.class);
        intent.putExtra("item", postion);
        startActivity(intent);
    }
}