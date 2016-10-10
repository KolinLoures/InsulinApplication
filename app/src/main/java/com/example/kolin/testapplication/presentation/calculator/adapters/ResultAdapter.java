package com.example.kolin.testapplication.presentation.calculator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 07.10.2016.
 */

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.ViewHolder> {

    private List<Food> foodList = new ArrayList<>();


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Food food = foodList.get(position);
        holder.textViewProductWeight.setText(String.valueOf(food.getWeight()));
        holder.textViewProductName.setText(food.getName() + " (" + food.getOwner() + ")");
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewProductName;
        private TextView textViewProductWeight;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewProductName = (TextView) itemView.findViewById(R.id.item_result_product_name);
            textViewProductWeight = (TextView) itemView.findViewById(R.id.item_result_product_weight);
        }
    }

    public void addAll(List<Food> foodList){
        this.foodList.clear();
        this.foodList.addAll(foodList);
        notifyDataSetChanged();
    }

    public void clearAll(){
        foodList.clear();
        notifyDataSetChanged();
    }
}
