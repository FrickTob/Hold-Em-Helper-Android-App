package com.example.mypokerassistant.MyPokerAssistantActivities;

import android.app.AlertDialog;
import android.app.StatusBarManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.mypokerassistant.R;

public class MainActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private ImageButton mainInfoCloseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ConstraintLayout constraintLayout = findViewById(R.id.mainLayout);
        constraintLayout.setBackgroundColor(Color.WHITE);

        // Click listener for info pop up
        ImageButton infoButton = findViewById(R.id.infoButton);
        infoButton.setOnClickListener(view -> createInfoPopUp());

        //Set up click listeners for buttons to send user to different activities
        final Button handRankingButton = findViewById(R.id.handRankingButton);
        handRankingButton.setOnClickListener(this::goToHandRankingActivity);

        final Button winningOddsButton = findViewById(R.id.winningOddsButton);
        winningOddsButton.setOnClickListener(this::goToWinningOddsActivity);

        final Button handGuideButton = findViewById(R.id.handGuideButton);
        handGuideButton.setOnClickListener(this::goToHandGuideActivity);
    }

    // Set up Info Pop Up
    public void createInfoPopUp() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View helpPopUpView = getLayoutInflater().inflate(R.layout.popup_main_activity_info, null);
        mainInfoCloseButton = helpPopUpView.findViewById(R.id.mainInfoCloseButton);

        dialogBuilder.setView(helpPopUpView);
        dialog = dialogBuilder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
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
        Intent intent = new Intent(this, StartingHandGuideActivity.class);
        startActivity(intent);
    }

}