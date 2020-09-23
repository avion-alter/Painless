package com.ucsf.painless.view;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ucsf.painless.ApplicationTest;
import com.ucsf.painless.R;
import com.ucsf.painless.model.firstVideo.FirstVideoResponse;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.viewModel.SaveVideoViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FirstVideoRecordActivity extends AppCompatActivity {

    SessionManager sessionManager;

    static final int REQUEST_VIDEO_CAPTURE = 1;
    @BindView(R.id.videoview)
    VideoView videoview;
    @BindView(R.id.btn_record)
    ImageView btnRecord;
    @BindView(R.id.linear_complete_pain_report)
    LinearLayout linearCompletePainReport;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.card_back)
    CardView cardBack;
    Bitmap bitmap;
    @BindView(R.id.img_thumb)
    ImageView imgThumb;
    @BindView(R.id.btn_play)
    ImageView btnPlay;

    Uri videoUri;
    SaveVideoViewModel saveVideoViewModel;
    String path="";

    Dialog myDialog;
    private Runnable doAfterAllPermissionsGranted;
    private static final int REQUEST_PERMISSIONS = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_record);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplication());
        saveVideoViewModel = ViewModelProviders.of(this).get(SaveVideoViewModel.class);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());

        /*AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Instruction");
        alert.setMessage("Tap red button to record begin");
        alert.setPositiveButton("OK",null);
        alert.show();*/

        AlertDialog.Builder dialog=new AlertDialog.Builder(this);
        dialog.setMessage("Tap red button to record begin");
        dialog.setTitle("Instruction");
        dialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,
                                        int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog alertDialog=dialog.create();
        alertDialog.show();
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (doAfterAllPermissionsGranted != null) {
            doAfterAllPermissionsGranted.run();
            doAfterAllPermissionsGranted = null;
        } else {
            String[] neededPermissions = {
                    Manifest.permission.CAMERA,
                    Manifest.permission.RECORD_AUDIO,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
            };
            List<String> deniedPermissions = new ArrayList<>();
            for (String permission : neededPermissions) {
                if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                    deniedPermissions.add(permission);
                }
            }
            if (deniedPermissions.isEmpty()) {
                // All permissions are granted
               // doAfterAllPermissionsGranted();
            } else {
                String[] array = new String[deniedPermissions.size()];
                array = deniedPermissions.toArray(array);
                ActivityCompat.requestPermissions(this, array, REQUEST_PERMISSIONS);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        if (requestCode == REQUEST_VIDEO_CAPTURE && resultCode == RESULT_OK) {
            videoUri = intent.getData();

            btnRecord.setVisibility(View.GONE);
            videoview.setVisibility(View.VISIBLE);
            btnPlay.setVisibility(View.GONE);
            imgThumb.setVisibility(View.GONE);
            start(videoUri);
            path = getRealPathFromURI(this, videoUri);
            /*try {
                path = getRealPathFromURI(this, videoUri);
                bitmap = retriveVideoFrameFromVideo(path.toString());
                if (bitmap != null) {
                    imgThumb.setImageBitmap(bitmap);
                }
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }*/

        }
    }



    @OnClick(R.id.btn_play)
    public void onPlayViewClicked() {
        start(videoUri);
    }

    @OnClick({R.id.btn_record, R.id.btn_next})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_record:

                if (doAfterAllPermissionsGranted != null) {
                    doAfterAllPermissionsGranted.run();
                    doAfterAllPermissionsGranted = null;
                } else {
                    String[] neededPermissions = {
                            Manifest.permission.CAMERA,
                            Manifest.permission.RECORD_AUDIO,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    };
                    List<String> deniedPermissions = new ArrayList<>();
                    for (String permission : neededPermissions) {
                        if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                            deniedPermissions.add(permission);
                        }
                    }
                    if (deniedPermissions.isEmpty()) {
                        // All permissions are granted
                        // doAfterAllPermissionsGranted();
                        Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                        takeVideoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                        takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,180);
                        takeVideoIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);
                        if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                            startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                        }
                    } else {
                        String[] array = new String[deniedPermissions.size()];
                        array = deniedPermissions.toArray(array);
                        ActivityCompat.requestPermissions(this, array, REQUEST_PERMISSIONS);
                    }
                }
                break;
            case R.id.btn_next:
                myDialog = new Dialog(FirstVideoRecordActivity.this);
                ShowPopup();


                break;
        }
    }

    @OnClick(R.id.card_back)
    public void onViewClicked() {
        finish();
    }

    public static Bitmap retriveVideoFrameFromVideo(String videoPath) throws Throwable {
        Bitmap bitmap = null;
        MediaMetadataRetriever mediaMetadataRetriever = null;
        try {
            mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(videoPath, new HashMap<String, String>());
            //   mediaMetadataRetriever.setDataSource(videoPath);
            bitmap = mediaMetadataRetriever.getFrameAtTime();
        } catch (Exception e) {
            e.printStackTrace();
            throw new Throwable("Exception in retriveVideoFrameFromVideo(String videoPath)" + e.getMessage());

        } finally {
            if (mediaMetadataRetriever != null) {
                mediaMetadataRetriever.release();
            }
        }
        return bitmap;
    }

    public String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    void start(Uri uri) {
        ProgressDialog pd = new ProgressDialog(FirstVideoRecordActivity.this);
        pd.setMessage("Buffering video please wait...");
        pd.show();

        MediaController m = new MediaController(this);
        videoview.setMediaController(m);

        videoview.setVideoURI(uri);
        videoview.start();

        videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //close the progress dialog when buffering is done
                pd.dismiss();
            }
        });
    }


    public void ShowPopup() {
        Button btn_accept;
        Button btn_clear;
        Button btn_keep;
        myDialog.setContentView(R.layout.dialog_video_accept);
        btn_accept = (Button) myDialog.findViewById(R.id.btn_accept);
        btn_clear = (Button) myDialog.findViewById(R.id.btn_clear);
        btn_keep = (Button) myDialog.findViewById(R.id.btn_keep);
        myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        myDialog.setCancelable(true);
        btn_accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationTest.getInstance().setSelectedSeverity("mild");
                saveVideo();
                myDialog.dismiss();
            }
        });
        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               btnRecord.setVisibility(View.VISIBLE);
                myDialog.dismiss();
            }
        });
        btn_keep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveVideo();
                myDialog.dismiss();
            }
        });
        myDialog.show();
    }

    void saveVideo(){
        if (!path.equals("")) {
        saveVideoViewModel.saveFirstVideo(path.toString(), FirstVideoRecordActivity.this).observe(this, new Observer<FirstVideoResponse>() {
            @Override
            public void onChanged(@Nullable FirstVideoResponse loginResponse) {
                try {
                    if (loginResponse.isStatus()){
                        Intent n = new Intent(FirstVideoRecordActivity.this, SecondVideoRecordActivity.class);
                        n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        n.putExtra("pvdo_id",String.valueOf(loginResponse.getData().getPvdoId()));
                        startActivity(n);
                    }else {
                        Toast.makeText(FirstVideoRecordActivity.this, ""+loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }catch (Exception e){

                    e.printStackTrace();
                }
            }
        });
        }else {
            Toast.makeText(this, "Please record video", Toast.LENGTH_SHORT).show();
        }

    }


}
