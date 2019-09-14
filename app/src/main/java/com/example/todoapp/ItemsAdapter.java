package com.example.todoapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.Viewholder> {

    public interface onLongClickListener {
        void onItemLongClicked (int position);
    }


    List<String> items;
    onLongClickListener longClickListener;
    public ItemsAdapter(List<String> items,onLongClickListener longClickListener) {
        this.items = items;
        this.longClickListener = longClickListener;
    }

    @NonNull
    @Override
    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // use layout inflator to inflate a view
        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        // wrap it inside a viewholder and return it
        return new Viewholder((todoView));
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        // Grab the Item at the position

        String item = items.get(position);
        //Bind the item to the specified view holder
        holder.bind(item);
    }

    @Override
    public int getItemCount() {

        return items.size();
    }


    // container to provide easy access to views that repersent each row of the list
    class Viewholder extends RecyclerView.ViewHolder {
        TextView tvItem;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //updates the view inside the view holder with the data of the string item
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    // notify that it has been long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }


    }



}
