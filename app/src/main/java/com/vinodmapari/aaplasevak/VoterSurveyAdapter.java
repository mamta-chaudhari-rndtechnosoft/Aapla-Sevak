package com.vinodmapari.aaplasevak;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

public class VoterSurveyAdapter extends RecyclerView.Adapter<VoterSurveyAdapter.ViewHolder> {
    private final Context context;
    private ArrayList<String> voterIds;


    public VoterSurveyAdapter(Context context, ArrayList<String> voterIds) {
        this.context = context;
        this.voterIds = voterIds;
    }

    @NonNull
    @Override
    public VoterSurveyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_voter_survey, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VoterSurveyAdapter.ViewHolder holder, int position) {
        String voterId = voterIds.get(position);
        holder.tvDob.setText("Voter Id: " + voterId);

    }

    @Override
    public int getItemCount() {
        return voterIds.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        SearchableSpinner spinner_constituency, spinner_city, spinner_ward, spinner_zone;
        EditText etName, etMiddleName, etSurName, etEpicNo, etBoothNo, etSerialNo, etVotingCenter;
        String gender, dob;
        RadioGroup radioGroup;
        TextView tvDob;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDob = itemView.findViewById(R.id.dob);
            spinner_constituency = itemView.findViewById(R.id.spinnerConstituency);
            spinner_city = itemView.findViewById(R.id.spinnerCity_Village);
            spinner_zone = itemView.findViewById(R.id.spinnerZone);
            spinner_ward = itemView.findViewById(R.id.spinnerPrabhag_Ward);

            etName = itemView.findViewById(R.id.name);
            etMiddleName = itemView.findViewById(R.id.middle_name);
            etSurName = itemView.findViewById(R.id.surname);
            etBoothNo = itemView.findViewById(R.id.etBoothNo);
            etSerialNo = itemView.findViewById(R.id.etSerialNo);
            etVotingCenter = itemView.findViewById(R.id.voting_center);
            etEpicNo = itemView.findViewById(R.id.voterID);
        }
    }
}
