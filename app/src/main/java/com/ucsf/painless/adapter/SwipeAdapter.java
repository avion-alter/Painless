package com.ucsf.painless.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.ucsf.painless.R;
import com.ucsf.painless.model.questionnaire.TypesPainDataItem;

import java.util.ArrayList;
import java.util.List;

public class SwipeAdapter extends RecyclerView.Adapter<SwipeAdapter.SwipeViewHolder> {

    private Context context;
    private List<TypesPainDataItem> employees;
    private final ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public SwipeAdapter(Context context, List<TypesPainDataItem> employees) {
        this.context = context;
        this.employees = employees;
    }

    public void setEmployees(ArrayList<TypesPainDataItem> employees) {
        this.employees = new ArrayList<>();
        this.employees = employees;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SwipeViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.row_selected_question, viewGroup, false);
        return new SwipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SwipeViewHolder swipeViewHolder, int i) {
        viewBinderHelper.setOpenOnlyOne(true);

        TypesPainDataItem typesPainDataItem= (TypesPainDataItem) employees.get(i);

        Log.e("Selected",typesPainDataItem.getTypnName()+typesPainDataItem.isSelected());
        //if (typesPainDataItem.isSelected()){
            swipeViewHolder.textView.setText(typesPainDataItem.getTypnName());
        //}

        swipeViewHolder.img_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (typesPainDataItem.isSelected()){
                employees.remove(i);
                notifyDataSetChanged();
                }
            }
        });


        try {
            if (employees.get(i).getSeverity().equals("0")){
                swipeViewHolder.text_zero.setBackgroundResource(R.drawable.bg_border_selected);
                swipeViewHolder.text_one.setBackgroundResource(R.drawable.rounded_border_edittext);
                swipeViewHolder.text_two.setBackgroundResource(R.drawable.rounded_border_edittext);
                swipeViewHolder.text_three.setBackgroundResource(R.drawable.rounded_border_edittext);
            }else if (employees.get(i).getSeverity().equals("1")){
                swipeViewHolder.text_zero.setBackgroundResource(R.drawable.rounded_border_edittext);
                swipeViewHolder.text_one.setBackgroundResource(R.drawable.bg_border_selected);
                swipeViewHolder.text_two.setBackgroundResource(R.drawable.rounded_border_edittext);
                swipeViewHolder.text_three.setBackgroundResource(R.drawable.rounded_border_edittext);
            }else if ((employees.get(i).getSeverity().equals("2"))){
                swipeViewHolder.text_zero.setBackgroundResource(R.drawable.rounded_border_edittext);
                swipeViewHolder.text_one.setBackgroundResource(R.drawable.rounded_border_edittext);
                swipeViewHolder.text_two.setBackgroundResource(R.drawable.bg_border_selected);
                swipeViewHolder.text_three.setBackgroundResource(R.drawable.rounded_border_edittext);
            }else if ((employees.get(i).getSeverity().equals("3"))){
                swipeViewHolder.text_zero.setBackgroundResource(R.drawable.rounded_border_edittext);
                swipeViewHolder.text_one.setBackgroundResource(R.drawable.rounded_border_edittext);
                swipeViewHolder.text_two.setBackgroundResource(R.drawable.rounded_border_edittext);
                swipeViewHolder.text_three.setBackgroundResource(R.drawable.bg_border_selected);
            }else {
                swipeViewHolder.text_zero.setBackgroundResource(R.drawable.rounded_border_edittext);
                swipeViewHolder.text_one.setBackgroundResource(R.drawable.rounded_border_edittext);
                swipeViewHolder.text_two.setBackgroundResource(R.drawable.rounded_border_edittext);
                swipeViewHolder.text_three.setBackgroundResource(R.drawable.rounded_border_edittext);
            }
        }catch (Exception e){
            e.printStackTrace();
        }


        swipeViewHolder.text_zero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employees.get(i).setSeverity("0");
                notifyDataSetChanged();
            }
        });

        swipeViewHolder.text_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employees.get(i).setSeverity("1");
                notifyDataSetChanged();
            }
        });

        swipeViewHolder.text_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employees.get(i).setSeverity("2");
                notifyDataSetChanged();
            }
        });

        swipeViewHolder.text_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                employees.get(i).setSeverity("3");
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        /*int selected=0;
        for (int i = 0; i < employees.size(); i++) {
            TypesPainDataItem typesPainDataItem= (TypesPainDataItem) employees.get(i);
            if (typesPainDataItem.isSelected()){
                selected++;
            }
        }*/
        return employees.size();
    }

    class SwipeViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private TextView text_one;
        private TextView text_two;
        private TextView text_three;
        private TextView text_zero;
        private ImageView img_delete;
        private SwipeRevealLayout swipelayout;

        SwipeViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            img_delete = itemView.findViewById(R.id.img_delete);
            swipelayout = itemView.findViewById(R.id.swipelayout);
            text_one = itemView.findViewById(R.id.text_one);
            text_two = itemView.findViewById(R.id.text_two);
            text_three = itemView.findViewById(R.id.text_three);
            text_zero = itemView.findViewById(R.id.text_zero);
        }
    }
}
