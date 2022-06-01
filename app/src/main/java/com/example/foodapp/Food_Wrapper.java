package com.example.foodapp;

public class Food_Wrapper {
    public static final String TABLE_NAME        = "FoodCategories";

    public static final String COLUMN_ID      = "ID";
    public static final String COLUMN_PICTURE      = "Picture";
    public static final String COLUMN_NAME       = "Name";
    public static final String COLUMN_INGREDIENTS   = "Ingredients";
    public static final String COLUMN_RECIPE   = "RECIPE";

    public static final String CreateFoodCategoriesTable = String.format("CREATE TABLE IF NOT EXISTS "+"%s(%s INTEGER PRIMARY KEY AUTOINCREMENT,%s TEXT,%s TEXT,%s TEXT,%s TEXT)",
            TABLE_NAME,COLUMN_ID,COLUMN_PICTURE,COLUMN_NAME,COLUMN_INGREDIENTS,COLUMN_RECIPE);

    public static final String DropFoodCategoriesTable = "DROP TABLE IF EXISTS "+TABLE_NAME;
    public static final String SelectAllFoodCategories = "SELECT * FROM " +TABLE_NAME;

}
