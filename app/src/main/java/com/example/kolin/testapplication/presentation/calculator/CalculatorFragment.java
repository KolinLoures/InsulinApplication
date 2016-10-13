package com.example.kolin.testapplication.presentation.calculator;

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

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.CalculatedFood;
import com.example.kolin.testapplication.presentation.calculator.adapters.CalculatorHistoryAdapter;

import java.util.List;

public class CalculatorFragment extends Fragment implements CalculatorView {

    private RecyclerView recyclerView;
    private FloatingActionButton fab;

    private OnClickCalculationFragmentListener listener;

    private CalculatorPresenter presenter;
    private CalculatorHistoryAdapter adapter;

    public interface OnClickCalculationFragmentListener {

        void onClickFab(View v);

        void onClickReCalc(Long id);
    }

    public CalculatorFragment() {
    }


    public static CalculatorFragment newInstance() {
        CalculatorFragment fragment = new CalculatorFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new CalculatorPresenter();
        presenter.attachView(this);

        adapter = new CalculatorHistoryAdapter();
        adapter.setListener(new CalculatorHistoryAdapter.OnClickCalculatorHistoryAdapterListener() {
            @Override
            public void onClickRemove(int position) {
                presenter.deleteResultCalculation(position);
            }

            @Override
            public void onClickCalculate(int position) {
                presenter.openCalculator(position);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.calculator_fab);
        recyclerView = (RecyclerView) view.findViewById(R.id.calculator_recycler_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickFab(v);
                }
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter.load();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (OnClickCalculationFragmentListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement OnClickCalculationFragmentListener");
        }
    }

    @Override
    public void onDetach() {
        listener = null;
        presenter.detachView();
        presenter.unSubscribe();
        fab.setOnClickListener(null);
        adapter.setListener(null);
        super.onDetach();
    }


    @Override
    public void showLoadedData(List<CalculatedFood> list) {
        adapter.addAll(list);
    }

    @Override
    public void showSnackBar(String title) {
        Snackbar.make(getView(), title, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void openCalculator(Long id) {
        if (listener != null) {
            listener.onClickReCalc(id);
        }
    }
}
