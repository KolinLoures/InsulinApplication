package com.example.kolin.testapplication.presentation.products.catalog.adapters;

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
 * Created by kolin on 27.09.2016.
 */

public class DialogAdapter extends RecyclerView.Adapter<DialogAdapter.DialogHolder> {

    private List<Food> list = new ArrayList<>();

    private FavoriteAdapter.OnClickItemFavoriteAdapter listener;

    public interface OnClickItemFavoriteAdapter{
        void onClickDeleteItem(Food food);
    }

    @Override
    public DialogAdapter.DialogHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_dialog, parent, false);
        return new DialogAdapter.DialogHolder(itemView);
    }

    @Override
    public void onBindViewHolder(DialogAdapter.DialogHolder holder, int position) {
        Food food = list.get(position);
        holder.textViewName.setText(food.getName()+" ("+food.getOwner()+")");
        holder.textViewB.setText("Б: " + String.valueOf(food.getB()));
        holder.textViewJ.setText("Ж: " + String.valueOf(food.getJ()));
        holder.textViewY.setText("У: " + String.valueOf(food.getY()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DialogHolder extends RecyclerView.ViewHolder{

        private TextView textViewB;
        private TextView textViewJ;
        private TextView textViewY;

        private TextView textViewName;

        private ImageButton imageButtonRemove;



        public DialogHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.dialog_name_product);
            textViewB = (TextView) itemView.findViewById(R.id.dialog_food_b);
            textViewJ = (TextView) itemView.findViewById(R.id.dialog_food_j);
            textViewY = (TextView) itemView.findViewById(R.id.dialog_food_y);

        }
    }

    public void clearAll(){
        list.clear();
        notifyDataSetChanged();
    }

    public void addAll(List<Food> list){
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public void setListener(FavoriteAdapter.OnClickItemFavoriteAdapter listener) {
        this.listener = listener;
    }
}
