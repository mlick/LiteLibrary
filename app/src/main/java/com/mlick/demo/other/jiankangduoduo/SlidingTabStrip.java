package com.mlick.demo.other.jiankangduoduo;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mlick.demo.R;

import java.util.List;

/**
 * Created by lxx on 2016/6/14 16:42
 */
public class SlidingTabStrip extends HorizontalScrollView {

    private int tabCount;

    private LinearLayout tabsContainer;
    private int tabPadding = 24;
    private boolean shouldExpand = true;//是否自动适配

    private LinearLayout.LayoutParams defaultTabLayoutParams;
    private LinearLayout.LayoutParams expandedTabLayoutParams;

    private boolean isAdd = true;

    public SlidingTabStrip(Context context) {
        this(context, null);
    }

    public SlidingTabStrip(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlidingTabStrip(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        setFillViewport(true);
        setWillNotDraw(false);

        tabsContainer = new LinearLayout(context);
        tabsContainer.setOrientation(LinearLayout.HORIZONTAL);
        tabsContainer.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
        addView(tabsContainer);

        defaultTabLayoutParams = new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        expandedTabLayoutParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f);
    }

    public void setViews(List<PersonTabBean> titles, boolean isAdd) {
        this.isAdd = isAdd;
        if (titles != null && titles.size() > 0) {
            tabCount = titles.size();
            tabsContainer.removeAllViews();

            for (int i = 0; i < tabCount; i++) {
                PersonTabBean bean = titles.get(i);
                View tab = LayoutInflater.from(getContext()).inflate(R.layout.item_select_person, null);
                TextView tabTitle = (TextView) tab.findViewById(R.id.item_select_title);
                TextView tabContent = (TextView) tab.findViewById(R.id.item_select_content);
                tabTitle.setText(bean.getTitle());
                tabContent.setText(bean.getName());
                addTab(i, tab);
            }
        }
    }

    public void setSmallViews(List<PersonTabBean> titles, boolean isAdd) {
        this.isAdd = isAdd;
        if (titles != null && titles.size() > 0) {
            tabCount = titles.size();
            tabsContainer.removeAllViews();

            for (int i = 0; i < tabCount; i++) {
                PersonTabBean bean = titles.get(i);
                View tab = LayoutInflater.from(getContext()).inflate(R.layout.item_select_person_small, null);
                TextView tabTitle = (TextView) tab.findViewById(R.id.item_select_title);
                TextView tabContent = (TextView) tab.findViewById(R.id.item_select_content);
                tabTitle.setText(bean.getTitle());
                tabContent.setText(bean.getName());
                addTab(i, tab);
            }
        }
    }

    private void addTab(final int position, View tab) {
        tab.setFocusable(true);
        tab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    if (isAdd && position == tabCount - 1) {
                        listener.onAddTabStrip();
                    } else {
                        listener.onClickTabStrip(position);
                    }
                }
            }
        });

        tab.setPadding(tabPadding, 0, tabPadding, 0);
        tabsContainer.addView(tab, position, shouldExpand ? expandedTabLayoutParams : defaultTabLayoutParams);
    }

    public void updateTabStyles(int postion) {

        for (int i = 0; i < tabCount; i++) {
            View v = tabsContainer.getChildAt(i);
            TextView titleTv = (TextView) v.findViewById(R.id.item_select_title);
            if (titleTv != null) {
                if (i == postion) {
                    titleTv.setBackgroundResource(R.drawable.shap_circle_small_select_bg);
                    titleTv.setTextColor(Color.parseColor("#00E381"));
                } else {
                    titleTv.setBackgroundResource(R.drawable.shap_circle_small_bg);
                    titleTv.setTextColor(Color.parseColor("#FFFFFF"));
                }
            }
        }
    }

    private SlidingTabStripListener listener;

    public interface SlidingTabStripListener {
        void onAddTabStrip();

        void onClickTabStrip(int postion);
    }

    public void setListener(SlidingTabStripListener listener) {
        this.listener = listener;
    }
}
