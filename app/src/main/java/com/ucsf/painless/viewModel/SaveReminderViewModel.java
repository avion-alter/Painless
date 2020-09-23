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
import com.ucsf.painless.model.saveRemainder.SaveReminderResponse;
import com.ucsf.painless.network.APIClient;
import com.ucsf.painless.network.APIInterface;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.view.DashboardActivity;

import retrofit2.Call;
import retrofit2.Callback;

public class SaveReminderViewModel extends ViewModel {
    private Context mContext;
    ProgressDialog pDialog;

    SessionManager sessionManager;
    APIInterface apiInterface;



    //this is the data that we will fetch asynchronously
    private MutableLiveData<SaveReminderResponse> saveReminderResponseMutableLiveData;

    //we will call this method to get the data
    public LiveData<SaveReminderResponse> saveReminder(String reminder_value_json,Context context) {
        //if the list is null
        this.mContext=context;

            saveReminderResponseMutableLiveData = new MutableLiveData<SaveReminderResponse>();
            //we will load it asynchronously from server in this method
            saveReminder(reminder_value_json);
        //finally we will return the list
        return saveReminderResponseMutableLiveData;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void saveReminder(String reminder_value_json) {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<SaveReminderResponse> call3 = apiInterface.savePatientReminder(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id),reminder_value_json);
            call3.enqueue(new Callback<SaveReminderResponse>() {
                @Override
                public void onResponse(Call<SaveReminderResponse> call, retrofit2.Response<SaveReminderResponse> response) {

                    pDialog.dismiss();
                    try {
                        if (response.body().isStatus()){
                            saveReminderResponseMutableLiveData.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SaveReminderResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }
}
