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
import android.widget.ProgressBar;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.ItemOfGroup;
import com.example.kolin.testapplication.domain.groups.Group;
import com.example.kolin.testapplication.presentation.common.SimpleDividerItemDecoration;

import java.util.List;

public class SelectionFragment extends Fragment implements SelectionContractView {


    private SelectionPresenter presenter;
    private SelectionAdapter selectionAdapter;

    //Views
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private String currentGroup;

    private OnClickItemOnSelectionFragment listener;

    public SelectionFragment() {
        // Required empty public constructor
    }

    public interface OnClickItemOnSelectionFragment {
        void clickItemSelectionFragment(String itemName, String title);
    }

    public static SelectionFragment newInstance(int param1) {
        SelectionFragment fragment = new SelectionFragment();
        Bundle args = new Bundle();
        args.putInt("idCategory", param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = null;
        if (getArguments() != null) {
            bundle = getArguments();
            currentGroup = Group.getCategoryById(bundle.getInt("idCategory"));
        }
        presenter = new SelectionPresenter();
        presenter.attachView(this);
        selectionAdapter = new SelectionAdapter(getContext());
        selectionAdapter.setClickItemListener(new SelectionAdapter.ClickItemSelection() {
            @Override
            public void onClickItemSelection(int position) {
                if (listener != null) {
                    ItemOfGroup objectAtPosition = selectionAdapter.getObjectAtPosition(position);
                    listener.clickItemSelectionFragment(objectAtPosition.getIdName(),
                            objectAtPosition.getName());
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_selection, container, false);

        progressBar = (ProgressBar) root.findViewById(R.id.progress_bar_selection_fragment);
        recyclerView = (RecyclerView) root.findViewById(R.id.recycler_view_selection);

        recyclerView.setAdapter(selectionAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new SimpleDividerItemDecoration(getContext()));

        return root;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        if (currentGroup != null)
            presenter.load(currentGroup);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnClickItemOnSelectionFragment) {
            listener = (OnClickItemOnSelectionFragment) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnClickItemOnSelectionFragment");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
        presenter.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.unSubscribe();
    }

    @Override
    public void showLoadedRestaurants(List<ItemOfGroup> itemCategories) {
        selectionAdapter.addAll(itemCategories);
    }

    @Override
    public void showLoadingProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
