package com.trj.mvvmdemo.ui.fragment;

import com.trj.mvvmdemo.R;
import com.trj.mvvmdemo.api.ApiService;
import com.trj.mvvmdemo.ui.base.BaseFragment;
import com.trj.mvvmdemo.ui.base.BaseFragmentViewModule;
import com.trj.mvvmdemo.ui.vm.IndexViewModule;

import javax.inject.Inject;

/**
 * @author TRJ
 * @date 2017/12/20
 * Description:
 */
public class IndexFragment extends BaseFragment {

    @Inject
    IndexViewModule indexViewModule;

    @Inject
    ApiService mA;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_index;
    }

    @Override
    protected BaseFragmentViewModule getViewModule() {
        return indexViewModule;
    }

    @Override
    protected void init() {

    }

    public static IndexFragment newInstance(){
        IndexFragment fragment = new IndexFragment();
        return fragment;
    }
}
