package org.webx.client.countries_name.countries;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdjectiveInfo {

    @JsonProperty("meals")
    private Object meals;

    @JsonProperty("meals")
    public Object getMeal() {
        return meals;
    }

    @JsonProperty("meals")
    public void setMeal(Object idMeal) {
        this.meals = idMeal;
    }
}
