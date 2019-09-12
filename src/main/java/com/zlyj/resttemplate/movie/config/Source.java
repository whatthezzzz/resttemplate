package com.zlyj.resttemplate.movie.config;

import lombok.Data;

import java.io.Serializable;

@Data
public class Source implements Serializable {
    private static final long serialVersionUID = -6957361951748382519L;


    private String literal;

    private String pic;

    private String name;
}
