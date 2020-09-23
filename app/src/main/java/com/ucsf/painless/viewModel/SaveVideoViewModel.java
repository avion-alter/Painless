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
import com.ucsf.painless.model.firstVideo.FirstVideoResponse;
import com.ucsf.painless.model.login.LoginResponse;
import com.ucsf.painless.network.APIClient;
import com.ucsf.painless.network.APIInterface;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.view.DashboardActivity;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;

public class SaveVideoViewModel extends ViewModel {
    private Context mContext;
    ProgressDialog pDialog;

    SessionManager sessionManager;
    APIInterface apiInterface;



    //this is the data that we will fetch asynchronously
    private MutableLiveData<FirstVideoResponse> firstVideoResponseMutableLiveData;
    private MutableLiveData<FirstVideoResponse> secondVideoResponseMutableLiveData;
    private MutableLiveData<FirstVideoResponse> heroList;

    //we will call this method to get the data
    public LiveData<FirstVideoResponse> saveFirstVideo(String firstVideo, Context context) {
        //if the list is null
        this.mContext=context;

        firstVideoResponseMutableLiveData = new MutableLiveData<FirstVideoResponse>();
        //we will load it asynchronously from server in this method
        saveFirstVideo(firstVideo);
        //finally we will return the list
        return firstVideoResponseMutableLiveData;
    }

    //we will call this method to get the data
    public LiveData<FirstVideoResponse> saveSecondVideo(String secondVideo,String pvdo_id, Context context) {
        //if the list is null
        this.mContext=context;

        secondVideoResponseMutableLiveData = new MutableLiveData<FirstVideoResponse>();
        //we will load it asynchronously from server in this method
        saveSecondVideo(secondVideo,pvdo_id);
        //finally we will return the list
        return secondVideoResponseMutableLiveData;
    }

    //we will call this method to get the data
    public LiveData<FirstVideoResponse> saveThirdVideo(String secondVideo,String pvdo_id, Context context) {
        //if the list is null
        this.mContext=context;

        heroList = new MutableLiveData<FirstVideoResponse>();
        //we will load it asynchronously from server in this method
        saveThirdVideo(secondVideo,pvdo_id);
        //finally we will return the list
        return heroList;
    }

    //This method is using Retrofit to get the JSON data from URL
    private void saveFirstVideo(String firstVideo) {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();

            File file1 = new File(firstVideo);
            RequestBody requestBody1;
            String firstVideoName = "";

            if (firstVideo.equals("")){
                requestBody1 =  RequestBody.create(MediaType.parse("text/plain"), "");
            }else {
                requestBody1 = RequestBody.create(MediaType.parse("*/*"), file1);
                firstVideoName=file1.getName();
            }

            Log.e("Pat_id",ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id));
            MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("video_file", firstVideoName, requestBody1);

            Call<FirstVideoResponse> call3 = apiInterface.savePatientVideoOne(fileToUpload1,Integer.parseInt(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id)));
            call3.enqueue(new Callback<FirstVideoResponse>() {
                @Override
                public void onResponse(Call<FirstVideoResponse> call, retrofit2.Response<FirstVideoResponse> response) {
                    pDialog.dismiss();
                    try {
                        if (response.body().isStatus()){
                            firstVideoResponseMutableLiveData.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<FirstVideoResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }

    //This method is using Retrofit to get the JSON data from URL
    private void saveSecondVideo(String secondVideo,String pvdo_id) {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();

            File file1 = new File(secondVideo);
            RequestBody requestBody1;
            String firstVideoName = "";

            if (secondVideo.equals("")){
                requestBody1 =  RequestBody.create(MediaType.parse("text/plain"), "");
            }else {
                requestBody1 = RequestBody.create(MediaType.parse("*/*"), file1);
                firstVideoName=file1.getName();
            }

            MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("video_file", firstVideoName, requestBody1);

            Call<FirstVideoResponse> call3 = apiInterface.savePatientVideoTwo(fileToUpload1,Integer.parseInt(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id)),Integer.parseInt(pvdo_id),"second");
            call3.enqueue(new Callback<FirstVideoResponse>() {
                @Override
                public void onResponse(Call<FirstVideoResponse> call, retrofit2.Response<FirstVideoResponse> response) {
                    pDialog.dismiss();
                    try {
                        if (response.body().isStatus()){
                            secondVideoResponseMutableLiveData.setValue(response.body());
                        }else {
                            Toast.makeText(mContext, ""+response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<FirstVideoResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }

    //This method is using Retrofit to get the JSON data from URL
    private void saveThirdVideo(String secondVideo,String pvdo_id) {

        sessionManager=new SessionManager(mContext);
        if(ApplicationTest.getInstance().connectionDetector.isConnectingToInternet()){
            apiInterface = APIClient.getClient().create(APIInterface.class);
            pDialog = new ProgressDialog(mContext, R.style.MyTheme);
            pDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Small);
            pDialog.setCancelable(false);
            pDialog.show();

            File file1 = new File(secondVideo);
            RequestBody requestBody1;
            String firstVideoName = "";

            if (secondVideo.equals("")){
                requestBody1 =  RequestBody.create(MediaType.parse("text/plain"), "");
            }else {
                requestBody1 = RequestBody.create(MediaType.parse("*/*"), file1);
                firstVideoName=file1.getName();
            }

            MultipartBody.Part fileToUpload1 = MultipartBody.Part.createFormData("video_file", firstVideoName, requestBody1);

            Call<FirstVideoResponse> call3 = apiInterface.savePatientVideoTwo(fileToUpload1,Integer.parseInt(ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id)),Integer.parseInt(pvdo_id),"thrid");
            call3.enqueue(new Callback<FirstVideoResponse>() {
                @Override
                public void onResponse(Call<FirstVideoResponse> call, retrofit2.Response<FirstVideoResponse> response) {
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
                public void onFailure(Call<FirstVideoResponse> call, Throwable t) {
                    pDialog.dismiss();
                    call.cancel();
                }
            });
        }else {
            Toast.makeText(mContext, ""+mContext.getResources().getString(R.string.no_internet_message), Toast.LENGTH_SHORT).show();
        }
    }
}
