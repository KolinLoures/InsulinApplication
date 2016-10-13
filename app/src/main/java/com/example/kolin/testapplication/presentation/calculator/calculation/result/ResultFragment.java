package com.example.kolin.testapplication.presentation.calculator.calculation.result;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
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
import java.util.Locale;


public class ResultFragment extends Fragment implements ResultView {

    private RecyclerView recyclerView;
    private Spinner spinner;
    private TextView textViewBJY;
    private TextView textViewWeight;
    private TextView textViewHE;
    private TextView textViewInsulin;
    private FloatingActionButton fab;


    private ResultPresenter presenter;
    private ResultAdapter adapter;
    private ArrayVitalAdapter arrayVitalAdapter;
    private Locale locale = Locale.ENGLISH;


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

        presenter = new ResultPresenter();
        presenter.attachView(this);

//        presenter.load();

        adapter = new ResultAdapter();
        arrayVitalAdapter = new ArrayVitalAdapter(getContext(), new ArrayList<VitalCharacteristic>());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.result_recycler_view);
        spinner = (Spinner) view.findViewById(R.id.result_spinner);
        fab = (FloatingActionButton) view.findViewById(R.id.result_fab_save);


        textViewBJY = (TextView) view.findViewById(R.id.result_bjy_value);
        textViewWeight = (TextView) view.findViewById(R.id.result_common_weight_value);
        textViewHE = (TextView) view.findViewById(R.id.result_he_value);
        textViewInsulin = (TextView) view.findViewById(R.id.result_insulin_value);

        spinner.setAdapter(arrayVitalAdapter);
        setSpinnerListener();

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.saveCalculation();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter.load();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    private void setSpinnerListener() {
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                presenter.computeAllValues(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println();
            }
        });
    }

    @Override
    public void onDetach() {
        presenter.detachView();
        spinner.setOnItemSelectedListener(null);
        presenter.unSubscribe();
        super.onDetach();
    }

    @Override
    public void showLoadedData(List<Food> foodList) {
        adapter.addAll(foodList);
        presenter.computeAllValues(spinner.getSelectedItemPosition());
    }

    @Override
    public void setVitalCharacteristic(List<VitalCharacteristic> list) {
        arrayVitalAdapter.addAll(list);
    }

    @Override
    public void showComputeResult(CalculatedFood calculatedFood) {
        String sumBWeight = String.format(locale, "%.1f", calculatedFood.getSumBWeight());
        String sumJWeight = String.format(locale, "%.1f", calculatedFood.getSumJWeight());
        String sumYWeight = String.format(locale, "%.1f", calculatedFood.getSumYWeight());
        String textHe = String.format(locale, "%.2f", calculatedFood.getSumHe());
        String textWeight = String.format(locale, "%.1f", calculatedFood.getSumWeight());
        String textInsulin = String.format(locale, "%.2f", calculatedFood.getSumInsulin());

        textViewBJY.setText(sumBWeight + "/" + sumJWeight + "/" + sumYWeight + " г");
        textViewHE.setText(textHe);
        textViewWeight.setText(textWeight);
        textViewInsulin.setText(textInsulin);
    }

    @Override
    public void showSnackBar(String title) {
        Snackbar.make(getView(), title, Snackbar.LENGTH_LONG).show();
    }
}
