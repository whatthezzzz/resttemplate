package com.zlyj.resttemplate.movie.entity;

import com.digidite.common.entity.BaseMongoEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.zlyj.resttemplate.movie.config.Media;
import com.zlyj.resttemplate.movie.config.MediaAssetId;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.LinkedList;
import java.util.List;

@Data
@Document(collection = "t_std_media_top")
@JsonInclude(JsonInclude.Include.ALWAYS)
public class TopMovie extends BaseMongoEntity {

        /**
         * 名称
         */
        @NotNull
        String title;

        /**
         * 别名
         */
        String[] aka;

        /**
         * 演员，用|分隔
         */
        String casts;

        /**
         * 导演，用|分隔
         */
        String directors;

        /**
         * 编剧，用|分隔
         */
        String scriptwriters;

        /**
         * 年份
         */
        String year;

        /**
         * 分类，用|分隔
         */
        @NotNull
        String genres;

        /**
         * 海报地址
         */
        String posterUrl;

        /**
         * 媒资类型
         *
         * 电影
         * 电视剧
         * 综艺
         * 纪录片
         *
         */
        @NotNull
        Media media;

        /**
         * 1 - 正片
         * 2 - 预告片/花絮
         */
        @NotNull
        int trailerType;

        /**
         * 简介
         */
        String summary;

        /**
         * 国家地区，用|分隔
         */
        String countries;

//    /**
//     * 地区
//     */
//    String region;

        /**
         * 评分
         */
        Double rating;

        /**
         * 详情id
         */
        String detailsId;

        /**
         * 详情状态
         * 1 - douban
         * 2 - tvmao
         * 3 - iqiyi
         * 4 - tencent
         */
        Integer detailsSource;

        /**
         * tvmao url
         */
        String tvmaoLink;

        /**
         *
         */

        //    String seasonsCount;
//    String episodesCount;
//    String currentSeason;



        /**
         * Tag，用|分隔
         */
        String tags;

        /**
         * 0: 系统自带
         * 1: live
         * 2: vod
         */
        @NotNull
        int source;

        /**
         * 0: 下架
         * 1: 上架
         */
        @NotNull
        int onShelfStatus;

        List<MediaAssetId> providerAssets = new LinkedList<>();

        private String lasttime;

}

