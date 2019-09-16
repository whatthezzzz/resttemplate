package com.zlyj.resttemplate.movie.controller;

import com.digidite.core.exception.BusinessException;
import com.digidite.core.model.Response;
import com.zlyj.resttemplate.movie.service.MovieService;

import com.zlyj.resttemplate.movie.util.ApiErrorCode;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
@RequestMapping(value = "/test")
@Controller
public class MovieController {

@Autowired
private MovieService movieService;


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Response update(String apikey , HttpServletResponse response) {
 try {
     movieService.updateMovie(apikey, response);
     return Response.success("success");
 } catch (Exception e) {
     e.printStackTrace();
     return Response.fail(new BusinessException(ApiErrorCode.OPERATION_ERROR));
 }
    }


    @RequestMapping(value = "/top",method = RequestMethod.POST)
    public Response top() {
        try {
            movieService.top250Movie();
            return Response.success("niupi");
        } catch (Exception e) {
            e.printStackTrace();
            return Response.fail(new BusinessException(ApiErrorCode.OPERATION_ERROR));
        }
    }


    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public void doPost4(String detailsId ) {
        try {
           movieService.findMovieBydetailsId(detailsId);

        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}

