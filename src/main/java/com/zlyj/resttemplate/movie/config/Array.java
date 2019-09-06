package com.zlyj.resttemplate.movie.config;

import lombok.Data;
import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;


@Data
public class Array {
    JSONArray arr1;

    String arr2;

    List<String> list = new ArrayList<>();
}
