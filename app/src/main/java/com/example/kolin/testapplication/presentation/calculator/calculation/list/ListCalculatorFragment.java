package com.example.kolin.testapplication.presentation.calculator.calculation.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.CalculatedFood;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.presentation.calculator.calculation.adapters.CalculationAdapter;

import java.util.List;

public class ListCalculatorFragment extends Fragment implements ListCalculatorView {


    private RecyclerView recyclerView;

    private CalculationAdapter adapter;
    private ListCalculatorPresenter presenter;
    private TextView textViewHe;
    private TextView textViewInsulin;

//    private VitalCharacteristic vitalCharacteristic;

    private ListCalculationFragmentListener listener;

    public interface ListCalculationFragmentListener {
        void onClickChangeFood(Food food);
    }

    public ListCalculatorFragment() {
    }

    public static ListCalculatorFragment newInstance() {
        ListCalculatorFragment fragment = new ListCalculatorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        vitalCharacteristic = new VitalCharacteristic();
//        vitalCharacteristic.setHe(10);
//        vitalCharacteristic.setkOne(1.5);
//        vitalCharacteristic.setkTwo(0.1);
//        vitalCharacteristic.setGi(60);

        adapter = new CalculationAdapter();

        presenter = new ListCalculatorPresenter();
        presenter.attachView(this);

        adapter.setListener(new CalculationAdapter.OnClickCalculationAdapterListener() {
            @Override
            public void onClickButtonRemove(Food food) {
                presenter.deleteFromCalculated(food);
            }

            @Override
            public void onClickButtonChange(Food food) {
                if (listener != null) {
                    listener.onClickChangeFood(food);
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_calculator, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_calculation);

        textViewHe = (TextView) view.findViewById(R.id.result_he_value);
        textViewInsulin = (TextView) view.findViewById(R.id.result_insulin_value);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter.load();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ListCalculationFragmentListener) {
            listener = (ListCalculationFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() +
                    "must implement ListCalculationFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        adapter.setListener(null);
        presenter.detachView();
        super.onDetach();
    }

    @Override
    public void showCalculationFood(List<Food> foodList) {
        adapter.addAll(foodList);
    }

    @Override
    public void showSnackBar(String title) {
        Snackbar.make(getView(), title, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showComputeResult(CalculatedFood calculatedFood) {
        textViewHe.setText("");
        textViewInsulin.setText("");
    }
}
