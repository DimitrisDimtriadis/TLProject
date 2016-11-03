package com.example.johnywalker.adventure_go;

import android.os.AsyncTask;

/**
 * Created by JohnyWalker on 10/29/2016.
 */

class UserLoginTask extends AsyncTask<Void, Void, Boolean>
{
    private final String mEmail;
    private final String mPassword;

    private String[] accounts = new String[]
    {
            "JohnyWalker94@hotmail.com:6947032331marios",
            "ioantzio@hotmail.com:IsabellaCullen"
    };

    UserLoginTask(String email, String password)
    {
        mEmail = email;
        mPassword = password;
    }

    @Override
    protected Boolean doInBackground(Void... params)
    {
        String tempEmail;
        String tempPassword;

        try
        {
            Thread.sleep(2000);
        } catch (InterruptedException e)
        {
            return false;
        }

        for (int i = 0; i<= accounts.length; i = i+2)
        {
            tempEmail = accounts[i];
            tempPassword = accounts[i+1];

            if(tempEmail.equals(mEmail) && tempPassword.equals(mPassword))
            {
                return true;
            }
        }

        return false;
    }
}
