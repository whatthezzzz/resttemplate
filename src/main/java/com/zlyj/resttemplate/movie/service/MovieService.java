package com.zlyj.resttemplate.movie.service;



import com.zlyj.resttemplate.movie.config.Artist;
import com.zlyj.resttemplate.movie.config.Media;
import com.zlyj.resttemplate.movie.config.MediaAssetId;
import com.zlyj.resttemplate.movie.config.Tag;
import com.zlyj.resttemplate.movie.controller.MovieController;
import com.zlyj.resttemplate.movie.dao.MovieDao;

import com.zlyj.resttemplate.movie.entity.DetailsId;
import com.zlyj.resttemplate.movie.entity.Movie;

import com.zlyj.resttemplate.movie.entity.TopMovie;
import com.zlyj.resttemplate.movie.util.TopMovieDetailsIdUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovieService {


    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);

    RestTemplate template = new RestTemplate();

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MovieDao movieDao;

    /**
     * 根据电影名查找对象
     *
     * @param title
     * @return
     */
    public Movie findMovieByName(String title) {
        return movieDao.findMovieByName(title);
    }

    /**
     * 根据detailsId查找对象
     *
     * @param detailsId
     * @return
     */
    public Movie findMovieBydetailsId(String detailsId) {
        return movieDao.findMovieBydetailsId(detailsId);
    }



    /**
     * 豆瓣TOP250
     * @return
     */
    public void top250Movie() {
    try {


    TopMovieDetailsIdUtil topMovieDetailsIdUtil = new TopMovieDetailsIdUtil();

    List<String> detailsId = topMovieDetailsIdUtil.Test2();

    for(String d:detailsId){
        Query query=new Query(Criteria.where("detailsId").is(d));
        try {

                Movie mgt = mongoTemplate.findOne(query, Movie.class);
                TopMovie movie = new TopMovie();
                movie.setProviderAssets(mgt.getProviderAssets());
                movie.setRating(mgt.getRating());
                movie.setTags(mgt.getTags());
                movie.setCountries(mgt.getCountries());
                movie.setGenres(mgt.getGenres());
                movie.setDirectors(mgt.getDirectors());
                movie.setCasts(mgt.getCasts());
                movie.setSummary(mgt.getSummary());
                movie.setTitle(mgt.getTitle());
                movie.setYear(mgt.getYear());
                movie.setTrailerType(1);
                movie.setDetailsId(d);
                movie.setLasttime(String.valueOf(new Date()));
                mongoTemplate.save(movie);
            }catch (Exception e){
                logger.error(d);
            }
        }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * 更新数据
     * @return
     */
    public void updateMovie(String apikey,HttpServletResponse response) {
        List<DetailsId>list = movieDao.findAllDetailsId();

            for (DetailsId d : list) {
                String detailsId = d.getDetailsId();

//              String detailsId = "26100958";
//              String detailsId = "30206924 ";
//              String detailsId = "4301280";
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html;charset=UTF-8");
                Movie movie = new Movie();

                String url = "http://api.douban.com/v2/movie/" + detailsId;

                String url2 = "http://api.douban.com/v2/movie/subject/" + detailsId;

                MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<String, Object>();

                paramMap.add("apikey", apikey);

                HttpHeaders headers = new HttpHeaders();


                //定义请求参数类型，这里用json所以是MediaType.APPLICATION_JSON
                headers.setContentType(MediaType.APPLICATION_JSON);
                //RestTemplate带参传的时候要用HttpEntity<?>对象传递
                /**
                 * 使用postforentity传参
                 */
                HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(paramMap, headers);

                try {

                    ResponseEntity<String> entity = template.postForEntity(url, request, String.class);

                    ResponseEntity<String> entity2 = template.postForEntity(url2, request, String.class);

                    String res = entity.getBody();

                    String res2 = entity2.getBody();

                    try {
                        JSONObject jsonObject = new JSONObject(JSONTokener(res));

                        JSONObject jsonObject2 = new JSONObject(JSONTokener(res2));

                        JSONArray year = (JSONArray) jsonObject.getJSONObject("attrs").get("year");

                        Integer detailsSource = movieDao.findMovieBydetailsId(detailsId).getDetailsSource();

//                if (!arry(year).equals("2019") || arry(year).isEmpty() || null == arry(year) || detailsSource != 1) {
//                    logger.error("NOT FOUND ON 2019 DOUBAN");
//                    return;
//                }

                        if (1 == detailsSource) {
                            JSONArray cast = (JSONArray) jsonObject.getJSONObject("attrs").opt("cast");
                            JSONArray directors = (JSONArray) jsonObject.getJSONObject("attrs").opt("director");
                            JSONArray genres = (JSONArray) jsonObject.getJSONObject("attrs").opt("movie_type");
                            JSONArray country = (JSONArray) jsonObject.getJSONObject("attrs").opt("country");
                            String video = String.valueOf(jsonObject2.get("videos"));
                            String tags = String.valueOf(jsonObject.getJSONArray("tags"));
                            String mediaType = String.valueOf(jsonObject2.get("subtype"));
                            String title = String.valueOf(jsonObject2.get("title"));
                            String summary = String.valueOf(jsonObject.get("summary"));
                            String rating = String.valueOf(jsonObject.getJSONObject("rating").get("average"));

                            movie.setProviderAssets(MediaAsset(video));
                            movie.setRating(empty(rating));
                            movie.setTags(merge(tags));
                            movie.setCountries(arry(country));
                            movie.setGenres(arry(genres));
                            movie.setDirectors(arry(directors));
                            movie.setCasts(arry(cast));
                            movie.setSummary(summary);
                            movie.setTitle(title);
                            movie.setYear(arry(year));
                            movie.setTrailerType(1);
                            movie.setDetailsId(detailsId);
                            movie.setLasttime(String.valueOf(new Date()));
                            movie.setCheck(true);


                            if ("movie".equals(mediaType)) {
                                movie.setMedia(Media.movie);
                            } else if (movie.getTags().contains("综艺") || movie.getTags().contains("真人秀")) {
                                movie.setMedia(Media.show);
                            } else {
                                movie.setMedia(Media.series);
                            }

                            movieDao.updateMovie(movie);

//                            logger.info(title + "   " + detailsId);

                        } else {

                            logger.error("此影片不来自豆瓣：" + detailsId);

                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    movie.setDetailsId(detailsId);
                    movie.setLasttime(String.valueOf(new Date()));
                    movie.setCheck(false);
                    movieDao.noteMovie(movie);
                    logger.error("此影片在豆瓣不存在：" + detailsId);

                }
            }
            logger.info("完事");
    }

    private static List<MediaAssetId> MediaAsset(String array1) {

        com.alibaba.fastjson.JSONArray jsonarray = com.alibaba.fastjson.JSONArray.parseArray(array1);
        List<MediaAssetId> mediaAssetIds = new ArrayList<>();


        for (int q = 0; q < jsonarray.size(); q++) {
            MediaAssetId mediaAssetId = new MediaAssetId();

            com.alibaba.fastjson.JSONObject jsonObj = jsonarray.getJSONObject(q);

            com.alibaba.fastjson.JSONObject source = (com.alibaba.fastjson.JSONObject) jsonObj.get("source");

            String literal = source.getString("literal");

            String video_id = jsonObj.getString("video_id");


            mediaAssetId.setAssetId(video_id);
            mediaAssetId.setAssetProvider(literal);

            mediaAssetIds.add(mediaAssetId);

        }


        return mediaAssetIds;
    }


    private static Double empty(String rating){
        Double average;
        if (rating.isEmpty()||null == rating){
            average = Double.valueOf(0);
        }else{
            double average1 = Double.parseDouble(rating);
            return average1;
        }
        return average;
    }


    private static String arry(JSONArray array1) throws JSONException {
        String array2;
        if(null == array1){
           array2 = "";
           return array2;
        }
        List<String> list = new ArrayList<>();
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

}
