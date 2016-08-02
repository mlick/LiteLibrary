package com.mlick.demo.recyclerview;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lite.library.utils.StrUtils;
import com.mlick.base.BaseActivity;
import com.mlick.demo.R;

import butterknife.BindView;
import jp.wasabeef.recyclerview.adapters.SlideInBottomAnimationAdapter;

/**
 * Created by lxx on 2016/7/30 11:55
 */
public class ListDemoActivity extends BaseActivity {


    public String[] DATAS = new String[100];
    @BindView(R.id.recyclerview) RecyclerView recyclerView;

    @Override
    public int getLayoutId() {
        return R.layout.activity_recyclerview_list;
    }

    @Override
    public void initViewData() {
        for (int i = 0; i < 100; i++) {
            DATAS[i] = i + "";
        }
        // 设置适配器
        MyAdapter myAdapter = new MyAdapter();
//        recyclerView.setAdapter(myAdapter);
//        recyclerView.setAdapter(new AlphaInAnimationAdapter(new MyAdapter()));

        // 混合操作
//        ScaleInAnimationAdapter scaleAdapter = new ScaleInAnimationAdapter(new AlphaInAnimationAdapter(new MyAdapter()));
//        scaleAdapter.setFirstOnly(false);
//        recyclerView.setAdapter(scaleAdapter);

        SlideInBottomAnimationAdapter slideAdapter = new SlideInBottomAnimationAdapter(myAdapter);
        slideAdapter.setFirstOnly(false);
        recyclerView.setAdapter(slideAdapter);

//      设置布局管理器
//      listview风格则设置为LinearLayoutManager
//      gridview风格则设置为GridLayoutManager
//      瀑布流风格的设置为StaggeredGridLayoutManager
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        recyclerView.setLayoutManager(new GridLayoutManager(this,3));
        recyclerView
                .setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        // 设置分割线
//      recyclerView.addItemDecoration(new DividerItemDecoration(this));
        // 设置item动画
        recyclerView.setItemAnimator(new DefaultItemAnimator());

//        recyclerView.setItemAnimator(new SlideInDownAnimator(new OvershootInterpolator(1f)));
    }

    class MyAdapter extends RecyclerView.Adapter<HoldView> {

        @Override
        public HoldView onCreateViewHolder(ViewGroup parent, int viewType) {
            return new HoldView(LayoutInflater.from(parent.getContext())
                                              .inflate(R.layout.item_recyclerview_list, parent, false));
        }

        @Override
        public void onBindViewHolder(final HoldView holder, int position) {
            StrUtils.setTvStr(holder.tv, DATAS[position]);
            if (!ViewCompat.hasOnClickListeners(holder.itemView)) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int pos1 = holder.getAdapterPosition();
                        int pos2 = holder.getLayoutPosition();
                        showToast("AdpterPostition: " + pos1 + "  LayoutPosition:  " + pos2);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            return DATAS.length;
        }
    }


    class HoldView extends RecyclerView.ViewHolder {

        //        @BindView() public ImageView iv;
//        @BindView(R.id.rc_list_tv)
        public TextView tv;

        public HoldView(View itemView) {
            super(itemView);
//            ButterKnife.bind(itemView);
            tv = (TextView) itemView.findViewById(R.id.rc_list_tv);
        }
    }
}
