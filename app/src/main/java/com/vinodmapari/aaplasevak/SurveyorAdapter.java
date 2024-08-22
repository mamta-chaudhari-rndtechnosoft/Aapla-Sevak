package com.vinodmapari.aaplasevak;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.vinodmapari.aaplasevak.Model.SurveyorItem;

import java.util.ArrayList;

public class SurveyorAdapter extends RecyclerView.Adapter<SurveyorAdapter.ViewHolder> {

    public interface OnDeleteClickListener {
        void onDeleteClick(String userId);
    }

    public interface OnToggleStatusListener {
        void onStatusToggle(String userId, String newStatus);
    }

    Context context;
    ArrayList<SurveyorItem> surveyorList;
    private OnDeleteClickListener onDeleteClickListener;
    private OnToggleStatusListener onToggleStatusListener;


    public SurveyorAdapter(Context context, ArrayList<SurveyorItem> surveyorList,
                           OnDeleteClickListener onDeleteClickListener,
                           OnToggleStatusListener onToggleStatusListener) {
        this.context = context;
        this.surveyorList = surveyorList;
        this.onDeleteClickListener = onDeleteClickListener;
        this.onToggleStatusListener = onToggleStatusListener;
    }

    @NonNull
    @Override
    public SurveyorAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_surveyor_member_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SurveyorAdapter.ViewHolder holder, int position) {
        SurveyorItem item = surveyorList.get(position);
        String name = item.getName() + " " + item.getMiddleName() + " " + item.getSurname();

        holder.tvName.setText(name);
        holder.tvNumber.setText(item.getMobile());
        holder.tvCount.setText(item.getCount());


        holder.imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("Are you sure want to delete surveyor ?");
                alertDialogBuilder.setTitle("Delete Surveyor ?");

                alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position = holder.getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            SurveyorItem itemToRemove = surveyorList.get(position);
                            surveyorList.remove(position);
                            notifyItemRemoved(position);
                            // Call the delete method in the activity
                            if (onDeleteClickListener != null) {
                                onDeleteClickListener.onDeleteClick(itemToRemove.getId());
                            }
                        }
                    }
                });
                // Create and show the dialog
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

            }
        });

        // Set up the toggle button based on the current status
        if (item.getStatus().equalsIgnoreCase("0")) {
            // Status is "0", meaning "BAN"
            holder.toggleButton.setChecked(false);
            holder.toggleButton.setTextOff("BAN");
            holder.toggleButton.setBackgroundColor(Color.parseColor("#d2040d")); // Red color for BAN
            holder.toggleButton.setText("BAN");
        } else if (item.getStatus().equalsIgnoreCase("1")) {
            // Status is "1", meaning "NOT BAN"
            holder.toggleButton.setChecked(true);
            holder.toggleButton.setTextOn("NOT BAN");
            holder.toggleButton.setBackgroundColor(Color.parseColor("#4CAF50")); // Green color for NOT BAN
            holder.toggleButton.setText("NOT BAN");
        }

        holder.toggleButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String newStatus = isChecked ? "1" : "0";
            if (onToggleStatusListener != null) {
                onToggleStatusListener.onStatusToggle(item.getId(), newStatus);
            }
            // Update the text and color based on the new status
            if (isChecked) {
                holder.toggleButton.setText("NOT BAN");
                holder.toggleButton.setTextOn("NOT BAN");
                holder.toggleButton.setBackgroundColor(Color.parseColor("#4CAF50")); // Green color for NOT BAN
            } else {
                holder.toggleButton.setText("BAN");
                holder.toggleButton.setTextOff("BAN");
                holder.toggleButton.setBackgroundColor(Color.parseColor("#d2040d")); // Red color for BAN
            }
        });


    }

    @Override
    public int getItemCount() {
        return surveyorList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvName, tvNumber, tvCount;
        ImageButton imgDelete;
        MaterialCardView cardSurveyor;
        ToggleButton toggleButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvNameSurveyor);
            tvNumber = itemView.findViewById(R.id.tvNumberSurveyor);
            tvCount = itemView.findViewById(R.id.tvCountSurveyor);
            imgDelete = itemView.findViewById(R.id.imgDelete);
            cardSurveyor = itemView.findViewById(R.id.cardSurveyor);
            toggleButton = itemView.findViewById(R.id.btnToggle);
        }
    }
}
