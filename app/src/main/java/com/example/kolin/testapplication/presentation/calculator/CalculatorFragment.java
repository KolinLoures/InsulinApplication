package com.example.kolin.testapplication.presentation.calculator;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kolin.testapplication.R;

public class CalculatorFragment extends Fragment {

    private FloatingActionButton fab;

    private OnClickCalculationFragmentListener listener;

    public interface OnClickCalculationFragmentListener {
        void onClickFab(View v);
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calculator, container, false);
        fab = (FloatingActionButton) view.findViewById(R.id.calculator_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onClickFab(v);
            }
        });
        return view;
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
        super.onDetach();
        listener = null;
        fab.setOnClickListener(null);
    }


    public void onClickFab(View view) {
        if (listener != null) {
            listener.onClickFab(view);
        }
    }

}
