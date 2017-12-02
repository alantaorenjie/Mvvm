package com.trj.mvvmdemo.model;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableArrayList;


import com.baoyz.pg.Parcelable;
import com.trj.mvvmdemo.BR;

import java.io.Serializable;
import java.util.List;
import java.util.Observable;

/**
 * Created by TRJ on 2017/9/6.
 * Description:
 */
@Parcelable
public class GankioData extends BaseObservable implements Serializable {

    public boolean error;
    public List<ResultsBean> results;

    public static class ResultsBean extends BaseObservable implements Serializable{
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;


        public void setWho(String who) {
            this.who = who;
            notifyPropertyChanged(BR.who);
        }

        public @Bindable String who;

        public List<String> images;



        @Override
        public String toString() {
            return "ResultsBean{" +
                    "_id='" + _id + '\'' +
                    ", createdAt='" + createdAt + '\'' +
                    ", desc='" + desc + '\'' +
                    ", publishedAt='" + publishedAt + '\'' +
                    ", source='" + source + '\'' +
                    ", type='" + type + '\'' +
                    ", url='" + url + '\'' +
                    ", used=" + used +
                    ", who='" + who + '\'' +
                    ", images=" + images +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GankioData{" +
                "error=" + error +
                ", results=" + results +
                '}';
    }
}
