package com.example.johnywalker.adventure_go.frontEnd;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

        Button mRegisterButton = (Button) findViewById(R.id.email_register_button);
        mRegisterButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(myIntent);
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

        mDatabaseConnection = initializeDatabaseConnection();

        if(attemptDatabaseConnection())
        {
            if(!userExists(email, password) && registerUser(username, email, password));
            {
                Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(myIntent);
                finish();
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

    public boolean registerUser(String name, String mail, String pass)
    {
        return mDatabaseConnection.registerUser(name, mail, pass);
    }
}
