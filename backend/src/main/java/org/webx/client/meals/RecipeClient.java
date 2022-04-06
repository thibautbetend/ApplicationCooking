package org.webx.client.meals;

import jakarta.ws.rs.ClientErrorException;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.core.MediaType;
import org.webx.client.exceptions.ApiFailedException;
import org.webx.client.meals.recipe.RecipeArray;
import org.webx.client.meals.recipe.RecipeInformation;

import java.util.Random;

public class RecipeClient {

    private static final String API_NAME = "https://www.themealdb.com";
    private static final String REST_URI_COUNTRY = "https://www.themealdb.com/api/json/v1/1/filter.php";
    private static final String REST_URI_RANDOM_MEAL = "https://www.themealdb.com/api/json/v1/1/random.php";
    private static final String REST_URI_MEAL_BY_ID = "https://www.themealdb.com/api/json/v1/1/lookup.php";

    private final Client client = ClientBuilder.newClient();


    /**
     * @param country Adjective of the country
     * @return a RecipeArray in line with the adjective of the country
     * if the adjective is null or empty return a random RecipeInformation in RecipeArray[0]
     */
    public RecipeArray getRecipeInformation(String country) throws ApiFailedException {
        try {
            if (country != null && !country.equals("")) {
                return client.target(REST_URI_COUNTRY)
                    .queryParam("a", country)
                    .request(MediaType.APPLICATION_JSON_TYPE)
                    .get(RecipeArray.class);
            } else {
                return getRandomRecipe();
            }
        } catch (ClientErrorException e) {
            throw new ApiFailedException(API_NAME, Integer.toString(e.getResponse().getStatus()));
        }
    }


    /**
     * @return a random RecipeInformation in RecipeArray
     */
    public RecipeArray getRandomRecipe() throws ApiFailedException {
        try {
            return client.target(REST_URI_RANDOM_MEAL)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(RecipeArray.class);
        } catch (ClientErrorException e) {
            throw new ApiFailedException(API_NAME, Integer.toString(e.getResponse().getStatus()));
        }
    }


    /**
     * @param meals RecipeArray containing an array of RecipeInformation
     * @return a random meal between the meals proposed by getRecipeInformation()
     * if there are no meals that exists, return a random meal
     */
    public RecipeInformation getRandomMeal(RecipeArray meals) throws ApiFailedException {
        if (meals.getMeals() != null) {
            int rnd = new Random().nextInt(meals.getMeals().length);
            return getRecetteById(meals.getMeals()[rnd].getIdMeal()).getMeals()[0];
        } else {
            return getRandomRecipe().getMeals()[0];
        }
    }


    /**
     * @param id String with the Id of a meal
     * @return A recipeGroupe with the instructions of the meal with the id in param
     */
    public RecipeArray getRecetteById(String id) throws ApiFailedException {
        try {
            return client.target(REST_URI_MEAL_BY_ID)
                .queryParam("i", id)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .get(RecipeArray.class);
        } catch (ClientErrorException e) {
            throw new ApiFailedException(API_NAME, Integer.toString(e.getResponse().getStatus()));
        }
    }


}
