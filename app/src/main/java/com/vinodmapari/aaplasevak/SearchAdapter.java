package com.vinodmapari.aaplasevak;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.vinodmapari.aaplasevak.Activity.SearchedUserDetailActivity;
import com.vinodmapari.aaplasevak.Model.SearchList;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{
    //private Activity activity;
    //ArrayList<SearchList> searchLists;
    List<SearchList> searchLists;
    //private List<SearchList> searchList;
    private final Context context;


   /* public SearchAdapter(Activity activity, ArrayList<SearchList> searchLists) {
        this.activity = activity;
        this.searchLists = searchLists;
    }*/

    public SearchAdapter(Context context, List<SearchList> searchLists) {
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

        SearchList searchList=searchLists.get(position);
        holder.adhar.setText(searchList.getAdhar_card());
        //holder.tvName.setText(searchList.getSurname()+" "+searchList.getName()+" "+searchList.getMiddle_name());
        holder.tvName.setText(searchList.getName()+" "+searchList.getMiddle_name()+" "+searchList.getSurname());
        holder.voterId.setText(searchList.getVoter_id());

        holder.itemView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, SearchedUserDetailActivity.class);

                intent.putExtra("name",searchLists.get(position).getName());
                intent.putExtra("middle_name",searchLists.get(position).getMiddle_name());
                intent.putExtra("surname",searchLists.get(position).getSurname());
                intent.putExtra("voter_id",searchLists.get(position).getVoter_id());
                intent.putExtra("adhar_card",searchLists.get(position).getAdhar_card());
                intent.putExtra("house_no",searchLists.get(position).getHouse_n0());
                intent.putExtra("series_no",searchLists.get(position).getSeries_id());
                intent.putExtra("row_id",searchLists.get(position).getRow_id());
                intent.putExtra("water_Supply_id",searchLists.get(position).getWatersupply_id());
                intent.putExtra("dob",searchLists.get(position).getDob());
                intent.putExtra("caste",searchLists.get(position).getCaste());
                intent.putExtra("relation",searchLists.get(position).getRelation());
                intent.putExtra("colony_id",searchLists.get(position).getColony_id());
                intent.putExtra("mobile1",searchLists.get(position).getMobile1());
                intent.putExtra("mobile2",searchLists.get(position).getMobile2());
                intent.putExtra("gender",searchLists.get(position).getGender());
                intent.putExtra("qualification",searchLists.get(position).getQualification());
                intent.putExtra("id",searchLists.get(position).getId());
                intent.putExtra("status_id",searchLists.get(position).getStatus_id());
                intent.putExtra("voting_center",searchLists.get(position).getVoting_center());
                intent.putExtra("event",searchLists.get(position).getEvent());
                intent.putExtra("member_id",searchLists.get(position).getMember_id());



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

    public void filterList(List<SearchList> filteredList) {
        this.searchLists = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName,adhar,voterId;
        CardView cd;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName=itemView.findViewById(R.id.tv_name);
            adhar=itemView.findViewById(R.id.tv_adhar);
            voterId=itemView.findViewById(R.id.tv_voterID);
            cd=itemView.findViewById(R.id.cd_search);

        }
    }
}
