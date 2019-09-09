package com.zlyj.resttemplate.movie.service;



import com.zlyj.resttemplate.movie.dao.MovieDao;
import com.zlyj.resttemplate.movie.entity.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieService {

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
     * 更新数据
     * @return
     */
    public void UpdateMovie(Movie movie) {
        movieDao.updateTest(movie);
    }


    /**
     * 取detailsSource
     * @return
     */
    public Movie findMovieDetailsSource(String detailsId){
        return movieDao.findMovieDetailsSource(detailsId);
    }
}
