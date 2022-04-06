package org.webx.client.countries_name.countries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Used to fetch information
 * as the name of the country,
 * and the nationality
 * from its code (2 letters code).
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountriesNameInfo {

    @JsonProperty("name")
    private String name;

    @JsonProperty("nationality")
    private String adjective;


    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("nationality")
    public String getAdjective() {
        return adjective;
    }

    @JsonProperty("nationality")
    public void setAdjective(String adjective) {
        this.adjective = adjective;
    }
}
