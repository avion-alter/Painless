package com.ucsf.painless.viewModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ucsf.painless.ApplicationTest;
import com.ucsf.painless.R;
import com.ucsf.painless.model.saveWeekly.SaveWeeklyResponse;
import com.ucsf.painless.model.weeklyOne.WeeklyOneResponse;
import com.ucsf.painless.model.weeklySecond.WeeklyTwoResponse;
import com.ucsf.painless.network.APIClient;
import com.ucsf.painless.network.APIInterface;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.utils.SessionManager;

import retrofit2.Call;
import retrofit2.Callback;

public class WeeklyTwoViewModel extends ViewModel {
    private Context mContext;
    ProgressDialog pDialog;

    SessionManager sessionManager;
    APIInterface apiInterface;

    private MutableLiveData<SaveWeeklyResponse> saveWeeklyResponseMutableLiveData;

    //this is the data that we will fetch asynchronously
    private MutableLiveData<WeeklyTwoResponse> heroList;

    //we will call this method to get the data
    public LiveData<WeeklyTwoResponse> getWeeklyTwo(Context context) {
        //if the list is null
        this.mContext=context;

            heroList = new MutableLiveData<WeeklyTwoResponse>();
            //we will load it asynchronously from server in this method
        getWeeklyTwo();
        //finally we will return the list
        return heroList;
    }


    //we will call this method to get the data
    public LiveData<SaveWeeklyResponse> saveWeeklyTwo(Context context,String weeklyOneJson,String report_id) {
        //if the list is null
        this.mContext=context;

        saveWeeklyResponseMutableLiveData = new MutableLiveData<SaveWeeklyResponse>();
        //we will load it asynchronously from server in this method
        saveWeeklyTwo(weeklyOneJson,report_id);
        //finally we will return the list
        return saveWeeklyResponseMutableLiveData;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void getWeeklyTwo() {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<WeeklyTwoResponse> call3 = apiInterface.getWeeklyTwo(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id));
            call3.enqueue(new Callback<WeeklyTwoResponse>() {
                @Override
                public void onResponse(Call<WeeklyTwoResponse> call, retrofit2.Response<WeeklyTwoResponse> response) {

                    pDialog.dismiss();
                    try {
                        if (response.body().isStatus()){
                            heroList.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<WeeklyTwoResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }

    //This method is using Retrofit to get the JSON data from URL
    private void saveWeeklyTwo(String weeklyOneJson,String report_id) {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<SaveWeeklyResponse> call3 = apiInterface.saveWeeklyTwo(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id),report_id,weeklyOneJson);
            call3.enqueue(new Callback<SaveWeeklyResponse>() {
                @Override
                public void onResponse(Call<SaveWeeklyResponse> call, retrofit2.Response<SaveWeeklyResponse> response) {

                    pDialog.dismiss();
                    try {
                        if (response.body().isStatus()){
                            saveWeeklyResponseMutableLiveData.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SaveWeeklyResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }

}
