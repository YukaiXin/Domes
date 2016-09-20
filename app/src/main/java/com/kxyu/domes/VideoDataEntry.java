package com.kxyu.domes;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by kxyu on 16-8-2.
 */
public class VideoDataEntry {
    @SerializedName("code")
    public String code;

    @SerializedName("message")
    public String message;

    @SerializedName("result_array")
    public ArrayList<videoDataEntry> videosDataEntryList;

    public class videoDataEntry {
        @SerializedName("category")
        public String category;

        @SerializedName("top_images")
        public ArrayList<TopImageInfo> imgInfoList;

        @SerializedName("seq_id")
        public String seqId;

        @SerializedName("liked")
        public String liked;

        @SerializedName("shared_count")
        public String shared_count;

        @SerializedName("shared_url")
        public String shared_url;

        @SerializedName("like_count")
        public String like_count;

        @SerializedName("content")
        public String content;

        @SerializedName("comments_count")
        public String commentsCount;

        @SerializedName("has_commented")
        public String hasCommented;

        @SerializedName("type")
        public String type;

        @SerializedName("id")
        public String id;

        @SerializedName("detail_url")
        public String detailUrl;

        @Override
        public String toString() {
            return "NewsDataEntry{" +
                    "category='" + category + '\'' +
                    ", imgInfoList=" + imgInfoList +
                    ", like_count=" + like_count +
                    ", seqId='" + seqId + '\'' +
                    ", liked='" + liked + '\'' +
                    ", shared_url='" + shared_url + '\'' +
                    ", shared_count='" + shared_count + '\'' +
                    ", content='" + content + '\'' +
                    ", commentsCount='" + commentsCount + '\'' +
                    ", hasCommented='" + hasCommented + '\'' +
                    ", type='" + type + '\'' +
                    ", id='" + id + '\'' +
                    ", detailUrl='" + detailUrl + '\'' +
                    '}';
        }
    }


    public class youtobe{

        public String code;
    }

    public class TopImageInfo{


        @SerializedName("origin")
        public String origin;

        @SerializedName("width")
        public String width;

        @SerializedName("thumb_height")
        public String thumb_height;

        @SerializedName("thumb")
        public String thumb;

        @SerializedName("thumb_width")
        public String thumb_width;

        @SerializedName("source")
        public String source;

        @SerializedName("height")
        public String height;

        @Override
        public String toString() {
            return "TopImageInfo{" +
                    "origin='" + origin + '\'' +
                    ", height='" + height + '\'' +
                    ", width='" + width + '\'' +
                    ", thumbHeight='" + thumb_height + '\'' +
                    ", thumbWidth='" + thumb_width + '\'' +
                    ", source='" + source + '\'' +
                    ", thumb='" + thumb + '\'' +
                    '}';
        }
    }
}
