package com.example.kolin.testapplication.presentation.products.catalog.favoritelist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.Food;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kolin on 18.09.2016.
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteHolder> {

    private List<Food> list = new ArrayList<>();

    @Override
    public FavoriteHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_favorite_fragment, parent, false);
        return new FavoriteHolder(itemView);
    }

    @Override
    public void onBindViewHolder(FavoriteHolder holder, int position) {
        Food food = list.get(position);
        holder.textViewName.setText(food.getName()+" ("+food.getOwner()+")");
        holder.textViewB.append(String.valueOf(food.getB()));
        holder.textViewJ.append(String.valueOf(food.getJ()));
        holder.textViewY.append(String.valueOf(food.getY()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class FavoriteHolder extends RecyclerView.ViewHolder{

        private TextView textViewB;
        private TextView textViewJ;
        private TextView textViewY;

        private TextView textViewName;

        private ImageButton imageButtonRemove;



        public FavoriteHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.favorite_food_name_product);
            textViewB = (TextView) itemView.findViewById(R.id.favorite_food_b);
            textViewJ = (TextView) itemView.findViewById(R.id.favorite_food_j);
            textViewY = (TextView) itemView.findViewById(R.id.favorite_food_y);
            imageButtonRemove = (ImageButton) itemView.findViewById(R.id.favorite_food_remove_from_favorite);
        }
    }

    public void clearAll(){
        list.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Food> list){
        this.list.addAll(list);
        notifyDataSetChanged();
    }

}
