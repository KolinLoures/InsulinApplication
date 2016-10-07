package com.example.kolin.testapplication.presentation.calculator.calculation.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.VitalCharacteristic;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by kolin on 07.10.2016.
 */

public class ArrayVitalAdapter extends ArrayAdapter<VitalCharacteristic> {

    private List<VitalCharacteristic> list = new ArrayList<>();

    public ArrayVitalAdapter(Context context, List<VitalCharacteristic> list) {
        super(context, R.layout.item_spinner, list);
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        VitalCharacteristic characteristic = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_spinner, parent, false);
        }

        TextView textName = (TextView) convertView.findViewById(R.id.item_spinner_name_character);
        TextView textHe = (TextView) convertView.findViewById(R.id.item_spinner_he);
        TextView textKone = (TextView) convertView.findViewById(R.id.item_spinner_k_one);
        TextView textKtwo = (TextView) convertView.findViewById(R.id.item_spinner_k_two);
        TextView textGi = (TextView) convertView.findViewById(R.id.item_spinner_gi);

        textName.setText(characteristic.getName());
        textGi.setText(String.valueOf(characteristic.getGi()));
        textHe.setText(String.valueOf(characteristic.getHe()));
        textKone.setText(String.valueOf(characteristic.getkOne()));
        textKtwo.setText(String.valueOf(characteristic.getkTwo()));

        return convertView;
    }

    @Override
    public void addAll(@NonNull Collection<? extends VitalCharacteristic> collection) {
        list.clear();
        list.addAll(collection);
        notifyDataSetChanged();
    }

    @Nullable
    @Override
    public VitalCharacteristic getItem(int position) {
        return list.get(position);
    }

}
