package com.trj.mvvmdemo.binding.viewadapter.swiperefresh;

import android.databinding.BindingAdapter;
import android.support.v4.widget.SwipeRefreshLayout;

import com.trj.mvvmdemo.binding.command.BindingCommand;


public class ViewAdapter {
    @BindingAdapter({"onRefreshCommand"})
    public static void onRefreshCommand(SwipeRefreshLayout swipeRefreshLayout, final BindingCommand onRefreshCommand) {
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (onRefreshCommand != null) {
                    onRefreshCommand.execute();
                }
            }
        });
    }

}
