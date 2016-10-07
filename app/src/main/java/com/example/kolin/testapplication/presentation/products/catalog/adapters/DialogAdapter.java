package com.example.kolin.testapplication.presentation.products.catalog.adapters;

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
 * Created by kolin on 27.09.2016.
 */

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.DialogHolder> {

    private List<Food> list = new ArrayList<>();

    private OnClickItemDialogAdapter listener;

    public interface OnClickItemDialogAdapter {
        void onClickDeleteItem(Food food);
    }

    @Override
    public DialogAdapter.DialogHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_dialog, parent, false);
        return new DialogAdapter.DialogHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DialogAdapter.DialogHolder holder, int position) {
        Food food = list.get(position);
        holder.textViewName.setText(food.getName() + " (" + food.getOwner() + ")");
        holder.textViewB.setText(String.valueOf(food.getB()));
        holder.textViewJ.setText(String.valueOf(food.getJ()));
        holder.textViewY.setText(String.valueOf(food.getY()));
        holder.textViewYWeight.setText(String.valueOf(FoodCalculation.getYOnWeight(food)));
        holder.textViewYWeightText.setText("У в " + food.getWeight() + "г");
        holder.textViewWeight.setText(String.valueOf(food.getWeight()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DialogHolder extends RecyclerView.ViewHolder {

        private TextView textViewB;
        private TextView textViewJ;
        private TextView textViewY;
        private TextView textViewWeight;
        private TextView textViewName;
        private TextView textViewYWeight;
        private TextView textViewYWeightText;

        private ImageButton imageButtonRemove;


        public DialogHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.dialog_name_product);
            textViewB = (TextView) itemView.findViewById(R.id.dialog_food_b);
            textViewJ = (TextView) itemView.findViewById(R.id.dialog_food_j);
            textViewY = (TextView) itemView.findViewById(R.id.dialog_food_y);
            textViewWeight = (TextView) itemView.findViewById(R.id.dialog_food_weight);
            textViewYWeightText = (TextView) itemView.findViewById(R.id.dialog_food_y_weight_text);
            imageButtonRemove = (ImageButton) itemView.findViewById(R.id.dialog_remove_from_calc);
            textViewYWeight = (TextView) itemView.findViewById(R.id.dialog_food_y_weight);


            imageButtonRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClickDeleteItem(list.get(getLayoutPosition()));
                    }
                }
            });

        }
    }

    public void clearAll() {
        list.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Food> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setListener(OnClickItemDialogAdapter listener) {
        this.listener = listener;
    }
}
