package com.zlyj.resttemplate.movie.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TopMovieDetailsIdUtil {

    public List<String> Test2() throws IOException {

        String s1 = "https://movie.douban.com/top250?start=";
        String s2 = "&filter=";
        String link;
//        String title = null;
//        String score = null;
//        String num = null ;

        List<String>detailsIds = new ArrayList<>();
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i <= 225; i += 25) {
            list.add(s1+i+s2);
        }

        for (String string : list) {
            Document doc = Jsoup.connect(string).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.12; rv:60.0) Gecko/20100101 Firefox/60.0").timeout(6000).get();
            Element content = doc.getElementById("content");
            Elements infos = content.getElementsByClass("info");
            for (Element element : infos) {
//                Element links = element.getElementsByTag("a").get(0);
//                Element star = element.getElementsByClass("star").get(0);
//                link = links.attr("href");// 获取电影的链接
//                title = links.child(0).html();    // 获取电影名称
//                score = star.child(1).html();     // 获取电影评分
//                num= star.child(3).html();       // 获取评价人数
//                System.out.println(link + "\t" + title + "\t评分" + score + "\t"+num );
                Element links = element.getElementsByTag("a").get(0);

                link = links.attr("href");

                String regEx="[^0-9]";

                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(link);
                String detailsId = m.replaceAll("").trim();
                detailsIds.add(detailsId);
            }

        }
        return detailsIds;
    }
}
