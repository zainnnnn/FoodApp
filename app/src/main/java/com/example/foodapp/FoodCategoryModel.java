package com.example.foodapp;

public class FoodCategoryModel {
    private String FoodPic;
    private int Id;
    private String Title;
    private String Ingredients;

    public String getFoodPic() {
        return FoodPic;
    }

    public void setFoodPic(String foodPic) {
        FoodPic = foodPic;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public String getRecipeDetail() {
        return RecipeDetail;
    }

    public void setRecipeDetail(String recipeDetail) {
        RecipeDetail = recipeDetail;
    }

    public FoodCategoryModel() {
    }

    public FoodCategoryModel(String foodPic, int id, String title, String ingredients, String recipeDetail) {
        FoodPic = foodPic;
        Id = id;
        Title = title;
        Ingredients = ingredients;
        RecipeDetail = recipeDetail;
    }

    private String RecipeDetail;
}
