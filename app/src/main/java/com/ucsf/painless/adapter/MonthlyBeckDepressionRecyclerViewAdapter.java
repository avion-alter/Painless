package com.ucsf.painless.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsf.painless.R;
import com.ucsf.painless.model.monthlyBackDepression.MonthlyBeckDepressionInventoryQuestionDataItem;
import com.ucsf.painless.model.monthlyBackDepression.MonthlyBeckDepressionResponse;
import com.ucsf.painless.model.monthlyBeckAnxiety.MonthlyBeckAnxietyInventoryQuestionDataItem;
import com.ucsf.painless.model.monthlyBeckAnxiety.MonthlyBeckAnxietyInventoryQuestionResponse;

import java.util.ArrayList;
import java.util.List;

public class MonthlyBeckDepressionRecyclerViewAdapter extends RecyclerView.Adapter<MonthlyBeckDepressionRecyclerViewAdapter.ViewHolder> {

    private MonthlyBeckDepressionResponse packageList;
    private Context context;

    private RadioGroup lastCheckedRadioGroup = null;

    public MonthlyBeckDepressionRecyclerViewAdapter(MonthlyBeckDepressionResponse packageListIn
            , Context ctx) {
        packageList = packageListIn;
        context = ctx;
    }

    @Override
    public MonthlyBeckDepressionRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                  int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_monthly_back_depression_questions, parent, false);

        MonthlyBeckDepressionRecyclerViewAdapter.ViewHolder viewHolder =
                new MonthlyBeckDepressionRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MonthlyBeckDepressionRecyclerViewAdapter.ViewHolder holder, int position) {
        MonthlyBeckDepressionInventoryQuestionDataItem packageModel = packageList.getData().getMonthlyBeckDepressionInventoryQuestionData().get(position);
        holder.packageName.setText(packageModel.getBdiQuestionNumber());
        //holder.txt_count.setText(position+1+") ");
        holder.txt_count.setVisibility(View.GONE);

        List<String> priceList = new ArrayList<String>();
        priceList.add(packageModel.getBdiAnswer1());
        priceList.add(packageModel.getBdiAnswer2());
        priceList.add(packageModel.getBdiAnswer3());
        priceList.add(packageModel.getBdiAnswer4());
        priceList.add(packageModel.getBdiAnswer5());
        priceList.add(packageModel.getBdiAnswer6());
        priceList.add(packageModel.getBdiAnswer7());



        if (!packageModel.isRadioButtonDone()){
        int id = (position+1)*100;
        for(String price : priceList){
            if (!price.equals("")){
            RadioButton rb = new RadioButton(MonthlyBeckDepressionRecyclerViewAdapter.this.context);
            rb.setId(id++);
            rb.setText(price);
            rb.setTextColor(ContextCompat.getColorStateList(context, R.color.black));
            rb.setButtonTintList(ContextCompat.getColorStateList(context, R.color.blue));

            holder.priceGroup.addView(rb);
            }
        }
            packageList.getData().getMonthlyBeckDepressionInventoryQuestionData().get(position).setRadioButtonDone(true);

        }

        holder.priceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton)holder.viewPublic.findViewById(radioGroup.getCheckedRadioButtonId());
                /*Toast.makeText(MonthlyBeckAnxietyInventoryRecyclerViewAdapter.this.context,
                        "Radio  " + radioButton.getText(),
                        Toast.LENGTH_SHORT).show();*/
               // packageList.get(position).setSelectedOption(radioButton.getText().toString());
                packageList.getData().getMonthlyBeckDepressionInventoryQuestionData().get(position).setRadioButtonSelected(radioButton.getText().toString());
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return packageList.getData().getMonthlyBeckDepressionInventoryQuestionData().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView packageName;
        public TextView txt_desc;
        public TextView txt_count;
        public RadioGroup priceGroup;
        public View viewPublic;

        public ViewHolder(View view) {
            super(view);
            packageName = (TextView) view.findViewById(R.id.package_name);
            txt_desc = (TextView) view.findViewById(R.id.txt_desc);
            txt_count = (TextView) view.findViewById(R.id.txt_count);
            priceGroup = (RadioGroup) view.findViewById(R.id.price_grp);
            viewPublic=view;

        }
    }
}

