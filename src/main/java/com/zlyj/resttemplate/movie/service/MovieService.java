package com.zlyj.resttemplate.movie.service;

import com.zlyj.resttemplate.movie.controller.MovieController;
import com.zlyj.resttemplate.movie.dao.MovieDao;

import com.zlyj.resttemplate.movie.entity.DetailsId;
import com.zlyj.resttemplate.movie.entity.Movie;

import com.zlyj.resttemplate.movie.entity.TopMovie;
import com.zlyj.resttemplate.movie.jobs.MovieQueryJobs;
import com.zlyj.resttemplate.movie.util.TopMovieDetailsIdUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MovieService {


    private static final Logger logger = LoggerFactory.getLogger(MovieController.class);


    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private MovieDao movieDao;

    @Autowired
    private MovieQueryJobs jobs;


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
     *
     * @return
     */
    public void top250Movie(String apikey) {
        List<String> erroId = new ArrayList<>();
        try {

            TopMovieDetailsIdUtil topMovieDetailsIdUtil = new TopMovieDetailsIdUtil();
            List<String> detailsId = topMovieDetailsIdUtil.Test2();

            for (String d : detailsId) {
                Query query = new Query(Criteria.where("detailsId").is(d));
                try {

                    Movie mgt = mongoTemplate.findOne(query, Movie.class);

                    TopMovie tmovie = new TopMovie();

                    tmovie.setProviderAssets(mgt.getProviderAssets());
                    tmovie.setRating(mgt.getRating());
                    tmovie.setTags(mgt.getTags());
                    tmovie.setCountries(mgt.getCountries());
                    tmovie.setGenres(mgt.getGenres());
                    tmovie.setDirectors(mgt.getDirectors());
                    tmovie.setCasts(mgt.getCasts());
                    tmovie.setSummary(mgt.getSummary());
                    tmovie.setTitle(mgt.getTitle());
                    tmovie.setYear(mgt.getYear());
                    tmovie.setTrailerType(1);
                    tmovie.setDetailsId(d);
                    tmovie.setLasttime(String.valueOf(new Date()));

                    movieDao.addTopMovie(tmovie);

                } catch (Exception e) {
                    erroId.add(d);

                }
            }
            if (null !=erroId) {
                List<DetailsId> detailsIdList = new ArrayList<>();

                for (String e : erroId) {
                    DetailsId d = null;

                    d.setDetailsId(e);

                    detailsIdList.add(d);
                }

                System.out.println(detailsIdList);

//                jobs.updateMovie(apikey, detailsIdList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 更新数据
     *
     * @return
     */
    public void updateMovie(String apikey) {

//        List<DetailsId> list = movieDao.findAllDetailsId();

        jobs.updateMovie(apikey);

    }
}