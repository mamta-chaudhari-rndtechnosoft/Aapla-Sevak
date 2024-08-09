package com.vinodmapari.aaplasevak;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vinodmapari.aaplasevak.Activity.UpdateMemberActivity;
import com.vinodmapari.aaplasevak.Model.HouseDetail;

import java.util.ArrayList;

public class EditFamilyAdapter extends RecyclerView.Adapter<EditFamilyAdapter.ViewHolder> {

    Context context;
    ArrayList<HouseDetail> familyMemberList;
    OnDeleteClickListener onDeleteClickListener;
    int memberId;

    public interface OnDeleteClickListener {
        void onDeleteMember(int position, int memberId);
    }

    public void setOnDeleteClickListener(OnDeleteClickListener listener) {
        onDeleteClickListener = listener;
        // this.addressId = addressId;
    }


    public EditFamilyAdapter(Context context, ArrayList<HouseDetail> familyMemberList) {
        this.context = context;
        this.familyMemberList = familyMemberList;
    }


    @NonNull
    @Override
    public EditFamilyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_edit_family_member, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EditFamilyAdapter.ViewHolder holder, int position) {

        HouseDetail houseDetailData = familyMemberList.get(position);

        String name = "NAME: " + houseDetailData.getName() + " " + houseDetailData.getMiddleName() + " " + houseDetailData.getSurname();
        holder.tvName.setText(name);

        holder.tvAadhaarNo.setText("AADHAR NO: " + houseDetailData.getAdharCard());
        holder.tvVoterId.setText("EPIC NO " + houseDetailData.getVoterId());

        holder.imgEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, UpdateMemberActivity.class);
                //intent.putExtra("id",houseDetailData.getId());
                intent.putExtra("id",familyMemberList.get(holder.getAdapterPosition()).getId());
                //intent.putExtra("constituencyId",familyMemberList.get(holder.getAdapterPosition()).getConstituencyId());
                //intent.putExtra("cityId",familyMemberList.get(holder.getAdapterPosition()).getCityVillageId());
                //intent.putExtra("zoneId",familyMemberList.get(holder.getAdapterPosition()).getZoneId());
                //intent.putExtra("wardId",familyMemberList.get(holder.getAdapterPosition()).getWardId());

                context.startActivity(intent);
            }
        });

        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete Member");
                builder.setMessage("Do You really want to delete this Member");
                builder.setPositiveButton("Yes", (dialogInterface, i) -> {

                    memberId = houseDetailData.getId();
                    //Toast.makeText(context, "Yes clicked : " + addressId + " position : " + position, Toast.LENGTH_SHORT).show();

                    if (onDeleteClickListener != null) {
                        onDeleteClickListener.onDeleteMember(position, memberId);
                    }

                });

                builder.setNegativeButton("No", (dialogInterface, i) -> {

                });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return familyMemberList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvAadhaarNo, tvVoterId;
        ImageView imgEdit, imgDelete;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvName = itemView.findViewById(R.id.tvName);
            tvAadhaarNo = itemView.findViewById(R.id.tvAadhaarCard);
            tvVoterId = itemView.findViewById(R.id.tvVoterID);
            imgEdit = itemView.findViewById(R.id.imgEdit);
            imgDelete = itemView.findViewById(R.id.imgDelete);

        }
    }
}
