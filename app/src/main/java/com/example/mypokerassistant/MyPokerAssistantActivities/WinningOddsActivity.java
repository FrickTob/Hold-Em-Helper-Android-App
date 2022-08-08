package com.example.mypokerassistant.MyPokerAssistantActivities;

import android.app.AlertDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mypokerassistant.PokerStats.Stats2Player;
import com.example.mypokerassistant.R;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Activity visited by user when winningOddsButton is pressed.
 * User is able to enter cards in handInput and winning odds are calculated when getOddsButton is pressed.
 * Help Pop-Up is also available to explain odds calculation and how to enter cards.
 * @author tobyf
 */
public class WinningOddsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning_odds);

        // Make Progress bar have indeterminate progress
        ProgressBar progressBar = findViewById(R.id.oddsProgressBar);
        progressBar.setIndeterminate(true);

        // Click listeners
        final Button oddsButton = findViewById(R.id.getOddsButton);
        oddsButton.setOnClickListener(this::getOddsButtonPress);

        final ImageButton helpButton = findViewById(R.id.oddsHelpButton);
        helpButton.setOnClickListener(view -> createHelpPopUp());
    }

    /**
     * Pulls data from handInput UI component and checks this data for proper formatting.
     * If properly formatted, start a new background thread to calculate the winning odds.
     * Also make progress bar visible while background thread is running. The background
     * thread reverts this change when finished calculating.
     *
     * @param view the current view
     */
    public void getOddsButtonPress(View view) {
        // Get UI elements
        TextView outputMessage = findViewById(R.id.oddsOutput);
        ProgressBar progressBar = findViewById(R.id.oddsProgressBar);
        EditText hand = findViewById(R.id.handInput);
        EditText tableCards = findViewById(R.id.tableInput);

        // Get Data From UI
        String handText = hand.getText().toString();
        String tableCardsText = tableCards.getText().toString();
        String cardString = handText + tableCardsText;

        // Check for invalid input formatting
        if(handText.length() != 4 || tableCardsText.length() < 6 || tableCardsText.length() > 10) {
            outputMessage.setText(getString(R.string.oddsInvalidInput));
            return;
        }

        // Set up and start background thread to calculate odds
        GetOddsRunnable oddsRunnable = new GetOddsRunnable(cardString);
        Thread oddsThread = new Thread(oddsRunnable);
        oddsThread.start();

        // UI to show loading is in progress
        progressBar.setVisibility(View.VISIBLE); // start progress bar
        outputMessage.setText(""); // clear invalid hand output if applicable
    }

    /**
     * Creates dialog to help user when help button is pressed
     */
    public void createHelpPopUp() {
        AlertDialog.Builder dialogBuilder;
        AlertDialog dialog;
        ImageButton closeButton;

        //Create and show dialog
        dialogBuilder = new AlertDialog.Builder(this);
        final View helpPopUpView = getLayoutInflater().inflate(R.layout.popup_winning_odds, null);
        dialogBuilder.setView(helpPopUpView);
        dialog = dialogBuilder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        // close if close button is pressed
        closeButton = helpPopUpView.findViewById(R.id.helpPopUpCloseButton);
        closeButton.setOnClickListener(view -> dialog.dismiss());
    }


    /**
     * Inner Runnable Class for Background Thread
     */
    public class GetOddsRunnable implements Runnable {
        private String cardString; // data field to store user's cards

        /**
         * Constructor
         * @param cardString string that stores cards in value-suit format where each character is a value or suit
         *                  used to calculate winning odds
         *
         */
        public GetOddsRunnable (String cardString) {
            this.cardString = cardString;
        }

        /**
         * run method calculates winning odds given local cardString string, stores it in a bundle, and sents the data to the handler
         */
        @Override
        public void run() {
            Stats2Player stats = new Stats2Player();
            final double odds = stats.getOdds(cardString) * 100;

            // Update UI components after calculating odds to display odds and remove progressBar
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    // Retrieve UI components
                    TextView oddsOutput = findViewById(R.id.oddsOutput);
                    ProgressBar progressBar = findViewById(R.id.oddsProgressBar);

                    // Update UI components
                    oddsOutput.setText(String.format(Locale.ENGLISH, "Winning Odds: %.1f%%", odds));
                    progressBar.setVisibility(View.INVISIBLE);

                }
            });
        }
    }

}