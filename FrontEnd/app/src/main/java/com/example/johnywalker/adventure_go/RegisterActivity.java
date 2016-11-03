package com.example.johnywalker.adventure_go;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by JohnyWalker94 on 03-Nov-16.
 */

public class RegisterActivity extends AppCompatActivity {

    private LoginActivity login = null;

    private AutoCompleteTextView mEmailView;
    private AutoCompleteTextView mEmailVerificationView;
    private EditText mPasswordView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        mEmailVerificationView = (AutoCompleteTextView) findViewById(R.id.emailVerify);
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
    }

    private void attemptRegister()
    {
        String email = mEmailView.getText().toString();
        String emailVerify = mEmailVerificationView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        //Validate password
        if(!TextUtils.isEmpty(password) && !isPasswordValid(password))
        {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        //Validate email
        if(TextUtils.isEmpty(email))
        {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        }
        else if(!isEmailValid(email))
        {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if(!TextUtils.isEmpty(emailVerify))
            {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailVerificationView;
            cancel = true;
        }
        else if(!emailVerify.equals(email))
        {
            mEmailView.setError(getString(R.string.error_invalid_email_verification));
            focusView = mEmailVerificationView;
            cancel = true;
        }

        if(cancel)
        {
            focusView.requestFocus();
        }
        else
        {
            login = new LoginActivity();
        }
    }

    private boolean isPasswordValid(String p)
    {
        return true;
    }

    private boolean isEmailValid(String p)
    {
        return true;
    }
}
