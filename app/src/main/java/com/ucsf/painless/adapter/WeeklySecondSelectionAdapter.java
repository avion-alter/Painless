package com.ucsf.painless.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.ucsf.painless.R;

import java.util.Collections;
import java.util.List;

public class WeeklySecondSelectionAdapter extends RecyclerView.Adapter<WeeklySecondSelectionAdapter.ViewHolder>{
    private List<String> listdata;
    Integer selectedId;
    String selectedNumber;
    // RecyclerView recyclerView;
    public WeeklySecondSelectionAdapter(List<String> listdata, Integer selectedId) {
        this.listdata = listdata;
        this.selectedId = selectedId;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_weekly_second, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final String myListData = listdata.get(position);
        holder.btn_question.setText(myListData);

        if (selectedId==position){
            selectedNumber=holder.btn_question.getText().toString();
            holder.btn_question.setBackgroundResource(R.drawable.bg_border_selected);
        }else {
            holder.btn_question.setBackgroundResource(R.drawable.bg_border);
        }



        holder.btn_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (selectedId==position){
                    selectedId=listdata.size()-1;
                    selectedNumber=holder.btn_question.getText().toString();;
                    holder.btn_question.setBackgroundResource(R.drawable.bg_border);
                    notifyDataSetChanged();
                }else {
                    selectedId=position;
                    selectedNumber=holder.btn_question.getText().toString();
                    holder.btn_question.setBackgroundResource(R.drawable.bg_border_selected);
                    notifyDataSetChanged();
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
      Button btn_question;
        public ViewHolder(View itemView) {
            super(itemView);
            btn_question = (Button) itemView.findViewById(R.id.btn_question);
        }
    }

    public String getSelectedId(){

        return selectedNumber;
    }

}  
