package com.example.mypokerassistant.MyPokerAssistantActivities;

import android.app.AlertDialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mypokerassistant.PokerStats.Stats2Player;
import com.example.mypokerassistant.R;

import org.w3c.dom.Text;

import java.util.Locale;

public class WinningOddsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning_odds);

        ProgressBar progressBar = findViewById(R.id.oddsProgressBar);
        progressBar.setIndeterminate(true);


        // Click listener for Get Odds Button
        final Button oddsButton = findViewById(R.id.getOddsButton);
        oddsButton.setOnClickListener(this::getOddsButtonPress);

        final ImageButton helpButton = findViewById(R.id.oddsHelpButton);
        helpButton.setOnClickListener(view -> createHelpPopUp());
    }

    public void getOddsButtonPress(View view) {

        TextView outputMessage = findViewById(R.id.oddsOutput);
        ProgressBar progressBar = findViewById(R.id.oddsProgressBar);
        EditText hand = findViewById(R.id.handInput);
        EditText tableCards = findViewById(R.id.tableInput);

        progressBar.setVisibility(View.VISIBLE); // start progress bar
        outputMessage.setText(""); // clear invalid hand output if applicable

        String handText = hand.getText().toString();
        String tableCardsText = tableCards.getText().toString();
        String cardString = handText + tableCardsText;



        if(handText.length() != 4 || tableCardsText.length() < 6 || tableCardsText.length() > 10) {
            outputMessage.setText(getString(R.string.oddsInvalidInput));
            return;
        }

        Stats2Player stats = new Stats2Player();

       // calculate winningOdds and output to user
        double odds = stats.getOdds(cardString);
        odds *= 100; // convert to percent
        outputMessage.setText(String.format(Locale.ENGLISH, "Winning Odds: %.1f%%", odds));


        progressBar.setVisibility(View.INVISIBLE); // remove progress bar after execution
    }

    public void createHelpPopUp() {
        AlertDialog.Builder dialogBuilder;
        AlertDialog dialog;
        ImageButton closeButton;

        dialogBuilder = new AlertDialog.Builder(this);
        final View helpPopUpView = getLayoutInflater().inflate(R.layout.popup_winning_odds, null);
        closeButton = helpPopUpView.findViewById(R.id.helpPopUpCloseButton);

        dialogBuilder.setView(helpPopUpView);
        dialog = dialogBuilder.create();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();

        closeButton.setOnClickListener(view -> dialog.dismiss());

    }

}