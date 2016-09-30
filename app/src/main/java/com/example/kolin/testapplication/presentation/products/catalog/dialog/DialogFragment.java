package com.example.kolin.testapplication.presentation.products.catalog.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.Food;
import com.example.kolin.testapplication.presentation.common.SimpleDividerItemDecoration;
import com.example.kolin.testapplication.presentation.products.catalog.adapters.DialogAdapter;

import java.util.List;


public class DialogFragment extends AppCompatDialogFragment implements DialogView {

    private static final String keyToArgs = "title";

    private RecyclerView recyclerView;
    private TextView textViewTitle;
    private Button btnBack;
    private Button btnCalculate;

    private DialogAdapter adapter;
    private DialogPresenter presenter;

    public DialogFragment() {
        // Required empty public constructor
    }


    public static DialogFragment newInstance(String title) {
        DialogFragment fragment = new DialogFragment();
        Bundle args = new Bundle();
        args.putString(keyToArgs, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new DialogPresenter();
        adapter = new DialogAdapter();

        adapter.setListener(new DialogAdapter.OnClickItemDialogAdapter() {
            @Override
            public void onClickDeleteItem(Food food) {
                presenter.removeFoodFromCalc(food);
            }
        });

        presenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.dialog_recycler_view);
        textViewTitle = (TextView) view.findViewById(R.id.dialog_title);
        btnBack = (Button) view.findViewById(R.id.dialog_btn_back);
        btnCalculate = (Button) view.findViewById(R.id.dialog_btn_calculate);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));

        textViewTitle.setText(getArguments().getString(keyToArgs));

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

    @Override
    public void onDetach() {
        adapter.setListener(null);
        super.onDetach();
    }

    @Override
    public void showLoadedData(List<Food> foodList) {
        adapter.addAll(foodList);
    }

    @Override
    public void showToast(String title) {
        Toast.makeText(getContext(), title, Toast.LENGTH_SHORT).show();
    }
}
