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

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.presentation.calculator.adapters.CalculationAdapter;

import java.util.List;

public class ListCalculatorFragment extends Fragment implements ListCalculatorView {

    private static final String ARG = "arg_key";

    private RecyclerView recyclerView;

    private CalculationAdapter adapter;
    private ListCalculatorPresenter presenter;

    private ListCalculationFragmentListener listener;

    public interface ListCalculationFragmentListener {
        void onClickChangeFood(Food food);
    }

    public ListCalculatorFragment() {
    }

    public static ListCalculatorFragment newInstance(Long id) {
        ListCalculatorFragment fragment = new ListCalculatorFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG, id);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new ListCalculatorPresenter();
        presenter.attachView(this);

        adapter = new CalculationAdapter();
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
        presenter.unSubscribe();
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
}
