package com.example.johnywalker.adventure_go.frontEnd;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
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

/**
 * Created by JohnyWalker94 on 03-Nov-16.
 */

public class RegisterActivity extends AppCompatActivity
{
    //Variables
    //Database connection
    private IDao mDatabaseConnection  = null;

    //Activity inputs
    private AutoCompleteTextView mUsernameView;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    //User information
    private String username;
    private String email;
    private String password;

    private boolean userExists = false;
    private boolean userRegistered = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        if(Build.VERSION.SDK_INT > 9)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        mUsernameView = (AutoCompleteTextView) findViewById(R.id.username);
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mPasswordView = (EditText) findViewById(R.id.password);

        //Add password listener
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.register || true) {
                    attemptRegister();
                    return true;
                }
                return false;
            }
        });

        Button mRegisterButton = (Button) findViewById(R.id.register_button);
        Button mLoginButton = (Button) findViewById(R.id.login_button);

        mRegisterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                attemptRegister();
            }
        });

        mLoginButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void attemptRegister()
    {
        //Get activity input values
        username = mUsernameView.getText().toString();
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();

        if(username.isEmpty() || email.isEmpty() || password.isEmpty())
        {
            Toast.makeText(this, "Empty field.", Toast.LENGTH_LONG).show();
            return;
        }

        mDatabaseConnection = initializeDatabaseConnection();

        userExists = userExists(username, password);
        userRegistered = registerUser(username, email, password);

        if (!userExists && userRegistered)
        {
            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            finish();
        } else if (userExists)
        {
            Toast.makeText(this, "User already exists", Toast.LENGTH_LONG).show();
        } else if (!userRegistered)
        {
            Toast.makeText(this, "Unable to register", Toast.LENGTH_LONG).show();
        } else
        {
            Toast.makeText(this, "Error. Try again later", Toast.LENGTH_LONG).show();
        }
    }

    public IDao initializeDatabaseConnection()
    {
        return new Controller();
    }

    public boolean userExists(String username, String pass)
    {
        return mDatabaseConnection.attemptUserVerification(username, pass);
    }

    public boolean registerUser(String name, String mail, String pass)
    {
        return mDatabaseConnection.attemptUserRegistration(name, mail, pass);
    }
}
