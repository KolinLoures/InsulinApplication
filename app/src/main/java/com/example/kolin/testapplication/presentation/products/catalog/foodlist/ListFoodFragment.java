package com.example.kolin.testapplication.presentation.products.catalog.foodlist;

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
import com.example.kolin.testapplication.domain.FoodCategory;
import com.example.kolin.testapplication.presentation.common.SimpleDividerItemDecoration;
import com.example.kolin.testapplication.presentation.common.SimpleSectionedRecyclerViewAdapter;
import com.example.kolin.testapplication.presentation.common.Updateable;
import com.example.kolin.testapplication.presentation.products.catalog.adapters.ListFoodAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class ListFoodFragment extends Fragment implements ListFoodView, Updateable {

    private static final String keyToArgs = "itemName";
    private static final String keyToState = "state";

    private ListFoodAdapter adapter;

    private ListFoodPresenter presenter;

    private String currentItemOfGroup;

    private RecyclerView recyclerView;
    private List<SimpleSectionedRecyclerViewAdapter.Section> sections = new ArrayList<>();
    private SimpleSectionedRecyclerViewAdapter sectionedAdapter;


    private List<Food> loadedList = new ArrayList<>();


    public ListFoodFragment() {
    }


    public static ListFoodFragment newInstance(String itemName) {
        ListFoodFragment fragment = new ListFoodFragment();
        Bundle args = new Bundle();
        args.putString(keyToArgs, itemName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentItemOfGroup = getArguments().getString(keyToArgs);

        presenter = new ListFoodPresenter();
        adapter = new ListFoodAdapter();
        sectionedAdapter = new SimpleSectionedRecyclerViewAdapter(getContext(), adapter);

        presenter.attachView(this);

        setAdapterListener();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_list, container, false);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_list_fragment);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));
        recyclerView.setAdapter(sectionedAdapter);
        return root;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        clearAll();
        if (savedInstanceState != null) {
            adapter.setCheckedFood((Set<Food>) savedInstanceState.getSerializable("check"));
            presenter.showLoadedData((HashMap<FoodCategory, List<Food>>)
                    savedInstanceState.getSerializable(keyToState));
        } else {
            presenter.load(currentItemOfGroup);
        }
        presenter.getLoadedData();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detachView();
        presenter.unSubscribe();
        adapter.setListener(null);
    }

    @Override
    public void showLoadedFood(HashMap<FoodCategory, List<Food>> foodCategoryListHashMap) {
        int i = 0;
        for (HashMap.Entry<FoodCategory, List<Food>> pair : foodCategoryListHashMap.entrySet()) {
            FoodCategory key = pair.getKey();
            sections.add(new SimpleSectionedRecyclerViewAdapter
                    .Section(i, key.getNameFoodCategory(), key.getSrc()));
            i += pair.getValue().size();
            loadedList.addAll(pair.getValue());
        }

        SimpleSectionedRecyclerViewAdapter.Section[] dummy =
                new SimpleSectionedRecyclerViewAdapter.Section[sections.size()];

        adapter.addAll(loadedList);
        sectionedAdapter.setSections(sections.toArray(dummy));
    }

    @Override
    public void showSnackBar(String title) {
        Snackbar.make(recyclerView, title, Snackbar.LENGTH_LONG).show();
    }


    @Override
    public void update(Bundle bundleToUpdate) {
        clearAll();
        presenter.load(bundleToUpdate.getString(keyToArgs));
    }

    @Override
    public void clearAll() {
        sections.clear();
        adapter.clearAll();
        loadedList.clear();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        HashMap<FoodCategory, List<Food>> loadedData = presenter.getLoadedData();
        Set<Food> checkedFood = presenter.getCheckedFood();
        if (loadedData != null) {
            outState.putSerializable(keyToState, loadedData);
            outState.putSerializable("check", (Serializable) checkedFood);
        }
        super.onSaveInstanceState(outState);
    }

    private void setAdapterListener() {
        adapter.setListener(new ListFoodAdapter.OnClickListFoodAdapter() {
            @Override
            public void onLongClickItemView(int position) {
                Food food = null;
                if (loadedList != null && !loadedList.isEmpty()) {
                    food = loadedList.get(sectionedAdapter.sectionedPositionToPosition(position));
                }
                presenter.addToFavorite(food);
            }

            @Override
            public void onClickBtnAddToCalc(int position) {
                Food food = null;
                if (loadedList != null && !loadedList.isEmpty()) {
                    food = loadedList.get(sectionedAdapter.sectionedPositionToPosition(position));
                }
                presenter.addCalculationFood(food);
            }

            @Override
            public void onRemoveFromCalculator(int position) {
                Food food = null;
                if (loadedList != null && !loadedList.isEmpty()) {
                    food = loadedList.get(sectionedAdapter.sectionedPositionToPosition(position));
                }
                presenter.removeFromCheckedFood(food);
            }


        });
    }
}
