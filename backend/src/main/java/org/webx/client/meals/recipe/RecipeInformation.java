package org.webx.client.meals.recipe;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RecipeInformation {

    private static final String URL_REAL_DBMEAL = "https://www.themealdb.com/meal.php?c=";


    @JsonPropertyDescription("Id correspondant à la recette.")
    private String idMeal;

    @JsonPropertyDescription("Adjectif désignant la région associée à la recette.")
    private String area = "Unknown";

    @JsonPropertyDescription("Nom de la recette.")
    private String name = "";

    @JsonPropertyDescription("La recette proprement dite, en anglais.")
    private String instructions = "";

    @JsonPropertyDescription("URL d'une photo du plat.")
    private String imageUrl = "";

    @JsonPropertyDescription("URL de la page source décrivant la recette.")
    private String sourceUrl = "";

    @JsonProperty("themealdb_url")
    @JsonPropertyDescription("URL de la recette sur la page officielle de TheMealDB.")
    public String getThemealdbUrl() {
        return URL_REAL_DBMEAL + getIdMeal();
    }

    @JsonProperty("area")
    public String getArea() {
        return area;
    }

    @JsonProperty("strArea")
    public void setArea(String area) {
        this.area = area;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("strMeal")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("instructions")
    public String getInstructions() {
        return instructions;
    }

    @JsonProperty("strInstructions")
    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    @JsonProperty("strMealThumb")
    public String getImageUrl() {
        return imageUrl;
    }

    @JsonProperty("strMealThumb")
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @JsonProperty("strSource")
    public String getSourceUrl() {
        return sourceUrl;
    }

    @JsonProperty("strSource")
    public void setSourceUrl(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @JsonProperty("idMeal")
    public String getIdMeal() {
        return idMeal;
    }

    @JsonProperty("idMeal")
    public void setIdMeal(String idMeal) {
        this.idMeal = idMeal;
    }
}
