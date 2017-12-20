package com.trj.mvvmdemo.common;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.trj.mvvmdemo.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TRJ on 2017/3/8.
 * Description: viewpager Fragment通用的适配器
 */

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {

    private List<BaseFragment> fragments = new ArrayList<>();

    List<String> titles = new ArrayList<>();

    public ViewPagerFragmentAdapter(FragmentManager fm, List<BaseFragment> fragments,
                                    List<String> titles) {
        super(fm);
        if (fragments != null) {
            this.fragments.addAll(fragments);
        }
        if (titles != null) {
            this.titles.addAll(titles);
        }
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if (titles != null && position < titles.size()) {
            return titles.get(position);
        } else {
            return "";
        }
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
        notifyDataSetChanged();
    }
}
