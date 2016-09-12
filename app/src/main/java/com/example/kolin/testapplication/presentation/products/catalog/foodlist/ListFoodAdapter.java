package com.example.kolin.testapplication.presentation.products.catalog.foodlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 12.09.2016.
 */

public class ListFoodAdapter extends RecyclerView.Adapter<ListFoodAdapter.ListFoodViewHolder> {

    private List<Food> listFood;
    private Context context;

    public ListFoodAdapter(Context context) {
        this.context = context;
        listFood = new ArrayList<>();
    }

    @Override
    public ListFoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_fragment, parent, false);
        return new ListFoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListFoodViewHolder holder, int position) {
        Food food = listFood.get(position);
        holder.textViewNameProduct.setText(food.getName());
        holder.textViewB.append(String.valueOf(food.getB()));
        holder.textViewJ.append(String.valueOf(food.getJ()));
        holder.textViewY.append(String.valueOf(food.getY()));
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    public void addAll(List<Food> listFood){
        this.listFood.clear();
        this.listFood.addAll(listFood);
        notifyDataSetChanged();
    }

    public void clearAll(){
        this.listFood.clear();
        notifyDataSetChanged();
    }

    class ListFoodViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewNameProduct;
        private TextView textViewB;
        private TextView textViewJ;
        private TextView textViewY;
        private ImageView imageViewFavorite;

        public ListFoodViewHolder(View itemView) {
            super(itemView);

            textViewNameProduct = (TextView) itemView.findViewById(R.id.list_food_name_product);
            textViewB = (TextView) itemView.findViewById(R.id.list_food_b);
            textViewJ = (TextView) itemView.findViewById(R.id.list_food_j);
            textViewY = (TextView) itemView.findViewById(R.id.list_food_y);
            imageViewFavorite = (ImageView) itemView.findViewById(R.id.list_food_add_to_favorite);
        }
    }
}
