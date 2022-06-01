package com.example.foodapp;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

public class FoodCategoryActivity extends AppCompatActivity {

    String picPath;
    private final int RequestCodeGallery = 1;
    ImageView imageView;

    List<FoodCategoryModel> modelList;
    dbhelper dbHelper;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_category);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbHelper = dbhelper.getInstance(this);
        recyclerView = findViewById(R.id.rc_foodCategory);
        modelList = dbHelper.getAllFoodList();
        FoodCategoriesAdapter adapter = new FoodCategoriesAdapter(this, modelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(FoodCategoryActivity.this);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.setContentView(R.layout.dialoge_for_add);
                final EditText etName, etIngredients, etRecipe;
                Button btnAdd;

                imageView = dialog.findViewById(R.id.imageViewForDialog);
                etName = dialog.findViewById(R.id.etDishTitleForDialog);
                etIngredients = dialog.findViewById(R.id.etDishIngredientsForDialog);
                etRecipe = dialog.findViewById(R.id.etDishRecipeForDialog);
                btnAdd = dialog.findViewById(R.id.btnAddNow);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(Intent.ACTION_PICK);
                        File file= Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                        String imagepath=file.getPath();
                        Uri uri=Uri.parse(imagepath);
                        intent.setDataAndType(uri,"image/*");
                        startActivityForResult(intent,RequestCodeGallery);

                    }
                });
                btnAdd.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(etName.getText().toString().isEmpty()){
                            etName.setError("Enter name");
                        }
                        else if(etRecipe.getText().toString().isEmpty()){
                            etRecipe.setError("Enter Recipi");
                        }else if(etIngredients.getText().toString().isEmpty()){
                            etIngredients.setError("Enter ingredients");
                        }else {

                            dbhelper dbHelper=dbhelper.getInstance(FoodCategoryActivity.this);
                            if(dbHelper.AddNewCategory(getDataFromUI())){
                                Toast.makeText(FoodCategoryActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                                FoodCategoryActivity.this.recreate();
                                dialog.dismiss();
                            }

                        }

                    }
                    public FoodCategoryModel getDataFromUI(){
                        FoodCategoryModel model = new FoodCategoryModel();
                        model.setTitle(etName.getText().toString());
                        model.setIngredients(etIngredients.getText().toString());
                        model.setRecipeDetail(etRecipe.getText().toString());
                        model.setFoodPic(picPath);
                        return model;
                    }
                });
                dialog.show();
            }

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RequestCodeGallery)
        {
            if (resultCode == RESULT_OK)
            {
                Uri uri = data.getData();
                if (uri != null)
                {
                    try {

                        InputStream stream = getContentResolver().openInputStream(uri);
                        Bitmap bitmap   = BitmapFactory.decodeStream(stream);


                        imageView.setImageBitmap(bitmap);
                        picPath = uri.toString();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
