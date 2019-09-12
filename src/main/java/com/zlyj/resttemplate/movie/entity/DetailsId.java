package com.zlyj.resttemplate.movie.entity;


import com.digidite.common.entity.BaseMongoEntity;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "t_std_media_asset")
public class DetailsId extends BaseMongoEntity {

    private String detailsId;
}
