package com.ucsf.painless.view;

import android.app.ActivityOptions;
import android.app.Dialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.NotificationCompat;

import com.ucsf.painless.ApplicationTest;
import com.ucsf.painless.R;
import com.ucsf.painless.utils.Constant;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TrialInfoActivity extends AppCompatActivity {


    @BindView(R.id.linear_form)
    LinearLayout linearForm;
    @BindView(R.id.linear_web)
    LinearLayout linearWeb;
    @BindView(R.id.card_back)
    CardView cardBack;
    private Dialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trial_info);
        ButterKnife.bind(this);


        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());

    }

    @OnClick({R.id.linear_form, R.id.linear_web})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.linear_form:
                /**/
                myDialog=new Dialog(this);
                ShowPopup();
                break;
            case R.id.linear_web:
                Intent intent = new Intent(TrialInfoActivity.this, WebViewActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.putExtra("url", "https://clinicaltrials.gov/ct2/show/NCT04144972");
                startActivity(intent, ActivityOptions.makeSceneTransitionAnimation(this).toBundle());

                break;
        }
    }

    @OnClick(R.id.card_back)
    public void onViewClicked() {
        finish();
    }


    private void copyReadAssets()
    {
        AssetManager assetManager = getAssets();

        InputStream in = null;
        OutputStream out = null;

        String strDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+ File.separator + "Painless";
        File fileDir = new File(strDir);
        fileDir.mkdirs();   // crear la ruta si no existe
        File file = new File(fileDir, "consent.pdf");



        try
        {

            in = assetManager.open("consent.pdf");  //leer el archivo de assets
            out = new BufferedOutputStream(new FileOutputStream(file)); //crear el archivo


            copyFile(in, out);
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e)
        {
            Log.e("tag", e.getMessage());
        }

        Toast.makeText(this, "File downloaded successfully, Please find document in download.", Toast.LENGTH_SHORT).show();
    }

    private void copyFile(InputStream in, OutputStream out) throws IOException
    {
        byte[] buffer = new byte[1024];
        int read;
        while ((read = in.read(buffer)) != -1)
        {
            out.write(buffer, 0, read);
        }
    }


   /* public void copyFileOrDir(String path, String destinationDir) {
        AssetManager assetManager = this.getAssets();
        String assets[] = null;
        try {
            assets = assetManager.list(path);
            if (assets.length == 0) {
                copyFile(path,destinationDir);
            } else {
                String fullPath = destinationDir + "/" + path;
                File dir = new File(fullPath);
                if (!dir.exists())
                    dir.mkdir();
                for (int i = 0; i < assets.length; ++i) {
                    copyFileOrDir(path + "/" + assets[i], destinationDir + path + "/" + assets[i]);
                }
            }
        } catch (IOException ex) {
            Log.e("tag", "I/O Exception", ex);
        }
    }

    private void copyFile(String filename, String destinationDir) {
        AssetManager assetManager = this.getAssets();
        String newFileName = destinationDir + "/" + filename;

        InputStream in = null;
        OutputStream out = null;
        try {
            in = assetManager.open(filename);
            out = new FileOutputStream(newFileName);

            byte[] buffer = new byte[1024];
            int read;
            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);
            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (Exception e) {
            Log.e("tag", e.getMessage());
        }
        new File(newFileName).setExecutable(true, false);
    }*/

    public void ShowPopup() {
        Button btn_view;
        Button btn_download;
        Button btn_keep;
        myDialog.setContentView(R.layout.dialog_view_download_doc);
        btn_view = (Button) myDialog.findViewById(R.id.btn_view);
        btn_download = (Button) myDialog.findViewById(R.id.btn_download);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.setCancelable(true);
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                File pdfFile = new File("res/raw/consent.pdf");
                Uri path = Uri.fromFile(pdfFile);
                String url="file:///android_asset/pdfviewer/index.html?file=" + path;
                Intent n = new Intent(TrialInfoActivity.this, WebViewActivity.class);
                n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                n.putExtra("url", "pdf");
                startActivity(n, ActivityOptions.makeSceneTransitionAnimation(TrialInfoActivity.this).toBundle());
                myDialog.dismiss();
            }
        });
        btn_download.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                copyReadAssets();
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

}
