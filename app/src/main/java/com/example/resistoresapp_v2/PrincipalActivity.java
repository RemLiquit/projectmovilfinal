package com.example.resistoresapp_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentCallbacks;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;


import java.util.Arrays;
import java.util.Random;

public class PrincipalActivity extends AppCompatActivity {
    TextView tvInfo;
    Integer [] botones;
    int [] tablero = new int []{
            0,0,0,
            0,0,0,
            0,0,0,
    };

    int estado = 0;
    int fichaCuenta= 0;
    int turno =1;
    int [] posGanadora = new int[]{
      -1,-1,-1
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        tvInfo = findViewById(R.id.tvResult);
        tvInfo.setVisibility(View.INVISIBLE);

        botones = new Integer[]{
                R.id.btn1,R.id.btn2,R.id.btn3,
                R.id.btn4,R.id.btn5,R.id.btn6,
                R.id.btn7,R.id.btn8,R.id.btn9,
        };
    }
    public void ponerFicha (View v){
        if (estado == 0){
            turno = 1;
            int numBtn = Arrays.asList(botones).indexOf(v.getId());
            if (tablero[numBtn] == 0)
            {
                v.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("green")));
                v.setBackgroundResource(R.drawable.cross_svgrepo_com);

                tablero[numBtn] =1;
                fichaCuenta +=1;
                estado = Comprobar();
                terminarPartida();
                if (estado == 0){
                    turno = -1;
                    ia();
                    fichaCuenta +=1;
                    estado = Comprobar();
                    terminarPartida();
                }
            }
        }
    }
    public void ia (){
        Random r = new Random();
        int pos = r.nextInt(tablero.length);
        while (tablero[pos] != 0){
            pos = r.nextInt(tablero.length);
        }
        Button b = findViewById(botones[pos]);
        b.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#FF0000")));
        b.setBackgroundResource(R.drawable.circle_svgrepo_com);
        tablero[pos] = -1;
    }
    public int Comprobar(){
        int newState = 0;
        if (Math.abs(tablero[0]+tablero[1]+tablero[2]) == 3){
            posGanadora = new int[]{0,1,2};
            newState = 1*turno;
        }
        else if(Math.abs(tablero[3]+tablero[4]+tablero[5]) == 3){
            posGanadora = new int[]{3,4,5};
            newState = 1*turno;
        }
        else if(Math.abs(tablero[6]+tablero[7]+tablero[8]) == 3){
            posGanadora = new int[]{6,7,8};
            newState = 1*turno;
        }
        else if(Math.abs(tablero[0]+tablero[3]+tablero[6]) == 3){
            posGanadora = new int[]{0,3,6};
            newState = 1*turno;
        }
        else if(Math.abs(tablero[1]+tablero[4]+tablero[7]) == 3){
            posGanadora = new int[]{1,4,7};
            newState = 1*turno;
        }
        else if(Math.abs(tablero[2]+tablero[5]+tablero[8]) == 3){
            posGanadora = new int[]{2,5,8};
            newState = 1*turno;
        }
        else if(Math.abs(tablero[0]+tablero[4]+tablero[8]) == 3){
            posGanadora = new int[]{0,4,8};
            newState = 1*turno;
        }
        else if(Math.abs(tablero[2]+tablero[4]+tablero[6]) == 3){
            posGanadora = new int[]{2,4,6};
            newState = 1*turno;
        } 
        else if (fichaCuenta == 9){
            newState = 2;
        }
        return newState;
    }
    public void terminarPartida(){
        if (estado == 1 || estado == -1)
        {
            if (estado == 1){
                tvInfo.setVisibility(View.VISIBLE);
                //button background green 
                tvInfo.setTextColor(Color.GREEN);
                tvInfo.setText("Ganaste");
            }
            else{
                tvInfo.setVisibility(View.VISIBLE);
                tvInfo.setTextColor(Color.RED);
                tvInfo.setText("Perdiste");
            }
        }
        else if (estado == 2){           
            tvInfo.setVisibility(View.VISIBLE);
            tvInfo.setText("Empate");
        }
    }
}