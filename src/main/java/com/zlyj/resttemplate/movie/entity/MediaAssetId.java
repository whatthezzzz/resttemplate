package com.zlyj.resttemplate.movie.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * TODO 类描述
 *
 * @author taorong
 */
@Data
public class MediaAssetId {

    String assetId;
    String assetProvider;

    public MediaAssetId(){}

    @JsonCreator
    public MediaAssetId(@JsonProperty("assetId") String assetId, @JsonProperty("assetProvider") String assetProvider) {
        this.assetId = assetId;
        this.assetProvider = assetProvider;
    }
}
