package com.ucsf.painless.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ucsf.painless.ApplicationTest;
import com.ucsf.painless.R;
import com.ucsf.painless.adapter.ClipBoardListAdapter;
import com.ucsf.painless.model.clipBoard.ClipBoardResponse;
import com.ucsf.painless.model.clipBoard.PatientPreviousMapDatum;
import com.ucsf.painless.utils.Constant;
import com.ucsf.painless.viewModel.ClipBoardViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ClipBoardActivity extends AppCompatActivity {

    ClipBoardViewModel clipBoardViewModel;
    @BindView(R.id.txt_screen)
    TextView txtScreen;
    @BindView(R.id.txt_heading)
    TextView txtHeading;
    @BindView(R.id.recycler_view_one)
    RecyclerView recyclerViewOne;
    @BindView(R.id.btn_next)
    Button btnNext;
    @BindView(R.id.card_back)
    CardView cardBack;

    List<PatientPreviousMapDatum> clipBoardData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clip_board);
        ButterKnife.bind(this);

        Constant.overrideFonts(this, getWindow().getDecorView().getRootView());
        clipBoardViewModel = ViewModelProviders.of(this).get(ClipBoardViewModel.class);

        clipBoardViewModel.getClipBoard(ClipBoardActivity.this).observe(this, new Observer<ClipBoardResponse>() {
            @Override
            public void onChanged(@Nullable ClipBoardResponse boardResponse) {
                try {
                    if (boardResponse.getStatus()) {
                        txtHeading.setText("Pain Map "+boardResponse.getData().getPatientPreviousMapData().get(0).getBpmCreateDate());
                        if (boardResponse.getData().getPatientPreviousMapData().size() != 0) {
                            clipBoardData = boardResponse.getData().getPatientPreviousMapData();
                            LinearLayoutManager recyclerLayoutManager = new LinearLayoutManager(ClipBoardActivity.this);
                            recyclerViewOne.setLayoutManager(recyclerLayoutManager);

                            DividerItemDecoration dividerItemDecoration =
                                    new DividerItemDecoration(recyclerViewOne.getContext(),
                                            recyclerLayoutManager.getOrientation());
                            recyclerViewOne.addItemDecoration(dividerItemDecoration);

                            ClipBoardListAdapter recyclerViewAdapter = new
                                    ClipBoardListAdapter(boardResponse.getData(), ClipBoardActivity.this);
                            recyclerViewOne.setAdapter(recyclerViewAdapter);
                        }

                    } else {
                        Toast.makeText(ClipBoardActivity.this, "" + boardResponse.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {

                    e.printStackTrace();
                }
            }
        });

    }

    @OnClick(R.id.btn_next)
    public void onViewClicked() {

        ApplicationTest.getInstance().selectedSevere=new ArrayList<>();
        ApplicationTest.getInstance().selectedPartName=new ArrayList<>();
        ApplicationTest.getInstance().selectedRightLeft=new ArrayList<>();
        ApplicationTest.getInstance().selectedFrontBack=new ArrayList<>();
        ApplicationTest.getInstance().selectedPartName=new ArrayList<>();
        ApplicationTest.getInstance().selectedColor=new ArrayList<>();

        ApplicationTest.getInstance().clipBoardData=this.clipBoardData;
        finish();
    }

    @OnClick(R.id.card_back)
    public void onBackViewClicked() {
        finish();
    }
}
