package org.webx.client.geo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

/**
 * Used to store a bounding box.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BoundingBox {
    @NotNull
    @JsonProperty("max_latitude")
    private Double max_latitude;

    @NotNull
    @JsonProperty("max_longitude")
    private Double max_longitude;

    @NotNull
    @JsonProperty("min_latitude")
    private Double min_latitude;

    @NotNull
    @JsonProperty("min_longitude")
    private Double min_longitude;


    public Double getMax_latitude() {
        return max_latitude;
    }

    public void setMax_latitude(Double max_latitude) {
        this.max_latitude = max_latitude;
    }

    public Double getMax_longitude() {
        return max_longitude;
    }

    public void setMax_longitude(Double max_longitude) {
        this.max_longitude = max_longitude;
    }

    public Double getMin_latitude() {
        return min_latitude;
    }

    public void setMin_latitude(Double min_latitude) {
        this.min_latitude = min_latitude;
    }

    public Double getMin_longitude() {
        return min_longitude;
    }

    public void setMin_longitude(Double min_longitude) {
        this.min_longitude = min_longitude;
    }
}
