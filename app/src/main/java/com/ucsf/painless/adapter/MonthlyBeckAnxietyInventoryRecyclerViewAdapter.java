package com.ucsf.painless.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsf.painless.R;
import com.ucsf.painless.model.monthlyBeckAnxiety.MonthlyBeckAnxietyInventoryQuestionDataItem;
import com.ucsf.painless.model.monthlyBeckAnxiety.MonthlyBeckAnxietyInventoryQuestionResponse;

import java.util.ArrayList;
import java.util.List;

public class MonthlyBeckAnxietyInventoryRecyclerViewAdapter extends RecyclerView.Adapter<MonthlyBeckAnxietyInventoryRecyclerViewAdapter.ViewHolder> {

    private MonthlyBeckAnxietyInventoryQuestionResponse packageList;
    private Context context;

    private RadioGroup lastCheckedRadioGroup = null;

    public MonthlyBeckAnxietyInventoryRecyclerViewAdapter(MonthlyBeckAnxietyInventoryQuestionResponse packageListIn
            , Context ctx) {
        packageList = packageListIn;
        context = ctx;
    }

    @Override
    public MonthlyBeckAnxietyInventoryRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                                        int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_monthly_questions, parent, false);

        MonthlyBeckAnxietyInventoryRecyclerViewAdapter.ViewHolder viewHolder =
                new MonthlyBeckAnxietyInventoryRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MonthlyBeckAnxietyInventoryRecyclerViewAdapter.ViewHolder holder, int position) {
        MonthlyBeckAnxietyInventoryQuestionDataItem packageModel = packageList.getData().getMonthlyBeckAnxietyInventoryQuestionData().get(position);
        holder.packageName.setText(packageModel.getBaiQuestion());
        holder.txt_count.setText(position+1+") ");

        List<String> priceList = new ArrayList<String>();
        priceList.add(packageModel.getBaiAnswer1());
        priceList.add(packageModel.getBaiAnswer2());
        priceList.add(packageModel.getBaiAnswer3());
        priceList.add(packageModel.getBaiAnswer4());



        if (!packageModel.isRadioButtonDone()){
        int id = (position+1)*100;
        for(String price : priceList){
            if (!price.equals("")){
            RadioButton rb = new RadioButton(MonthlyBeckAnxietyInventoryRecyclerViewAdapter.this.context);
            rb.setId(id++);
            rb.setText(price);
            rb.setTextColor(ContextCompat.getColorStateList(context, R.color.black));
            rb.setButtonTintList(ContextCompat.getColorStateList(context, R.color.blue));

            holder.priceGroup.addView(rb);
            }
        }
            packageList.getData().getMonthlyBeckAnxietyInventoryQuestionData().get(position).setRadioButtonDone(true);

        }

        holder.priceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton)holder.viewPublic.findViewById(radioGroup.getCheckedRadioButtonId());
                /*Toast.makeText(MonthlyBeckAnxietyInventoryRecyclerViewAdapter.this.context,
                        "Radio  " + radioButton.getText(),
                        Toast.LENGTH_SHORT).show();*/
               // packageList.get(position).setSelectedOption(radioButton.getText().toString());
                packageList.getData().getMonthlyBeckAnxietyInventoryQuestionData().get(position).setRadioButtonSelected(radioButton.getText().toString());
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return packageList.getData().getMonthlyBeckAnxietyInventoryQuestionData().size();
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

