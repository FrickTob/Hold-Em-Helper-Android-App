package com.mypokerassistant.holdemhelper.MyPokerAssistantActivities;

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

import com.mypokerassistant.holdemhelper.PokerParts.HandRankList;
import com.mypokerassistant.holdemhelper.PokerParts.PokerHandSuitless;
import com.mypokerassistant.holdemhelper.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

/**
 * Activity visited by user when handGuideButton is pressed.
 * User is able to enter their starting hand in handGuideInputText and
 * the rank and winning percentage of that hand is displayed when handGuideRankButton
 * is pressed
 * @author FrickTob
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
        EditText userInput = findViewById(R.id.handGuideInputText);
        TextView outputRank = findViewById(R.id.handGuideRankText);
        TextView outputWinPercent = findViewById(R.id.handGuideWinPCTText);
        String inputString = userInput.getText().toString();

        // Check for correct num of characters
        if(inputString.length() != 4) {
            outputRank.setText(R.string.rankingInvalidHandNumCards);
            outputWinPercent.setText(R.string.rankingPressForHelp);
            return;
        }

        String userHandString = userInput.getText().toString();

        // Check for invalid input characters
        Character[] validValueChars = {'2', '3', '4', '5', '6', '7', '8', '9', '0', 'J', 'Q', 'K', 'A'};
        Character[] validSuitChars = {'C', 'D', 'H', 'S'};
        ArrayList<Character> validSuitArray = new ArrayList<>(Arrays.asList(validSuitChars));
        ArrayList<Character> validValueArray = new ArrayList<>(Arrays.asList(validValueChars));
        if(!validValueArray.contains(userHandString.charAt(0)) || !validValueArray.contains(userHandString.charAt(2)) // Check values
        || !validSuitArray.contains(userHandString.charAt(1)) || !validSuitArray.contains(userHandString.charAt(3))) { // Check suits
            outputRank.setText(R.string.rankingInvalidHandInvalidChar);
            outputWinPercent.setText(R.string.rankingPressForHelp);
            return;
        }
        for(int i = 0; i < userHandString.length(); i+= 2) { // check values
            if(!validValueArray.contains(userHandString.charAt(i))) {
                outputRank.setText(R.string.rankingInvalidHand);
                outputWinPercent.setText(R.string.rankingPressForHelp);
                return;
            }
        }

        PokerHandSuitless inputHand = new PokerHandSuitless(userHandString); // Make hand from user input
        int handRank = HandRankList.getRank(inputHand);

        // Check for Repeated Cards
        if(handRank == -1) {
            outputRank.setText(R.string.rankingInvalidHandDuplicateCard);
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