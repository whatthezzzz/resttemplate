package com.zlyj.resttemplate.movie.dao;

import com.zlyj.resttemplate.movie.entity.DetailsId;
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
     * 根据电影名查询对象
     * @return
     */
    public Movie findMovieByName(String title) {
        Query query=new Query(Criteria.where("title").is(title));
        Movie mgt =  mongoTemplate.findOne(query,Movie.class);
        return mgt;
    }

    /**
     * 根据电影名查询对象
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
     * 最后一次修改时间
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
            mongoTemplate.save(movie);
        }else {
            return;
        }
    }

}
