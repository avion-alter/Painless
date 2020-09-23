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
import com.ucsf.painless.model.intensity.IntensityResponse;
import com.ucsf.painless.network.APIClient;
import com.ucsf.painless.network.APIInterface;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.view.DashboardActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Field;

public class IntensityRatingViewModel extends ViewModel {
    private Context mContext;
    ProgressDialog pDialog;

    SessionManager sessionManager;
    APIInterface apiInterface;



    //this is the data that we will fetch asynchronously
    private MutableLiveData<IntensityResponse> heroList;

    //we will call this method to get the data
    public LiveData<IntensityResponse> saveIntensity( String inrt_pain_in, String inrt_painrelief, String inrt_unpleasantness, Context context) {
        //if the list is null
        this.mContext=context;

        heroList = new MutableLiveData<IntensityResponse>();
        //we will load it asynchronously from server in this method
        loadSignIn(inrt_pain_in,  inrt_painrelief,  inrt_unpleasantness);
        //finally we will return the list
        return heroList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void loadSignIn( String inrt_pain_in, String inrt_painrelief, String inrt_unpleasantness) {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();
            Log.e("Request",ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id)+inrt_pain_in+inrt_painrelief+inrt_unpleasantness);
            Call<IntensityResponse> call3 = apiInterface.saveIntensityRating(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id),inrt_pain_in,inrt_painrelief,inrt_unpleasantness);
            call3.enqueue(new Callback<IntensityResponse>() {
                @Override
                public void onResponse(Call<IntensityResponse> call, retrofit2.Response<IntensityResponse> response) {

                    pDialog.dismiss();
                    Log.e("Responce",response.toString());
                    //heroList.setValue(response.body());
                    try {
                        if (response.body().getStatus()){
                            //Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                           /* Intent n = new Intent(mContext, DashboardActivity.class);
                            n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            mContext.startActivity(n);*/

                            heroList.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<IntensityResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }
}
