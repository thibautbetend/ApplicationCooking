package org.webx.client.geo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Used to store geographic information
 * such as the name of the country in english,
 * its code (2 letters code) and its bounding box.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoInfo {
    @NotBlank
    @JsonProperty("name")
    private String name;

    @NotBlank
    @JsonProperty("alpha2")
    private String code;

    @NotNull
    @JsonProperty("geo")
    private BoundingBox boundingBox;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("alpha2")
    public String getCode() {
        return code;
    }

    @JsonProperty("alpha2")
    public void setCode(String code) {
        this.code = code;
    }

    @JsonProperty("geo")
    public BoundingBox getGeoCoord() {
        return boundingBox;
    }

    @JsonProperty("geo")
    public void setGeoCoord(BoundingBox boundingBox) {
        this.boundingBox = boundingBox;
    }

    @Override
    public String toString() {
        try {
            return new ObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            return super.toString();
        }
    }
}
