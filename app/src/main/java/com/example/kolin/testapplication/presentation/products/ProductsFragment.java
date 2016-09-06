package com.example.kolin.testapplication.presentation.products;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kolin.testapplication.R;


public class ProductsFragment extends Fragment {

    private CardView cardViewRestaurants;
    private CardView cardViewFood;

    private OnClickCardViews listener;

    public ProductsFragment() {
    }


    public static ProductsFragment newInstance() {
        ProductsFragment fragment = new ProductsFragment();
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
        View root = inflater.inflate(R.layout.fragment_products, container, false);
        cardViewRestaurants = (CardView) root.findViewById(R.id.card_products_restaurant);
        cardViewRestaurants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null)
                    listener.onClickCardViewRestaurant();
            }
        });
        cardViewFood = (CardView) root.findViewById(R.id.card_products_for_everyday);
        cardViewFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null){
                    listener.onClickCardViewFood();
                }
            }
        });
        return root;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnClickCardViews) {
            listener = (OnClickCardViews) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }


    public interface OnClickCardViews {
        void onClickCardViewRestaurant();

        void onClickCardViewFood();
    }


}
