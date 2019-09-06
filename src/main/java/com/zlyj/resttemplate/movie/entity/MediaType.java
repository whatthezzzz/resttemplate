package com.zlyj.resttemplate.movie.entity;

/**
 * TODO 类描述
 *
 * @author taorong
 */
public enum MediaType {

    /**
     *
     */
    movie("电影"),
    series("电视剧"),
    show("综艺"),
    kid("少儿"),
    cartoon("动漫"),
    documentary("纪录片"),
    news("资讯"),
    finance("财经"),
    sports("体育"),
    life("生活"),
    music("音乐"),
    military("军事"),
    opera("戏曲"),
    law("法制"),
    science("科技"),
    education("教育"),
    travel("旅游"),
    others("其他"),
    ;
    private String value;

    MediaType(String val) {
        value = val;
    }

    public String value() {
        return value;
    }
}
