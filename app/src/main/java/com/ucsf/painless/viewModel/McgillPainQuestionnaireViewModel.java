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
import com.ucsf.painless.model.questionnaire.McGillQuestionnaireResponse;
import com.ucsf.painless.network.APIClient;
import com.ucsf.painless.network.APIInterface;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.view.DashboardActivity;

import retrofit2.Call;
import retrofit2.Callback;

public class McgillPainQuestionnaireViewModel extends ViewModel {
    private Context mContext;
    ProgressDialog pDialog;

    SessionManager sessionManager;
    APIInterface apiInterface;



    //this is the data that we will fetch asynchronously
    private MutableLiveData<McGillQuestionnaireResponse> heroList;

    //we will call this method to get the data
    public LiveData<McGillQuestionnaireResponse> getQuestionnaire(Context context) {
        //if the list is null
        this.mContext=context;

        heroList = new MutableLiveData<McGillQuestionnaireResponse>();
        //we will load it asynchronously from server in this method
        getQuestionnaire();
        //finally we will return the list
        return heroList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void getQuestionnaire() {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Call<McGillQuestionnaireResponse> call3 = apiInterface.getQuestionnaire(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id));
            call3.enqueue(new Callback<McGillQuestionnaireResponse>() {
                @Override
                public void onResponse(Call<McGillQuestionnaireResponse> call, retrofit2.Response<McGillQuestionnaireResponse> response) {

                    pDialog.dismiss();
                    //heroList.setValue(response.body());
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
                public void onFailure(Call<McGillQuestionnaireResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }
}
