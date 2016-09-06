package com.example.kolin.testapplication.presentation.products.catalog.selection;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.Restaurant;

import java.util.List;

public class SelectionFragment extends Fragment implements SelectionContract.View {


    private SelectionPresenter presenter;
    private RecyclerView recyclerView;
    private SelectionAdapter selectionAdapter;

    public SelectionFragment() {
        // Required empty public constructor
    }

    public static SelectionFragment newInstance(String param1, String param2) {
        SelectionFragment fragment = new SelectionFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new SelectionPresenter();
        presenter.attachView(this);
        presenter.load();
        selectionAdapter = new SelectionAdapter(getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_selection, container, false);

        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_selection);
        recyclerView.setAdapter(selectionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        presenter.load();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showLoadedRestaurants(List<Restaurant> restaurants) {
        selectionAdapter.addAll(restaurants);
    }
}
