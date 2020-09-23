package com.ucsf.painless.view;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.ucsf.painless.ApplicationTest;
import com.ucsf.painless.R;
import com.ucsf.painless.model.firstVideo.FirstVideoResponse;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.utils.SessionManager;
import com.ucsf.painless.viewModel.SaveVideoViewModel;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SecondVideoRecordActivity extends AppCompatActivity {

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
    String pvdo_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_video_record);
        ButterKnife.bind(this);
        sessionManager = new SessionManager(getApplication());
        saveVideoViewModel = ViewModelProviders.of(this).get(SaveVideoViewModel.class);
        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        pvdo_id = getIntent().getStringExtra("pvdo_id");
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
                Intent takeVideoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);

                takeVideoIntent.putExtra(MediaStore.EXTRA_VIDEO_QUALITY, 1);
                takeVideoIntent.putExtra(MediaStore.EXTRA_DURATION_LIMIT,180);
                takeVideoIntent.putExtra("android.intent.extras.CAMERA_FACING", 1);

                if (takeVideoIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takeVideoIntent, REQUEST_VIDEO_CAPTURE);
                }
                break;
            case R.id.btn_next:

                myDialog = new Dialog(SecondVideoRecordActivity.this);
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
        ProgressDialog pd = new ProgressDialog(SecondVideoRecordActivity.this);
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
            saveVideoViewModel.saveSecondVideo(path.toString(), pvdo_id, SecondVideoRecordActivity.this).observe(this, new Observer<FirstVideoResponse>() {
                @Override
                public void onChanged(@Nullable FirstVideoResponse loginResponse) {
                    try {
                        if (loginResponse.isStatus()) {
                            Intent n = new Intent(SecondVideoRecordActivity.this, ThirdVideoRecordActivity.class);
                            n.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            n.putExtra("pvdo_id", pvdo_id);
                            startActivity(n);
                        } else {
                            Toast.makeText(SecondVideoRecordActivity.this, "" + loginResponse.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {

                        e.printStackTrace();
                    }
                }
            });
        }else {
            Toast.makeText(this, "Please record video", Toast.LENGTH_SHORT).show();
        }

    }


}
