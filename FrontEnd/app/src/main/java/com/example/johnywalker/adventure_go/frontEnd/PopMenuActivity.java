package com.example.johnywalker.adventure_go.frontEnd;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.johnywalker.adventure_go.R;
import com.example.johnywalker.adventure_go.miscellaneous.CompareStrings;
import com.example.johnywalker.adventure_go.miscellaneous.GlobalVariables;
import com.example.johnywalker.adventure_go.miscellaneous.MySingleton;
import com.example.johnywalker.adventure_go.miscellaneous.Randomize;
import com.example.johnywalker.adventure_go.models.Riddle;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Dimitriadis983 on 03-Jan-17.
 */
public class PopMenuActivity extends Activity
{
    static Riddle mRiddles[];
    CompareStrings compareStrings = new CompareStrings();
    GlobalVariables globalVariables = new GlobalVariables();

    private GoogleApiClient client;

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

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
                        mRiddles = new Riddle[response.length()];
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

                                mRiddles[count] = riddles;
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

    public void retrieveDataFromJson4(String requestID)
    {
        String asd1, asd2;
        int i;

        mDataLengthLoop:
        for (i = 0; i < mRiddles.length; i++)
        {
            final Riddle riddle = mRiddles[i];
            asd1 = requestID;
            asd2 = riddle.getQuestion();

            if (compareStrings.compareStrings(asd1, asd2))
            {

                EditText answer = (EditText) findViewById(R.id.user_answer);

                TextView categoryLabel = (TextView) findViewById(R.id.category_text);
                categoryLabel.setText(riddle.getCategory());

                TextView questionLabel = (TextView) findViewById(R.id.question_text);
                questionLabel.setText(riddle.getQuestion());

                answer.setOnEditorActionListener(new TextView.OnEditorActionListener()
                {
                    @Override
                    public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent)
                    {
                        checkAnswer(riddle);
                        return true;
                    }
                });

                Button actionAnswer = (Button) findViewById(R.id.action_answer);
                actionAnswer.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        checkAnswer(riddle);
                    }
                });

                Button show_hint = (Button) findViewById(R.id.show_hint);
                show_hint.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        TextView hint = (TextView) findViewById(R.id.hint_text);
                        hint.setText(riddle.getHint());
                        hint.setVisibility(View.VISIBLE);
                    }
                });

                Button close = (Button) findViewById(R.id.close_quest_window);
                close.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        finish();
                    }
                });

                break mDataLengthLoop;
            } else
            {
                System.out.println("Compare error");
            }
        }
    }

    public void checkAnswer(Riddle riddle)
    {
        EditText answer = (EditText) findViewById(R.id.user_answer);
        String user_answer = answer.getText().toString();

        if (compareStrings.strictCompareStrings(user_answer, riddle.getAnswer()))
        {
            Toast.makeText(this, "Correct answer!!", Toast.LENGTH_SHORT);
            globalVariables.getUser().setScore(globalVariables.getUser().getScore() + riddle.getPoints());
            globalVariables.setLastQuestionAnswered(riddle.getQuestion());
            finish();
        }
        else
        {
            Toast.makeText(this, "Wrong answer. Try again.", Toast.LENGTH_LONG);
        }
    }

    public Action getIndexApiAction()
    {
        Thing object = new Thing.Builder()
                .setName("PopMenu Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart()
    {
        super.onStart();

        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop()
    {
        super.onStop();

        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}


