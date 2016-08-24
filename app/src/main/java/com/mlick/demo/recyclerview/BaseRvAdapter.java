package com.mlick.demo.recyclerview;

import android.support.v4.view.ViewCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by lxx on 2016/8/15 15:10
 */
public abstract class BaseRvAdapter extends RecyclerView.Adapter<BaseRvHv> {


    protected ArrayList<?> baseData;

    private int layoutId;

    public ArrayList<?> getBaseData() {
        return baseData;
    }

    public void setBaseData(ArrayList<?> baseData) {
        this.baseData = baseData;
    }

    public BaseRvAdapter(int layoutId) {
        this.layoutId = layoutId;
    }

    @Override
    public BaseRvHv onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutId == 0) {
            Log.e("BaseRvAdapter", "layout id is zero!!!");
            return null;
        }
        return new BaseRvHv(LayoutInflater.from(parent.getContext())
                                          .inflate(layoutId, parent, false));
    }

    @Override
    public void onBindViewHolder(BaseRvHv holder, final int position) {
        if (!ViewCompat.hasOnClickListeners(holder.itemView)) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return baseData != null ? baseData.size() : 0;
    }


}
