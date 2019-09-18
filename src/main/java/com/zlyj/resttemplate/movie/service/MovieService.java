package com.zlyj.resttemplate.movie.service;

import com.zlyj.resttemplate.movie.controller.MovieController;
import com.zlyj.resttemplate.movie.dao.MovieDao;

import com.zlyj.resttemplate.movie.entity.DetailsId;
import com.zlyj.resttemplate.movie.entity.HotMovie;
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
            List<String> detailsId = topMovieDetailsIdUtil.reptile();

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
                System.out.println(erroId );
                jobs.updateTopMovie(apikey,erroId);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 更新数据
     * @return
     */
    public void updateMovie(String apikey) {
    try {
        List<DetailsId> list = movieDao.findAllDetailsId();

        jobs.updateMovie(apikey,list);
    }catch (Exception e){
        e.printStackTrace();
        }
    }

    /**
     * 热门电影
     * @return
     */
    public void hotMovie(String apikey) {

        List<String> detailsId = jobs.queryHotMovie(apikey);

        for (String d : detailsId) {
            Query query = new Query(Criteria.where("detailsId").is(d));

            try {
                Movie mgt = mongoTemplate.findOne(query, Movie.class);

                HotMovie hotMovie = new HotMovie();

                hotMovie.setProviderAssets(mgt.getProviderAssets());
                hotMovie.setRating(mgt.getRating());
                hotMovie.setTags(mgt.getTags());
                hotMovie.setCountries(mgt.getCountries());
                hotMovie.setGenres(mgt.getGenres());
                hotMovie.setDirectors(mgt.getDirectors());
                hotMovie.setCasts(mgt.getCasts());
                hotMovie.setSummary(mgt.getSummary());
                hotMovie.setTitle(mgt.getTitle());
                hotMovie.setYear(mgt.getYear());
                hotMovie.setTrailerType(1);
                hotMovie.setDetailsId(d);
                hotMovie.setDetailsSource(1);
                hotMovie.setLasttime(String.valueOf(new Date()));

                movieDao.addHotMovie(hotMovie);

                logger.info(hotMovie.getTitle()+"   "+hotMovie.getDetailsId());

            } catch (Exception e) {
                e.printStackTrace();
                logger.error(d);

            }

        }


    }



}