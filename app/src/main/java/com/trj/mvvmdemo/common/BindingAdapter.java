package com.trj.mvvmdemo.common;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.trj.mvvmdemo.BR;
import com.trj.mvvmdemo.R;
import com.trj.mvvmdemo.databinding.ItemGankIoBinding;
import com.trj.mvvmdemo.model.GankioData;

import java.util.ArrayList;
import java.util.List;

/**
 * @author TRJ
 * @date 2017/11/17
 * Description:
 */

public class BindingAdapter extends RecyclerView.Adapter<BindingAdapter.GankHolder> {

    List<GankioData.ResultsBean> mData;

    public BindingAdapter(List<GankioData.ResultsBean> data) {
        mData = data;
        if (data == null) {
            mData = new ArrayList<>();
        }
    }

    @Override
    public GankHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ItemGankIoBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_gank_io, parent, false);
        return new GankHolder(binding);
    }

    @Override
    public void onBindViewHolder(GankHolder holder, int position) {
        GankioData.ResultsBean bean = mData.get(position);
        holder.bindData(bean);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public void replace(List<GankioData.ResultsBean> data) {
        mData = data;
        notifyDataSetChanged();
    }


    public class GankHolder extends RecyclerView.ViewHolder {

        ItemGankIoBinding viewDataBinding;

        public GankHolder(ItemGankIoBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.viewDataBinding = viewDataBinding;
        }

        public void bindData(GankioData.ResultsBean bean){
//            viewDataBinding.setData(bean);
            viewDataBinding.setVariable(BR.data,bean);
        }
    }
}
