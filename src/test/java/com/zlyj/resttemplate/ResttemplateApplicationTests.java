package com.zlyj.resttemplate;


import com.alibaba.fastjson.*;

import com.zlyj.resttemplate.movie.config.MediaAssetId;
import com.zlyj.resttemplate.movie.config.Source;
import com.zlyj.resttemplate.movie.config.Tag;
import com.zlyj.resttemplate.movie.config.Videos;

import com.zlyj.resttemplate.movie.entity.Movie;
import org.assertj.core.error.ShouldBeAfterYear;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest


public class ResttemplateApplicationTests {


    private static final Logger logger = LoggerFactory.getLogger(ResttemplateApplicationTests.class);


    @Test
    public void jsonarraytest() throws JSONException {
        String str = "[{\"sample_link\":\"http:\\/\\/www.iqiyi.com\\/v_19rt88sfpk.html?vfm=m_331_dbdy&fv=4904d94982104144a1548dd9040df241\",\"need_pay\":true,\"source\":{\"name\":\"爱奇艺视频\",\"pic\":\"https:\\/\\/img3.doubanio.com\\/f\\/movie\\/7c9e516e02c6fe445b6559c0dd2a705e8b17d1c9\\/pics\\/movie\\/video-iqiyi.png\",\"literal\":\"iqiyi\"},\"video_id\":\"19rt88sfpk\"},{\"sample_link\":\"http:\\/\\/v.qq.com\\/x\\/cover\\/v2098lbuihuqs11.html?ptag=douban.movie\",\"need_pay\":true,\"source\":{\"name\":\"腾讯视频\",\"pic\":\"https:\\/\\/img3.doubanio.com\\/f\\/movie\\/0a74f4379607fa731489d7f34daa545df9481fa0\\/pics\\/movie\\/video-qq.png\",\"literal\":\"qq\"},\"video_id\":\"v2098lbuihuqs11\"},{\"sample_link\":\"http:\\/\\/v.youku.com\\/v_show\\/id_XNDMwMjk5OTU2MA==.html?tpa=dW5pb25faWQ9MzAwMDA4XzEwMDAwMl8wMl8wMQ&refer=esfhz_operation.xuka.xj_00003036_000000_FNZfau_19010900\",\"need_pay\":true,\"source\":{\"name\":\"优酷视频\",\"pic\":\"https:\\/\\/img1.doubanio.com\\/f\\/movie\\/886b26a83d18bc60de4ee1daac38145f03c88792\\/pics\\/movie\\/video-youku.png\",\"literal\":\"youku\"},\"video_id\":\"XNDMwMjk5OTU2MA==\"},{\"sample_link\":\"http:\\/\\/film.sohu.com\\/album\\/9569532.html\",\"need_pay\":true,\"source\":{\"name\":\"搜狐视频\",\"pic\":\"https:\\/\\/img1.doubanio.com\\/f\\/movie\\/77358cffb08eb6750a0880136f0575c9e7e9a749\\/pics\\/movie\\/video-sohu.png\",\"literal\":\"sohu\"},\"video_id\":\"5615522\"}]";
//        //获取jsonobject对象
//        JSONObject jsonObject = JSON.parseObject(str);
//        //把对象转换成jsonArray数组
        JSONArray str1 = JSONObject.parseArray(str);

        logger.info(""+str1);
//        //error==>[{"code":"UUM70004","message":"组织单元名称不能为空","data":{"id":"254","suborderNo":"SUB_2018062797348039","organUnitType":"部门","action":"add","parent":"10000","ordinal":0,"organUnitFullName":"组织单元全称"},"success":false},{"code":"UUM70004","message":"组织单元名称不能为空","data":{"id":"255","suborderNo":"SUB_2018062797348039","organUnitType":"部门","action":"add","parent":"10000","ordinal":0,"organUnitFullName":"组织单元全称"},"success":false}]
//        //将数组转换成字符串
//        String jsonString = JSONObject.toJSONString(error);//将array数组转换成字符串
//        //将字符串转成list集合
//        logger.info(jsonString);
        List<Videos> videos = JSONObject.parseArray(str1.toJSONString(), Videos.class);//把字符串转换成集合
        for (Videos v: videos) {
            //Error的属性
            System.out.println("video_id="+v.getVideo_id());
            List<Source> s =v.getSources();
            for (Source source: s) {
                System.out.println("Source对象属性="+source.getLiteral());
                System.out.println("Source对象属性="+source.getName());
                System.out.println("Source对象属性="+source.getPic());
            }
        }


    }

    @Test
    public void Video() {

        String array1 = "[{\"sample_link\":\"http:\\/\\/www.iqiyi.com\\/v_19rt88sfpk.html?vfm=m_331_dbdy&fv=4904d94982104144a1548dd9040df241\",\"need_pay\":true,\"source\":{\"name\":\"爱奇艺视频\",\"pic\":\"https:\\/\\/img3.doubanio.com\\/f\\/movie\\/7c9e516e02c6fe445b6559c0dd2a705e8b17d1c9\\/pics\\/movie\\/video-iqiyi.png\",\"literal\":\"iqiyi\"},\"video_id\":\"19rt88sfpk\"},{\"sample_link\":\"http:\\/\\/v.qq.com\\/x\\/cover\\/v2098lbuihuqs11.html?ptag=douban.movie\",\"need_pay\":true,\"source\":{\"name\":\"腾讯视频\",\"pic\":\"https:\\/\\/img3.doubanio.com\\/f\\/movie\\/0a74f4379607fa731489d7f34daa545df9481fa0\\/pics\\/movie\\/video-qq.png\",\"literal\":\"qq\"},\"video_id\":\"v2098lbuihuqs11\"},{\"sample_link\":\"http:\\/\\/v.youku.com\\/v_show\\/id_XNDMwMjk5OTU2MA==.html?tpa=dW5pb25faWQ9MzAwMDA4XzEwMDAwMl8wMl8wMQ&refer=esfhz_operation.xuka.xj_00003036_000000_FNZfau_19010900\",\"need_pay\":true,\"source\":{\"name\":\"优酷视频\",\"pic\":\"https:\\/\\/img1.doubanio.com\\/f\\/movie\\/886b26a83d18bc60de4ee1daac38145f03c88792\\/pics\\/movie\\/video-youku.png\",\"literal\":\"youku\"},\"video_id\":\"XNDMwMjk5OTU2MA==\"},{\"sample_link\":\"http:\\/\\/film.sohu.com\\/album\\/9569532.html\",\"need_pay\":true,\"source\":{\"name\":\"搜狐视频\",\"pic\":\"https:\\/\\/img1.doubanio.com\\/f\\/movie\\/77358cffb08eb6750a0880136f0575c9e7e9a749\\/pics\\/movie\\/video-sohu.png\",\"literal\":\"sohu\"},\"video_id\":\"5615522\"}]";List<String>list3 = Collections.singletonList(array1);

        com.alibaba.fastjson.JSONArray jsonarray = com.alibaba.fastjson.JSONArray.parseArray(array1);
        List<MediaAssetId>mediaAssetIds = new ArrayList<>();
        MediaAssetId mediaAssetId = new MediaAssetId();
        for (int q = 0; q < jsonarray.size(); q++) {

            JSONObject jsonObj = jsonarray.getJSONObject(q);

            JSONObject source = (JSONObject) jsonObj.get("source");

            String literal = source.getString("literal");
            mediaAssetId.setAssetProvider(literal);
            String video_id = jsonObj.getString("video_id");
            mediaAssetId.setAssetId(video_id);
            mediaAssetIds.add(mediaAssetId);
        }


        System.out.println(mediaAssetIds);
//        List<Source> video = com.alibaba.fastjson.JSONObject.parseArray(jsonarray.toJSONString(), Source.class);
//
//        System.out.println(video);
//
//        if(video==null|| video.isEmpty()) {
//            return;
//        }
//        List<String> list1 = new ArrayList<>();
//        String w ;
//        for (Source videos : video) {
//            w = videos.getLiteral();
//
//            list1.add(w);
//
//        }
//
//        System.out.println(list1);

            }


    @Test
    public void merge() {
        String array1 = "[{\"count\":114482,\"name\":\"漫威\"},{\"count\":99031,\"name\":\"超级英雄\"},{\"count\":63603,\"name\":\"科幻\"},{\"count\":61083,\"name\":\"美国\"},{\"count\":55202,\"name\":\"2019\"},{\"count\":50568,\"name\":\"情怀\"},{\"count\":34820,\"name\":\"动作\"},{\"count\":34470,\"name\":\"漫画改编\"}]";
        com.alibaba.fastjson.JSONArray jsonarray = com.alibaba.fastjson.JSONArray.parseArray(array1);

        List<Tag> tags = com.alibaba.fastjson.JSONObject.parseArray(jsonarray.toJSONString(), Tag.class);

        System.out.println(tags );
        if(tags==null|| tags.isEmpty()) {
            return ;
        }
        String s="";
        for (Tag tag : tags) {
            s+= tag.getName()+"|";
        }
        System.out.println(s.substring(0,s.length()-1));
    }


//    @Test
//    public class Message implements Delayed {
//        private int id;
//        private String body; // 消息内容
//        private long excuteTime;// 延迟时长，这个是必须的属性因为要按照这个判断延时时长。
//
//        public int getId() {
//            return id;
//        }
//
//        public String getBody() {
//            return body;
//        }
//
//        public long getExcuteTime() {
//            return excuteTime;
//        }
//
//        public Message(int id, String body, long delayTime) {
//            this.id = id;
//            this.body = body;
//            this.excuteTime = TimeUnit.NANOSECONDS.convert(delayTime, TimeUnit.MILLISECONDS) + System.nanoTime();
//        }
//
//        // 自定义实现比较方法返回 1 0 -1三个参数
//        @Override
//        public int compareTo(Delayed delayed) {
//            Message msg = (Message) delayed;
//            return Integer.valueOf(this.id) > Integer.valueOf(msg.id) ? 1
//                    : (Integer.valueOf(this.id) < Integer.valueOf(msg.id) ? -1 : 0);
//        }
//
//        // 延迟任务是否到时就是按照这个方法判断如果返回的是负数则说明到期否则还没到期
//        @Override
//        public long getDelay(TimeUnit unit) {
//            return unit.convert(this.excuteTime - System.nanoTime(), TimeUnit.NANOSECONDS);
//        }
//    }
}












