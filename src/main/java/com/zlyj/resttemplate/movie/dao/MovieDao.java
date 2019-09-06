package com.zlyj.resttemplate.movie.dao;

import com.zlyj.resttemplate.movie.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

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
     * 更新对象
     */
    public void updateTest(Movie movie) {

        Query query=new Query(Criteria.where("detailsId").is(movie.getDetailsId()));
        Update update= new Update().set("casts", movie.getCasts()).set("title",movie.getTitle() ).set("summary",movie.getSummary()).set("rating",movie.getRating()).set("year",movie.getYear()).set("genres",movie.getGenres()).set("directors",movie.getDirectors()).set("trailerType",movie.getTrailerType()).set("countries",movie.getCountries()).set("tags",movie.getTags());
        //更新查询返回结果集的第一条
        mongoTemplate.updateFirst(query,update,Movie.class);
        //更新查询返回结果集的所有
        // mongoTemplate.updateMulti(query,update,TestEntity.class);
    }
}
