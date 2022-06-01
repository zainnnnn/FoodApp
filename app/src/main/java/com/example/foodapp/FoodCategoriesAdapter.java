package com.example.foodapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class FoodCategoriesAdapter extends RecyclerView.Adapter<FoodCategoriesAdapter.ViewHolder> {


    Context context;
    List<FoodCategoryModel> foodCategoryList;

    public FoodCategoriesAdapter(Context context, List<FoodCategoryModel> foodCategoryList) {
        this.context = context;
        this.foodCategoryList = foodCategoryList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.rowfoodcategory,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        FoodCategoryModel model = foodCategoryList.get(i);
        viewHolder.tvTitle.setText(model.getTitle());
        viewHolder.tvIngridents.setText(model.getIngredients());
        viewHolder.tvRecipe.setText(model.getRecipeDetail());

        Glide.with(context).load(model.getFoodPic()).into(viewHolder.imageView);


    }

    @Override
    public int getItemCount() {
        return foodCategoryList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView tvTitle,tvIngridents,tvRecipe;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewForRow);
            tvTitle = itemView.findViewById(R.id.tv_FoodTitleForRow);
            tvIngridents = itemView.findViewById(R.id.tv_FoodIngredientsForRow);
            tvRecipe = itemView.findViewById(R.id.tv_FoodRecipeForRow);
        }
    }
}


