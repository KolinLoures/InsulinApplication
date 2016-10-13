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
import com.example.kolin.testapplication.domain.VitalCharacteristic;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by kolin on 09.10.2016.
 */

public class CalculatorHistoryAdapter extends RecyclerView.Adapter<CalculatorHistoryAdapter.ViewHolder> {

    private List<CalculatedFood> list = new ArrayList<>();
    private Locale locale = Locale.ENGLISH;

    private OnClickCalculatorHistoryAdapterListener listener;

    public interface OnClickCalculatorHistoryAdapterListener {
        void onClickRemove(int position);

        void onClickCalculate(int position);
    }

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
        for (int i = 0; i < foodList.size(); i++) {
            Food food = foodList.get(i);
            if (i + 1 != foodList.size()) {
                textProducts += food.getName() + " (" + food.getOwner() + ")" + " \n";
            } else {
                textProducts += food.getName() + " (" + food.getOwner() + ")";
            }
        }

        VitalCharacteristic vitalCharacteristic = calculatedFood.getVitalCharacteristic();

        holder.textViewValueHe.setText(String.format(locale, "%.2f", vitalCharacteristic.getHe()));
        holder.textViewValueKone.setText(String.format(locale, "%.2f", vitalCharacteristic.getkOne()));
        holder.textViewValueKtwo.setText(String.format(locale, "%.2f", vitalCharacteristic.getkTwo()));
        holder.textViewValueGi.setText(String.format(locale, "%.2f", vitalCharacteristic.getGi()));
        holder.textViewProducts.setText(textProducts);
        holder.textViewHE.setText(String.format(locale, "%.2f", calculatedFood.getSumHe()));
        holder.textViewInsulin.setText(String.format(locale, "%.2f", calculatedFood.getSumInsulin()));
        holder.textViewNum.setText(String.valueOf(calculatedFood.getId()));
        holder.textViewPartOfDay.setText(vitalCharacteristic.getName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewValueHe;
        private TextView textViewValueKtwo;
        private TextView textViewValueKone;
        private TextView textViewValueGi;
        private TextView textViewProducts;
        private TextView textViewHE;
        private TextView textViewInsulin;
        private TextView textViewNum;
        private TextView textViewPartOfDay;

        private ImageButton btnClear;
        private ImageButton btnReCalc;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewNum = (TextView) itemView.findViewById(R.id.item_history_value_num);
            textViewHE = (TextView) itemView.findViewById(R.id.item_history_he);
            textViewInsulin = (TextView) itemView.findViewById(R.id.item_history_insulin);
            textViewProducts = (TextView) itemView.findViewById(R.id.item_history_products);
            textViewValueHe = (TextView) itemView.findViewById(R.id.item_history_value_he);
            textViewValueKone = (TextView) itemView.findViewById(R.id.item_history_value_k_one);
            textViewValueKtwo = (TextView) itemView.findViewById(R.id.item_history_value_k_two);
            textViewValueGi = (TextView) itemView.findViewById(R.id.item_history_value_gi);
            textViewPartOfDay = (TextView) itemView.findViewById(R.id.item_history_value_part_of_day);

            btnClear = (ImageButton) itemView.findViewById(R.id.item_history_btn_clear);
            btnClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClickRemove(getLayoutPosition());
                    }
                }
            });
            btnReCalc = (ImageButton) itemView.findViewById(R.id.item_history_btn_re_calc);
            btnReCalc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClickCalculate(getLayoutPosition());
                    }
                }
            });
        }
    }

    public void addAll(List<CalculatedFood> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        this.list.clear();
        notifyDataSetChanged();
    }

    public void setListener(OnClickCalculatorHistoryAdapterListener listener) {
        this.listener = listener;
    }
}
