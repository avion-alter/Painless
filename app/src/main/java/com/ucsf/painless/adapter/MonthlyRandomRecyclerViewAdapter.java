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
import com.ucsf.painless.model.monthlyRandom.MonthlyRand36QuestionDataItem;
import com.ucsf.painless.model.monthlyRandom.MonthlyRandomResponse;
import com.ucsf.painless.view.MonthlyRandomActivity;

import java.util.ArrayList;
import java.util.List;

public class MonthlyRandomRecyclerViewAdapter extends RecyclerView.Adapter<MonthlyRandomRecyclerViewAdapter.ViewHolder> {

    private MonthlyRandomResponse packageList;
    private Context context;

    private RadioGroup lastCheckedRadioGroup = null;


    public MonthlyRandomRecyclerViewAdapter(MonthlyRandomResponse packageListIn, Context ctx) {
        packageList = packageListIn;
        context = ctx;
    }

    @Override
    public MonthlyRandomRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                                          int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_monthly_questions, parent, false);

        MonthlyRandomRecyclerViewAdapter.ViewHolder viewHolder =
                new MonthlyRandomRecyclerViewAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MonthlyRandomRecyclerViewAdapter.ViewHolder holder, int position) {
        MonthlyRand36QuestionDataItem packageModel = packageList.getData().getMonthlyRand36QuestionData().get(position);
        holder.packageName.setText(packageModel.getMrqueQuestion());
        holder.txt_count.setText(position+1+") ");
        if (!packageModel.getMrqueQuestionDescription().equals("")){
            holder.txt_desc.setVisibility(View.VISIBLE);
        holder.txt_desc.setText(packageModel.getMrqueQuestionDescription());
        }else {
            holder.txt_desc.setVisibility(View.GONE);
        }


        List<String> priceList = new ArrayList<String>();
        priceList.add(packageModel.getMrqueAnswer1());
        priceList.add(packageModel.getMrqueAnswer2());
        priceList.add(packageModel.getMrqueAnswer3());
        priceList.add(packageModel.getMrqueAnswer4());
        priceList.add(packageModel.getMrqueAnswer5());
        priceList.add(packageModel.getMrqueAnswer6());
        priceList.add(packageModel.getMrqueAnswer7());


        if (!packageModel.isRadioButtonDone()){
        int id = (position+1)*100;
        for(String price : priceList){
            if (!price.equals("")){
            RadioButton rb = new RadioButton(MonthlyRandomRecyclerViewAdapter.this.context);
            rb.setId(id++);
            rb.setText(price);
            rb.setTextColor(ContextCompat.getColorStateList(context, R.color.black));
            rb.setButtonTintList(ContextCompat.getColorStateList(context, R.color.blue));

            holder.priceGroup.addView(rb);
            }
        }
            packageList.getData().getMonthlyRand36QuestionData().get(position).setRadioButtonDone(true);

        }

        holder.priceGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = (RadioButton)holder.viewPublic.findViewById(radioGroup.getCheckedRadioButtonId());
                /*Toast.makeText(MonthlyRandomRecyclerViewAdapter.this.context,
                        "Radio  " + radioButton.getText(),
                        Toast.LENGTH_SHORT).show();*/
               // packageList.get(position).setSelectedOption(radioButton.getText().toString());
                packageList.getData().getMonthlyRand36QuestionData().get(position).setRadioButtonSelected(radioButton.getText().toString());
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return packageList.getData().getMonthlyRand36QuestionData().size();
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

