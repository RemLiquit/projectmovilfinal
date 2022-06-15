package com.example.resistoresapp_v2;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.app.Activity;

import java.util.Arrays;

public class GameActivity extends Activity {
    TextView tvInfo;
    Integer [] botones;
    int [] tablero = new int []{
            0,0,0,
            0,0,0,
            0,0,0,
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        tvInfo = findViewById(R.id.tvInfo);
        tvInfo.setVisibility(View.INVISIBLE);

        botones = new Integer[]{
                R.id.b1,R.id.b2,R.id.b3,
                R.id.b4,R.id.b5,R.id.b6,
                R.id.b7,R.id.b8,R.id.b9,
        };
    }
    public void ponerFicha (View v){
        int numBtn = Arrays.asList(botones).indexOf(v.getId());
        v.setBackgroundResource(R.drawable.cross_svgrepo_com);
        tablero[numBtn] =1;
    }
}
