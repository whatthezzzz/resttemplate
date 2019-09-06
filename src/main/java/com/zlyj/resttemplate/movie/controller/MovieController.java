package com.zlyj.resttemplate.movie.controller;



import com.zlyj.resttemplate.movie.config.Artist;
import com.zlyj.resttemplate.movie.config.Tag;
import com.zlyj.resttemplate.movie.entity.Movie;
import com.zlyj.resttemplate.movie.service.MovieService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    RestTemplate template = new RestTemplate();

    /**
     * 更新
     * @return
     */
    @RequestMapping(value = "/doPost", method = RequestMethod.POST)
    public void doPost3(String detailsId,String apikey,HttpServletResponse response) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        Movie movie = new Movie();
            movie.setDetailsId(detailsId);

            String url = "http://api.douban.com/v2/movie/" + movie.getDetailsId();

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

            String res = entity.getBody();
            try{
                JSONObject jsonObject = new JSONObject(JSONTokener(res));

                Double rating = Double.valueOf(String.valueOf(jsonObject.getJSONObject("rating").get("average")));
                JSONArray cast = (JSONArray) jsonObject.getJSONObject("attrs").get("cast");
                String title = String.valueOf(jsonObject.get("alt_title"));
                String summary = String.valueOf(jsonObject.get("summary"));
                JSONArray year = (JSONArray) jsonObject.getJSONObject("attrs").get("year");
                JSONArray directors = (JSONArray) jsonObject.getJSONObject("attrs").get("director");
                JSONArray genres = (JSONArray) jsonObject.getJSONObject("attrs").get("movie_type");
                JSONArray country = (JSONArray) jsonObject.getJSONObject("attrs").get("country");
                String tags = String.valueOf(jsonObject.getJSONArray("tags"));

                movie.setTags(merge(tags));
                movie.setCountries(String.join("|",arry(country)));
                movie.setGenres(String.join("|",arry(genres)));
                movie.setDirectors(String.join("|",arry(directors)));
                movie.setRating(rating);
                movie.setCasts(String.join("|",arry(cast)));
                movie.setSummary(summary);
                movie.setTitle(title);
                movie.setYear(String.join("|",arry(year)));

                movieService.UpdateMovie(movie);

            }catch (Exception e){
                e.printStackTrace();
            }
    }

    private static List<String> arry(JSONArray array1) throws JSONException {
        String array2;
        List<String>list = new ArrayList<>();
        for (int i = 0; i < array1.length(); i++) {
            array2 = (String) array1.get(i);
            list.add(array2);
        }
        return list;
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



//                if(!year.equals("2019")||year.isEmpty()||null == year) {
//                    logger.error("year errro");
//                    return;
//                }
//


//            MongoClient mongoClient = new MongoClient("localhost", 27017);
//            MongoDatabase database = mongoClient.getDatabase("test");
//            MongoCollection<Document> collection = database.getCollection("test_digidite");
//            Document document = Document.parse(a);
//            collection.insertOne(document);
//            movie = movieService.findMovieBydetailsId(detailsId);
}

