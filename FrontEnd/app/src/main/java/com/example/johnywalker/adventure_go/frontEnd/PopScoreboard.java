package com.example.johnywalker.adventure_go.frontEnd;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.johnywalker.adventure_go.R;
import com.example.johnywalker.adventure_go.miscellaneous.GlobalVariables;
import com.example.johnywalker.adventure_go.models.User;

import java.util.List;

/**
 * Created by Ddimitriadis on 14-Jan-17.
 */

public class PopScoreboard extends Activity
{
    private GlobalVariables globalVariables;
    private TextView userInfo;
    private TextView userScore;
    private TextView topInfo;
    private TextView topScore;

    public PopScoreboard()
    {
        globalVariables = new GlobalVariables();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        userInfo = (TextView) findViewById(R.id.username_text);
        userScore = (TextView) findViewById(R.id.score_text);
        topInfo = (TextView) findViewById(R.id.top_username_text);
        topScore = (TextView) findViewById(R.id.top_score_text);

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        Button close = (Button) findViewById(R.id.close_scoreboard_window);
        close.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PopScoreboard.super.onBackPressed();
            }
        });

        refreshScoreboard();
    }

    private void refreshScoreboard()
    {
        User topPlayer;

        userInfo.setText("Player: " + globalVariables.getUser().getUsername());
        userScore.setText("Score: " + globalVariables.getUser().getScore().toString());

        topInfo.setText("Player: Petalidis");
        topScore.setText("Score: 9001");
    }
}
