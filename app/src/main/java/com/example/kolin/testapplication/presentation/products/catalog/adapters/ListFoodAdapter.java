package com.example.kolin.testapplication.presentation.products.catalog.adapters;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.Food;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by kolin on 12.09.2016.
 */

public class ListFoodAdapter extends RecyclerView.Adapter<ListFoodAdapter.ListFoodViewHolder> {

    private List<Food> listFood;
    private Set<Food> checkedFood;
    private OnClickListFoodAdapter listener;
    private Resources resources;

    public interface OnClickListFoodAdapter {
        void onLongClickItemView(int position);

        void onClickBtnAddToCalc(int position);

        void onRemoveFromCalculator(int position);
    }



    public ListFoodAdapter() {
        listFood = new ArrayList<>();
        checkedFood = new HashSet<>();
    }

    @Override
    public ListFoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        resources = parent.getResources();
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_list_fragment, parent, false);
        return new ListFoodViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(ListFoodViewHolder holder, int position) {
        Food food = listFood.get(position);
        if (checkedFood.contains(food)){
            holder.checkBoxAddToCalc.setChecked(true);
        }
        holder.textViewNameProduct.setText(food.getName());
        holder.textViewB.setText(resources.getString(R.string.b)
                + String.valueOf(food.getB()));
        holder.textViewJ.setText(resources.getString(R.string.j)
                + String.valueOf(food.getJ()));
        holder.textViewY.setText(resources.getString(R.string.y)
                + String.valueOf(food.getY()));
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    public void addAll(List<Food> listFood) {
        this.listFood.clear();
        this.listFood.addAll(listFood);
        notifyDataSetChanged();
    }

    public void clearAll() {
        this.listFood.clear();
        notifyDataSetChanged();
    }

    class ListFoodViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewNameProduct;
        private TextView textViewB;
        private TextView textViewJ;
        private TextView textViewY;
        private CheckBox checkBoxAddToCalc;

        public ListFoodViewHolder(View itemView) {
            super(itemView);

            textViewNameProduct = (TextView) itemView.findViewById(R.id.list_food_name_product);
            textViewB = (TextView) itemView.findViewById(R.id.list_food_b);
            textViewJ = (TextView) itemView.findViewById(R.id.list_food_j);
            textViewY = (TextView) itemView.findViewById(R.id.list_food_y);
            checkBoxAddToCalc = (CheckBox) itemView.findViewById(R.id.list_food_check_box_calc);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (listener != null) {
                        listener.onLongClickItemView(getLayoutPosition());
                    }
                    return true;
                }
            });

            checkBoxAddToCalc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox checkBox = (CheckBox) v;
                    if (listener != null) {
                        if (checkBox.isChecked()) {
                            listener.onClickBtnAddToCalc(getLayoutPosition());
                        } else {
                            listener.onRemoveFromCalculator(getLayoutPosition());
                        }
                    }
                }
            });
        }
    }

    public void setListener(OnClickListFoodAdapter listener) {
        this.listener = listener;
    }

    public void setCheckedFood(Set<Food> checkedFood) {
        this.checkedFood = checkedFood;
    }

    public Set<Food> getCheckedFood() {
        return checkedFood;
    }
}
