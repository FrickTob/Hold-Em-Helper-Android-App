package com.example.mypokerassistant.MyPokerAssistantActivities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mypokerassistant.PokerParts.HandRankList;
import com.example.mypokerassistant.PokerParts.PokerHandSuitless;
import com.example.mypokerassistant.R;

import java.util.Locale;

public class HandGuideActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_guide);

        final Button getRankButton = findViewById(R.id.handGuideRankButton);
        getRankButton.setOnClickListener(this::getRankButtonPress);
    }


    public void getRankButtonPress(View view) {
        EditText userInput = findViewById(R.id.handGuideInputText);
        TextView outputMessage = findViewById(R.id.handGuideOutputText);
        String inputString = userInput.getText().toString();

        if(inputString.length() != 4) {
            outputMessage.setText(R.string.rankingInvalidInput);
            return;
        }
        PokerHandSuitless inputHand = new PokerHandSuitless(userInput.getText().toString());
        int handRank = HandRankList.getRank(inputHand);

        if(handRank == -1) {
            outputMessage.setText(R.string.rankingInvalidInput);
            return;
        }

        double winPercent = HandRankList.getWinPercent(handRank);

        outputMessage.setText(String.format(Locale.ENGLISH,"%d %.3f", handRank, winPercent));
    }
}