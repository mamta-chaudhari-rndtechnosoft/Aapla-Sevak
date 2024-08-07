package com.vinodmapari.aaplasevak;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.vinodmapari.aaplasevak.Activity.UserSurveyActivity;
import com.vinodmapari.aaplasevak.Model.SearchItem;

import java.util.ArrayList;

public class SearchSurveyMemberAdapter extends RecyclerView.Adapter<SearchSurveyMemberAdapter.ViewHolder> {

    Context context;
    ArrayList<SearchItem> searchItemArrayList;

    public SearchSurveyMemberAdapter(Context context, ArrayList<SearchItem> searchItemArrayList) {
        this.context = context;
        this.searchItemArrayList = searchItemArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_search_survey_member, parent, false);
        ViewHolder viewHolder = new SearchSurveyMemberAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSurveyMemberAdapter.ViewHolder holder, int position) {

        SearchItem searchItem = searchItemArrayList.get(position);
        String name = searchItem.getFullName();
        holder.tvFullName.setText(name);

        holder.cardName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, UserSurveyActivity.class);
                intent.putExtra("isDataHave", "true");
                intent.putExtra("name", searchItem.getName());
                intent.putExtra("surname", searchItem.getSurname());
                intent.putExtra("middlename", searchItem.getMiddleName());
                intent.putExtra("voterId", searchItem.getVoterId());
                intent.putExtra("boothNo", searchItem.getBoothNo());
                intent.putExtra("sNo", searchItem.getSerialNo());
                intent.putExtra("votingCenter", searchItem.getVotingCenter());
                intent.putExtra("gender", searchItem.getGender());
                intent.putExtra("epicNo", searchItem.getEpicNo());
                intent.putExtra("dob", searchItem.getDob());
                intent.putExtra("constituency", searchItem.getConstituency());
                intent.putExtra("cityVillage", searchItem.getCityVillage());
                intent.putExtra("zone", searchItem.getZone());
                intent.putExtra("prabhagWard", searchItem.getPrabhagWard());
                context.startActivity(intent);

            }
        });

    }

    @Override
    public int getItemCount() {
        return searchItemArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFullName;
        MaterialCardView cardName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFullName = itemView.findViewById(R.id.tvFullName);
            cardName = itemView.findViewById(R.id.cardNameSearch);
        }
    }
}
