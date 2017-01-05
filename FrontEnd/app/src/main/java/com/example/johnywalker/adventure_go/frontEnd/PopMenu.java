package com.example.johnywalker.adventure_go.frontEnd;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.example.johnywalker.adventure_go.R;

/**
 * Created by Dimitriadis983 on 03-Jan-17.
 */
public class PopMenu extends Activity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popmenu);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width= dm.widthPixels;
        int height=dm.heightPixels;

        getWindow().setLayout((int)(width*.8),(int)(height*.6));

        initializeAswersAndQuestions();


    }
    protected void initializeAswersAndQuestions()
    {

        Button answerButton1 = (Button)findViewById(R.id.answer_1);
        answerButton1.setText("Click Me !");
        answerButton1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PopMenu.super.onBackPressed();
            }
        });

        Button answerButton2 = (Button)findViewById(R.id.answer_2);
        answerButton2.setText("No Me !");
        answerButton2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PopMenu.super.onBackPressed();
            }
        });
        Button answerButton3 = (Button)findViewById(R.id.answer_3);
        answerButton3.setText("Fuck u anyway !");
        answerButton3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                PopMenu.super.onBackPressed();
            }
        });

    }
}
