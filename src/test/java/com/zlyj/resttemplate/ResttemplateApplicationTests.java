package com.zlyj.resttemplate;


import com.alibaba.fastjson.*;
import com.zlyj.resttemplate.movie.config.Person;
import com.zlyj.resttemplate.movie.config.Source;
import com.zlyj.resttemplate.movie.config.Tag;
import com.zlyj.resttemplate.movie.config.Videos;
import com.zlyj.resttemplate.movie.controller.MovieController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest


public class ResttemplateApplicationTests {

    private static final Logger logger = LoggerFactory.getLogger(ResttemplateApplicationTests.class);

//    @Test
//    private static List<String> arry(JSONArray array1) throws JSONException, MalformedURLException, RemoteException {
//        String array2;
//        List<String>list = new ArrayList<>();
//        for (int i = 0; i < array1.length(); i++) {
//            array2 = (String) array1.get(i);
//            list.add(array2);
//            list = Arrays.asList(list(String.join("|", list)));
//        }
//        return list;
//    }

    @Test
    public void jsonarraytest() throws JSONException {
        String str = "{\"errors\":[{\"sample_link\":\"http:\\/\\/www.iqiyi.com\\/v_19rt88sfpk.html?vfm=m_331_dbdy&fv=4904d94982104144a1548dd9040df241\",\"need_pay\":true,\"source\":{\"name\":\"爱奇艺视频\",\"pic\":\"https:\\/\\/img3.doubanio.com\\/f\\/movie\\/7c9e516e02c6fe445b6559c0dd2a705e8b17d1c9\\/pics\\/movie\\/video-iqiyi.png\",\"literal\":\"iqiyi\"},\"video_id\":\"19rt88sfpk\"},{\"sample_link\":\"http:\\/\\/v.qq.com\\/x\\/cover\\/v2098lbuihuqs11.html?ptag=douban.movie\",\"need_pay\":true,\"source\":{\"name\":\"腾讯视频\",\"pic\":\"https:\\/\\/img3.doubanio.com\\/f\\/movie\\/0a74f4379607fa731489d7f34daa545df9481fa0\\/pics\\/movie\\/video-qq.png\",\"literal\":\"qq\"},\"video_id\":\"v2098lbuihuqs11\"},{\"sample_link\":\"http:\\/\\/v.youku.com\\/v_show\\/id_XNDMwMjk5OTU2MA==.html?tpa=dW5pb25faWQ9MzAwMDA4XzEwMDAwMl8wMl8wMQ&refer=esfhz_operation.xuka.xj_00003036_000000_FNZfau_19010900\",\"need_pay\":true,\"source\":{\"name\":\"优酷视频\",\"pic\":\"https:\\/\\/img1.doubanio.com\\/f\\/movie\\/886b26a83d18bc60de4ee1daac38145f03c88792\\/pics\\/movie\\/video-youku.png\",\"literal\":\"youku\"},\"video_id\":\"XNDMwMjk5OTU2MA==\"},{\"sample_link\":\"http:\\/\\/film.sohu.com\\/album\\/9569532.html\",\"need_pay\":true,\"source\":{\"name\":\"搜狐视频\",\"pic\":\"https:\\/\\/img1.doubanio.com\\/f\\/movie\\/77358cffb08eb6750a0880136f0575c9e7e9a749\\/pics\\/movie\\/video-sohu.png\",\"literal\":\"sohu\"},\"video_id\":\"5615522\"}]}";
        //获取jsonobject对象
        JSONObject jsonObject = JSON.parseObject(str);
        //把对象转换成jsonArray数组
        JSONArray error = jsonObject.getJSONArray("errors");
        //error==>[{"code":"UUM70004","message":"组织单元名称不能为空","data":{"id":"254","suborderNo":"SUB_2018062797348039","organUnitType":"部门","action":"add","parent":"10000","ordinal":0,"organUnitFullName":"组织单元全称"},"success":false},{"code":"UUM70004","message":"组织单元名称不能为空","data":{"id":"255","suborderNo":"SUB_2018062797348039","organUnitType":"部门","action":"add","parent":"10000","ordinal":0,"organUnitFullName":"组织单元全称"},"success":false}]
        //将数组转换成字符串
        String jsonString = JSONObject.toJSONString(error);//将array数组转换成字符串
        //将字符串转成list集合
        List<Videos>  errors = JSONObject.parseArray(jsonString, Videos.class);//把字符串转换成集合
        for (Videos e: errors) {
            //Error的属性
            System.out.println("另一种数组转换Error属性="+e.getVideo_id());
            List<Source> s = e.getSource();
            for (Source source: s) {
                System.out.println("data对象属性="+source.getLiteral());
                System.out.println("data对象属性="+source.getName());
                System.out.println("data对象属性="+source.getPic());
            }
        }


    }

    @Test
    public void Video() {

        String array1 = "[{\"sample_link\":\"http:\\/\\/www.iqiyi.com\\/v_19rt88sfpk.html?vfm=m_331_dbdy&fv=4904d94982104144a1548dd9040df241\",\"need_pay\":true,\"source\":{\"name\":\"爱奇艺视频\",\"pic\":\"https:\\/\\/img3.doubanio.com\\/f\\/movie\\/7c9e516e02c6fe445b6559c0dd2a705e8b17d1c9\\/pics\\/movie\\/video-iqiyi.png\",\"literal\":\"iqiyi\"},\"video_id\":\"19rt88sfpk\"},{\"sample_link\":\"http:\\/\\/v.qq.com\\/x\\/cover\\/v2098lbuihuqs11.html?ptag=douban.movie\",\"need_pay\":true,\"source\":{\"name\":\"腾讯视频\",\"pic\":\"https:\\/\\/img3.doubanio.com\\/f\\/movie\\/0a74f4379607fa731489d7f34daa545df9481fa0\\/pics\\/movie\\/video-qq.png\",\"literal\":\"qq\"},\"video_id\":\"v2098lbuihuqs11\"},{\"sample_link\":\"http:\\/\\/v.youku.com\\/v_show\\/id_XNDMwMjk5OTU2MA==.html?tpa=dW5pb25faWQ9MzAwMDA4XzEwMDAwMl8wMl8wMQ&refer=esfhz_operation.xuka.xj_00003036_000000_FNZfau_19010900\",\"need_pay\":true,\"source\":{\"name\":\"优酷视频\",\"pic\":\"https:\\/\\/img1.doubanio.com\\/f\\/movie\\/886b26a83d18bc60de4ee1daac38145f03c88792\\/pics\\/movie\\/video-youku.png\",\"literal\":\"youku\"},\"video_id\":\"XNDMwMjk5OTU2MA==\"},{\"sample_link\":\"http:\\/\\/film.sohu.com\\/album\\/9569532.html\",\"need_pay\":true,\"source\":{\"name\":\"搜狐视频\",\"pic\":\"https:\\/\\/img1.doubanio.com\\/f\\/movie\\/77358cffb08eb6750a0880136f0575c9e7e9a749\\/pics\\/movie\\/video-sohu.png\",\"literal\":\"sohu\"},\"video_id\":\"5615522\"}]";List<String>list3 = Collections.singletonList(array1);

        com.alibaba.fastjson.JSONArray jsonarray = com.alibaba.fastjson.JSONArray.parseArray(array1);

        logger.info(""+jsonarray);

        for (int q = 0; q < jsonarray.size(); q++) {

            JSONObject jsonObj = jsonarray.getJSONObject(q);

            String literal = jsonObj.getString("literal");

            String video_id = jsonObj.getString("video_id");

            logger.info(literal + video_id);
        }
        List<Source> video = com.alibaba.fastjson.JSONObject.parseArray(jsonarray.toJSONString(), Source.class);




        System.out.println(video);

        if(video==null|| video.isEmpty()) {
            return;
        }
        List<String> list1 = new ArrayList<>();
        String w ;
        for (Source videos : video) {
            w = videos.getLiteral();

            list1.add(w);

        }

        System.out.println(list1);


//
//        String str = "requestid=97&_workflowid=230&_workflowtype=&isovertime=0&preloadkey=1502342729232&_key=oyqdqq&field6322=1317%20&_t=1502340708152";
//        String[] strs = str.split("&");
//        Map<String, String> m = new HashMap<String, String>();
//        for(String s:strs){
//            String[] ms = s.split("=");
//            m.put(ms[0], ms[1]);
//        }
//
//        System.out.println(m.toString());
//        String str = "A_test1,A_test2,B_test3,B_test4";
//        //第一次分割,分隔符 ','
//        String[] stepOne = str.split(",");
//        Map<String,String> map = new HashMap<String,String>();
//        for(int i = 0; i<stepOne.length; i++)
//        {
//            //第二次分割，分隔符'_'
//            String[] stepTwo = stepOne[i].split("_");
//            //向HashMap中添加
//            if(map.get(stepTwo[0])==null)
//                map.put(stepTwo[0], stepTwo[1]);
//            else
//                map.put(stepTwo[0], stepTwo[1]+","+map.get(stepTwo[0]));
//        }
//        for(Map.Entry<String, String>entry : map.entrySet())
//        {
//            //按格式输出
//            System.out.println("key={"+entry.getKey()+"} value={"+entry.getValue()+"}");
//        }



//        String ww = "[{\"sample_link\":\"http:\\/\\/www.iqiyi.com\\/v_19rt88sfpk.html?vfm=m_331_dbdy&fv=4904d94982104144a1548dd9040df241\",\"need_pay\":true,\"source\":{\"name\":\"爱奇艺视频\",\"pic\":\"https:\\/\\/img3.doubanio.com\\/f\\/movie\\/7c9e516e02c6fe445b6559c0dd2a705e8b17d1c9\\/pics\\/movie\\/video-iqiyi.png\",\"literal\":\"iqiyi\"},\"video_id\":\"19rt88sfpk\"},{\"sample_link\":\"http:\\/\\/v.qq.com\\/x\\/cover\\/v2098lbuihuqs11.html?ptag=douban.movie\",\"need_pay\":true,\"source\":{\"name\":\"腾讯视频\",\"pic\":\"https:\\/\\/img3.doubanio.com\\/f\\/movie\\/0a74f4379607fa731489d7f34daa545df9481fa0\\/pics\\/movie\\/video-qq.png\",\"literal\":\"qq\"},\"video_id\":\"v2098lbuihuqs11\"},{\"sample_link\":\"http:\\/\\/v.youku.com\\/v_show\\/id_XNDMwMjk5OTU2MA==.html?tpa=dW5pb25faWQ9MzAwMDA4XzEwMDAwMl8wMl8wMQ&refer=esfhz_operation.xuka.xj_00003036_000000_FNZfau_19010900\",\"need_pay\":true,\"source\":{\"name\":\"优酷视频\",\"pic\":\"https:\\/\\/img1.doubanio.com\\/f\\/movie\\/886b26a83d18bc60de4ee1daac38145f03c88792\\/pics\\/movie\\/video-youku.png\",\"literal\":\"youku\"},\"video_id\":\"XNDMwMjk5OTU2MA==\"},{\"sample_link\":\"http:\\/\\/film.sohu.com\\/album\\/9569532.html\",\"need_pay\":true,\"source\":{\"name\":\"搜狐视频\",\"pic\":\"https:\\/\\/img1.doubanio.com\\/f\\/movie\\/77358cffb08eb6750a0880136f0575c9e7e9a749\\/pics\\/movie\\/video-sohu.png\",\"literal\":\"sohu\"},\"video_id\":\"5615522\"}]";
//        JSONArray json = JSONArray.parseArray(ww); // 首先把字符串转成 JSONArray 对象
//        if(json.size()>0){
//            for(int i=0;i<json.size();i++){// 遍历 jsonarray 数组，把每一个对象转成 json 对象
//                JSONObject job = json.getJSONObject(i);
//                JSONArray blow = job.get("source");
//                for (int j =0 ; j<blow.size();j++){
//                  JSONObject literal = blow.getJSONObject(j);
//                  System.out.println(literal.get("literal")+"=");
//                }

//               for (int j=0;j<job.size();j++){
//                   JSONObject blow = job.getJSONObject(String.valueOf(j));
//                   System.out.println(blow.get("literal")+"=");// 得到 每个对象中的属性值
//               }
            }
        }












