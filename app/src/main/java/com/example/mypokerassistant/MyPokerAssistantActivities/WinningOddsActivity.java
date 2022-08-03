package com.example.mypokerassistant.MyPokerAssistantActivities;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mypokerassistant.PokerStats.Stats2Player;
import com.example.mypokerassistant.R;

public class WinningOddsActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winning_odds);

        // Click listener for Get Odds Button
        final Button oddsButton = findViewById(R.id.getOddsButton);
        oddsButton.setOnClickListener(this::getOddsButtonPress);

        final ImageButton helpButton = findViewById(R.id.oddsHelpButton);
        helpButton.setOnClickListener(view -> createHelpPopUp());
    }

    public void getOddsButtonPress(View view) {
        TextView outputMessage = findViewById(R.id.oddsOutput);
        EditText hand = findViewById(R.id.handInput);
        EditText tableCards = findViewById(R.id.tableInput);
        Stats2Player stats = new Stats2Player();

        String handText = hand.getText().toString();
        String tableCardsText = tableCards.getText().toString();
        String cardString = handText + tableCardsText;

        if(handText.length() != 4) {
            outputMessage.setText(getString(R.string.oddsInvalidInput));
            return;
        }

        outputMessage.setText(R.string.oddsPleaseWaitMessage);

        float odds = stats.getOdds(cardString);

        outputMessage.setText(String.format(getString(R.string.oddsOutputMessage), odds));
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
        dialog.show();

        closeButton.setOnClickListener(view -> dialog.dismiss());

    }

}