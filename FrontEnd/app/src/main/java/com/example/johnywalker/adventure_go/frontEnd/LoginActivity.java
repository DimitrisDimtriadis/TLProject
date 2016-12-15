package com.example.johnywalker.adventure_go.frontEnd;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.KeyEvent;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.johnywalker.adventure_go.controller.Controller;
import com.example.johnywalker.adventure_go.mockController.IDao;
import com.example.johnywalker.adventure_go.mockController.MockDatabase;
import com.example.johnywalker.adventure_go.R;
import com.example.johnywalker.adventure_go.miscellaneous.ValidateString;

/**
 * Created by JohnyWalker94 on 01-Nov-16.
 */

public class LoginActivity extends AppCompatActivity
{
    //Variables
    //Database connection
    private IDao mController = null;

    //Activity inputs
    private AutoCompleteTextView mUsernameView;
    private EditText mPasswordView;

    //User information
    private String username;
    private String password;

    private ValidateString validate;
    private String errorMessage;

    private boolean userExists = false;


    @Override
    protected void onCreate(final Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);
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

        Button mRegisterButton = (Button) findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                finish();
            }
        });
    }

    private void attemptLogin()
    {
        //Get activity input values
        username = mUsernameView.getText().toString();
        password = mPasswordView.getText().toString();

        mController = initializeController();
        validate = new ValidateString();

        if(!validateString(username))
        {
            Toast.makeText(this, "Error! Username " + errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!validateString(password))
        {
            Toast.makeText(this, "Error! Password " + errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        userExists = userExists(username, password);

        if(userExists)
        {
            Toast.makeText(this, "Login successful", Toast.LENGTH_LONG).show();
            startActivity(new Intent(LoginActivity.this, MapsActivity.class));
            finish();
        }
        else if(!userExists)
        {
            Toast.makeText(this, "Login failed", Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(this, "Error", Toast.LENGTH_LONG).show();
        }
    }

    public IDao initializeController()
    {
        return new Controller();
    }

    public boolean userExists(String username, String pass)
    {
        return mController.attemptUserVerification(username, pass);
    }

    public boolean validateString(String string)
    {
        int result;

        result = validate.validateString(string);
        errorMessage = validate.getErrorMessage(result);

        if(result == 100)
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
