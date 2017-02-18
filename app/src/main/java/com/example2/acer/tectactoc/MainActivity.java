package com.example2.acer.tectactoc;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayout;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 0 to yallow 1 is red
    int activePlay = 0;
    boolean gameIsActive = true;

    android.support.v7.widget.GridLayout gridLayout;
    // 2 mean onPlay
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winingposition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};


    public void dropin(View view) {
        ImageView counter = (ImageView) view;
        //System.out.println(counter.getTag().toString());
        int tagCounter = Integer.parseInt(counter.getTag().toString());
        if (gameState[tagCounter] == 2 && gameIsActive) {
            gameState[tagCounter] = activePlay;

            counter.setTranslationY(-1000f);
           //
            if (activePlay == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlay = 1;

            } else {
                counter.setImageResource(R.drawable.red);
                activePlay = 0;

            }
            //counter.setImageResource(R.drawable.yellow);
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);
            for (int[] win : winingposition) {
                if (gameState[win[0]] == gameState[win[1]] && gameState[win[1]] == gameState[win[2]] && gameState[win[0]] != 2) {
                    //  System.out.println(gameState[win[0]]);
                    gameIsActive = false;
                    String winnerName = "Red";
                    if (gameState[win[0]] == 0) {
                        winnerName = "Yallow";
                    }
                    TextView winner = (TextView) findViewById(R.id.textView);
                    winner.setText(winnerName + " Has Won!");
                    LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                    layout.setVisibility(View.VISIBLE);



                } else {
                    boolean gameIsOver = true;

                    for (int w: gameState) {
                        if (w == 2)
                            gameIsOver = false;

                    }
                        if (gameIsOver) {
                            TextView winner = (TextView) findViewById(R.id.textView);
                            winner.setText("It's a drow");
                            LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
                            layout.setVisibility(View.VISIBLE);
                        }


                }
            }

        }
    }

    public void playAgine(View view) {
        gameIsActive = true;
        LinearLayout layout = (LinearLayout) findViewById(R.id.playAgainLayout);
        layout.setVisibility(View.INVISIBLE);
        activePlay = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        android.widget.GridLayout gridLayout = (android.widget.GridLayout) findViewById(R.id.gridLayout);

        for (int i = 0; i < gridLayout.getChildCount(); i++) {

            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

