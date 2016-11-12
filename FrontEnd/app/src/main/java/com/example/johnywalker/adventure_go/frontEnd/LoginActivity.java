package com.example.johnywalker.adventure_go.frontEnd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.johnywalker.adventure_go.controller.IDao;
import com.example.johnywalker.adventure_go.controller.MockDatabase;
import com.example.johnywalker.adventure_go.R;

/**
 * Created by JohnyWalker94 on 01-Nov-16.
 */

public class LoginActivity extends AppCompatActivity
{
    //Variables
    //Database connection
    private IDao mDatabaseConnection  = null;

    //Activity inputs
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    //User information
    private String email;
    private String password;

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
        //Get activity input values
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();

        mDatabaseConnection = initializeDatabaseConnection();

        if(attemptDatabaseConnection())
        {
            if(userExists(email, password))
            {
                startActivity(new Intent(LoginActivity.this, MapsActivity.class));
            }
        }
    }

    public IDao initializeDatabaseConnection()
    {
        return new MockDatabase();
    }

    public boolean attemptDatabaseConnection()
    {
        return mDatabaseConnection.attemptDatabaseConnection();
    }

    public boolean userExists(String mail, String pass)
    {
        return mDatabaseConnection.verifyUser(mail, pass);
    }
}
