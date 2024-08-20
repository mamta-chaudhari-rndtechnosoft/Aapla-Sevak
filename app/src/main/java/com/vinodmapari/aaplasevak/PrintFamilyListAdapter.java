package com.vinodmapari.aaplasevak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinodmapari.aaplasevak.Model.ColonyItem;
import com.vinodmapari.aaplasevak.Model.FamilyDetailsItem;

import java.util.ArrayList;

public class PrintFamilyListAdapter extends RecyclerView.Adapter<PrintFamilyListAdapter.ViewHolder> {
    private Context context;
    private ArrayList<FamilyDetailsItem> familyList;

    public PrintFamilyListAdapter(Context context, ArrayList<FamilyDetailsItem> familyList) {
        this.context = context;
        this.familyList = familyList;
    }

    @NonNull
    @Override
    public PrintFamilyListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_family_list_print, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PrintFamilyListAdapter.ViewHolder holder, int position) {

        FamilyDetailsItem item = familyList.get(position);
        holder.tvName.setText("Name: " + item.getName() + " " + item.getMiddleName() + " " + item.getSurname());
        //String VoterId = String.valueOf(Integer.parseInt(item.getVoterId()));
        /*if(item.getVoterId().equals("")){
            holder.tvVoterId.setText("Voter ID: ");
        }
        else{
            holder.tvVoterId.setText("Voter ID: " + item.getVoterId());
        }*/
        holder.tvVoterId.setText("Voter ID: " + item.getVoterId());
        holder.tvBoothNo.setText("Booth No.: " + item.getBoothNo());
        holder.tvSrNo.setText("Sr No.: " + item.getSerialNo());
        holder.tvVotingCenter.setText("Voting Center: " + item.getVotingCenter());
    }

    @Override
    public int getItemCount() {
        return familyList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvBoothNo, tvSrNo, tvVotingCenter, tvVoterId;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvFullName);
            tvBoothNo = itemView.findViewById(R.id.tvBoothNo);
            tvSrNo = itemView.findViewById(R.id.tvSrNo);
            tvVotingCenter = itemView.findViewById(R.id.tvVotingCenter);
            tvVoterId = itemView.findViewById(R.id.tvVoterId);


        }
    }
}
