package com.ucsf.painless.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.ucsf.painless.R;
import com.ucsf.painless.model.questionnaire.TypesPainDataItem;

import java.util.List;

public class SelectedQuestionnaireAdapter extends RecyclerView.Adapter<SelectedQuestionnaireAdapter.ViewHolder>{
    private List<TypesPainDataItem> listdata;

    // RecyclerView recyclerView;
    public SelectedQuestionnaireAdapter(List<TypesPainDataItem> listdata) {
        this.listdata = listdata;
    }
    @Override
    public SelectedQuestionnaireAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_question, parent, false);
        SelectedQuestionnaireAdapter.ViewHolder viewHolder = new SelectedQuestionnaireAdapter.ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SelectedQuestionnaireAdapter.ViewHolder holder, int position) {
        final TypesPainDataItem myListData = listdata.get(position);
        holder.btn_question.setText(myListData.getTypnName());
        holder.btn_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (myListData.isSelected()){
                    myListData.setSelected(false);
                    holder.btn_question.setBackgroundResource(R.drawable.bg_border);
                }else {
                    myListData.setSelected(true);
                    holder.btn_question.setBackgroundResource(R.drawable.bg_border_selected);
                }
                Toast.makeText(view.getContext(),"click on item: "+myListData.getTypnId(),Toast.LENGTH_LONG).show();
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
