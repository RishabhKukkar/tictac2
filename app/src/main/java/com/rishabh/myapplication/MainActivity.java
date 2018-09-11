package com.rishabh.myapplication;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button[][] buttons = new Button[3][3];
    private AdView mAdView;
    String adID = "ca-app-pub-5839140869399953~5339807778";

    private boolean player1turn = true;
    private int roundcount;
    private int player1point, player2point;
    private TextView textViewplayer1, textViewplayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MobileAds.initialize(this, adID);
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        textViewplayer1 = findViewById(R.id.text_view_player1);
        textViewplayer2 = findViewById(R.id.text_view_player2);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                String button_id = "button_" + i + j;
                int resid = getResources().getIdentifier(button_id, "id", getPackageName());
                buttons[i][j] = findViewById(resid);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button reset = findViewById(R.id.button_reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetgame();
            }
        });
    }


    @Override
    public void onClick(View view) {
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (player1turn) {
            ((Button) view).setText("x");
        } else {
            ((Button) view).setText("o");
        }

        roundcount++;
        if (checkfirwin()){
            if (player1turn){
                player1wins();
            }else{
                palyer2wins();
            }

        }else if(roundcount==9){
            draw();
        }
        else
        {
            player1turn=!player1turn;
        }



    }

    private boolean checkfirwin() {
        String[][] filed = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                filed[i][j] = buttons[i][j].getText().toString();
            }
        }

        for (int i = 0; i < 3; i++) {
            if (filed[i][0].equals(filed[i][1])
                    && filed[i][0].equals(filed[i][2])
                    && !filed[i][0].equals("")) {
                return true;
            }

        }
        for (int i = 0; i < 3; i++) {
            if (filed[0][i].equals(filed[1][i])
                    && filed[0][i].equals(filed[2][i])
                    && !filed[0][i].equals("")) {
                return true;
            }

        }
        if (filed[0][0].equals(filed[1][1])
                && filed[0][0].equals(filed[2][2])
                && !filed[0][0].equals("")) {
            return true;
        }
        if (filed[0][2].equals(filed[1][1])
                && filed[0][2].equals(filed[2][0])
                && !filed[0][2].equals("")) {
            return true;
        }
        return false;

    }

    private void player1wins()
    {
        player1point++;
        Toast.makeText(this,"Player 1 Wins",Toast.LENGTH_SHORT).show();
        updatepointtext();
        resetboard();

    }
    private void palyer2wins()
    {
        player2point++;
        Toast.makeText(this,"Player 2 Wins",Toast.LENGTH_SHORT).show();
        updatepointtext();
        resetboard();
    }
    private void draw()
    {
        Toast.makeText(this,"Draw",Toast.LENGTH_SHORT).show();
        resetboard();


    }
    private void updatepointtext()
    {
        textViewplayer1.setText("Player 01 : "+player1point);
        textViewplayer2.setText("Player 02 : "+player2point);
    }
    private void resetboard()
    {
        for(int i=0;i<3;i++)
        {
            for (int j=0;j<3;j++)
            {
                buttons[i][j].setText("");
            }
        }
        roundcount=0;
        player1turn=true;
    }

    private void resetgame()
    {
        player1point=0;
        player2point=0;
        updatepointtext();
        resetboard();

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("roundcoiunt",roundcount);
        outState.putInt("player1point",player1point);
        outState.putInt("player2point",player2point);
        outState.putBoolean("player1turn",player1turn);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        roundcount=savedInstanceState.getInt("roundcoiunt");
        player1point=savedInstanceState.getInt("player1point");
        player2point=savedInstanceState.getInt("player2point");
        player1turn=savedInstanceState.getBoolean("player1turn");

    }
}
