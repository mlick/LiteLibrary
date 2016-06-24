package com.mlick.demo.other.jiankangduoduo;

import android.graphics.Color;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.FileUtils;
import com.mlick.base.BaseActivity;
import com.mlick.demo.R;

import java.util.ArrayList;

import butterknife.BindView;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by lxx on 2016/6/15 9:42
 * 体脂秤的个人数据页面
 */
public class PersonFatDetailActivity extends BaseActivity implements SlidingTabStrip.SlidingTabStripListener {
    @BindView(R.id.person_fat_tab_strip) SlidingTabStrip slidingTabStrip;
    @BindView(R.id.chart) LineChart lineChart;
    private String[] strings = {"爸爸", "妈妈", "爷爷", "奶奶", "自己"};
    private ArrayList<PersonTabBean> list;// = Arrays.asList(new PersonTabBean("爸爸"));

    @Override
    public int getLayoutId() {
        return R.layout.activity_person_fat_detail;
    }

    @Override
    public void initViewData() {
        list = new ArrayList<>();
        setWindowTitle(Color.parseColor("#00E381"));
        Observable.from(strings).map(new Func1<String, PersonTabBean>() {
            @Override
            public PersonTabBean call(String s) {
                return new PersonTabBean(s);
            }
        }).subscribe(new Action1<PersonTabBean>() {
            @Override
            public void call(PersonTabBean personTabBean) {
                list.add(personTabBean);
            }
        });
        slidingTabStrip.setSmallViews(list, false);
        slidingTabStrip.setListener(this);

        ArrayList<ILineDataSet> sets = new ArrayList<ILineDataSet>();
        LineDataSet ds1 = new LineDataSet(FileUtils
                .loadEntriesFromAssets(getAssets(), "square.txt"), "");
        ds1.setColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        ds1.setCircleColor(ColorTemplate.VORDIPLOM_COLORS[0]);
        ds1.setLineWidth(2.5f);
        sets.add(ds1);
        LineData data = new LineData(ChartData.generateXVals(0, ds1.getEntryCount()), sets);
//        d.setValueTypeface(tf);
        lineChart.setDescription("");
        lineChart.setDrawGridBackground(false);
        lineChart.setData(data);
        YAxis leftAxis = lineChart.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinValue(0f); // this replaces setStartAtZero(true)
        leftAxis.setEnabled(false);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getLegend().setEnabled(false);
        XAxis xAxis = lineChart.getXAxis();
        xAxis.setEnabled(false);
        xAxis.setDrawGridLines(false);

        int postion = getIntent().getIntExtra("item", 0);
        slidingTabStrip.updateTabStyles(postion);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onAddTabStrip() {

    }

    @Override
    public void onClickTabStrip(int postion) {
        slidingTabStrip.updateTabStyles(postion);
    }
}
