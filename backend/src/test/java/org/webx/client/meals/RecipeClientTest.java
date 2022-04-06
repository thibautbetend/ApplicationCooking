package org.webx.client.meals;

import org.junit.Before;
import org.junit.Test;
import org.webx.client.exceptions.ApiFailedException;
import org.webx.client.meals.recipe.RecipeArray;
import org.webx.client.meals.recipe.RecipeInformation;

import static org.junit.Assert.*;

public class RecipeClientTest {

    private RecipeClient recipeClient;

    @Before
    public void setUp(){
        recipeClient = new RecipeClient();
    }

    @Test
    public void getRecipeInformation() throws ApiFailedException {
        RecipeArray recipeArray = new RecipeClient().getRecipeInformation("Canadian");
        assertNotEquals(0, recipeArray.getMeals().length);
    }

    @Test
    public void getRecipeInstructionForGoodCountry() throws ApiFailedException {
        RecipeArray recipeArray = recipeClient.getRecipeInformation("Canadian");
        String recipeId = recipeClient.getRandomMeal(recipeArray).getIdMeal();
        String recipe = recipeClient.getRecetteById(recipeId).getMeals()[0].getInstructions();
        assertNotEquals(0,recipe.length());
    }

    @Test
    public void getRecipeInstructionForBadCountry() throws ApiFailedException {
        RecipeArray recipeArray = recipeClient.getRecipeInformation("Cana");
        String recipe = recipeClient.getRandomMeal(recipeArray).getInstructions();
        assertNotEquals(0,recipe.length());
    }

    @Test
    public void getRecipeInstructionForNoneCountry() throws ApiFailedException {
        RecipeArray recipeArray = recipeClient.getRecipeInformation("");
        String recipe = recipeClient.getRandomMeal(recipeArray).getInstructions();
        assertNotEquals(0,recipe.length());
    }



    @Test
    public void getRandomRecipe() throws ApiFailedException {
        assertNotEquals(0, recipeClient.getRandomRecipe().getMeals()[0].getInstructions().length());
    }

    @Test
    public void getRandomMealInArray() throws ApiFailedException {
        RecipeArray recipeArray = recipeClient.getRecipeInformation("Canadian");
        assertNotEquals("", recipeClient.getRandomMeal(recipeArray).getInstructions());
    }

    @Test
    public void getRandomMealInEmptyArray() throws ApiFailedException {
        RecipeArray recipeArray = recipeClient.getRecipeInformation(null);
        assertNotEquals(0, recipeClient.getRandomMeal(recipeArray).getInstructions().length());
    }


    @Test
    public void getMealById() throws ApiFailedException {
        RecipeArray recipeArray = recipeClient.getRecetteById("52832");
        RecipeInformation recipeInformation = recipeArray.getMeals()[0];
        assertEquals("52832", recipeInformation.getIdMeal());
        assertEquals("French", recipeInformation.getArea());
        assertEquals("Coq au vin", recipeInformation.getName());
        assertTrue(recipeInformation
            .getInstructions()
            .startsWith("Heat 1 tbsp of the oil in a large, heavy-based saucepan or flameproof dish.")
        );
        assertEquals("https://www.themealdb.com/images/media/meals/qstyvs1505931190.jpg", recipeInformation.getImageUrl());
        assertEquals("https://www.bbcgoodfood.com/recipes/1913655/coq-au-vin", recipeInformation.getSourceUrl());
        assertEquals("https://www.themealdb.com/meal.php?c=52832", recipeInformation.getThemealdbUrl());
    }
}