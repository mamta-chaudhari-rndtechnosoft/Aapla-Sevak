package com.vinodmapari.aaplasevak;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.vinodmapari.aaplasevak.Model.WhatsAppNumList;

import java.util.ArrayList;
import java.util.HashMap;

public class WhatsAppApiAdapter extends RecyclerView.Adapter<WhatsAppApiAdapter.ViewHolder> {

    private final Context context;
    private final ArrayList<WhatsAppNumList> numLists;
    private final EditText etMessage;
    private final HashMap<String, Boolean> messageSentMap = new HashMap<>();

    public WhatsAppApiAdapter(Context context, ArrayList<WhatsAppNumList> numLists, EditText etMessage) {
        this.context = context;
        this.numLists = numLists;
        this.etMessage = etMessage;
    }

    /*public void whatsAppMsg(String msg){
        this.message = msg;
    }*/

    @NonNull
    @Override
    public WhatsAppApiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_whatsapp_api_number, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WhatsAppNumList numListData = numLists.get(position);

        String name = numListData.getName() + " " + numListData.getMiddle_name() + " " + numListData.getSurname();
        holder.tvName.setText(name);
        holder.tvNumber.setText("+91" + numListData.getMobile1());

        String mobileNumber = numListData.getMobile1();

        if (messageSentMap.containsKey(mobileNumber) && messageSentMap.get(mobileNumber)) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#cccccc"));
        } else {
            holder.cardView.setCardBackgroundColor(Color.WHITE);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //holder.cardView.setCardBackgroundColor(Color.parseColor("#cccccc"));
                // Handle the message sending

                messageSentMap.put(mobileNumber, true);
                notifyItemChanged(position);

                //Constants.flag = true;
                String currentMessage = etMessage.getText().toString().trim();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" + holder.tvNumber.getText().toString() + "&text=" + currentMessage));
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return numLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNumber;
        ImageView imageView;
        MaterialCardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tvNumber);
            imageView = itemView.findViewById(R.id.img);
            cardView = itemView.findViewById(R.id.cd_search);
        }
    }
}
