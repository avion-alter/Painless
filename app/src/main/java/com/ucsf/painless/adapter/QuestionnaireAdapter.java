package com.ucsf.painless.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ucsf.painless.R;
import com.ucsf.painless.model.questionnaire.TypesPainDataItem;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireAdapter extends RecyclerView.Adapter<QuestionnaireAdapter.ViewHolder>{
    private List<TypesPainDataItem> listdata;

    // RecyclerView recyclerView;  
    public QuestionnaireAdapter(List<TypesPainDataItem> listdata) {
        this.listdata = listdata;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_question, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final TypesPainDataItem myListData = listdata.get(position);
        holder.btn_question.setText(myListData.getTypnName());

        if (myListData.isSelected()){
            holder.btn_question.setBackgroundResource(R.drawable.bg_border_selected);
        }else {
            holder.btn_question.setBackgroundResource(R.drawable.bg_border);
        }

        holder.btn_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (myListData.isSelected()){
                    listdata.get(position).setSelected(false);
                    holder.btn_question.setBackgroundResource(R.drawable.bg_border);
                    notifyDataSetChanged();
                }else {
                    listdata.get(position).setSelected(true);
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
}  
