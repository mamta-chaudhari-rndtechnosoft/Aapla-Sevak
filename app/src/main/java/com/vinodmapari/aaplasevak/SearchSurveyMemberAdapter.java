package com.vinodmapari.aaplasevak;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.vinodmapari.aaplasevak.Activity.UserSurveyActivity;
import com.vinodmapari.aaplasevak.Model.SearchItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class SearchSurveyMemberAdapter extends RecyclerView.Adapter<SearchSurveyMemberAdapter.ViewHolder> {

    Context context;
    public ArrayList<SearchItem> searchItemArrayList;
    public ArrayList<String> selectedItems = new ArrayList<>();
    OnSelectionChangedListener selectionChangedListener;

    private final HashMap<String, Boolean> selectedVoter = new HashMap<>();

    public interface OnSelectionChangedListener {
        void onSelectionChanged(boolean hasSelection);
    }

    public SearchSurveyMemberAdapter(Context context, ArrayList<SearchItem> searchItemArrayList, OnSelectionChangedListener listener) {
        this.context = context;
        this.searchItemArrayList = searchItemArrayList;
        this.selectionChangedListener = listener;
    }

    public void updateData(ArrayList<SearchItem> surveyList) {
        searchItemArrayList = surveyList;
        notifyDataSetChanged();
    }

    public ArrayList<String> getSelectedVoterId() {
        return selectedItems;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_search_survey_member, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SearchSurveyMemberAdapter.ViewHolder holder, int position) {

        SearchItem searchItem = searchItemArrayList.get(position);
        String name = searchItem.getName() + " " + searchItem.getMiddleName() + " " + searchItem.getSurname();
        holder.tvFullName.setText(name);

        String voterId = searchItem.getId();

        //holder.cardName.setCardBackgroundColor(searchItem.isSelected() ? Color.parseColor("#e1e1e1") : Color.parseColor("#FFFFFF"));

        if (selectedItems.contains(voterId)) {
            holder.selectedBg();
        } else {
            holder.defaultBg();
        }

        holder.cardName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* Intent intent = new Intent(context, UserSurveyActivity.class);
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
                context.startActivity(intent);*/

                //holder.cardName.setCardBackgroundColor(Color.parseColor("#e1e1e1"));

                /*Intent intent = new Intent(context, UserSurveyActivity.class);
                intent.putExtra("voterSearchItem", (Serializable) searchItem);
                intent.putExtra("isDataHave", "true");
                context.startActivity(intent);*/

                if (selectedItems.contains(voterId)) {
                    selectedItems.remove(voterId);
                } else {
                    selectedItems.add(voterId);
                }

                notifyItemChanged(holder.getAdapterPosition());

                if (selectionChangedListener != null) {
                    selectionChangedListener.onSelectionChanged(!selectedItems.isEmpty());
                }

            }

        });


    }

    @Override
    public int getItemCount() {
        return searchItemArrayList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvFullName;
        MaterialCardView cardName;
        boolean isSelected = false;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvFullName = itemView.findViewById(R.id.tvFullName);
            cardName = itemView.findViewById(R.id.cardNameSearch);
        }


        public void defaultBg() {
            //itemView.setBackgroundResource(R.drawable.home_unselect_shape);
            cardName.setCardBackgroundColor(Color.parseColor("#FFFFFF"));

        }

        public void selectedBg() {
            //itemView.setBackgroundResource(R.drawable.home_select_shape);
            cardName.setCardBackgroundColor(Color.parseColor("#e3e3e3"));
        }
    }
}
