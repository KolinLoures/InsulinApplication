package com.example.kolin.testapplication.presentation.calculator.calculation.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.calculation.FoodCalculation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 02.10.2016.
 */

public class CalculationAdapter extends RecyclerView.Adapter<CalculationAdapter.ViewHolder> {

    private List<Food> list = new ArrayList<>();

    private OnClickCalculationAdapterListener listener;

    public interface OnClickCalculationAdapterListener {
        void onClickButtonRemove(Food food);

        void onClickButtonChange(Food food);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calculation, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Food food = list.get(position);

        holder.textViewProductName.setText(food.getName() + " (" + food.getOwner() + ")");
        holder.textViewProductWeight.setText(String.valueOf(food.getWeight()));
        holder.textViewProductB.setText(String.valueOf(food.getB()));
        holder.textViewProductJ.setText(String.valueOf(food.getJ()));
        holder.textViewProductY.setText(String.valueOf(food.getY()));
        holder.textViewProductYWeight.setText(getFormattedString(FoodCalculation.getYOnWeight(food)));
        holder.textViewProductTextYWeight.setText("У на " + food.getWeight()+" г");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewProductName;
        private TextView textViewProductWeight;
        private TextView textViewProductB;
        private TextView textViewProductJ;
        private TextView textViewProductY;
        private TextView textViewProductYWeight;
        private TextView textViewProductTextYWeight;

        private ImageButton imageButtonChange;
        private ImageButton imageButtonRemove;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewProductName = (TextView) itemView.findViewById(R.id.item_calculation_product_name);
            textViewProductWeight = (TextView) itemView.findViewById(R.id.item_calculation_product_weight);
            textViewProductB = (TextView) itemView.findViewById(R.id.item_calculation_product_b);
            textViewProductJ = (TextView) itemView.findViewById(R.id.item_calculation_product_j);
            textViewProductY = (TextView) itemView.findViewById(R.id.item_calculation_product_y);
            textViewProductYWeight = (TextView) itemView.findViewById(R.id.item_calculation_product_y_weight);
            textViewProductTextYWeight = (TextView) itemView.findViewById(R.id.item_calculation_product_text_y_weight);

            imageButtonChange = (ImageButton) itemView.findViewById(R.id.item_calculation_change_button);
            imageButtonRemove = (ImageButton) itemView.findViewById(R.id.item_calculation_remove_button);

            imageButtonRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClickButtonRemove(list.get(getLayoutPosition()));
                    }
                }
            });

            imageButtonChange.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null){
                        listener.onClickButtonChange(list.get(getLayoutPosition()));
                    }
                }
            });
        }
    }

    public void clearAll() {
        list.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Food> foodList) {
        list.clear();
        list.addAll(foodList);
        notifyDataSetChanged();
    }

    public List<Food> getList() {
        return list;
    }

    public void setListener(OnClickCalculationAdapterListener listener) {
        this.listener = listener;
    }

    private String getFormattedString(double value){
        return String.format("%.2f", value);
    }
}
