package com.zlyj.resttemplate.movie.config;


import lombok.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
public class Videos implements Serializable {
    private static final long serialVersionUID = -432908543160176349L;
    private String video_id;

    private String sample_link;

    private String need_pay;

    private List<Source>sources = new ArrayList<>();

}
