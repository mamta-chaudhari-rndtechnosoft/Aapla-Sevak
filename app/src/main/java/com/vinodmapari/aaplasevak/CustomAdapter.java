package com.vinodmapari.aaplasevak;

import android.app.Activity;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SpinnerAdapter;
import com.vinodmapari.aaplasevak.Model.Colony;

import java.util.ArrayList;

public class CustomAdapter implements SpinnerAdapter {

    private Activity activity;
    ArrayList<Colony> colonyList;

    public CustomAdapter(Activity activity, ArrayList<Colony> colonyList) {
        this.activity = activity;
        this.colonyList = colonyList;
    }

    /*
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(activity).inflate(R.layout.list_items, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position)
    {
    }

    @Override
    public int getItemCount() {
        return colonyList.size();
    }
    */

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    /*public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvColonyName;
        public ViewHolder(View itemView) {
            super(itemView);
            tvColonyName = (TextView) itemView.findViewById(R.id.colony_name);
        }
    }*/

}