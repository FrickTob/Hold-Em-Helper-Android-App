package com.example.mypokerassistant.MyPokerAssistantActivities;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mypokerassistant.R;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView infoPopUpTitle;
    private ImageButton mainInfoCloseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        constraintLayout.setBackgroundColor(Color.WHITE);
        ImageButton infoButton = findViewById(R.id.infoButton);
        infoButton.setOnClickListener(view -> createInfoPopUp());


        //Set up info pop up

        //Set up click listeners for buttons to send user to different activities
        final Button handRankingButton = findViewById(R.id.handRankingButton);
        handRankingButton.setOnClickListener(this::goToHandRankingActivity);

        final Button winningOddsButton = findViewById(R.id.winningOddsButton);
        winningOddsButton.setOnClickListener(this::goToWinningOddsActivity);

        final Button handGuideButton = findViewById(R.id.handGuideButton);
        handGuideButton.setOnClickListener(this::goToHandGuideActivity);
    }

    public void createInfoPopUp() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View helpPopUpView = getLayoutInflater().inflate(R.layout.main_activity_info_popup, null);
        infoPopUpTitle = helpPopUpView.findViewById(R.id.mainInfoTitle);
        mainInfoCloseButton = helpPopUpView.findViewById(R.id.mainInfoCloseButton);

        dialogBuilder.setView(helpPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        mainInfoCloseButton.setOnClickListener(view -> dialog.dismiss());
    }

    // Methods to take user to different activities from main activity
    public void goToHandRankingActivity(View view) {
        Intent intent = new Intent(this, HandRankingActivity.class);
        startActivity(intent);
    }

    public void goToWinningOddsActivity(View view) {
        Intent intent = new Intent(this, WinningOddsActivity.class);
        startActivity(intent);
    }

    public void goToHandGuideActivity(View view) {
        Intent intent = new Intent(this, HandGuideActivity.class);
        startActivity(intent);
    }

}