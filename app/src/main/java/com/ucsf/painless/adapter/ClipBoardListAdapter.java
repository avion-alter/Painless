package com.ucsf.painless.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ucsf.painless.R;
import com.ucsf.painless.model.clipBoard.Data;
import com.ucsf.painless.model.clipBoard.PatientPreviousMapDatum;


public class ClipBoardListAdapter extends RecyclerView.Adapter<ClipBoardListAdapter.ViewHolder> {

    private Context context;
    Data data;

    private RadioGroup lastCheckedRadioGroup = null;

    public ClipBoardListAdapter(Data data, Context ctx) {
        this.data = data;
        context = ctx;
    }

    @Override
    public ClipBoardListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                          int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_clipboard, parent, false);

        ClipBoardListAdapter.ViewHolder viewHolder =
                new ClipBoardListAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClipBoardListAdapter.ViewHolder holder, int position) {
        PatientPreviousMapDatum order = data.getPatientPreviousMapData().get(position);
        holder.txt_location.setText(order.getBodyPartName());
        if (order.getBpmColor().equals("1")){
        holder.txt_severity.setText("Mild (1)");
        }else if (order.getBpmColor().equals("2")){
        holder.txt_severity.setText("Moderate (2)");
        }else if (order.getBpmColor().equals("3")){
        holder.txt_severity.setText("Severe (3)");
        }

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return data.getPatientPreviousMapData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView txt_location;
        public TextView txt_severity;

        public ViewHolder(View view) {
            super(view);
            txt_location = (TextView) view.findViewById(R.id.txt_location);
            txt_severity = (TextView) view.findViewById(R.id.txt_severity);

        }
    }
}

