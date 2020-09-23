package com.ucsf.painless.viewModel;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.ucsf.painless.ApplicationTest;
import com.ucsf.painless.R;
import com.ucsf.painless.model.bodyMap.BodyMapResponse;
import com.ucsf.painless.model.login.LoginResponse;
import com.ucsf.painless.model.saveBodyMapToWeb.SaveWebView;
import com.ucsf.painless.network.APIClient;
import com.ucsf.painless.network.APIInterface;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.view.DashboardActivity;

import retrofit2.Call;
import retrofit2.Callback;

public class BodyMapViewModel extends ViewModel {
    private Context mContext;
    ProgressDialog pDialog;

    SessionManager sessionManager;
    APIInterface apiInterface;



    //this is the data that we will fetch asynchronously
    private MutableLiveData<BodyMapResponse> heroList;
    private MutableLiveData<SaveWebView> saveWebViewMutableLiveData;

    //we will call this method to get the data
    public LiveData<BodyMapResponse> saveBodyMap(String intensity_id, String body_pain_map_value_json, Context context) {
        //if the list is null
        this.mContext=context;

            heroList = new MutableLiveData<BodyMapResponse>();
            //we will load it asynchronously from server in this method
        sendBodyMap(intensity_id,body_pain_map_value_json);
            //finally we will return the list
        return heroList;
    }

    //we will call this method to get the data
    public LiveData<SaveWebView> saveBodyMap(String intensity_id, Context context) {
        //if the list is null
        this.mContext=context;

        saveWebViewMutableLiveData = new MutableLiveData<SaveWebView>();
            //we will load it asynchronously from server in this method
        sendBodyMapWeb(intensity_id);
            //finally we will return the list
        return saveWebViewMutableLiveData;
    }

    //This method is using Retrofit to get the JSON data from URL
    //This method is using Retrofit to get the JSON data from URL
    private void sendBodyMap(String intensity_id, String body_pain_map_value_json) {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<BodyMapResponse> call3 = apiInterface.saveBodyMap(intensity_id,ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id),body_pain_map_value_json);
            call3.enqueue(new Callback<BodyMapResponse>() {
                @Override
                public void onResponse(Call<BodyMapResponse> call, retrofit2.Response<BodyMapResponse> response) {

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
                public void onFailure(Call<BodyMapResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }

    private void sendBodyMapWeb(String intensity_id) {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<SaveWebView> call3 = apiInterface.saveBodyMapWeb(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id),intensity_id);
            call3.enqueue(new Callback<SaveWebView>() {
                @Override
                public void onResponse(Call<SaveWebView> call, retrofit2.Response<SaveWebView> response) {

                    pDialog.dismiss();
                    try {
                        if (response.body().getStatus().equals("true")){
                            saveWebViewMutableLiveData.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SaveWebView> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }
}
