package com.example.kolin.testapplication.presentation.index;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.VitalCharacteristic;
import com.example.kolin.testapplication.presentation.index.adapter.IndexAdapter;

import java.util.List;

public class IndexFragment extends Fragment implements IndexView {


    private RecyclerView recyclerView;
    private FloatingActionButton fab;
    private IndexAdapter adapter;
    private IndexPresenter presenter;

    private OnClickIndexFragmentListener listener;


    public interface OnClickIndexFragmentListener {
        void onClickFabIndexFragment();

        void onClickChangeIndexFragment(VitalCharacteristic vitalCharacteristic);
    }

    public IndexFragment() {
        // Required empty public constructor
    }

    public static IndexFragment newInstance(String param1, String param2) {
        IndexFragment fragment = new IndexFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        adapter = new IndexAdapter();
        adapter.setListener(new IndexAdapter.OnClickIndexAdapterListener() {
            @Override
            public void onClickBtnRemove(VitalCharacteristic vitalCharacteristic) {
                presenter.deleteVitalCharacteristic(vitalCharacteristic);
            }

            @Override
            public void onClickBtnCreate(VitalCharacteristic vitalCharacteristic) {
                if (listener != null){
                    listener.onClickChangeIndexFragment(vitalCharacteristic);
                }
            }
        });
        presenter = new IndexPresenter();
        presenter.attachView(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_index_recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));


        fab = (FloatingActionButton) view.findViewById(R.id.fragment_index_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onClickFabIndexFragment();
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
        if (context instanceof OnClickIndexFragmentListener) {
            listener = (OnClickIndexFragmentListener) context;
        } else {
            throw new RuntimeException(context.toString() +
                    "must implement OnClickIndexFragmentListener");
        }
    }

    @Override
    public void showLoadedVitalCharacteristic(List<VitalCharacteristic> list) {
        adapter.addAll(list);
    }

    @Override
    public void showSnackBar(String title) {
        Snackbar.make(getView(), title, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void onDetach() {
        fab.setOnClickListener(null);
        adapter.setListener(null);
        super.onDetach();
    }
}
