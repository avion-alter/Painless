package com.ucsf.painless.adapter;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.recyclerview.widget.RecyclerView;

import com.ucsf.painless.R;
import com.ucsf.painless.utils.ClickListener;

import java.util.List;

public class WeeklyYesNoSelectionAdapter extends RecyclerView.Adapter<WeeklyYesNoSelectionAdapter.ViewHolder> implements RecyclerView.OnItemTouchListener{
    private List<String> listdata;
    Integer selectedId;
    String selectedNumber;
    // RecyclerView recyclerView;

    private ClickListener clicklistener;
    private GestureDetector gestureDetector;
    public WeeklyYesNoSelectionAdapter(List<String> listdata, Integer selectedId) {
        this.listdata = listdata;
        this.selectedId = selectedId;

    }

    public  WeeklyYesNoSelectionAdapter(Context context, final RecyclerView recycleView, final ClickListener clicklistener){

        this.clicklistener=clicklistener;
        gestureDetector=new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                View child=recycleView.findChildViewUnder(e.getX(),e.getY());
                if(child!=null && clicklistener!=null){
                    clicklistener.onLongClick(child,recycleView.getChildAdapterPosition(child));
                }
            }
        });
    }


    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        View child=rv.findChildViewUnder(e.getX(),e.getY());
        if(child!=null && clicklistener!=null && gestureDetector.onTouchEvent(e)){
            clicklistener.onClick(child,rv.getChildAdapterPosition(child));
        }

        return false;
    }

    @Override
    public void onTouchEvent(RecyclerView rv, MotionEvent e) {

    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.row_weekly_yes_no, parent, false);
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
