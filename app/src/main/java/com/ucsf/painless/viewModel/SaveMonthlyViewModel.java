package com.ucsf.painless.viewModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ucsf.painless.ApplicationTest;
import com.ucsf.painless.R;
import com.ucsf.painless.model.login.LoginResponse;
import com.ucsf.painless.model.saveMonthlyReport.SaveMonthlyResponse;
import com.ucsf.painless.network.APIClient;
import com.ucsf.painless.network.APIInterface;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.view.DashboardActivity;

import retrofit2.Call;
import retrofit2.Callback;

public class SaveMonthlyViewModel extends ViewModel {
    private Context mContext;
    ProgressDialog pDialog;

    SessionManager sessionManager;
    APIInterface apiInterface;



    //this is the data that we will fetch asynchronously
    private MutableLiveData<SaveMonthlyResponse> heroList;
    private MutableLiveData<SaveMonthlyResponse> saveMonthlyResponseMutableLiveData;
    private MutableLiveData<SaveMonthlyResponse> saveMonthlysaveMonthlyBeckAnxietyMutableLiveData;
    private MutableLiveData<SaveMonthlyResponse> saveMonthlyDepressionLiveData;

    //we will call this method to get the data
    public LiveData<SaveMonthlyResponse> saveMonthly(String answer_value_json, Context context) {
        //if the list is null
        this.mContext=context;

            heroList = new MutableLiveData<SaveMonthlyResponse>();
            //we will load it asynchronously from server in this method
        saveMonthly(answer_value_json);
        //finally we will return the list
        return heroList;
    }

    //we will call this method to get the data
    public LiveData<SaveMonthlyResponse> saveMonthlyClinical(String answer_value_json,String reportId, Context context) {
        //if the list is null
        this.mContext=context;

        saveMonthlyResponseMutableLiveData = new MutableLiveData<SaveMonthlyResponse>();
            //we will load it asynchronously from server in this method
        saveMonthlyClinical(answer_value_json,reportId);
        //finally we will return the list
        return saveMonthlyResponseMutableLiveData;
    }

    //we will call this method to get the data
    public LiveData<SaveMonthlyResponse> saveMonthlyBeckAnxiety(String answer_value_json,String reportId, Context context) {
        //if the list is null
        this.mContext=context;

        saveMonthlysaveMonthlyBeckAnxietyMutableLiveData = new MutableLiveData<SaveMonthlyResponse>();
            //we will load it asynchronously from server in this method
        saveMonthlyBeckAnxiety(answer_value_json,reportId);
        //finally we will return the list
        return saveMonthlysaveMonthlyBeckAnxietyMutableLiveData;
    }

    //we will call this method to get the data
    public LiveData<SaveMonthlyResponse> saveMonthlyBeckDepression(String answer_value_json,String reportId, Context context) {
        //if the list is null
        this.mContext=context;

        saveMonthlyDepressionLiveData = new MutableLiveData<SaveMonthlyResponse>();
            //we will load it asynchronously from server in this method
        saveMonthlyBeckDepression(answer_value_json,reportId);
        //finally we will return the list
        return saveMonthlyDepressionLiveData;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void saveMonthly(String answer_value_json) {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<SaveMonthlyResponse> call3 = apiInterface.saveMonthly(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id),answer_value_json);
            call3.enqueue(new Callback<SaveMonthlyResponse>() {
                @Override
                public void onResponse(Call<SaveMonthlyResponse> call, retrofit2.Response<SaveMonthlyResponse> response) {

                    pDialog.dismiss();
                    try {
                        if (response.isSuccessful()){
                            heroList.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SaveMonthlyResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }

    //This method is using Retrofit to get the JSON data from URL
    private void saveMonthlyClinical(String answer_value_json,String reportId) {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<SaveMonthlyResponse> call3 = apiInterface.saveClinicalGlobalImpression(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id),reportId,answer_value_json);
            call3.enqueue(new Callback<SaveMonthlyResponse>() {
                @Override
                public void onResponse(Call<SaveMonthlyResponse> call, retrofit2.Response<SaveMonthlyResponse> response) {

                    pDialog.dismiss();
                    try {
                        if (response.isSuccessful()){
                            saveMonthlyResponseMutableLiveData.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SaveMonthlyResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }

    //This method is using Retrofit to get the JSON data from URL
    private void saveMonthlyBeckAnxiety(String answer_value_json,String reportId) {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<SaveMonthlyResponse> call3 = apiInterface.saveMonthlyBeckAnxietyInventoryAnswer(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id),reportId,answer_value_json);
            call3.enqueue(new Callback<SaveMonthlyResponse>() {
                @Override
                public void onResponse(Call<SaveMonthlyResponse> call, retrofit2.Response<SaveMonthlyResponse> response) {

                    pDialog.dismiss();
                    try {
                        if (response.isSuccessful()){
                            saveMonthlysaveMonthlyBeckAnxietyMutableLiveData.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SaveMonthlyResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }

    //This method is using Retrofit to get the JSON data from URL
    private void saveMonthlyBeckDepression(String answer_value_json,String reportId) {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<SaveMonthlyResponse> call3 = apiInterface.saveMonthlyBeckDepressionAnswer(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id),reportId,answer_value_json);
            call3.enqueue(new Callback<SaveMonthlyResponse>() {
                @Override
                public void onResponse(Call<SaveMonthlyResponse> call, retrofit2.Response<SaveMonthlyResponse> response) {

                    pDialog.dismiss();
                    try {
                        if (response.isSuccessful()){
                            saveMonthlyDepressionLiveData.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SaveMonthlyResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }
}
