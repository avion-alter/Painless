package com.ucsf.painless.viewModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ucsf.painless.ApplicationTest;
import com.ucsf.painless.R;
import com.ucsf.painless.model.clinicalGlobalImpressions.ClinicalGlobalImpressionsResponse;
import com.ucsf.painless.model.monthlyBackDepression.MonthlyBeckDepressionResponse;
import com.ucsf.painless.model.monthlyBeckAnxiety.MonthlyBeckAnxietyInventoryQuestionResponse;
import com.ucsf.painless.model.monthlyRandom.MonthlyRandomResponse;
import com.ucsf.painless.network.APIClient;
import com.ucsf.painless.network.APIInterface;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;

public class MonthlyRandQuesViewModel extends ViewModel {
    private Context mContext;
    ProgressDialog pDialog;

    SessionManager sessionManager;
    APIInterface apiInterface;



    //this is the data that we will fetch asynchronously
    private MutableLiveData<MonthlyRandomResponse> heroList;
    private MutableLiveData<MonthlyBeckAnxietyInventoryQuestionResponse> responseMutableLiveData;
    private MutableLiveData<ClinicalGlobalImpressionsResponse> impressionsResponseMutableLiveData;
    private MutableLiveData<MonthlyBeckDepressionResponse> depressionResponseMutableLiveData;

    //we will call this method to get the data
    public LiveData<MonthlyRandomResponse> getMonthlyRandQues(Context context) {
        //if the list is null
        this.mContext=context;

        heroList = new MutableLiveData<MonthlyRandomResponse>();
        //we will load it asynchronously from server in this method
        getMonthlyRandQues();
        //finally we will return the list
        return heroList;
    }

    //we will call this method to get the data
    public LiveData<MonthlyBeckAnxietyInventoryQuestionResponse> getMonthlyBeckAnxietyInventoryQuestion(Context context) {
        //if the list is null
        this.mContext=context;

        responseMutableLiveData = new MutableLiveData<MonthlyBeckAnxietyInventoryQuestionResponse>();
        //we will load it asynchronously from server in this method
        getMonthlyBeckAnxietyInventoryQuestion();
        //finally we will return the list
        return responseMutableLiveData;
    }

    //we will call this method to get the data
    public LiveData<ClinicalGlobalImpressionsResponse> getClinicalGlobalImpressionsQuestion(Context context) {
        //if the list is null
        this.mContext=context;

        impressionsResponseMutableLiveData= new MutableLiveData<ClinicalGlobalImpressionsResponse>();
        //we will load it asynchronously from server in this method
        getClinicalGlobalImpressionsQuestion();
        //finally we will return the list
        return impressionsResponseMutableLiveData;
    }

    //we will call this method to get the data
    public LiveData<MonthlyBeckDepressionResponse> getMonthlyBeckDepression(Context context) {
        //if the list is null
        this.mContext=context;

        depressionResponseMutableLiveData= new MutableLiveData<MonthlyBeckDepressionResponse>();
        //we will load it asynchronously from server in this method
        getMonthlyBeckDepression();
        //finally we will return the list
        return depressionResponseMutableLiveData;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void getMonthlyRandQues() {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<MonthlyRandomResponse> call3 = apiInterface.getMonthlyRandQues(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id));
            call3.enqueue(new Callback<MonthlyRandomResponse>() {
                @Override
                public void onResponse(Call<MonthlyRandomResponse> call, retrofit2.Response<MonthlyRandomResponse> response) {

                    pDialog.dismiss();
                    try {
                        if (response.body().isStatus()){
                            Log.e("response",response.body().getData().getMonthlyRand36QuestionData().toString());
                            heroList.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<MonthlyRandomResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }

    //This method is using Retrofit to get the JSON data from URL
    private void getMonthlyBeckAnxietyInventoryQuestion() {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<MonthlyBeckAnxietyInventoryQuestionResponse> call3 = apiInterface.getMonthlyBeckAnxietyInventoryQuestion(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id));
            call3.enqueue(new Callback<MonthlyBeckAnxietyInventoryQuestionResponse>() {
                @Override
                public void onResponse(Call<MonthlyBeckAnxietyInventoryQuestionResponse> call, retrofit2.Response<MonthlyBeckAnxietyInventoryQuestionResponse> response) {

                    pDialog.dismiss();
                    try {
                        if (response.body().isStatus()){
                            Log.e("response",response.body().getData().getMonthlyBeckAnxietyInventoryQuestionData().toString());
                            responseMutableLiveData.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<MonthlyBeckAnxietyInventoryQuestionResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }

    //This method is using Retrofit to get the JSON data from URL
    private void getClinicalGlobalImpressionsQuestion() {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<ClinicalGlobalImpressionsResponse> call3 = apiInterface.getClinicalGlobalImpressionsQuestion(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id));
            call3.enqueue(new Callback<ClinicalGlobalImpressionsResponse>() {
                @Override
                public void onResponse(Call<ClinicalGlobalImpressionsResponse> call, retrofit2.Response<ClinicalGlobalImpressionsResponse> response) {

                    pDialog.dismiss();
                    try {
                        if (response.body().isStatus()){
                            Log.e("response",response.body().getData().getMonthlyClinicalGlobalImpressionScalesQuestionData().toString());
                            impressionsResponseMutableLiveData.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ClinicalGlobalImpressionsResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }

    //This method is using Retrofit to get the JSON data from URL
    private void getMonthlyBeckDepression() {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<MonthlyBeckDepressionResponse> call3 = apiInterface.getMonthlyBeckDepression(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id));
            call3.enqueue(new Callback<MonthlyBeckDepressionResponse>() {
                @Override
                public void onResponse(Call<MonthlyBeckDepressionResponse> call, retrofit2.Response<MonthlyBeckDepressionResponse> response) {

                    pDialog.dismiss();
                    try {
                        if (response.body().isStatus()){
                            depressionResponseMutableLiveData.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<MonthlyBeckDepressionResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }
}