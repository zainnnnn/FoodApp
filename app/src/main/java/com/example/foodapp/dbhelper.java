package com.example.foodapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.nfc.Tag;
import android.support.v4.view.ViewCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class dbhelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = " FoodAppDB ";
    private static final int DATABASE_VERSION = 1;
    private static dbhelper ourInstance;

    public static dbhelper getInstance(Context context) {
        if (ourInstance == null) {
            ourInstance = new dbhelper(context.getApplicationContext());

        }
        return ourInstance;
    }

    public dbhelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(Food_Wrapper.CreateFoodCategoriesTable);
        db.execSQL(Signup_Wrapper.CreateSignUpTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            db.execSQL(Signup_Wrapper.DropSignUpTable);
            db.execSQL(Food_Wrapper.DropFoodCategoriesTable);
            onCreate(db);
        }

    }

    public boolean CreateAcoount(Signup_Model s) {
        boolean IsSuccess = false;
        SQLiteDatabase database = getWritableDatabase();
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(Signup_Wrapper.COLUMN_EMAIL, s.getEmail());
            values.put(Signup_Wrapper.COLUMN_NAME, s.getName());
            values.put(Signup_Wrapper.COLUMN_PASSWORD, s.getPassword());
            values.put(Signup_Wrapper.COLUMN_PHONE_NO, s.getPhoneNo());

            Log.d(TAG, "The Data Before insertion.................");

            database.insertOrThrow(Signup_Wrapper.TABLE_NAME, null, values);
            database.setTransactionSuccessful();
            IsSuccess = true;
        } catch (Exception ex) {
            Log.d(TAG, "Error while adding" + s.getName());
            IsSuccess = false;
        } finally {
            database.endTransaction();
        }
        return IsSuccess;
    }

    public String SearchPassword(String email) {
        SQLiteDatabase liteDatabase = getReadableDatabase();
        String query = Signup_Wrapper.SelectSignUpAllRecords;
        Cursor cursor = liteDatabase.rawQuery(query, null);
        String emailAddress, password;

        password = "Not Found";
        if (cursor.moveToFirst()) {
            do {
                emailAddress = cursor.getString(cursor.getColumnIndex(Signup_Wrapper.COLUMN_EMAIL));
                Log.d("ddd", "UserName.." + emailAddress);
                Log.d("ddd", "User.." + email);
                if (emailAddress.equals(email)) {
                    password = cursor.getString(cursor.getColumnIndex(Signup_Wrapper.COLUMN_PASSWORD));

                    Log.d("ddd", "Password.." + password);

                }
            } while (cursor.moveToNext());
        }
        return password;

    }

    public boolean AddNewCategory(FoodCategoryModel f) {
        SQLiteDatabase database = getWritableDatabase();
        boolean isSucces = false;
        database.beginTransaction();
        try {
            ContentValues values = new ContentValues();
            values.put(Food_Wrapper.COLUMN_NAME, f.getTitle());
            values.put(Food_Wrapper.COLUMN_INGREDIENTS, f.getIngredients());
            values.put(Food_Wrapper.COLUMN_RECIPE, f.getRecipeDetail());
            values.put(Food_Wrapper.COLUMN_PICTURE, f.getFoodPic());

            database.insertOrThrow(Food_Wrapper.TABLE_NAME, null, values);
            database.setTransactionSuccessful();
            isSucces = true;
        } catch (Exception ex) {
            isSucces = false;
        } finally {
            database.endTransaction();
        }
        return isSucces;
    }

    public List<FoodCategoryModel> getAllFoodList() {
        SQLiteDatabase database = getReadableDatabase();
        boolean isSucces = false;
        Cursor cursor = database.rawQuery(Food_Wrapper.SelectAllFoodCategories, null);

        List<FoodCategoryModel> list = new ArrayList<>(cursor.getCount());

        for (cursor.moveToNext(); !cursor.isAfterLast(); cursor.moveToNext()) {

            FoodCategoryModel model = new FoodCategoryModel();
            model.setTitle(cursor.getString(cursor.getColumnIndex(Food_Wrapper.COLUMN_NAME)));
            model.setFoodPic(cursor.getString(cursor.getColumnIndex(Food_Wrapper.COLUMN_PICTURE)));
            model.setIngredients(cursor.getString(cursor.getColumnIndex(Food_Wrapper.COLUMN_INGREDIENTS)));
            model.setRecipeDetail(cursor.getString(cursor.getColumnIndex(Food_Wrapper.COLUMN_RECIPE)));

            list.add(model);
        }

        return list;
    }
}
