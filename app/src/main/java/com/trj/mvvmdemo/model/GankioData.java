package com.trj.mvvmdemo.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by TRJ on 2017/9/6.
 * Description:
 */

public class GankioData implements Serializable{

    public boolean error;
    public List<ResultsBean> results;

    public static class ResultsBean {
        public String _id;
        public String createdAt;
        public String desc;
        public String publishedAt;
        public String source;
        public String type;
        public String url;
        public boolean used;
        public String who;
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
