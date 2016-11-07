package com.example.johnywalker.adventure_go;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by JohnyWalker94 on 03-Nov-16.
 */

public class RegisterActivity extends AppCompatActivity {

    private LoginActivity login = null;
    private IDao mDatabaseConnection  = null;

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
        String email = mEmailView.getText().toString();
        String emailVerify = mEmailVerificationView.getText().toString();
        String password = mPasswordView.getText().toString();

        mDatabaseConnection = new MockDatabase();

        if(mDatabaseConnection.attemptDatabaseConnection())
        {
            if(mDatabaseConnection.registerUser(email, emailVerify, password));
            {
                Intent myIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                RegisterActivity.this.startActivity(myIntent);
                finish();
            }
        }
    }
}
