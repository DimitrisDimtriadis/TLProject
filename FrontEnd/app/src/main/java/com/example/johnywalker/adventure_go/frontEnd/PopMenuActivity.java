package com.example.johnywalker.adventure_go.frontEnd;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.johnywalker.adventure_go.R;
import com.example.johnywalker.adventure_go.miscellaneous.GlobalVariables;
import com.example.johnywalker.adventure_go.miscellaneous.MySingleton;
import com.example.johnywalker.adventure_go.miscellaneous.Randomize;
import com.example.johnywalker.adventure_go.models.Riddle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dimitriadis983 on 03-Jan-17.
 */
public class PopMenuActivity extends Activity
{
    static String mData[][];
    GlobalVariables globalVariables = new GlobalVariables();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popmenu);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int) (width * .8), (int) (height * .6));

        retrieveDataFromJson();
        System.out.println(globalVariables.getReqID());
    }

    protected void retrieveDataFromJson()
    {
        String URL = globalVariables.getUrl();
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
                        mData = new String[response.length()][4];
                        int count = 0;
                        while (count < response.length())
                        {
                            try
                            {
                                JSONObject jsonObject = response.getJSONObject(count);
                                JSONObject riddle = jsonObject.getJSONObject("riddle");
                                Riddle riddles = new Riddle(riddle.getInt("id"),
                                        riddle.getInt("points"),
                                        riddle.getString("question"),
                                        riddle.getString("answer"),
                                        riddle.getString("hint"),
                                        riddle.getString("category"),
                                        riddle.getString("difficulty"));

                                retrieveDataFromJson3(riddles.getQuestion(), riddles.getAnswer(), riddles.getCategory(), riddles.getHint(), count);
                                count++;

                            } catch (JSONException e)
                            {
                                e.printStackTrace();
                            }
                        }
                        retrieveDataFromJson4(globalVariables.getReqID());
                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
                        error.printStackTrace();
                    }
                }
        );
        MySingleton.getInstance(PopMenuActivity.this).addToRequestqueue(jsonArrayRequest);
    }

    public void retrieveDataFromJson3(String questions, String answers, String categories, String hints, int count)
    {
        mData[count][0] = questions;
        mData[count][1] = answers;
        mData[count][2] = categories;
        mData[count][3] = hints;
    }

    public void retrieveDataFromJson4(String requestID)
    {
        String asd1, asd2;

        mDataLengthLoop:
        for (int i = 0; i < mData.length; i++)
        {
            asd1 = requestID;
            asd2 = mData[i][0];

            if (asd1.equals(asd2))
            {
//                System.out.println("Before shuffle");
//                System.out.println(mData[i][0]);
//                System.out.println(mData[i][1]);
//                System.out.println(mData[i][2]);

                Button answerButton1 = (Button) findViewById(R.id.answer_1);
                answerButton1.setText(mData[i][0]);
                answerButton1.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        PopMenuActivity.super.onBackPressed();
                    }
                });
                Button answerButton2 = (Button) findViewById(R.id.answer_2);
                answerButton2.setText(mData[i][1]);
                answerButton2.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        PopMenuActivity.super.onBackPressed();
                    }
                });
                Button answerButton3 = (Button) findViewById(R.id.answer_3);
                answerButton3.setText(mData[i][2]);
                answerButton3.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        PopMenuActivity.super.onBackPressed();
                    }
                });

                //SHUFFLE ARRAY TO GET DIFFERENT ANSWER POSITIONS
//                Randomize randomize = new Randomize(mData[i]);
//                String[] temp = randomize.shuffleArray();

//                System.out.println("After shuffle");
//                System.out.println(temp[0]);
//                System.out.println(temp[1]);
//                System.out.println(temp[2]);

                break mDataLengthLoop;
            } else
            {
                System.out.println("Compare error");
            }
        }
    }
}


