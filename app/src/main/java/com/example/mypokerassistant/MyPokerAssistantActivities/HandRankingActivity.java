package com.example.mypokerassistant.MyPokerAssistantActivities;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mypokerassistant.R;

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