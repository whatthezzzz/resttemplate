package com.zlyj.resttemplate;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.zlyj.resttemplate.movie.config.Person;
import com.zlyj.resttemplate.movie.config.Tag;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest


public class ResttemplateApplicationTests {

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
//        String str = "[{'columnId':5,'columnName':'人文历史'},{'columnId':2,'columnName':'商业视野'}]}";
//        JSONArray jsonArray = null;
//        jsonArray = new JSONArray(str);
//        System.out.println(jsonArray.getJSONObject(0).get("columnName"));

        String json = "[{\"count\":113753,\"name\":\"漫威\"},{\"count\":98407,\"name\":\"超级英雄\"},{\"count\":63164,\"name\":\"科幻\"},{\"count\":60658,\"name\":\"美国\"},{\"count\":54872,\"name\":\"2019\"},{\"count\":50182,\"name\":\"情怀\"},{\"count\":34552,\"name\":\"动作\"},{\"count\":34202,\"name\":\"漫画改编\"}]";

        JSONArray jsonarray = JSONArray.parseArray(json);

        System.out.println(jsonarray);

        List<Tag> tags = JSONObject.parseArray(jsonarray.toJSONString(), Tag.class);

        System.out.println(tags);

        if(tags==null|| tags.isEmpty()) {
            return;
        }
        String s="";
        for (Tag tag : tags) {
            s+= tag.getName()+"|";
        }
        System.out.println(s.substring(0,s.length()-1));
    }
}
