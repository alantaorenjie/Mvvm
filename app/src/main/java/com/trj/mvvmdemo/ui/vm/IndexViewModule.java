package com.trj.mvvmdemo.ui.vm;

import com.trj.mvvmdemo.ui.base.BaseFragmentViewModule;
import com.trj.mvvmdemo.ui.fragment.IndexFragment;

import javax.inject.Inject;

/**
 * @author TRJ
 * @date 2017/12/20
 * Description:
 */
public class IndexViewModule extends BaseFragmentViewModule {

    @Inject
    public IndexViewModule(IndexFragment fragment) {
        super(fragment.getActivity());
    }
}
