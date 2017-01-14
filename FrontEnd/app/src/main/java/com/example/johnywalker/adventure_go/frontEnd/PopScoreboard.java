package com.example.johnywalker.adventure_go.frontEnd;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.johnywalker.adventure_go.R;

/**
 * Created by Ddimitriadis on 14-Jan-17.
 */

public class PopScoreboard extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        Button close = (Button) findViewById(R.id.close_scoreboard_window);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopScoreboard.super.onBackPressed();
            }
        });
    }
}
