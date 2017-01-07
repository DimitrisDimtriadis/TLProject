package com.example.johnywalker.adventure_go.frontEnd;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.johnywalker.adventure_go.R;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Dimitriadis983 on 03-Jan-17.
 */
public class PopMenu extends Activity
{
    public static final String TAG = "GeofenceService";
    String Question, Answer, Category, Hint;
    static String mData[][] = new String[10][4];


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

        Testing testing = new Testing();
        retrieveDataFromJson();
        retrieveDataFromJson4(testing.getReqID());
//        initializeAswersAndQuestions();
//        retrieveDataFromJson2(testing.getReqID());


    }
//
//    protected void initializeAswersAndQuestions()
//    {
//
//
//        Button answerButton1 = (Button) findViewById(R.id.answer_1);
//        answerButton1.setText("Click Me !");
//        answerButton1.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                PopMenu.super.onBackPressed();
//            }
//        });
//
//        Button answerButton2 = (Button) findViewById(R.id.answer_2);
//        answerButton2.setText("No Me !");
//        answerButton2.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                PopMenu.super.onBackPressed();
//            }
//        });
//        Button answerButton3 = (Button) findViewById(R.id.answer_3);
//        answerButton3.setText("Fuck u anyway !");
//        answerButton3.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                PopMenu.super.onBackPressed();
//            }
//        });
//
//    }

    protected void retrieveDataFromJson()
    {
        String URL = "http://83.212.100.247:8090/quest/getQuests?latitude=15.23&longitude=14.52&score=5";


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, URL, null,
                new Response.Listener<JSONArray>()
                {
                    @Override
                    public void onResponse(JSONArray response)
                    {
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

                    }
                },
                new Response.ErrorListener()
                {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {
//                            Toast.makeText(MapsActivity.this, "WRONG", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }
        );
        MySingleton.getInstance(PopMenu.this).addToRequestqueue(jsonArrayRequest);
    }

//    public void retrieveDataFromJson2()
//    {
//        Button answerButton1 = (Button) findViewById(R.id.answer_1);
//        answerButton1.setText(Myquestions);
//        answerButton1.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                PopMenu.super.onBackPressed();
//            }
//        });
//        Button answerButton2 = (Button) findViewById(R.id.answer_2);
//        answerButton2.setText(Myanswers);
//        answerButton2.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                PopMenu.super.onBackPressed();
//            }
//        });
//        Button answerButton3 = (Button) findViewById(R.id.answer_3);
//        answerButton3.setText(Mycategorys);
//        answerButton3.setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View view)
//            {
//                PopMenu.super.onBackPressed();
//            }
//        });
//
//    }

    public void retrieveDataFromJson3(String questions, String answers, String categorys, String hints, int count)

    {
        mData[count][0] = questions;
        mData[count][1] = answers;
        mData[count][2] = categorys;
        mData[count][3] = hints;

    }

    public void retrieveDataFromJson4(String requestiD)
    {
//
//        for (int i = 0; i < 10; i++)
//        {
//            for (int j = 0; i < 4; j++)
//            {
//        String asd1,asd2;
//        asd1 = requestiD;
//        asd2 = mData[1][0];
//
//        if (asd1.equals(asd2))
//                {
                    Button answerButton1 = (Button) findViewById(R.id.answer_1);
                    answerButton1.setText(mData[1][0]);
                    answerButton1.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            PopMenu.super.onBackPressed();
                        }
                    });
                    Button answerButton2 = (Button) findViewById(R.id.answer_2);
                    answerButton2.setText(mData[1][1]);
                    answerButton2.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            PopMenu.super.onBackPressed();
                        }
                    });
                    Button answerButton3 = (Button) findViewById(R.id.answer_3);
                    answerButton3.setText(mData[1][2]);
                    answerButton3.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick(View view)
                        {
                            PopMenu.super.onBackPressed();
                        }
                    });
                }
//        else
//                {
//
//                }
//            }


        }

//    }
//}


