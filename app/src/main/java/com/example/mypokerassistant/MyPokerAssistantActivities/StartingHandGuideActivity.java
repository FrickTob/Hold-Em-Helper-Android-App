package com.example.mypokerassistant.MyPokerAssistantActivities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mypokerassistant.PokerParts.HandRankList;
import com.example.mypokerassistant.PokerParts.PokerHandSuitless;
import com.example.mypokerassistant.R;

import java.util.Locale;

public class StartingHandGuideActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView infoPopUpTitle;
    private ImageButton startingHandGuidePopUpCloseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_hand_guide);

        final ImageButton infoButton = findViewById(R.id.handGuideInfoButton);
        infoButton.setOnClickListener(view -> createInfoPopUp());
        // Click listener for get rank button
        final Button getRankButton = findViewById(R.id.handGuideRankButton);
        getRankButton.setOnClickListener(this::getRankButtonPress);
    }

    public void createInfoPopUp() {
        dialogBuilder = new AlertDialog.Builder(this);
        final View helpPopUpView = getLayoutInflater().inflate(R.layout.popup_starting_hand_guide_info, null);
        infoPopUpTitle = helpPopUpView.findViewById(R.id.startingHandPopUpText);
        startingHandGuidePopUpCloseButton = helpPopUpView.findViewById(R.id.startingHandPopUpCloseButton);

        dialogBuilder.setView(helpPopUpView);
        dialog = dialogBuilder.create();
        dialog.show();

        startingHandGuidePopUpCloseButton.setOnClickListener(view -> dialog.dismiss());
    }

    // Calculate Rank and Win Percent on Button Press
    public void getRankButtonPress(View view) {
        EditText userInput = findViewById(R.id.handGuideInputText);
        TextView outputRank = findViewById(R.id.handGuideRankText);
        TextView outputWinPercent = findViewById(R.id.handGuideWinPCTText);
        TextView detailedOutput = findViewById(R.id.handGuideDetailedOutput);
        String inputString = userInput.getText().toString();

        if(inputString.length() != 4) {
            outputRank.setText(R.string.rankingInvalidHand);
            outputWinPercent.setText(R.string.rankingPressForHelp);

            return;
        }
        PokerHandSuitless inputHand = new PokerHandSuitless(userInput.getText().toString());
        int handRank = HandRankList.getRank(inputHand);

        if(handRank == -1) {
            outputRank.setText(R.string.rankingInvalidHand);
            outputWinPercent.setText(R.string.rankingPressForHelp);

            return;
        }

        double winPercent = HandRankList.getWinPercent(handRank);
        winPercent *= 100;

        outputRank.setText(String.format(Locale.ENGLISH,"Rank: %d", handRank));
        outputWinPercent.setText(String.format(Locale.ENGLISH, "Win Percent: %.1f%%", winPercent));
    }
}