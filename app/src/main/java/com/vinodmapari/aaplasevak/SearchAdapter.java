package com.vinodmapari.aaplasevak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vinodmapari.aaplasevak.Activity.SearchedUserDetailActivity;
import com.vinodmapari.aaplasevak.Model.SearchList;
import com.vinodmapari.aaplasevak.Model.SearchListItem;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {

    List<SearchListItem> searchLists;
    private final Context context;


   /* public SearchAdapter(Activity activity, ArrayList<SearchList> searchLists) {
        this.activity = activity;
        this.searchLists = searchLists;
    }*/

    public SearchAdapter(Context context, List<SearchListItem> searchLists) {
        this.context = context;
        this.searchLists = searchLists;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.search_item, parent, false);
        return new SearchAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {

        //SearchList searchList = searchLists.get(position);
        SearchListItem searchList = searchLists.get(position);

        // change the aadhar card with house no
        //holder.adhar.setText(searchList.getAdharCard());
        holder.adhar.setText(searchList.getHouseNo());
        //holder.adhar.setText(searchList.getSeriesName() + "/" + searchList.getHouseNo());
        holder.voterId.setText(searchList.getVoterId());

        /*if (!searchList.getFullName().equals("") || !searchList.getName().equals("") || !searchList.getMiddleName().equals("") || !searchList.getSurname().equals("")) {

            *//*holder.adhar.setText(searchList.getAdharCard());
            holder.voterId.setText(searchList.getVoterId());
            holder.tvName.setText(searchList.getFullName());*//*
            Log.d("Api Response","Name Not Found");
        } else {
            holder.adhar.setText(searchList.getAdharCard());
            holder.voterId.setText(searchList.getVoterId());
            holder.tvName.setText(searchList.getName() + " " + searchList.getMiddleName() + " " + searchList.getSurname());
        }*/

        //holder.tvName.setText(searchList.getSurname()+" "+searchList.getName()+" "+searchList.getMiddle_name());
        holder.tvName.setText(searchList.getName() + " " + searchList.getMiddleName() + " " + searchList.getSurname());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SearchedUserDetailActivity.class);

                intent.putExtra("name", searchLists.get(position).getName());
                intent.putExtra("middle_name", searchLists.get(position).getMiddleName());
                intent.putExtra("surname", searchLists.get(position).getSurname());
                intent.putExtra("voter_id", searchLists.get(position).getVoterId());
                intent.putExtra("adhar_card", searchLists.get(position).getAdharCard());
                intent.putExtra("house_no", searchLists.get(position).getHouseNo());
                intent.putExtra("series_name", searchLists.get(position).getSeriesName());
                intent.putExtra("row_id", searchLists.get(position).getRowId());
                intent.putExtra("water_Supply_id", searchLists.get(position).getWaterSupplyId());
                intent.putExtra("dob", searchLists.get(position).getDob());
                intent.putExtra("caste", searchLists.get(position).getCaste());
                intent.putExtra("colony_id", searchLists.get(position).getColonyId());
                intent.putExtra("mobile1", searchLists.get(position).getMobile1());
                intent.putExtra("mobile2", searchLists.get(position).getMobile2());
                intent.putExtra("gender", searchLists.get(position).getGender());
                intent.putExtra("qualification", searchLists.get(position).getQualification());
                intent.putExtra("id", searchLists.get(position).getId());
                intent.putExtra("status_id", searchLists.get(position).getStatusId());
                intent.putExtra("voting_center", searchLists.get(position).getVotingCenter());
                intent.putExtra("booth_no", searchLists.get(position).getBoothNo());
                intent.putExtra("voting_sr_no", searchLists.get(position).getVotingSrNo());
                intent.putExtra("member_id", searchLists.get(position).getMemberId());
                intent.putExtra("apartment", searchLists.get(position).getApartment());
                intent.putExtra("flate_no", searchLists.get(position).getFlatNo());
                intent.putExtra("constituency", searchLists.get(position).getConstituency());
                intent.putExtra("city_village", searchLists.get(position).getCityVillage());
                intent.putExtra("zone", searchLists.get(position).getZone());
                intent.putExtra("prabhag_ward", searchLists.get(position).getPrabhagWard());
                intent.putExtra("s_no", searchLists.get(position).getsNo());
                intent.putExtra("fullname", searchLists.get(position).getFullName());
                intent.putExtra("series_id", searchLists.get(position).getSeriesId());
                intent.putExtra("constituencyName", searchLists.get(position).getConstituencyName());
                intent.putExtra("cityName", searchLists.get(position).getCityName());
                intent.putExtra("zoneName", searchLists.get(position).getZoneName());
                intent.putExtra("wardName", searchLists.get(position).getWardName());
                context.startActivity(intent);

                //                SearchedUserDetailActivity userDetailActivity=(SearchedUserDetailActivity) activity;
                //                userDetailActivity.getSearchList();

            }
        });
    }

    @Override
    public int getItemCount() {
        return searchLists.size();
    }

    public void filterList(List<SearchListItem> filteredList) {
        this.searchLists = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, adhar, voterId;
        CardView cd;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tv_name);
            adhar = itemView.findViewById(R.id.tv_adhar);
            voterId = itemView.findViewById(R.id.tv_voterID);
            cd = itemView.findViewById(R.id.cd_search);

        }
    }
}
