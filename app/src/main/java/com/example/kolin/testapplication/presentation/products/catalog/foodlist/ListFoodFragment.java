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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ListFoodFragment extends Fragment implements ListFoodView {

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
        args.putString("itemName", itemName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        currentItemOfGroup = getItemNameFromBundle();

        presenter = new ListFoodPresenter();
        adapter = new ListFoodAdapter();
        sectionedAdapter = new SimpleSectionedRecyclerViewAdapter(getContext(), adapter);

        presenter.attachView(this);

        adapter.setListener(new ListFoodAdapter.OnClickFavoriteBtn() {
            @Override
            public void onClickFavoriteBtn(int position) {
                Food food = null;
                if (loadedList != null && !loadedList.isEmpty()) {
                    food = loadedList.get(sectionedAdapter.sectionedPositionToPosition(position));
                }
                presenter.addToFavorite(food);
            }
        });
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

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        presenter.load(currentItemOfGroup);
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        presenter.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    @Override
    public void showLoadedFood(HashMap<FoodCategory, List<Food>> foodCategoryListHashMap) {
        sections.clear();
        loadedList.clear();
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
    public void showSnackBar() {
        Snackbar.make(recyclerView, "Добавленно в избранное", Snackbar.LENGTH_LONG).show();
    }

    @Override
    public String getItemNameFromBundle() {
        Bundle bundle = getArguments();
        String itemName = bundle.getString("itemName");
        return itemName;
    }

}