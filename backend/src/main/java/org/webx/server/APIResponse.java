package org.webx.server;

import com.fasterxml.jackson.annotation.*;
import org.webx.client.flickr.Photo.Photo;
import org.webx.client.meals.recipe.RecipeInformation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * The root schema
 * JSON Schema pour le projet WebX 2021.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse {

    /**
     * true si le payload est une réponse valide.
     * (Required)
     */
    @JsonProperty("success")
    @JsonPropertyDescription("true si le payload est une r\u00e9ponse valide.")
    private Boolean success;
    /**
     * Nom de l'API REST qui a échoué.
     */
    @JsonProperty("api_failed")
    @JsonPropertyDescription("Nom de l'API REST qui a \u00e9chou\u00e9.")
    private String apiFailed;
    /**
     * Code de retour HTTP de l'API REST qui a échoué.
     */
    @JsonProperty("api_status")
    @JsonPropertyDescription("Code de retour HTTP de l'API REST qui a \u00e9chou\u00e9.")
    private String apiStatus;
    /**
     * Adresse IP du client.
     */
    @JsonProperty("client_ip")
    @JsonPropertyDescription("Adresse IP du client.")
    private String clientIp;
    /**
     * Code ISO 3166-1 alpha-2 du Pays choisi.
     */
    @JsonProperty("country_alpha2")
    @JsonPropertyDescription("Code ISO 3166-1 alpha-2 du Pays choisi.")
    private String countryAlpha2;
    /**
     * true si le Pays choisi n'a pas de recette typique référencée.
     */
    @JsonProperty("random_area")
    @JsonPropertyDescription("true si le Pays choisi n'a pas de recette typique r\u00e9f\u00e9renc\u00e9e.")
    private Boolean randomArea;
    /**
     * Informations sur une recette (choisie aléatoirement).
     */
    @JsonProperty("recipe")
    @JsonPropertyDescription("Informations sur une recette (choisie al\u00e9atoirement).")
    private RecipeInformation recipe;
    /**
     * Liste de (0 à) 5 photos.
     */
    @JsonProperty("photos")
    @JsonPropertyDescription("Liste de (0 \u00e0) 5 photos.")
    private List<Photo> photos;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
    public APIResponse() {

    }
    public APIResponse(
        Boolean success,
        String apiFailed,
        String apiStatus,
        String clientIp,
        String countryAlpha2,
        Boolean randomArea,
        RecipeInformation recipe,
        List<Photo> photos,
        Map<String, Object> additionalProperties
    ) {
        this.success = success;
        this.apiFailed = apiFailed;
        this.apiStatus = apiStatus;
        this.clientIp = clientIp;
        this.countryAlpha2 = countryAlpha2;
        this.randomArea = randomArea;
        this.recipe = recipe;
        this.photos = photos;
        this.additionalProperties = additionalProperties;
    }

    /**
     * true si le payload est une réponse valide.
     * (Required)
     */
    @JsonProperty("success")
    public Boolean getSuccess() {
        return success;
    }

    /**
     * true si le payload est une réponse valide.
     * (Required)
     */
    @JsonProperty("success")
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    /**
     * Nom de l'API REST qui a échoué.
     */
    @JsonProperty("api_failed")
    public String getApiFailed() {
        return apiFailed;
    }

    /**
     * Nom de l'API REST qui a échoué.
     */
    @JsonProperty("api_failed")
    public void setApiFailed(String apiFailed) {
        this.apiFailed = apiFailed;
    }

    /**
     * Code de retour HTTP de l'API REST qui a échoué.
     */
    @JsonProperty("api_status")
    public String getApiStatus() {
        return apiStatus;
    }

    /**
     * Code de retour HTTP de l'API REST qui a échoué.
     */
    @JsonProperty("api_status")
    public void setApiStatus(String apiStatus) {
        this.apiStatus = apiStatus;
    }

    /**
     * Adresse IP du client.
     */
    @JsonProperty("client_ip")
    public String getClientIp() {
        return clientIp;
    }

    /**
     * Adresse IP du client.
     */
    @JsonProperty("client_ip")
    public void setClientIp(String clientIp) {
        this.clientIp = clientIp;
    }

    /**
     * Code ISO 3166-1 alpha-2 du Pays choisi.
     */
    @JsonProperty("country_alpha2")
    public String getCountryAlpha2() {
        if (countryAlpha2 == null) {
            return null;
        }
        return countryAlpha2.toUpperCase();
    }

    /**
     * Code ISO 3166-1 alpha-2 du Pays choisi.
     */
    @JsonProperty("country_alpha2")
    public void setCountryAlpha2(String countryAlpha2) {
        this.countryAlpha2 = countryAlpha2;
    }

    /**
     * true si le Pays choisi n'a pas de recette typique référencée.
     */
    @JsonProperty("random_area")
    public Boolean getRandomArea() {
        return randomArea;
    }

    /**
     * true si le Pays choisi n'a pas de recette typique référencée.
     */
    @JsonProperty("random_area")
    public void setRandomArea(Boolean randomArea) {
        this.randomArea = randomArea;
    }

    /**
     * Informations sur une recette (choisie aléatoirement).
     */
    @JsonProperty("recipe")
    public RecipeInformation getRecipe() {
        return recipe;
    }

    /**
     * Informations sur une recette (choisie aléatoirement).
     */
    @JsonProperty("recipe")
    public void setRecipe(RecipeInformation recipe) {
        this.recipe = recipe;
    }

    /**
     * Liste de (0 à) 5 photos.
     */
    @JsonProperty("photos")
    public List<Photo> getPhotos() {
        return photos;
    }

    /**
     * Liste de (0 à) 5 photos.
     */
    @JsonProperty("photos")
    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}