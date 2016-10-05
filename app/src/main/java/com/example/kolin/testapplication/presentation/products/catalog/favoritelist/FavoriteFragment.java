package com.example.kolin.testapplication.presentation.products.catalog.favoritelist;

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
import com.example.kolin.testapplication.presentation.common.SimpleDividerItemDecoration;
import com.example.kolin.testapplication.presentation.products.catalog.adapters.FavoriteAdapter;

import java.util.List;


public class FavoriteFragment extends Fragment implements FavoriteView {

    private static final String key = "loadedData";

    private FavoriteAdapter adapter;
    private FavoritePresenter presenter;

    private RecyclerView recyclerView;

    private OnClickAddFavoriteFoodToCalculator listener;

    public interface OnClickAddFavoriteFoodToCalculator {
        void onClickAddFavoriteToCalculator(Food food);
    }

    public FavoriteFragment() {
        // Required empty public constructor
    }

    public static FavoriteFragment newInstance() {
        FavoriteFragment fragment = new FavoriteFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = new FavoritePresenter();
        adapter = new FavoriteAdapter();
        setListenerAdapter();

        presenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_favorite, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_favorite_fragment);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        return root;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter.loadFavorite();
//        presenter.loadCalculated();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnClickAddFavoriteFoodToCalculator)
            listener = (OnClickAddFavoriteFoodToCalculator) context;
        else {
            throw new RuntimeException(context.toString() +
                    "must implement OnClickAddFavoriteFoodToCalculator");
        }

    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detachView();
        presenter.unSubscribe();
        adapter.setListener(null);
    }

    @Override
    public void showFavoriteFood(List<Food> foodList) {
        adapter.addAll(foodList);
    }

    @Override
    public void showSnackBar(String title) {
        Snackbar.make(recyclerView, title, Snackbar.LENGTH_LONG).show();
    }

    private void setListenerAdapter() {
        adapter.setListener(new FavoriteAdapter.OnClickItemFavoriteAdapter() {
            @Override
            public void onClickAddToCalc(Food food) {
                if (listener != null){
                    listener.onClickAddFavoriteToCalculator(food);
                }
            }

            @Override
            public void onClickRemoveFromCalc(Food food) {
                presenter.deleteCalculationFood(food);
            }

            @Override
            public void onLongClickItem(int position) {
                presenter.deleteFromFavorite(position);
            }
        });
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void addCheckedFood(List<Food> foodList) {
        adapter.addCheckedFood(foodList);
    }
}
