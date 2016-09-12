package com.example.kolin.testapplication.presentation.products.catalog.selection;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.kolin.testapplication.R;
import com.example.kolin.testapplication.domain.ItemOfGroup;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by kolin on 04.09.2016.
 */

public class SelectionAdapter extends RecyclerView.Adapter<SelectionAdapter.SelectionHolder> {

    private List<ItemOfGroup> list;
    private Context context;

    private ClickItemSelection clickItemListener;

    public SelectionAdapter(Context context) {
        this.list = new ArrayList<>();
        this.context = context;
    }

    public interface ClickItemSelection{
        void onClickItemSelection(int position);
    }

    @Override
    public SelectionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_selection_view, parent, false);
        return new SelectionHolder(view);
    }

    @Override
    public void onBindViewHolder(SelectionHolder holder, int position) {
        ItemOfGroup itemOfGroup = list.get(position);
        holder.textViewName.setText(itemOfGroup.getName());
        holder.textViewDescription.setText(itemOfGroup.getDescription());
        Picasso.with(context).load(itemOfGroup.getSrc())
                .placeholder(R.color.colorWhite)
                .fit()
                .into(holder.avatar);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class SelectionHolder extends RecyclerView.ViewHolder{

        private CircleImageView avatar;
        private TextView textViewName;
        private TextView textViewDescription;

        public SelectionHolder(View itemView) {
            super(itemView);

            avatar = (CircleImageView) itemView.findViewById(R.id.avatar_rv_item);
            textViewName = (TextView) itemView.findViewById(R.id.name_rv_item);
            textViewDescription = (TextView) itemView.findViewById(R.id.description_rv_item);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (clickItemListener != null){
                        clickItemListener.onClickItemSelection(getLayoutPosition());
                    }
                }
            });
        }
    }

    public void addAll(List<ItemOfGroup> itemCategories){
        list.addAll(itemCategories);
        notifyDataSetChanged();
    }

    public void clearAll(){
        list.clear();
        notifyDataSetChanged();
    }

    public void setClickItemListener(ClickItemSelection clickItemListener) {
        this.clickItemListener = clickItemListener;
    }
}
