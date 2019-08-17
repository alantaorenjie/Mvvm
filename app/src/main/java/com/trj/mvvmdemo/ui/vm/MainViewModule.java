package com.trj.mvvmdemo.ui.vm;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;
import android.databinding.ViewDataBinding;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.trj.mvvmdemo.BR;
import com.trj.mvvmdemo.R;
import com.trj.mvvmdemo.api.ApiService;
import com.trj.mvvmdemo.binding.command.BindingCommand;
import com.trj.mvvmdemo.common.LoggingRecyclerViewAdapter;
import com.trj.mvvmdemo.common.subscriber.DefaultRx2Subscriber;
import com.trj.mvvmdemo.model.GankioData;
import com.trj.mvvmdemo.ui.activity.MainActivity;
import com.trj.mvvmdemo.ui.base.BaseViewModule;
import com.trj.mvvmdemo.ui.fragment.IndexFragment;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.schedulers.Schedulers;
import me.tatarka.bindingcollectionadapter2.BindingRecyclerViewAdapter;
import me.tatarka.bindingcollectionadapter2.BindingViewPagerAdapter;
import me.tatarka.bindingcollectionadapter2.collections.MergeObservableList;
import me.tatarka.bindingcollectionadapter2.itembindings.OnItemBindClass;
import rx.functions.Action0;

/**
 * @author TRJ
 * @date 2017/12/6
 * Description:
 */
public class MainViewModule extends BaseViewModule{

    private MainActivity mMainActivity;

    @Inject
    public SubMainViewModule subMainViewModule;

    @Inject
    ApiService mApiService;

    @Inject
    public MainViewModule(MainActivity mainActivity) {
        super(mainActivity);
        this.mMainActivity = mainActivity;

        for (int i = 0; i < 3; i++) {
            mViewPagerItems.add(IndexFragment.newInstance());
        }
    }


    public final ObservableList<GankioData.ResultsBean> items = new ObservableArrayList<>();

    /**
     * 点击事件
     */
    public BindingCommand onClick = new BindingCommand(new Action0() {
        @Override
        public void call() {
            showToast("正在加载数据...");
            getData();
        }
    });


    public final BindingRecyclerViewAdapter.ViewHolderFactory viewHolder = new BindingRecyclerViewAdapter.ViewHolderFactory() {
        @Override
        public RecyclerView.ViewHolder createViewHolder(ViewDataBinding binding) {
            return new MyAwesomeViewHolder(binding.getRoot());
        }
    };

    private static class MyAwesomeViewHolder extends RecyclerView.ViewHolder {
        public MyAwesomeViewHolder(View itemView) {
            super(itemView);
        }
    }

    public final LoggingRecyclerViewAdapter<Object> adapter = new LoggingRecyclerViewAdapter<>();

    public final MergeObservableList<Object> headerFooterItems = new MergeObservableList<>()
            .insertItem("Header")
            .insertList(items)
            .insertItem("Footer");

    public final OnItemBindClass<Object> multipleItems = new OnItemBindClass<>()
            .map(String.class, BR.item, R.layout.item_header_footer)
            .map(GankioData.ResultsBean.class, BR.item, R.layout.item);

    private void getData() {
        mApiService.getData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultRx2Subscriber<GankioData>(){
                    @Override
                    public void onNext(@NonNull GankioData data) {
                        items.clear();
                        items.addAll(data.results);
                        showToast("数据加载完成");
                    }
                });
    }



    public final ObservableList<Fragment> mViewPagerItems = new ObservableArrayList<>();

    /**
     * Binds a homogeneous list of items to a layout.
     */
//    public final ItemBinding<Fragment> singleItem = ItemBinding.of(BR.item, R.layout.item_viewpager);

    /**
     * Define page titles for a ViewPager
     */
    public final BindingViewPagerAdapter.PageTitles<Fragment> pageTitles = new BindingViewPagerAdapter.PageTitles<Fragment>() {
        @Override
        public CharSequence getPageTitle(int position, Fragment item) {
            return "Item " + item;
        }
    };
}
