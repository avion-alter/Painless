package com.ucsf.painless.view;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ucsf.painless.ApplicationTest;
import com.ucsf.painless.R;
import com.ucsf.painless.model.bodyMap.BodyMapResponse;
import com.ucsf.painless.model.saveBodyMapToWeb.SaveWebView;
import com.ucsf.painless.utils.SessionKeyConstant;
import com.ucsf.painless.viewModel.BodyMapViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BodyMapWebViewActivity extends AppCompatActivity {
    @BindView(R.id.card_back)
    CardView cardBack;
    @BindView(R.id.webview)
    WebView webview;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    BodyMapViewModel bodyMapViewModel;
    @BindView(R.id.card_save)
    CardView cardSave;
    String intensity_id;

    Dialog myDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body_map_web);
        ButterKnife.bind(this);
        bodyMapViewModel = ViewModelProviders.of(this).get(BodyMapViewModel.class);
        intensity_id = getIntent().getStringExtra("intensity_id");

        progressBar.setVisibility(View.GONE);
        webview.loadUrl("https://server.ashoresystems.com/~painless/Daily_report/heatmap_body_part_web_view/"+intensity_id+"/"+ ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id));
        Log.e("LINK","https://server.ashoresystems.com/~painless/Daily_report/heatmap_body_part_web_view/"+intensity_id+"/"+ ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_user_id));
        WebSettings webSettings = webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLoadWithOverviewMode(true);
        webSettings.setUseWideViewPort(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setDisplayZoomControls(false);
        webSettings.setSupportZoom(true);
        webSettings.setDefaultTextEncodingName("utf-8");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @OnClick(R.id.card_back)
    public void onViewClicked() {
        finish();
    }

    @OnClick(R.id.card_save)
    public void onSaveViewClicked() {

        bodyMapViewModel.saveBodyMap(intensity_id, BodyMapWebViewActivity.this).observe(this, new Observer<SaveWebView>() {
            @Override
            public void onChanged(@Nullable SaveWebView bodyMapResponse) {
                try {
                    if (bodyMapResponse.getStatus().equals("true")) {
                        Context context;
                        myDialog = new Dialog(BodyMapWebViewActivity.this);
                        ShowPopup();

                    } else {
                        Toast.makeText(BodyMapWebViewActivity.this, "" + bodyMapResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });
    }


    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public class WebViewClient extends android.webkit.WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {

            // TODO Auto-generated method stub
            super.onPageStarted(view, url, favicon);
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {

            // TODO Auto-generated method stub
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {

            // TODO Auto-generated method stub

            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }

    }


    public void ShowPopup() {

        Button next;
        myDialog.setContentView(R.layout.dialog_video_saved);
        next = (Button) myDialog.findViewById(R.id.btn_next);
        TextView txt_heading = (TextView) myDialog.findViewById(R.id.txt_heading);
        TextView txt_date = (TextView) myDialog.findViewById(R.id.txt_date);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        txt_heading.setText("Just one more step to go!");


        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy- hh:mm a", Locale.getDefault());

        String currentDateandTime = sdf.format(new Date());

        txt_date.setText(currentDateandTime);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myDialog.dismiss();
                Intent n = new Intent(BodyMapWebViewActivity.this, CongratulationsScreenActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                n.putExtra("msg", "Congratulations,"+ApplicationTest.getInstance().sessionManager.getStringKey(SessionKeyConstant.KEY_full_name)+"! You just completed a pain report");
                startActivity(n);
            }
        });

        myDialog.show();
    }


}
