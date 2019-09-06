package com.zlyj.resttemplate.movie.config;

import lombok.Data;

import java.util.Map;

/**
 * TODO 类描述
 *
 * @author taorong
 */
@Data
public class Artist {
    String id;
    String alt;
    Map<String, Object> avatars;
    String name;
}
