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
import com.ucsf.painless.network.APIClient;
import com.ucsf.painless.network.APIInterface;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.view.DashboardActivity;

import retrofit2.Call;
import retrofit2.Callback;

public class LoginViewModel extends ViewModel {
    private Context mContext;
    ProgressDialog pDialog;

    SessionManager sessionManager;
    APIInterface apiInterface;



    //this is the data that we will fetch asynchronously
    private MutableLiveData<LoginResponse> heroList;

    //we will call this method to get the data
    public LiveData<LoginResponse> getSignIn(String email, String password, Context context) {
        //if the list is null
        this.mContext=context;

            heroList = new MutableLiveData<LoginResponse>();
            //we will load it asynchronously from server in this method
            loadSignIn(email,password);
        //finally we will return the list
        return heroList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadSignIn(String email, String password) {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<LoginResponse> call3 = apiInterface.signIn(email,password,"2","1","");
            call3.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, retrofit2.Response<LoginResponse> response) {

                    pDialog.dismiss();
                    try {
                        if (response.body().isStatus()){
                            Log.e("response",response.body().getData().getPatientData().toString());
                            sessionManager.setBooleanKey(SessionKeyConstant.KEY_is_login,true);
                            sessionManager.setStringKey(SessionKeyConstant.KEY_user_id, String.valueOf(response.body().getData().getPatientData().get(0).getPatId()));
                            sessionManager.setStringKey(SessionKeyConstant.KEY_full_name, String.valueOf(response.body().getData().getPatientData().get(0).getPatFname()));
                            sessionManager.setStringKey(SessionKeyConstant.KEY_email, String.valueOf(response.body().getData().getPatientData().get(0).getPatEmail()));
                            sessionManager.setStringKey(SessionKeyConstant.KEY_gender, String.valueOf(response.body().getData().getPatientData().get(0).getPatGender()));
                            Intent n = new Intent(mContext, DashboardActivity.class);
                            n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            mContext.startActivity(n);
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }
}
