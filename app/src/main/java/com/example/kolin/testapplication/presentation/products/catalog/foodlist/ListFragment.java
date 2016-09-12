package com.example.kolin.testapplication.presentation.products.catalog.foodlist;

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
import com.example.kolin.testapplication.domain.Food;

import java.util.List;

public class ListFragment extends Fragment implements ListFoodView {

    private ListFoodAdapter adapter;

    private ListFoodPresenter presenter;

    private String currentItemOfGroup;


    private OnSwipeRightListFragment listener;

    public ListFragment() {
    }

    public interface OnSwipeRightListFragment{
        void onSwipeRight();
    }

    public static ListFragment newInstance(String itemName) {
        ListFragment fragment = new ListFragment();
        Bundle args = new Bundle();
        args.putString("itemName", itemName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        currentItemOfGroup = bundle.getString("itemName");

        presenter = new ListFoodPresenter();
        presenter.attachView(this);

        adapter = new ListFoodAdapter(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        RecyclerView recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_list_fragment);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        return root;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter.load(currentItemOfGroup);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSwipeRightListFragment){
            listener = (OnSwipeRightListFragment) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnSwipeRightListFragment");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    @Override
    public void showLoadedFood(List<Food> listFood) {
        adapter.addAll(listFood);
    }


}
