package com.example.kolin.testapplication.presentation.index.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.VitalCharacteristic;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by kolin on 10.10.2016.
 */

public class IndexAdapter extends RecyclerView.Adapter<IndexAdapter.ViewHolder> {

    private List<VitalCharacteristic> vitalCharacteristicList = new ArrayList<>();
    private Locale locale = Locale.ENGLISH;

    private OnClickIndexAdapterListener listener;

    public interface OnClickIndexAdapterListener {
        void onClickBtnRemove(VitalCharacteristic vitalCharacteristic);

        void onClickBtnCreate(VitalCharacteristic vitalCharacteristic);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_index, parent, false);
        return new IndexAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        VitalCharacteristic characteristic = vitalCharacteristicList.get(position);

        holder.textViewName.setText(characteristic.getName());
        holder.textViewHe.setText(String.format(locale, "%.2f", characteristic.getHe()));
        holder.textViewGi.setText(String.format(locale, "%.2f", characteristic.getGi()));
        holder.textViewKOne.setText(String.format(locale, "%.2f", characteristic.getkOne()));
        holder.textViewKTwo.setText(String.format(locale, "%.2f", characteristic.getkTwo()));
    }

    @Override
    public int getItemCount() {
        return vitalCharacteristicList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewName;
        private TextView textViewHe;
        private TextView textViewGi;
        private TextView textViewKOne;
        private TextView textViewKTwo;

        private ImageButton imageButtonCreate;
        private ImageButton imageButtonClear;


        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.item_index_name);
            textViewHe = (TextView) itemView.findViewById(R.id.item_index_he);
            textViewGi = (TextView) itemView.findViewById(R.id.item_index_gi);
            textViewKOne = (TextView) itemView.findViewById(R.id.item_index_k_one);
            textViewKTwo = (TextView) itemView.findViewById(R.id.item_index_k_two);

            imageButtonClear = (ImageButton) itemView.findViewById(R.id.item_index_btn_clear);
            imageButtonCreate = (ImageButton) itemView.findViewById(R.id.item_index_btn_create);

            imageButtonClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClickBtnRemove(vitalCharacteristicList.get(getLayoutPosition()));
                    }
                }
            });

            imageButtonCreate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onClickBtnCreate(vitalCharacteristicList.get(getLayoutPosition()));
                    }
                }
            });

        }
    }

    public void addAll(List<VitalCharacteristic> list) {
        vitalCharacteristicList.clear();
        vitalCharacteristicList.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        vitalCharacteristicList.clear();
        notifyDataSetChanged();
    }

    public void setListener(OnClickIndexAdapterListener listener) {
        this.listener = listener;
    }
}
