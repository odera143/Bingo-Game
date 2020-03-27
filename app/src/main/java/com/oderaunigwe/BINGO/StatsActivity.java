package com.oderaunigwe.BINGO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;


public class StatsActivity extends AppCompatActivity {

    Integer CPUWins;
    Integer draws;
    public SharedPreferences sharedPreferences;
    TextView playerBingo;
    public static final String myPreference = "MyPref";
    public static final String playerWins = "playerWinsKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        playerBingo = findViewById(R.id.BINGO_num);
        sharedPreferences = getSharedPreferences(myPreference, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(playerWins))
        {
            playerBingo.setText(sharedPreferences.getString(playerWins,""));
        }

    }

}
