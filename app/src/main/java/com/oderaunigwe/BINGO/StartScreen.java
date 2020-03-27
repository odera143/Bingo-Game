package com.oderaunigwe.BINGO;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

public class StartScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    public void start_1p(View view)
    {
        Intent intent = new Intent(StartScreen.this, MainActivity.class);
        startActivity(intent);
    }

    public void start_1pcpu(View view)
    {
        Intent intent = new Intent(StartScreen.this, CPUPlayerActivity.class);
        startActivity(intent);
    }

    public void start_2p(View view)
    {
        Intent intent = new Intent(StartScreen.this, PlayerVsPlpayer.class);
        startActivity(intent);
    }
    public void start_stats(View view)
    {
        Intent intent = new Intent(StartScreen.this, StatsActivity.class);
        startActivity(intent);
    }

}
