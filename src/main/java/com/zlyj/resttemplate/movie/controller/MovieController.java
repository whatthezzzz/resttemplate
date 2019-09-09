package com.zlyj.resttemplate.movie.controller;

import com.zlyj.resttemplate.movie.config.Media;
import com.zlyj.resttemplate.movie.config.Artist;
import com.zlyj.resttemplate.movie.config.Tag;
import com.zlyj.resttemplate.movie.dao.MovieDao;
import com.zlyj.resttemplate.movie.entity.Movie;
import com.zlyj.resttemplate.movie.service.MovieService;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@RequestMapping(value = "/test")
@Controller
public class MovieController {

@Autowired
private MovieService movieService;

//@Autowired
//private MovieDao movieDao;



    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    RestTemplate template = new RestTemplate();

    /**
     * 更新
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public void doPost3(String detailsId,String apikey,HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        Movie movie = new Movie();
            movie.setDetailsId(detailsId);

            String url = "http://api.douban.com/v2/movie/"+detailsId;

            String url2 = "http://api.douban.com/v2/movie/subject/" + detailsId;

            MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();

            paramMap.add("apikey",apikey);

            HttpHeaders headers = new HttpHeaders();


            //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
            headers.setContentType(MediaType.APPLICATION_JSON);
            //RestTemplate带参传的时候要用HttpEntity<?>对象传递
            /**
             * 使用postforentity传参
             */
            HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(paramMap, headers);

            ResponseEntity<String> entity = template.postForEntity(url, request, String.class);

            ResponseEntity<String>entity2 = template.postForEntity(url2,request,String.class);

            String res = entity.getBody();

            String res2 = entity2.getBody();
            try{
                JSONObject jsonObject = new JSONObject(JSONTokener(res));

                JSONObject jsonObject2 = new JSONObject(JSONTokener(res2));

                JSONArray year = (JSONArray) jsonObject.getJSONObject("attrs").get("year");

                Integer detailsSource = movieService.findMovieBydetailsId(detailsId).getDetailsSource();

                if(!arry(year).equals("2019")||arry(year).isEmpty()||null == arry(year)||detailsSource != 1) {
                    logger.error("NOT FOUND ON 2019 DOUBAN");
                    return;
                }

                Double rating = Double.valueOf(String.valueOf(jsonObject.getJSONObject("rating").get("average")));
                JSONArray cast = (JSONArray) jsonObject.getJSONObject("attrs").get("cast");
                JSONArray directors = (JSONArray) jsonObject.getJSONObject("attrs").get("director");
                JSONArray genres = (JSONArray) jsonObject.getJSONObject("attrs").get("movie_type");
                JSONArray country = (JSONArray) jsonObject.getJSONObject("attrs").get("country");
                String tags = String.valueOf(jsonObject.getJSONArray("tags"));
                String mediaType = String.valueOf(jsonObject2.get("subtype"));
                String title = String.valueOf(jsonObject.get("alt_title"));
                String summary = String.valueOf(jsonObject.get("summary"));

                movie.setTags(merge(tags));
                movie.setCountries(arry(country));
                movie.setGenres(arry(genres));
                movie.setDirectors(arry(directors));
                movie.setRating(rating);
                movie.setCasts(arry(cast));
                movie.setSummary(summary);
                movie.setTitle(title);
                movie.setYear(arry(year));
                movie.setTrailerType(1);

                if("movie".equals(mediaType)){
                    movie.setMedia(Media.movie);
                } else if(movie.getTags().contains("综艺")||movie.getTags().contains("真人秀")) {
                    movie.setMedia(Media.show);
                } else {
                    movie.setMedia(Media.series);
                }

                movieService.UpdateMovie(movie);

            }catch (Exception e){
                e.printStackTrace();
            }
    }

    private static String arry(JSONArray array1) throws JSONException {
        String array2;
        List<String>list = new ArrayList<>();
        for (int i = 0; i < array1.length(); i++) {
            array2 = (String) array1.get(i);
            list.add(array2);
        }
        String array3 = String.join("|",list);
        return array3;
    }

    private static  String JSONTokener(String in) {
        if (in != null && in.startsWith("\ufeff")) {
            in = in.substring(1);
        }
        return in;
    }

    public static String merge(String array1) {

       com.alibaba.fastjson.JSONArray jsonarray = com.alibaba.fastjson.JSONArray.parseArray(array1);

        List<Tag> tags = com.alibaba.fastjson.JSONObject.parseArray(jsonarray.toJSONString(), Tag.class);

        if(tags==null|| tags.isEmpty()) {
            return "";
        }
        String s="";
        for (Tag tag : tags) {
            s+= tag.getName()+"|";
        }
        return s.substring(0,s.length()-1);
    }


    public static String merge(Artist[] artists) {
        if(artists==null|| artists.length==0) {
            return "";
        }
        String s="";
        for (Artist artist : artists) {
            s+= artist.getName()+"|";
        }
        return s.substring(0,s.length()-1);
    }



//            MongoClient mongoClient = new MongoClient("localhost", 27017);
//            MongoDatabase database = mongoClient.getDatabase("test");
//            MongoCollection<Document> collection = database.getCollection("test_digidite");
//            Document document = Document.parse(a);
//            collection.insertOne(document);
//            movie = movieService.findMovieBydetailsId(detailsId);
}

