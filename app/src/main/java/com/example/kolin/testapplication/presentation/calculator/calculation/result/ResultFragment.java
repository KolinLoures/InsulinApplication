package com.example.kolin.testapplication.presentation.calculator.calculation.result;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.CalculatedFood;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.domain.VitalCharacteristic;
import com.example.kolin.testapplication.presentation.calculator.adapters.ArrayVitalAdapter;
import com.example.kolin.testapplication.presentation.calculator.adapters.ResultAdapter;
import com.example.kolin.testapplication.presentation.common.SimpleDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;


public class ResultFragment extends Fragment implements ResultView {

    private RecyclerView recyclerView;
    private Spinner spinner;
    private TextView textViewBJY;
    private TextView textViewWeight;
    private TextView textViewHE;
    private TextView textViewInsulin;


    private ResultPresenter presenter;
    private ResultAdapter adapter;
    private VitalCharacteristic vitalCharacteristic;
    private VitalCharacteristic vitalCharacteristic2;
    private ArrayVitalAdapter arrayVitalAdapter;

    private List<VitalCharacteristic> vitalCharacteristicList;

    public ResultFragment() {
    }


    public static ResultFragment newInstance() {
        ResultFragment fragment = new ResultFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //TODO: replace vital characteristic to settings
        vitalCharacteristic = new VitalCharacteristic();
        vitalCharacteristic.setName("Утро");
        vitalCharacteristic.setHe(10);
        vitalCharacteristic.setkOne(1.5);
        vitalCharacteristic.setkTwo(0.1);
        vitalCharacteristic.setGi(60);

        vitalCharacteristic2 = new VitalCharacteristic();
        vitalCharacteristic2.setName("Ночь");
        vitalCharacteristic2.setHe(10);
        vitalCharacteristic2.setkOne(0.5);
        vitalCharacteristic2.setkTwo(0.1);
        vitalCharacteristic2.setGi(60);



        presenter = new ResultPresenter();
        presenter.attachView(this);

        adapter = new ResultAdapter();
        vitalCharacteristicList = new ArrayList<>();
        vitalCharacteristicList.add(vitalCharacteristic);
        vitalCharacteristicList.add(vitalCharacteristic2);
        arrayVitalAdapter = new ArrayVitalAdapter(getContext(),vitalCharacteristicList);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.result_recycler_view);
        spinner = (Spinner) view.findViewById(R.id.result_spinner);

        textViewBJY = (TextView) view.findViewById(R.id.result_bjy_value);
        textViewWeight = (TextView) view.findViewById(R.id.result_common_weight_value);
        textViewHE = (TextView) view.findViewById(R.id.result_he_value);
        textViewInsulin = (TextView) view.findViewById(R.id.result_insulin_value);

        spinner.setAdapter(arrayVitalAdapter);
        setSpinnerListener();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter.load();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void setSpinnerListener(){
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.computeAllValues(vitalCharacteristicList.get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public void onDetach() {
        presenter.detachView();
        spinner.setOnItemClickListener(null);
        super.onDetach();
    }

    @Override
    public void showLoadedData(List<Food> foodList) {
        adapter.addAll(foodList);
        presenter.computeAllValues(vitalCharacteristicList.get(spinner.getSelectedItemPosition()));
    }

    @Override
    public void showComputeResult(CalculatedFood calculatedFood) {
        String sumBWeight = String.format("%.1f", calculatedFood.getSumBWeight());
        String sumJWeight = String.format("%.1f", calculatedFood.getSumJWeight());
        String sumYWeight = String.format("%.1f", calculatedFood.getSumYWeight());
        String textHe = String.format("%.2f", calculatedFood.getSumHe());
        String textWeight = String.format("%.1f", calculatedFood.getSumWeight());
        String textInsulin = String.format("%.2f", calculatedFood.getSumInsulin());

        textViewBJY.setText(sumBWeight + "/" + sumJWeight + "/" + sumYWeight + " г");
        textViewHE.setText(textHe);
        textViewWeight.setText(textWeight);
        textViewInsulin.setText(textInsulin);
    }
}
