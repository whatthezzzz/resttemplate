package com.zlyj.resttemplate.movie.dao;

import com.zlyj.resttemplate.movie.entity.DetailsId;
import com.zlyj.resttemplate.movie.entity.HotMovie;
import com.zlyj.resttemplate.movie.entity.Movie;
import com.zlyj.resttemplate.movie.entity.TopMovie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.*;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;


import java.util.ArrayList;
import java.util.List;

@Repository
public class MovieDao {
    @Autowired
    private MongoTemplate mongoTemplate;


    /**
     * 根据detailsId查询对象
     * @Param detailsId
     * @return
     */
    public Movie findMovieBydetailsId(String detailsId) {
        Query query=new Query(Criteria.where("detailsId").is(detailsId));
        Movie mgt =  mongoTemplate.findOne(query,Movie.class);
        return mgt;
    }

    /**
     * 单条查询
     * @param detailsId
     * @return
     */
    public Movie findMovieDetailsSource(String detailsId){

        List<String>fields = new ArrayList<>();

        Query query = new Query(Criteria.where("detailsId").is(detailsId));

        Field findFields = query.fields().include("detailsSource");

        if (!CollectionUtils.isEmpty(fields)) {
            fields.forEach(findFields::include);
        }

        return mongoTemplate.findOne(query,Movie.class);

    }


    /**
     * 更新对象
     */
    public void updateMovie(Movie movie) {

        Query query=new Query(Criteria.where("detailsId").is(movie.getDetailsId()));
        Update update= new Update().set("casts", movie.getCasts()).set("title",movie.getTitle() ).set("summary",movie.getSummary()).set("rating",movie.getRating()).set("year",movie.getYear()).set("genres",movie.getGenres()).set("directors",movie.getDirectors()).set("trailerType",movie.getTrailerType()).set("countries",movie.getCountries()).set("tags",movie.getTags()).set("mediaType",movie.getMedia()).set("trailerType",movie.getTrailerType()).set("providerAssets",movie.getProviderAssets()).set("lastModifiedTime",movie.getLasttime()).set("check",movie.getCheck());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,Movie.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,Movie.class);
    }
    /**
     * 标记错误影片
     */
    public void noteMovie(Movie movie){
        Query query = new Query(Criteria.where("detailsId").is(movie.getDetailsId()));

        Update update = new Update().set("lastModifiedTime",movie.getLasttime()).set("check",movie.getCheck());

        mongoTemplate.updateFirst(query,update,Movie.class);
    }


    /**
     * 查詢出所有detailsId
     */
    public List<DetailsId> findAllDetailsId(){

        return mongoTemplate.findAll(DetailsId.class);
    }

    /**
     * 新增topMovie
     */
    public void addTopMovie(TopMovie topMovie){
        Query query = new Query(Criteria.where("detailsId").is(topMovie.getDetailsId()));
        TopMovie tm = mongoTemplate.findOne(query,TopMovie.class);
       if (null == tm){
          mongoTemplate.save(topMovie);
       }else {
           return;
       }
    }


    /**
     * 新增Movie
     */
    public void addMovie(Movie movie){

        Query query = new Query(Criteria.where("detailsId").is(movie.getDetailsId()));
        Movie mov = mongoTemplate.findOne(query,Movie.class);
        if(null == mov){
            movie.setDetailsSource(1);
            mongoTemplate.save(movie);
            System.out.println("新增成功");
        }else {
            System.out.println("已存在");
            return ;
        }
    }


    /**
     * 新增hotMovie
     */
    public void addHotMovie(HotMovie hotMovie){
        Query query = new Query(Criteria.where("detailsId").is(hotMovie.getDetailsId()));
        HotMovie hm = mongoTemplate.findOne(query,HotMovie.class);
        if (null == hm){
            mongoTemplate.save(hotMovie);
            System.out.println("此热门电影新增成功");
        }else {
            System.out.println("此热门电影已存在");
            return;

        }
    }


}
