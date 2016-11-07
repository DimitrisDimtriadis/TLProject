package com.example.johnywalker.adventure_go;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by JohnyWalker94 on 01-Nov-16.
 */

public class LoginActivity extends AppCompatActivity
{
    private IDao mDatabaseConnection  = null;

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        //Add password listener
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent)
            {
                if (id == R.id.login)
                {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        //Add button listener
        Button mSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mSignInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                attemptLogin();
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.email_register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                LoginActivity.this.startActivity(myIntent);
                finish();
            }
        });
    }

    private void attemptLogin()
    {
        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean userExists;

        mDatabaseConnection = new MockDatabase();

        if(mDatabaseConnection.attemptDatabaseConnection())
        {
            userExists = mDatabaseConnection.verifyUser(email, password);
            if(userExists)
            {
                //TODO
            }
        }
    }
}
