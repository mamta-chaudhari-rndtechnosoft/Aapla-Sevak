package com.vinodmapari.aaplasevak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinodmapari.aaplasevak.Model.ColonyItem;

import java.util.ArrayList;

public class ColonyListAdapter extends RecyclerView.Adapter<ColonyListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ColonyItem> colonyList;

    public ColonyListAdapter(Context context, ArrayList<ColonyItem> colonyList) {
        this.context = context;
        this.colonyList = colonyList;
    }

    @NonNull
    @Override
    public ColonyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_colony_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColonyListAdapter.ViewHolder holder, int position) {

        ColonyItem colonyItem = colonyList.get(position);
        holder.tvColonyName.setText(colonyItem.getColonyName());
    }

    @Override
    public int getItemCount() {
        return colonyList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvColonyName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvColonyName = itemView.findViewById(R.id.tvColonyName);
        }
    }
}
