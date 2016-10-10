package com.example.kolin.testapplication.presentation.calculator.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.CalculatedFood;
import com.example.kolin.testapplication.domain.Food;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 09.10.2016.
 */

public class CalculatorHistoryAdapter extends RecyclerView.Adapter<CalculatorHistoryAdapter.ViewHolder> {

    private List<CalculatedFood> list = new ArrayList<>();
    private SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm");


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calculator_history, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CalculatedFood calculatedFood = list.get(position);
        String textProducts = "";
        List<Food> foodList = calculatedFood.getFoodList();
        for (Food f: foodList){
            textProducts += f.getName() + " \n";
        }
        holder.textViewValueHe.setText(String.format("%.2f",calculatedFood.getValueHe()));
        holder.textViewValueKone.setText(String.format("%.2f",calculatedFood.getValueKone()));
        holder.textViewValueKtwo.setText(String.format("%.2f",calculatedFood.getValueKtwo()));
        holder.textViewValueGi.setText(String.format("%.2f",calculatedFood.getValueGi()));
        holder.textViewProducts.setText(textProducts);
        holder.textViewHE.setText(String.format("%.2f", calculatedFood.getSumHe()));
        holder.textViewInsulin.setText(String.format("%.2f", calculatedFood.getSumInsulin()));
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textViewValueHe;
        private TextView textViewValueKtwo;
        private TextView textViewValueKone;
        private TextView textViewValueGi;
        private TextView textViewProducts;
        private TextView textViewHE;
        private TextView textViewInsulin;

        private ImageButton btnClear;
        private ImageButton btnCreate;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewHE = (TextView) itemView.findViewById(R.id.item_history_he);
            textViewInsulin = (TextView) itemView.findViewById(R.id.item_history_insulin);
            textViewProducts = (TextView) itemView.findViewById(R.id.item_history_products);
            textViewValueHe = (TextView) itemView.findViewById(R.id.item_history_value_he);
            textViewValueKone = (TextView) itemView.findViewById(R.id.item_history_value_k_one);
            textViewValueKtwo = (TextView) itemView.findViewById(R.id.item_history_value_k_two);
            textViewValueGi = (TextView) itemView.findViewById(R.id.item_history_value_gi);

            btnClear = (ImageButton) itemView.findViewById(R.id.item_history_btn_clear);
            btnCreate = (ImageButton) itemView.findViewById(R.id.item_history_btn_create);
        }
    }

    public void addAll(List<CalculatedFood> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void clear(){
        this.list.clear();
        notifyDataSetChanged();
    }
}
