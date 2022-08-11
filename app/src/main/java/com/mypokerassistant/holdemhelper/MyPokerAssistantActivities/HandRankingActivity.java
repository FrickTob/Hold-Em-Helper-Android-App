package com.mypokerassistant.holdemhelper.MyPokerAssistantActivities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mypokerassistant.holdemhelper.R;


/**
 * Activity visited by user on handRankingButton press.
 * Displays a list of all scoring hands in order
 * @author FrickTob
 */
public class HandRankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hand_ranking);
    }
}