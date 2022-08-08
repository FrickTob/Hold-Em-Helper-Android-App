package com.example.mypokerassistant.MyPokerAssistantActivities;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

/**
 * Activity visited by user when handGuideButton is pressed.
 * User is able to enter their starting hand in handGuideInputText and
 * the rank and winning percentage of that hand is displayed when handGuideRankButton
 * is pressed
 * @author tobyf
 */
public class StartingHandGuideActivity extends AppCompatActivity {

    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private TextView infoPopUpTitle;
    private ImageButton startingHandGuidePopUpCloseButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_hand_guide);

        // Click listeners
        final ImageButton infoButton = findViewById(R.id.handGuideInfoButton);
        infoButton.setOnClickListener(view -> createInfoPopUp());

        final Button getRankButton = findViewById(R.id.handGuideRankButton);
        getRankButton.setOnClickListener(this::getRankButtonPress);
    }

    public void createInfoPopUp() {

        // Build and display dialog
        dialogBuilder = new AlertDialog.Builder(this);
        final View helpPopUpView = getLayoutInflater().inflate(R.layout.popup_starting_hand_guide_info, null);
        dialogBuilder.setView(helpPopUpView);
        dialog = dialogBuilder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        // Click listener for close button
        startingHandGuidePopUpCloseButton = helpPopUpView.findViewById(R.id.startingHandPopUpCloseButton);
        startingHandGuidePopUpCloseButton.setOnClickListener(view -> dialog.dismiss());
    }

    /**
     * Calculate Rank and Win Percent on Button Press and output to UI
     * @param view the current view
     */
    public void getRankButtonPress(View view) {
        // TODO: 8/8/2022 Do something with detailed output
        EditText userInput = findViewById(R.id.handGuideInputText);
        TextView outputRank = findViewById(R.id.handGuideRankText);
        TextView outputWinPercent = findViewById(R.id.handGuideWinPCTText);
        TextView detailedOutput = findViewById(R.id.handGuideDetailedOutput);
        String inputString = userInput.getText().toString();

        // Check for correct num of characters
        if(inputString.length() != 4) {
            outputRank.setText(R.string.rankingInvalidHand);
            outputWinPercent.setText(R.string.rankingPressForHelp);
            return;
        }

        PokerHandSuitless inputHand = new PokerHandSuitless(userInput.getText().toString()); // Make hand from user input
        int handRank = HandRankList.getRank(inputHand);

        // Check if hand is possible
        if(handRank == -1) {
            outputRank.setText(R.string.rankingInvalidHand);
            outputWinPercent.setText(R.string.rankingPressForHelp);
            return;
        }

        // Get winPercent and format it for output
        double winPercent = HandRankList.getWinPercent(handRank);
        winPercent *= 100;

        // Output to UI
        outputRank.setText(String.format(Locale.ENGLISH,"Rank: %d", handRank));
        outputWinPercent.setText(String.format(Locale.ENGLISH, "Win Percent: %.1f%%", winPercent));
    }
}