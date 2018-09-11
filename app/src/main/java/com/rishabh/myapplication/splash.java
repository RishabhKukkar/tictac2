package com.rishabh.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends Activity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Thread timer=new Thread(){
            public void run()
            {

                try
                {
                    sleep(700);
                }
                catch(InterruptedException e)
                {
                    e.printStackTrace();
                }
                finally
                { Intent i=new Intent("com.rishabh.myapplication.MAINACTIVITY");
                    startActivity(i);
                }
            }

        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();

        finish();
    }
}