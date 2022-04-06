package org.webx.server;

import org.glassfish.grizzly.http.server.Request;
import org.webx.client.countries_name.CountriesNameClient;
import org.webx.client.exceptions.ApiFailedException;
import org.webx.client.exceptions.InvalidCountryCodeException;
import org.webx.client.flickr.FlickrClient;
import org.webx.client.flickr.Photo.Photo;
import org.webx.client.flickr.Photo.PhotosSearchResponse;
import org.webx.client.geo.BoundingBox;
import org.webx.client.geo.GeoClient;
import org.webx.client.geo.GeoInfo;
import org.webx.client.meals.RecipeClient;
import org.webx.client.meals.recipe.RecipeArray;
import org.webx.client.meals.recipe.RecipeInformation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApiHandler {
    boolean success = true,
        randomArea = false;

    String apiFailed = null,
        apiStatus = null,
        clientIP = null,
        adjective = null,
        code = null;

    List<Photo> photos = new ArrayList<>();

    Map<String, Object> additionalProperties = new HashMap<>();

    RecipeInformation recipe = null;

    /**
     * Handles an API call.
     * If an error occurs on an API call, the server stops the execution of the request
     * and returns the data that have been properly fetched before the crash and a
     * description of the crash containing the API name that has failed and the error status.
     *
     * If no dish is available for the given country code, a random recipe will be fetch.
     * If a random recipe is fetched, pictures will be get for the given country code so they may
     * differ in location regarding to the fetched dish.
     *
     * @param country The 2 characters code of the country
     * @param request The current request that will be used to get information on the client
     * @return An {@link org.webx.server.ApiHandler} containing all the information fetched by
     * the server.
     */
    public APIResponse handleCall(String country, Request request) {
        try {
            clientIP = new IPHelper().getRemoteAddress(request);

            if (country == null || country.isBlank()) {
                GeoInfo geoInfo = new GeoClient().fetchGeoInfo(clientIP);
                code = geoInfo.getCode();
                buildResponseForChosenCountry(geoInfo.getCode());
            } else {
                code = country;
                buildResponseForChosenCountry(country);
            }
        } catch (ApiFailedException e) {
            apiFailed = e.getApiName();
            apiStatus = e.getApiStatus();
            success = false;
        } catch (Exception e) {
            apiFailed = "server";
            apiStatus = "unknown error";
        }

        return new APIResponse(
            success,
            apiFailed,
            apiStatus,
            clientIP,
            code,
            randomArea,
            recipe,
            photos,
            additionalProperties
        );
    }

    /**
     * Fill the different fields with information gathered by the clients.
     * @param country The 2-characters code that represents the country name
     * @throws ApiFailedException If any client fails, this exception is propagated to
     * the caller to be handled properly.
     */
    private void buildResponseForChosenCountry(String country) throws ApiFailedException {
        fetchCountryAdjective(country);
        fetchRecipe();

        BoundingBox bbox = fetchBoundingBox(country);
        fetchCountryPictures(bbox);
    }

    /**
     * Fetch the pictures for the given bounding box.
     *
     * @param bbox The bounding box to match for the location of the picture.
     * @throws ApiFailedException If any error is encountered during the process.
     */
    private void fetchCountryPictures(BoundingBox bbox) throws ApiFailedException {
        FlickrClient flickrClient = new FlickrClient();
        PhotosSearchResponse response = flickrClient.fetchURLs(bbox);
        photos = response.getPhotos().getPhoto();
    }

    /**
     * Fetch the corresponding bounding box for the given country.
     *
     * @param country A 2-characters code representing the country's name
     * @return The bouding box corresponding to this country
     * @throws ApiFailedException If any error occurs during the API call. May be
     * thrown for example when the specified country code is invalid.
     */
    private BoundingBox fetchBoundingBox(String country) throws ApiFailedException {
        GeoClient geoClient = new GeoClient();
        GeoInfo info = geoClient.fetchGeoInfoForCountry(country);
        return info.getGeoCoord();
    }

    /**
     * Fetch the recipe for the given adjective.
     * @throws ApiFailedException May be thrown if the adjective is incorrect
     * or if any error occurs during the API call.
     */
    private void fetchRecipe() throws ApiFailedException {
        RecipeClient recipeClient = new RecipeClient();
        RecipeArray meals = recipeClient.getRecipeInformation(adjective);
        recipe = recipeClient.getRandomMeal(meals);
    }

    /**
     * Fetch the corresponding adjective for the given country code.
     * @param countryCode A 2-characters code representing the country's name
     * @throws ApiFailedException If the country code is invalid or if any error
     * occurs during the API call.
     */
    private void fetchCountryAdjective(String countryCode) throws ApiFailedException {
        try {
            CountriesNameClient countryClient = new CountriesNameClient();
            adjective = countryClient.fetchGeoApiInfo(countryCode).getAdjective();
        } catch (InvalidCountryCodeException e) {
            adjective = null;
            randomArea = true;
        }
    }
}
