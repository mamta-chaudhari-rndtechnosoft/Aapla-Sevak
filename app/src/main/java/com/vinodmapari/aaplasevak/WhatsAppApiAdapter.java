package com.vinodmapari.aaplasevak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinodmapari.aaplasevak.Model.SearchList;
import com.vinodmapari.aaplasevak.Model.WhatsAppNumList;
import com.vinodmapari.aaplasevak.Model.WhatsAppNumberResponseData;

import java.util.ArrayList;

public class WhatsAppApiAdapter extends RecyclerView.Adapter<WhatsAppApiAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<WhatsAppNumList> numLists;

    public WhatsAppApiAdapter(Context context, ArrayList<WhatsAppNumList> numLists) {
        this.context = context;
        this.numLists = numLists;
    }

    @NonNull
    @Override
    public WhatsAppApiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_whatsapp_api_number,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WhatsAppNumList numListData = numLists.get(position);

        String name = numListData.getName() + " " + numListData.getMiddle_name() + " " + numListData.getSurname();
        holder.tvName.setText(name);
        holder.tvNumber.setText(numListData.getMobile1());

    }

    @Override
    public int getItemCount() {
        return numLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvNumber;
        ImageView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tvNumber);
        }
    }
}
