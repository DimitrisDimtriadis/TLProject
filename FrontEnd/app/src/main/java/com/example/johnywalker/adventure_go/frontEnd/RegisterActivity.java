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
import com.example.johnywalker.adventure_go.miscellaneous.ValidateString;
import com.example.johnywalker.adventure_go.mockController.IDao;
import com.example.johnywalker.adventure_go.R;
import com.example.johnywalker.adventure_go.mockController.MockDatabase;

/**
 * Created by JohnyWalker on 11/03/2016.
 */

public class RegisterActivity extends AppCompatActivity
{
    //Variables
    //Database connection
    private IDao mController = null;

    //Activity inputs
    private AutoCompleteTextView mUsernameView;
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    //User information
    private String username;
    private String email;
    private String password;

    private ValidateString validate;
    private String errorMessage;

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

        mController = initializeController();
        validate = new ValidateString();

        if(!validateString(username))
        {
            Toast.makeText(this, "Error! Username " + errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!validateString(email) && !validateEmailString(email))
        {
            Toast.makeText(this, "Error! Email " + errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!validateString(password))
        {
            Toast.makeText(this, "Error! Password " + errorMessage, Toast.LENGTH_SHORT).show();
            return;
        }

        userExists = userExists(username, password);
        userRegistered = registerUser(username, email, password);

        if (!userExists && userRegistered)
        {
            Toast.makeText(this, "Register successful", Toast.LENGTH_LONG).show();
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

    public IDao initializeController()
    {
        return new Controller();
    }

    public boolean userExists(String username, String pass)
    {
        return mController.attemptUserVerification(username, pass);
    }

    public boolean registerUser(String name, String mail, String pass)
    {
        return mController.attemptUserRegistration(name, mail, pass);
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

    public boolean validateEmailString(String string)
    {
        int result;

        result = validate.validateEmailString(string);
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
